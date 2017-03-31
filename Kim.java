/**
 *
 * @author Stancu Andrei 323CC
 */

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Kim {

	public static void main(String[] args) {

		Graph g = new Graph();
		g.read("kim.in");
		
		long first = g.kruskal();
		ArrayList <Long> costuri = g.vkruskal();
		System.out.println(g.getEdges());
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("kim.out"));
			bw.write("" + first);
			//for(Long cost : costuri) {
			//	bw.write("\n");
			//	bw.write("" + cost);
			//}
			bw.close();
		} catch (IOException ex) {
			Logger.getLogger(Kim.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
