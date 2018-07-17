package walker.wyp.tw.trains.domain.filter;

public class FilterFactory {
	public static ResultFilter createDisplay(FilterCollection displayType) {
		switch (displayType) {
		case DISTANCE_FILTER:
			return new DistanceFilter();
		case PATH_NUM_FILTER:
			return new PathNumberFilter();
		}
		return null;
	}
}
