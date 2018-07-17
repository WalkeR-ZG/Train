package walker.wyp.tw.trains.domain.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import walker.wyp.tw.trains.domain.exception.TrainException;
import walker.wyp.tw.trains.service.parser.GarphDescriptor;

public class TownGraph {
	private Map<String, TownNode> townGraph;
	
	public TownGraph() {
		this.townGraph = new HashMap<String, TownNode>(); 
	}
	
	private class TownNode {
		private List<TownEdge> neighbours;
		
		public TownNode(String townName) {
			this.neighbours = new ArrayList<TownEdge>();
		}
		
		public List<TownEdge> getNeighbours() {
			return neighbours;
		}
		
		public void addNeighbours(TownEdge townEdge) {
			this.neighbours.add(townEdge);
		}
	}
	
	public boolean isEmpty() {
		return townGraph.isEmpty();		
	}
	
	public void clear() {
		townGraph.clear();
	}
	
	public List<TownEdge> getTownNeighbours(String townName){
		return townGraph.get(townName) == null ? Collections.emptyList() : townGraph.get(townName).getNeighbours();
	}
	
	public void addTown(String origin, String dest, int distance) {
		Function<String, TownNode> f = (name) -> {
			if(townGraph.get(name) == null)
			{
				townGraph.put(name, new TownNode(name));
			}
			return townGraph.get(name); 	
		};
		TownEdge townEdge = new TownEdge(origin, dest, distance);
		f.apply(origin).addNeighbours(townEdge);;
		f.apply(dest);	
	}

	public int getDistance(String from, String to) {
		return getTownNeighbours(from).stream().filter(edge -> edge.getDestination().equals(to)).findFirst().map(edge -> edge.getDistance()).orElse(0);
	}

	
	public List<TownEdge> getEdgeRoute(String route) throws TrainException{
		List<TownEdge> edgeRoute = new ArrayList<TownEdge>();
		String from = getTownNameByIndex(route, 0);
		for (int i = 1; i < getTownNum(route) ; ++i) {
			String to = getTownNameByIndex(route, i);
			addEdge(edgeRoute, from, to);
			from = to;
		}
		return edgeRoute;
	}
		
	protected String getTownNameByIndex(String route, int index) {
		return route.substring(index * GarphDescriptor.getTownNameLength(), (index + 1) * GarphDescriptor.getTownNameLength());
	}
	
	protected int getTownNum(String route) {
		return route.length() / GarphDescriptor.getTownNameLength();
	}
	
	private void addEdge(List<TownEdge> route, String from, String to) throws TrainException{
		getTownNeighbours(from).stream().filter(edge -> edge.getDestination().equals(to))
		  .findFirst().map(edge -> route.add(edge)).orElseThrow(()->new TrainException(TrainException.NO_SUCH_ROUTE));
	}
}
