package walker.wyp.tw.trains.domain.command;

import org.junit.Test;

import walker.wyp.tw.trains.domain.command.MaxDistanceCommand;

public class MaxDistanceCommandTest {
	@Test
	public void testMaxDistanceCommand_RouteC2C() {	
			String[] expectedResults = { "[CD8, DC8]", "[CD8, DC8, CE2, EB3, BC4]", "[CD8, DE6, EB3, BC4]", "[CE2, EB3, BC4]",
					"[CE2, EB3, BC4, CD8, DC8]", "[CE2, EB3, BC4, CE2, EB3, BC4]", "[CE2, EB3, BC4, CE2, EB3, BC4, CE2, EB3, BC4]" };
			CmdBaseTest.exec(new MaxDistanceCommand("C", "C", 30), expectedResults);
	}
}
