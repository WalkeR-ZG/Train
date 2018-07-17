package walker.wyp.tw.trains.domain.command;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import walker.wyp.tw.trains.domain.dao.Route;
import walker.wyp.tw.trains.domain.dao.TownGraph;
import walker.wyp.tw.trains.domain.exception.TrainException;
import walker.wyp.tw.trains.service.parser.GraphBuilder;

public class CmdBaseTest {
	private static TownGraph graph = GraphBuilder.buildGraph(CmdBaseTest.class.getResource("/graph.txt").getFile());

	public static void exec(AbstractCommand cmd, String [] expectedResults){
		try {
			List<Route> routes = cmd.formRoute(graph);
			assertEquals(expectedResults.length, routes.size());
			assertTrue(routes.stream().allMatch(route -> Arrays.asList(expectedResults).contains(route.toString())));
		} catch (TrainException e) {
			fail(e.getMessage());
		}
	}
}
