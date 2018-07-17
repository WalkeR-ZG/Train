package walker.wyp.tw.trains.domain.command;

import org.junit.Test;

import walker.wyp.tw.trains.domain.command.ShortestRouteCmd;

public class ShortestRouteCmdTest {
	@Test
	public void testShortestRouteCmd_RouteC2C() {
		String[] expectedResults = {"[CE2, EB3, BC4]"};
		CmdBaseTest.exec(new ShortestRouteCmd("C", "C"), expectedResults);
	}

	@Test
	public void testShortestRouteCmd_RouteB2B() {
		String[] expectedResults = {"[BC4, CE2, EB3]"};
		CmdBaseTest.exec(new ShortestRouteCmd("B", "B"), expectedResults);
	}
	
	@Test
	public void testShortestRouteCmd_RouteA2C() {
		String[] expectedResults = {"[AB5, BC4]"};
		CmdBaseTest.exec(new ShortestRouteCmd("A", "C"), expectedResults);
	}
}
