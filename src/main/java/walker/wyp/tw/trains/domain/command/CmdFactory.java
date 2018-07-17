package walker.wyp.tw.trains.domain.command;

import walker.wyp.tw.trains.domain.exception.TrainException;

public class CmdFactory {
	public static AbstractCommand createCmd(CmdCollection cmdType, String... strings) throws TrainException {
		try {
			switch (cmdType) {
			case DESIGNATED_ROUTED_CMD:
				return new DesignatedRouteCommand(strings[0]);
			case MAX_STEP_CMD:
				return new MaxStopCmd(strings[0], strings[1], Integer.parseInt(strings[2]));
			case EXACTLY_STEP_CMD:
				return new ExactlyStopCmd(strings[0], strings[1], Integer.parseInt(strings[2]));
			case SHORTEST_ROUTE_CMD:
				return new ShortestRouteCmd(strings[0], strings[1]);
			case MAX_DISTANCE_CMD:
				return new MaxDistanceCommand(strings[0], strings[1], Integer.parseInt(strings[2]));
			}
		} catch (Exception e) {
			throw new TrainException(TrainException.ILLEGAL_CMD);
		}
		throw new TrainException(TrainException.ILLEGAL_CMD);
	}
}
