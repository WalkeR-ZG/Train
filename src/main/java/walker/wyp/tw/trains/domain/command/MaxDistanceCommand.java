package walker.wyp.tw.trains.domain.command;

import walker.wyp.tw.trains.domain.dao.TownGraph;
import walker.wyp.tw.trains.domain.exception.TrainException;

public class MaxDistanceCommand extends CmdDecorator {
	private int maxDistance;

	public MaxDistanceCommand(String from, String to, int totalSteps) {
		super(from, to);
		this.maxDistance = totalSteps;
	}

	@Override
	public boolean recursiveReturnRule(TownGraph graph, String step) {
		return getDistance(graph, step) >= maxDistance;
	}

	@Override
	public boolean satisfyRule(TownGraph graph, String step) {
		return getDistance(graph, step) < maxDistance && step.endsWith(getEnd());
	}
	
	private int getDistance(TownGraph graph, String step) {
		try {
			return graph.getEdgeRoute(step).stream().map(edge -> edge.getDistance()).reduce((sum, edge) -> sum + edge).orElse(0);
		} catch (TrainException e) {
			return 0;
		}
	}
}
