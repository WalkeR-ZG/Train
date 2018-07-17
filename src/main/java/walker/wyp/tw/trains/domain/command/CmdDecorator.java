package walker.wyp.tw.trains.domain.command;

import java.util.ArrayList;
import java.util.List;

import walker.wyp.tw.trains.domain.dao.Route;
import walker.wyp.tw.trains.domain.dao.TownGraph;
import walker.wyp.tw.trains.domain.exception.TrainException;
import walker.wyp.tw.trains.service.parser.GarphDescriptor;

public abstract class CmdDecorator extends AbstractCommand {
	private String start;
	private String end;

	public CmdDecorator(String start, String end) {
		super();
		this.start = start;
		this.end = end;
	}
	
	public abstract boolean recursiveReturnRule(TownGraph graph, String step);
	public abstract boolean satisfyRule(TownGraph graph, String step);
	
	@Override
	public List<Route> formRoute(TownGraph graph) throws TrainException {
		List<String> routeList = new ArrayList<String>();
		formRoute(graph, start, routeList);
		return formMatchedRoute(graph, routeList);
	}
	
	protected final void formRoute(TownGraph graph, String step, List<String> route) {
		if(isNotSingleTown(step) && satisfyRule(graph, step)) {
			route.add(step);
		}
		
		if(isNotSingleTown(step) && recursiveReturnRule(graph, step)) {
			return;
		}

		String next = step.substring(step.length() - GarphDescriptor.getTownNameLength());
		graph.getTownNeighbours(next).forEach(edge -> formRoute(graph, step + edge.getDestination(), route));
	}
	
	protected int getRouteSteps(String step) {
		return step.length() / GarphDescriptor.getTownNameLength();
	}

	protected String getEnd() {
		return end;
	}
	
	private boolean isNotSingleTown(String step){
		return step.length() > GarphDescriptor.getTownNameLength(); 
	}
}
