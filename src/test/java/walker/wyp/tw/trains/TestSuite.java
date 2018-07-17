package walker.wyp.tw.trains;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import walker.wyp.tw.trains.domain.command.CmdFactoryTest;
import walker.wyp.tw.trains.domain.command.DesignatedRouteCommandTest;
import walker.wyp.tw.trains.domain.command.ExactlyStopCmdTest;
import walker.wyp.tw.trains.domain.command.MaxDistanceCommandTest;
import walker.wyp.tw.trains.domain.command.MaxStopCmdTest;
import walker.wyp.tw.trains.domain.command.ShortestRouteCmdTest;
import walker.wyp.tw.trains.domain.dao.TownGraphTest;
import walker.wyp.tw.trains.domain.filter.FilterFactoryTest;
import walker.wyp.tw.trains.service.parser.GraphBuilderTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	GraphBuilderTest.class,
	TrainTest.class,
	CmdFactoryTest.class,
	DesignatedRouteCommandTest.class,
	ExactlyStopCmdTest.class,
	MaxDistanceCommandTest.class,
	MaxStopCmdTest.class,
	ShortestRouteCmdTest.class,
	TownGraphTest.class,
	FilterFactoryTest.class
})
public class TestSuite {
}
