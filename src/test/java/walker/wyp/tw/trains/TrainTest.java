package walker.wyp.tw.trains;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import walker.wyp.tw.trains.domain.command.CmdCollection;
import walker.wyp.tw.trains.domain.dao.TownGraph;
import walker.wyp.tw.trains.domain.exception.TrainException;
import walker.wyp.tw.trains.domain.filter.FilterCollection;
import walker.wyp.tw.trains.service.QueryService;
import walker.wyp.tw.trains.service.parser.GraphBuilder;

public class TrainTest {
	private static TownGraph graph;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		graph = GraphBuilder.buildGraph(TrainTest.class.getResource("/graph.txt").getFile());;
	}
	@Test
	public void testCase1() {
		assertTrue(QueryService.execute(graph, FilterCollection.DISTANCE_FILTER, CmdCollection.DESIGNATED_ROUTED_CMD, "A-B-C").equals(9));
	}
	
	@Test
	public void testCase2() {
		assertTrue(QueryService.execute(graph, FilterCollection.DISTANCE_FILTER, CmdCollection.DESIGNATED_ROUTED_CMD, "A-D").equals(5));
	}
	
	@Test
	public void testCase3() {
		assertTrue(QueryService.execute(graph, FilterCollection.DISTANCE_FILTER, CmdCollection.DESIGNATED_ROUTED_CMD, "A-D-C").equals(13));
	}
	
	@Test
	public void testCase4() {
		assertTrue(QueryService.execute(graph, FilterCollection.DISTANCE_FILTER, CmdCollection.DESIGNATED_ROUTED_CMD, "A-E-B-C-D").equals(22));
	}
	
	@Test
	public void testCase5() {
		assertTrue(QueryService.execute(graph, FilterCollection.DISTANCE_FILTER, CmdCollection.DESIGNATED_ROUTED_CMD, "A-E-D").equals(TrainException.NO_SUCH_ROUTE));
	}
	
	@Test
	public void testCase6() {
		assertTrue(QueryService.execute(graph, FilterCollection.PATH_NUM_FILTER, CmdCollection.MAX_STEP_CMD, "C", "C", "3").equals(2));
	}
	
	@Test
	public void testCase7() {
		assertTrue(QueryService.execute(graph, FilterCollection.PATH_NUM_FILTER, CmdCollection.EXACTLY_STEP_CMD, "A", "C", "4").equals(3));
	}
	
	@Test
	public void testCase8() {
		assertTrue(QueryService.execute(graph, FilterCollection.DISTANCE_FILTER, CmdCollection.SHORTEST_ROUTE_CMD, "A","C").equals(9));
	}
	
	@Test
	public void testCase9() {
		assertTrue(QueryService.execute(graph, FilterCollection.DISTANCE_FILTER, CmdCollection.SHORTEST_ROUTE_CMD, "B", "B").equals(9));
	}
	
	@Test
	public void testCase10() {
		assertTrue(QueryService.execute(graph, FilterCollection.PATH_NUM_FILTER, CmdCollection.MAX_DISTANCE_CMD, "C", "C", "30").equals(7));
	}
}
