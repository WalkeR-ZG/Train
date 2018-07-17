package walker.wyp.tw.trains.domain.command;

import java.util.ArrayList;
import java.util.List;

import walker.wyp.tw.trains.domain.dao.Route;
import walker.wyp.tw.trains.domain.dao.TownEdge;
import walker.wyp.tw.trains.domain.dao.TownGraph;
import walker.wyp.tw.trains.domain.exception.TrainException;

public abstract class AbstractCommand {
	public abstract List<Route> formRoute(TownGraph graph) throws TrainException;
	
	protected final List<Route> formMatchedRoute(TownGraph graph, List<String> routesList) throws TrainException{
		List<Route> routes = new ArrayList<>();
		for (String edgeList : routesList) {
			List<TownEdge> route = graph.getEdgeRoute(edgeList);	
			if(route.size() > 0) {
				routes.add(new Route(route));
			}
		}
		return routes;
	}
}
