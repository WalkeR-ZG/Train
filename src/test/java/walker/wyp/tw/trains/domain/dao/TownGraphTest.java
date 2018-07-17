package walker.wyp.tw.trains.domain.dao;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import walker.wyp.tw.trains.domain.dao.TownEdge;
import walker.wyp.tw.trains.domain.dao.TownGraph;

public class TownGraphTest {
	private TownGraph graph;

	@Before
	public void setUp() throws Exception {
		graph = new TownGraph();
	}

	@Test
	public void test() {
		graph.addTown("A", "B", 7);
		graph.addTown("A", "C", 5);
		Map<String, Integer> neighbours = graph.getTownNeighbours("A").stream().
			collect(Collectors.toMap(TownEdge::getDestination, TownEdge::getDistance));
		assertTrue(neighbours.get("B").equals(7));
		assertTrue(neighbours.get("C").equals(5));
		assertNull(neighbours.get("D"));
	}
}
