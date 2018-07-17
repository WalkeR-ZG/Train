package walker.wyp.tw.trains.domain.filter;

import java.util.List;
import walker.wyp.tw.trains.domain.dao.Route;
import walker.wyp.tw.trains.domain.exception.TrainException;

public class DistanceFilter implements ResultFilter {

	@Override
	public Object outputResult(List<Route> routes) {
		if((routes != null) && (!routes.isEmpty()) && (null != routes.get(0))) {
			return routes.get(0).getRoute().stream().map(edge -> edge.getDistance()).reduce((sum, dis) -> sum + dis).get();
		}
		return TrainException.NO_SUCH_ROUTE;
	}
}
