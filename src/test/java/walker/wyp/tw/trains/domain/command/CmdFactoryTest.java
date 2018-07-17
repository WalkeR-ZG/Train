package walker.wyp.tw.trains.domain.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import walker.wyp.tw.trains.domain.command.CmdCollection;
import walker.wyp.tw.trains.domain.command.CmdFactory;
import walker.wyp.tw.trains.domain.exception.TrainException;

public class CmdFactoryTest {
	
	private void illegalCmdTestBase(CmdCollection cmdType, String ...strings) {
		try {
			CmdFactory.createCmd(cmdType, strings);
			fail();		
		}catch (TrainException e) {
			assertEquals(e.getMessage(), TrainException.ILLEGAL_CMD);
		} 	
	}
	
	@Test
	public void testCreateCmd_MissPara() {
		illegalCmdTestBase(CmdCollection.DESIGNATED_ROUTED_CMD);
		illegalCmdTestBase(CmdCollection.EXACTLY_STEP_CMD, "A", "C");
		illegalCmdTestBase(CmdCollection.SHORTEST_ROUTE_CMD, "A");
	}
	
	@Test
	public void testCreateCmd_IllegalPara() {
		illegalCmdTestBase(CmdCollection.EXACTLY_STEP_CMD, "A", "C", "D");
	}
	
	@Test
	public void testCreateCmd_Correctly() throws TrainException {
		assertNotNull(CmdFactory.createCmd(CmdCollection.DESIGNATED_ROUTED_CMD, "A-B-C"));
		assertNotNull(CmdFactory.createCmd(CmdCollection.EXACTLY_STEP_CMD, "A", "C", "2"));
		assertNotNull(CmdFactory.createCmd(CmdCollection.MAX_DISTANCE_CMD, "A", "B", "10"));
		assertNotNull(CmdFactory.createCmd(CmdCollection.MAX_STEP_CMD, "C", "C", "4"));
		assertNotNull(CmdFactory.createCmd(CmdCollection.SHORTEST_ROUTE_CMD, "A", "C"));
	}

}
