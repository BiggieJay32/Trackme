package Trackme;
/**
 * Created by jkeys on 10/12/2016.
 */
 
import java.io.*;
import java.util.*;

/**
 * Created  by jkeys on 10/12/16.
 * Modified by jkeys on 10/13/16.
 */

public class FileHandler {
	private void writeUserFile(String userFile) {

	}

	private Node readNode(BufferedReader r) throws Exception {
		String nodeNote = r.readLine();
		String xVal_s = r.readLine();
		String yVal_s = r.readLine();
		int xVal = Integer.parseInt(xVal_s);
		int yVal = Integer.parseInt(yVal_s);

		return new Node(xVal, yVal, nodeNote);
	}

//	private Graph readGraph(BufferedReader r) throws Exception {
//		String graphName = r.readLine();
//		String xLabel = r.readLine();
//		String xMin_s = r.readLine();
//		String xMax_s = r.readLine();
//		String yLabel = r.readLine();
//		String yMin_s = r.readLine();
//		String yMax_s = r.readLine();
//
//		int xMin = Integer.parseInt(xMin_s);
//		int xMax = Integer.parseInt(xMax_s);
//		int yMin = Integer.parseInt(yMin_s);
//		int yMax = Integer.parseInt(yMax_s);
//
//		return( new Graph(graphName, xLabel, yLabel, xMin, xMax, yMin, yMax) );
//
//	}

	//reads the user file, and adds all nodes to each graph, and each graph to the user object
	private User readUserFile(String userFile) throws Exception {
		String userName, graphName;
		String numGraphs_s, numNodes_s;
		String xMin_s, xMax_s, yMin_s, yMax_s;
		String xLabel, yLabel;
		//String nodeNote, xVal_s, yVal_s;

		BufferedReader r = new BufferedReader(new FileReader(userFile));
		
		userName 	= r.readLine();
		numGraphs_s	= r.readLine();

		User u = new User(userName);

		int numGraphs = Integer.parseInt(numGraphs_s);

		for (int i = 0; i != numGraphs; i++) {	
			graphName = r.readLine();
			xLabel = r.readLine();
			xMin_s = r.readLine();
			xMax_s = r.readLine();
			yLabel = r.readLine();
			yMin_s = r.readLine();
			yMax_s = r.readLine();
			
			numNodes_s = r.readLine();

			int xMin = Integer.parseInt(xMin_s);
			int xMax = Integer.parseInt(xMax_s);
			int yMin = Integer.parseInt(yMin_s);
			int yMax = Integer.parseInt(yMax_s);
			
			int numNodes = Integer.parseInt(numNodes_s); //j many nodes per graph

			Graph g = new Graph(graphName, xLabel, yLabel, xMin, xMax, yMin, yMax);
			
			for (int j = 0; j != numNodes; j++) {
				Node n = readNode(r);
				g.addNode(n); //add this node to Graph g's node list
			} //add the j'th node to the user's i'th graph

			u.addGraph(g); //add the i'th graph to the User object
		}//end for

		//should be no more input here
		return u;
	}
	

	public void writeUserFile(User u, String userFile) throws IOException {
	  String userName = u.name();
	  int numGraphs = u.numGraphs();

        BufferedWriter r = new BufferedWriter(new FileWriter(userFile));

	  //write userName
	  //write numGraphs
	  r.write(userName, 0, userName.length() - 1);
	  r.write(String.valueOf(numGraphs), 0, String.valueOf(numGraphs).length() - 1);
	  

	  
	  for (int i = 0; i != u.numGraphs(); i++) {
	  
	  Graph g = u.getGraph(i);
	  String graphName = g.graphName();
	  String xLabel = g.xLabel();
	  String xMin = String.valueOf(g.xMin());
	  String xMax = String.valueOf(g.xMax());
	  String yLabel = g.yLabel();
	  String yMin = String.valueOf(g.yMin());
	  String yMax = String.valueOf(g.yMax());
	  
	  String numNodes = String.valueOf(g.numNodes());
	  
	  //write graphName
	  r.write(graphName, 0, graphName.length() - 1);
	  r.write(String.valueOf(numGraphs), 0, String.valueOf(numGraphs).length() - 1);
	  
	    for(int j = 0; j != g.numNodes(); j++) {
	      
	      Node n = g.getNode(i);
	      String note = n.note();
	      String xVal = String.valueOf(n.x());
	      String yVal = String.valueOf(n.y());
	    
	    }
	    
	  
	  }
	
	}
	
	public static void main(String[] args) {
		
	}

}
