package walker.wyp.tw.trains.service.parser;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import walker.wyp.tw.trains.domain.dao.TownGraph;
import walker.wyp.tw.trains.domain.exception.TrainException;
import walker.wyp.tw.trains.service.parser.GraphBuilder;

public class GraphBuilderTest {
	private  static final PrintStream sysErr = System.err;
	private  ByteArrayOutputStream err;
	private  File temp;
	private  OutputStream out;
	
	@Before
	public  void setUp() throws Exception {
		err = new ByteArrayOutputStream();
		System.setErr(new PrintStream(err));
		temp = File.createTempFile("test",".txt");
		out = new FileOutputStream(temp);
	}

	@After
	public  void tearDown() throws Exception {
		System.setErr(sysErr);
		out.close();
		temp.deleteOnExit();
	}
	
	private void GraphBuilder_IlleaglBase(String in, String exception) throws IOException{	
			out.write(in.getBytes());
			TownGraph graph = GraphBuilder.buildGraph(temp.getAbsolutePath());
			assertEquals(err.toString(), exception);
			assertTrue(graph.isEmpty());		
	}

	@Test
	public void testIllegalGraphLength() throws Exception {
		GraphBuilder_IlleaglBase("AB7,BC,CD8", TrainException.ILLEGAL_GRAPH_LENGTH);
	}
	
	@Test
	public void testIllegalTownDistance() throws Exception {
		GraphBuilder_IlleaglBase("AB7,BC4,CDE", TrainException.ILLEGAL_TOWN_DISTANCE);
	}
	
	@Test
	public void testFormGraphCorrectly() throws Exception{
		out.write("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7".getBytes());		
		TownGraph graph = GraphBuilder.buildGraph(temp.getAbsolutePath());
		Set<String> neighbours = graph.getTownNeighbours("A").stream()
				.map(edge -> edge.getDestination()).collect(Collectors.toSet());
		assertTrue(neighbours.contains("B"));
		assertTrue(neighbours.contains("D"));
		assertTrue(neighbours.contains("E"));			
	}
}
