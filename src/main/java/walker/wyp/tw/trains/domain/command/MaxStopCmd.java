package walker.wyp.tw.trains.domain.command;

import walker.wyp.tw.trains.domain.dao.TownGraph;

public class MaxStopCmd extends CmdDecorator {
	private int totalSteps;

	public MaxStopCmd(String from, String to, int totalSteps) {
		super(from, to);
		this.totalSteps = totalSteps;
	}

	@Override
	public boolean recursiveReturnRule(TownGraph graph, String step) {
		return getRouteSteps(step) > totalSteps + 1;
	}

	@Override
	public boolean satisfyRule(TownGraph graph, String step) {
		return getRouteSteps(step) <= totalSteps + 1 && step.endsWith(getEnd());
	}
}
