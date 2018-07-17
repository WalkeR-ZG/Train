package walker.wyp.tw.trains;

import walker.wyp.tw.trains.domain.command.CmdCollection;
import walker.wyp.tw.trains.domain.dao.TownGraph;
import walker.wyp.tw.trains.domain.filter.FilterCollection;
import walker.wyp.tw.trains.service.QueryService;
import walker.wyp.tw.trains.service.parser.GraphBuilder;

public class Train {
	public static void main(String[] args) {
		TownGraph graph = GraphBuilder.buildGraph(args[0]);
		Object[] results = {
				QueryService.execute(graph, FilterCollection.DISTANCE_FILTER, CmdCollection.DESIGNATED_ROUTED_CMD,
						"A-B-C"),
				QueryService.execute(graph, FilterCollection.DISTANCE_FILTER, CmdCollection.DESIGNATED_ROUTED_CMD,
						"A-D"),
				QueryService.execute(graph, FilterCollection.DISTANCE_FILTER, CmdCollection.DESIGNATED_ROUTED_CMD,
						"A-D-C"),
				QueryService.execute(graph, FilterCollection.DISTANCE_FILTER, CmdCollection.DESIGNATED_ROUTED_CMD,
						"A-E-B-C-D"),
				QueryService.execute(graph, FilterCollection.DISTANCE_FILTER, CmdCollection.DESIGNATED_ROUTED_CMD,
						"A-E-D"),
				QueryService.execute(graph, FilterCollection.PATH_NUM_FILTER, CmdCollection.MAX_STEP_CMD, "C", "C",
						"3"),
				QueryService.execute(graph, FilterCollection.PATH_NUM_FILTER, CmdCollection.EXACTLY_STEP_CMD, "A", "C",
						"4"),
				QueryService.execute(graph, FilterCollection.DISTANCE_FILTER, CmdCollection.SHORTEST_ROUTE_CMD, "A",
						"C"),
				QueryService.execute(graph, FilterCollection.DISTANCE_FILTER, CmdCollection.SHORTEST_ROUTE_CMD, "B",
						"B"),
				QueryService.execute(graph, FilterCollection.PATH_NUM_FILTER, CmdCollection.MAX_DISTANCE_CMD, "C", "C",
						"30") };
		for (int i = 1; i < results.length; i++) {
			System.out.println("Output #" + i + ": " + results[i - 1]);
		}
	}
}
