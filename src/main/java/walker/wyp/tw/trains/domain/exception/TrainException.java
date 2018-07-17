package walker.wyp.tw.trains.domain.exception;

@SuppressWarnings("serial")
public class TrainException extends Exception {
	public final static String ILLEGAL_GRAPH_LENGTH = "illegal graph length";
	public final static String ILLEGAL_TOWN_DISTANCE = "illegal town distance";
	public static final String NO_SUCH_ROUTE = "NO SUCH ROUTE";
	public static final String ILLEGAL_CMD = "illegal cmd";
	
	public TrainException(String exception) {
		super(exception);
	}
}
