package walker.wyp.tw.trains.service.parser;

public class GarphDescriptor {
	private final static int TOWN_EDGE_LENGTH = 3;
	private final static int TOWN_NAME_LENGTH = 1;
	private final static String spliter = ",";
	
	public boolean isValidTownName(String townName) {
		return townName.length() == TOWN_NAME_LENGTH;
	}
	
	public static int getTownEdgeLength() {
		return TOWN_EDGE_LENGTH;
	}
	
	public static int getTownNameLength() {
		return TOWN_NAME_LENGTH;
	}
	
	public static String getSpliter() {
		return spliter;
	}
}
