package walker.wyp.tw.trains.domain.dao;

public class TownEdge {
	private String origin ;
	private String destination;
	private int distance;
	
	public TownEdge(String origin, String destination, int distance) {
		super();
		this.origin = origin;
		this.destination = destination;
		this.distance = distance;
	}

	public String getDestination() {
		return destination;
	}
	
	public int getDistance() {
		return distance;
	}
	
	@Override
	public String toString() {
		return origin + destination + distance;
	}
}
