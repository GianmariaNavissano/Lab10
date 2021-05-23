package it.polito.tdp.rivers.model;

import java.time.LocalDate;
import java.util.List;
import java.util.PriorityQueue;

import it.polito.tdp.rivers.model.Event.EventType;

public class Simulator {
	
	//coda degli eventi
	private PriorityQueue<Flow> queue;
	
	//modello del mondo
	private List<Flow> flows;
	private double q;
	private double c;
	private double flowoutMin;
	
	
	//parametri di input
	private double k;
	private double flowAvg;
	
	//parametri di output
	private int numGiorniNoErogazione;
	private double cAvg;
	
	public void init(River river, double k) {
		//inizializzo la coda ad eventi
		this.queue = new PriorityQueue<>();
		
		//inizializzo il modello del mondo
		this.flows = river.getFlows();
		this.flowAvg = river.getFlowAvg();
		this.k = k;
		this.q = k*30*flowAvg*3600*24;
		this.flowoutMin = 0.8*flowAvg;
		this.c = (q/2);
		
		//inizializzo le variabili di output
		this.numGiorniNoErogazione = 0;
		this.cAvg = 0;
		
		for(Flow f : flows) {
			this.queue.add(f);
		}
		
	}
	
	public void run() {
		while(!this.queue.isEmpty()) {
			Flow f = this.queue.poll();
			System.out.println(f);
			this.processFlow(f);
		}
	}
	
	public void processFlow(Flow f) {
		double flow = f.getFlow();
		
		//calcolo se è il giorno con il flowOut maggiore
		int p = (int)(Math.random()*100);
		double flowOut = this.flowoutMin;
		
		if(p<5)
			flowOut = 10*this.flowoutMin;
			
		
		if(flow>=flowOut) {
			//il livello aumenta
			c += flow-flowOut;
			if(c>q) {
				//tracimazione
				c = q;
			}
		} else {
			//il livello diminuisce
			c -= flowOut;
			if(c<0) {
				//non posso erogare il minimo richiesto
				c = 0;
				this.numGiorniNoErogazione ++;
			}
		}
		
		//aggiorno la media
		this.cAvg += c;
		System.out.println("Sono presenti "+c+"m^3 di acqua nel bacino");
	}

	public int getNumGiorniNoErogazione() {
		return numGiorniNoErogazione;
	}

	public double getcAvg() {
		return cAvg/this.flows.size();
	}

	public double getQ() {
		return q;
	}

	public double getC() {
		return c;
	}

	public double getFlowoutMin() {
		return flowoutMin;
	}

	public double getFlowAvg() {
		return flowAvg;
	}
	
	
	
	
}
