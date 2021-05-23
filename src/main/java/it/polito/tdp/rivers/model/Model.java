package it.polito.tdp.rivers.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	
	private RiversDAO dao;
	private Map<Integer, River> idMap;
	private Simulator s;
	
	public Model() {
		this.dao = new RiversDAO();
		this.s = new Simulator();
	}
	
	public List<River> getAllRivers(){
		
		this.idMap = new HashMap<>();
		List<River> result = this.dao.getAllRivers();
		
		for(River r : result) {
			this.idMap.put(r.getId(), r);
		}
		this.getAllFlows();
		return result;
	}
	
	public void getAllFlows() {
		this.dao.getAllFlows(idMap);
	}
	
	public String getStartDate(River river) {
		LocalDate startDate = LocalDate.MAX;
		for(Flow f : river.getFlows()) {
			if (f.getDay().isBefore(startDate)) {
				startDate = f.getDay();
			}
		}
		return startDate.toString();
	}
	
	public String getEndDate(River river) {
		LocalDate endDate = LocalDate.MIN;
		for(Flow f : river.getFlows()) {
			if (f.getDay().isAfter(endDate)) {
				endDate = f.getDay();
			}
		}
		return endDate.toString();
	}
	
	public String getFlowAvg(River river) {
		river.setFlowAvg();
		return ""+river.getFlowAvg();
	}
	
	public String run(River river, double k) {
		this.getFlowAvg(river);
		s.init(river, k);
		String result = "Input simulazione:\ncapacità Q= "+s.getQ()+"\nlivello iniziale C= "+s.getC()+"\nflowoutmin= "+s.getFlowoutMin()+"\nflowAvg= "+s.getFlowAvg()+"\n";
		s.run();
		result += "Non è stato possibile fornire l'erogazione minima per "+s.getNumGiorniNoErogazione()+" giorni\n";
		result += "La quantità media di acqua nel bacino è stata di "+s.getcAvg()+" m^3\n";
		return result;
	}

}
