package walker.wyp.tw.trains.service.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import walker.wyp.tw.trains.domain.dao.TownGraph;

public class GraphBuilder {
	public static TownGraph buildGraph(String file){
		TownGraph graph = new TownGraph();
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			String str = br.readLine();
			while ((str != null) && (str.trim().length() != 0)) {
				LineParser.formGraphByLine(graph, str);
				str = br.readLine();
			}
		} catch (Exception e) {
			System.err.print(e.getMessage());
			graph.clear();
		}
		return graph;
	}
}
