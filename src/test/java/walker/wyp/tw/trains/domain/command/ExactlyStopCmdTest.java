package walker.wyp.tw.trains.domain.command;

import org.junit.Test;

import walker.wyp.tw.trains.domain.command.ExactlyStopCmd;

public class ExactlyStopCmdTest {
	@Test
	public void testExactlyStopCmd_RouteA2C() {
		String[] expectedResults = {"[AB5, BC4, CD8, DC8]", "[AD5, DC8, CD8, DC8]", "[AD5, DE6, EB3, BC4]"};
		CmdBaseTest.exec(new ExactlyStopCmd("A", "C", 4), expectedResults);		
	}
}
