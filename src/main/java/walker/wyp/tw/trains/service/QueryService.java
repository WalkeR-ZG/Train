package walker.wyp.tw.trains.service;

import walker.wyp.tw.trains.domain.command.AbstractCommand;
import walker.wyp.tw.trains.domain.command.CmdCollection;
import walker.wyp.tw.trains.domain.command.CmdFactory;
import walker.wyp.tw.trains.domain.dao.TownGraph;
import walker.wyp.tw.trains.domain.exception.TrainException;
import walker.wyp.tw.trains.domain.filter.FilterCollection;
import walker.wyp.tw.trains.domain.filter.FilterFactory;
import walker.wyp.tw.trains.domain.filter.ResultFilter;

public class QueryService {
	public static Object execute(TownGraph graph, FilterCollection displayType, 
			                     CmdCollection cmdType, String... cmdBody) {
		if(graph.isEmpty()) {
			return TrainException.NO_SUCH_ROUTE;
		}
		
		ResultFilter filter = FilterFactory.createDisplay(displayType);
		try {
			AbstractCommand cmd = CmdFactory.createCmd(cmdType, cmdBody);
			return filter.outputResult(cmd.formRoute(graph));
		} catch (TrainException e) {
			return e.getMessage();
		}		
	}
}
