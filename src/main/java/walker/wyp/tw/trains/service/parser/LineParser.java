package walker.wyp.tw.trains.service.parser;

import walker.wyp.tw.trains.domain.dao.TownGraph;
import walker.wyp.tw.trains.domain.exception.TrainException;

public class LineParser {
	public static void formGraphByLine (TownGraph graph, String line) throws TrainException {
		for (String edge : line.split(GarphDescriptor.getSpliter())) {
			if (edge != null) {
				edge = edge.trim();
				if (edge.length() < GarphDescriptor.getTownEdgeLength()) {
					throw new TrainException(TrainException.ILLEGAL_GRAPH_LENGTH);
				}
				try {
					graph.addTown(getFirstTown(edge), getSecondTown(edge), Integer.valueOf(getTownDistance(edge)));
				} catch (NumberFormatException e) {
					throw new TrainException(TrainException.ILLEGAL_TOWN_DISTANCE);
				}
			}
		}
	}
	
	private static String getFirstTown(String edge) {
		return edge.substring(0, GarphDescriptor.getTownNameLength());
	}
	
	private static String getSecondTown(String edge) {
		return edge.substring(GarphDescriptor.getTownNameLength(), GarphDescriptor.getTownNameLength() * 2);
	}
	
	private static String getTownDistance(String edge){
		return edge.substring(GarphDescriptor.getTownNameLength()*2);
	}
}
