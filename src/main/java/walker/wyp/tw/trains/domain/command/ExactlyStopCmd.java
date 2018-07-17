package walker.wyp.tw.trains.domain.command;

import walker.wyp.tw.trains.domain.dao.TownGraph;

public class ExactlyStopCmd extends CmdDecorator {
	private int totalSteps;

	public ExactlyStopCmd(String from, String to, int totalSteps) {
		super(from, to);
		this.totalSteps = totalSteps;
	}
		
	@Override
	public boolean recursiveReturnRule(TownGraph graph, String step) {
		return isBeyondStops(step) || satisfyRule(graph, step);
	}

	@Override
	public boolean satisfyRule(TownGraph graph, String step) {
		return isExactlyStops(step) && step.endsWith(getEnd());
	}
	
	private boolean isExactlyStops(String step) {
		return getRouteSteps(step) == totalSteps + 1;
	}
	
	private boolean isBeyondStops(String step){
		return getRouteSteps(step) > totalSteps + 1;		
	}
}
