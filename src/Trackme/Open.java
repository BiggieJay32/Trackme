package Trackme;

import javafx.collections.ObservableList;

/**
 * Created by Jorda on 11/8/2016.
 */
public class Open
{
    private String graphName;

    public Open()
    {
        this.graphName = "";
    }

    public String getGraphName()
    {
        return graphName;
    }

    public void setGraphName(String graphName)
    {
        this.graphName = graphName;
    }

    public Open(String name)
    {
        this.graphName = name;
    }


}
