package it.polito.tdp.rivers.model;

public class Event implements Comparable<Event>{
	
	enum EventType{
		FLOWIN, //flusso in ingresso
		FLOWOUT, //flusso in uscita
		TRACIMAZIONE //flusso fatto scorrere fuori perchè supererebbe la capacità del bacino
	}
	private Flow flow;
	private EventType type;
	
	public Event(Flow flow, EventType type) {
		
		this.flow = flow;
		this.type = type;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public Flow getFlow() {
		return flow;
	}

	public void setFlow(Flow flow) {
		this.flow = flow;
	}

	@Override
	public int compareTo(Event other) {
		if(flow.getDay().compareTo(other.getFlow().getDay())==0) { 
			if(this.type.equals(EventType.FLOWIN)) 
				return -1;
			else {
				if(this.type.equals(EventType.TRACIMAZIONE)) {
					if(other.getType().equals(EventType.FLOWIN))
						return +1;
					else return -1;
				} else return +1;
			}
		} else {
			return flow.getDay().compareTo(other.getFlow().getDay());
		}
	}

	@Override
	public String toString() {
		return "Event [flow=" + flow + ", type=" + type + "]";
	}
	
	
	
	

}
