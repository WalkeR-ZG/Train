package walker.wyp.tw.trains.domain.command;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import walker.wyp.tw.trains.domain.dao.Route;
import walker.wyp.tw.trains.domain.dao.TownGraph;
import walker.wyp.tw.trains.domain.exception.TrainException;
import walker.wyp.tw.trains.service.parser.GarphDescriptor;

public class DesignatedRouteCommand extends AbstractCommand {
	private String route;

	public DesignatedRouteCommand(String route) {
		this.route = route.replaceAll("\\s*", "");
	}
	
	@Override
	public List<Route> formRoute(TownGraph graph) throws TrainException {
		List<String> routeList = new ArrayList<String>();
		if(Stream.of(route.split("-")).anyMatch(name -> name.length() != GarphDescriptor.getTownNameLength())) {
			throw new TrainException(TrainException.NO_SUCH_ROUTE);
		}
		routeList.add(Stream.of(route.split("-")).reduce((path, item) ->  path + item).get());
		return formMatchedRoute(graph, routeList);
	}
}
