package it.polito.tdp.rivers.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Model m = new Model();
		List<River> rivers = m.getAllRivers();
		System.out.println("Ci sono "+rivers.size()+" fiumi");
		/*for(River r : m.getAllRivers()) {
			System.out.println("Il fiume "+r.getName()+" ha "+r.getFlows().size()+" flows");
		}*/
		
		//test simulazione
		Simulator s = new Simulator();
		m.getFlowAvg(rivers.get(0));
		s.init(rivers.get(0), 1);
		System.out.println("Input:\ncapacità Q= "+s.getQ()+"\nlivello iniziale C= "+s.getC()+"\nflowoutmin= "+s.getFlowoutMin()+"\nflowAvg= "+s.getFlowAvg());
		s.run();
		System.out.println("Non è stato possibile fornire l'erogazione minima per "+s.getNumGiorniNoErogazione()+" giorni");
		System.out.println("La quantità media di acqua nel bacino è stata di "+s.getcAvg()+" m^3");
		
	}

}
