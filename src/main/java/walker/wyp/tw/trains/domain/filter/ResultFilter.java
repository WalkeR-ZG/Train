package walker.wyp.tw.trains.domain.filter;

import java.util.List;

import walker.wyp.tw.trains.domain.dao.Route;

public interface ResultFilter {
	public Object outputResult(List<Route> routes); 
}
