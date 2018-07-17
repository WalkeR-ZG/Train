package walker.wyp.tw.trains.domain.filter;

import java.util.List;

import walker.wyp.tw.trains.domain.dao.Route;
import walker.wyp.tw.trains.domain.exception.TrainException;

public class PathNumberFilter implements ResultFilter {
	@Override
	public Object outputResult(List<Route> routeList) {
		return routeList.size() > 0 ? routeList.size() : TrainException.NO_SUCH_ROUTE;
	}
}
