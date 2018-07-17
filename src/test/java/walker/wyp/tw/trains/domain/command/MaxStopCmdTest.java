package walker.wyp.tw.trains.domain.command;

import org.junit.Test;

import walker.wyp.tw.trains.domain.command.MaxStopCmd;

public class MaxStopCmdTest {
	@Test
	public void testMaxStopCmd_RouteA2C() {
		String[] expectedResults = {"[CD8, DC8]", "[CE2, EB3, BC4]"};
		CmdBaseTest.exec(new MaxStopCmd("C", "C", 3), expectedResults);
	}
}
