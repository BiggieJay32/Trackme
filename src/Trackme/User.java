package Trackme;

/**
 * Created by  jkeys on 10/13/2016.
 * Modified by jkeys on 10/13/2016.
 */

import java.util.*;

public class User {
    ArrayList<Graph> graphList;
    String userName; //name of the user
    int numGraphs;

    public User(String n) {
        userName = n;
        numGraphs = 0;
    }

    public void addGraph(Graph g) {
        graphList.add(g);
        numGraphs++;
    }

    public void setName(String n) {
        userName = n;
    }

    public int numGraphs() {
      return numGraphs;
    }
    public String name()
    {
        return userName;
    }

    public void setNumGraphs(int numGraphsNew) {
      numGraphs = numGraphsNew;
    }

    public Graph getGraph(int i) {
	return graphList.get(i);
    }
    
}
