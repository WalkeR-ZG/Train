package walker.wyp.tw.trains.domain.dao;

import java.util.ArrayList;
import java.util.List;

public class Route {
	private List<TownEdge> route;

	public Route() {
		this.route = new ArrayList<TownEdge>();
	}
	
	public Route(List<TownEdge> route) {
		this.route = route;
	}

	public List<TownEdge> getRoute() {
		return route;
	}
	
	@Override
	public String toString() {
		return String.join(",", route.toString());
	}
}
