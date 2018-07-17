package walker.wyp.tw.trains.domain.command;

import java.util.ArrayList;
import java.util.List;

import walker.wyp.tw.trains.domain.dao.Route;
import walker.wyp.tw.trains.domain.dao.TownGraph;
import walker.wyp.tw.trains.domain.exception.TrainException;
import walker.wyp.tw.trains.service.parser.GarphDescriptor;

public class ShortestRouteCmd extends CmdDecorator {
	public ShortestRouteCmd(String start, String end) {
		super(start, end);
	}
    
	@Override
	public List<Route> formRoute(TownGraph graph) throws TrainException {
		List<Route> route = new ArrayList<>();
		route.add(super.formRoute(graph).stream().min((r1, r2) -> compareTo(r1, r2)).get());
		return route;
	}

	@Override
	public boolean recursiveReturnRule(TownGraph graph, String step) {
		return  satisfyRule(graph, step) || isStepHasLoop(step);
	}

	@Override
	public boolean satisfyRule(TownGraph graph, String step) {
		return step.endsWith(getEnd()); 
	}
	
    private int compareTo(Route r1, Route r2) {
    	Integer allDistance1 = r1.getRoute().stream().map(edge-> edge.getDistance()).reduce((sum, distance)-> sum + distance).orElse(Integer.MAX_VALUE);
    	Integer allDistance2 = r2.getRoute().stream().map(edge-> edge.getDistance()).reduce((sum, distance)-> sum + distance).orElse(Integer.MAX_VALUE);
      	return allDistance1.compareTo(allDistance2);
    }
    
    private boolean isStepHasLoop(String step) {
    	int lastEdgePos = step.length()- 2 *  GarphDescriptor.getTownNameLength();
    	return step.indexOf(step.substring(lastEdgePos)) != lastEdgePos;
    }
}
