package walker.wyp.tw.trains.domain.command;

import static org.junit.Assert.*;

import java.util.List;
import java.util.function.Function;

import org.junit.BeforeClass;
import org.junit.Test;

import walker.wyp.tw.trains.domain.command.DesignatedRouteCommand;
import walker.wyp.tw.trains.domain.dao.Route;
import walker.wyp.tw.trains.domain.dao.TownGraph;
import walker.wyp.tw.trains.domain.exception.TrainException;
import walker.wyp.tw.trains.domain.filter.DistanceFilter;
import walker.wyp.tw.trains.service.parser.GraphBuilder;

public class DesignatedRouteCommandTest {
	private static TownGraph graph;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		graph = GraphBuilder.buildGraph(DesignatedRouteCommandTest.class.getResource("/graph.txt").getFile());;
	}
	
	private void assertRouteBase(String route, int size, Object result)
	{
		Function<List<Route>, Integer> route_distance = (routes)-> { return routes.get(0).getRoute().size();};
		DesignatedRouteCommand dc = new DesignatedRouteCommand(route);
		try {
			assertEquals(size, route_distance.apply(dc.formRoute(graph)).intValue());
			assertEquals(result, new DistanceFilter().outputResult(dc.formRoute(graph)));
		} catch (TrainException e) {
			fail(e.getMessage());
		}
	}
	
	private void illegalRouteTest(String route)
	{
		DesignatedRouteCommand dc = new DesignatedRouteCommand(route);
		try {
			assertEquals(0, dc.formRoute(graph).size());
			assertEquals(TrainException.NO_SUCH_ROUTE, new DistanceFilter().outputResult(dc.formRoute(graph)));
		} catch (TrainException e) {
			assertEquals(TrainException.NO_SUCH_ROUTE, e.getMessage());
		}
		
	}

	@Test
	public void testDesignatedCmd_RouteABC() {
		assertRouteBase("A-B-C", 2, 9);
	}

	@Test
	public void testDesignatedCmd_RouteAD() {
		assertRouteBase("  A -   D", 1, 5);
	}
	
	@Test
	public void testDesignatedCmd_RouteADC() {
		assertRouteBase("A-D-C", 2, 13);
	}
	
	@Test
	public void testDesignatedCmd_RouteAEBCD() {
		assertRouteBase("A-E-B-C-D", 4, 22);
	}
	
	@Test
	public void testDesignatedCmd_IllegalRouteAED() {
		illegalRouteTest("A-E-D");
	}
	
	@Test
	public void testDesignatedCmd_IllegalRouteA() {
		illegalRouteTest("A");
	}
	@Test
	public void testDesignatedCmd_IllegalRouteAB() {
		illegalRouteTest("A--B");	
	}
}
