package Trackme;

import java.util.*;

import Trackme.Node;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.application.Application;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Clark on 10/12/2016.
 * Modified by jkeys on 10/13/16.
 */
/*Notes by Ian
AreaChart

 */
public final class Graph extends Application {
    ArrayList<Node> nodeList;
    String name;
    String xLabel, yLabel;
    int xMin, xMax, yMin, yMax;
    int numNodes;
    
    public String graphName() { return name; }
    public String xLabel() { return xLabel; }
    public String yLabel() { return yLabel; }
    public int xMin() { return xMin; }
    public int xMax() { return xMax; }
    public int yMin() { return yMin; }
    public int yMax() { return yMax; }
    public int numNodes() { return numNodes(); }    

    public Node getNode(int i) {
      return nodeList.get(i);
    }
    
    public void incrementNumNodes() {
      numNodes++;
      return;
    }
   
    

    public Graph(String n, String xLab, String yLab, int xMinimum, int xMaximum, int yMinimum, int yMaximum) {
        nodeList = new ArrayList<Node>();
        name = n;
        xLabel = xLab;
        yLabel = yLab;
        xMin = xMinimum;
        xMax = xMaximum;
        yMin = yMinimum;
        yMax = yMaximum;
    }

    public Graph(String n, String xLab, String yLab, ArrayList<Node> nl) {
        nodeList = nl;
        name = n;
        xLabel = xLab;
        yLabel = yLab;
    }


    public void addNode(Node n) {
        nodeList.add(n);
    }


    @Override
    public void start(Stage stage) {
        stage.setTitle("Graph");
        final NumberAxis xAxis = new NumberAxis(0, 10, 1);
        final NumberAxis yAxis = new NumberAxis(-100, 500, 100);
        final ScatterChart<Number,Number> sc = new
                ScatterChart<Number,Number>(xAxis,yAxis);
        xAxis.setLabel("Day");
        yAxis.setLabel("Calories");
        sc.setTitle("Calorie Watcher");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Non-working days");

        series1.getData().add(new XYChart.Data(4.2, 193.2));
        series1.getData().add(new XYChart.Data(2.8, 33.6));
        series1.getData().add(new XYChart.Data(6.2, 24.8));
        series1.getData().add(new XYChart.Data(1, 14));
        series1.getData().add(new XYChart.Data(1.2, 26.4));
        series1.getData().add(new XYChart.Data(4.4, 114.4));
        series1.getData().add(new XYChart.Data(8.5, 323));
        series1.getData().add(new XYChart.Data(6.9, 289.8));
        series1.getData().add(new XYChart.Data(9.9, 287.1));
        series1.getData().add(new XYChart.Data(0.9, -9));
        series1.getData().add(new XYChart.Data(3.2, 150.8));
        series1.getData().add(new XYChart.Data(4.8, 20.8));
        series1.getData().add(new XYChart.Data(7.3, -42.3));
        series1.getData().add(new XYChart.Data(1.8, 81.4));
        series1.getData().add(new XYChart.Data(7.3, 110.3));
        series1.getData().add(new XYChart.Data(2.7, 41.2));

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Working days");

        series2.getData().add(new XYChart.Data(5.2, 229.2));
        series2.getData().add(new XYChart.Data(2.4, 37.6));
        series2.getData().add(new XYChart.Data(3.2, 49.8));
        series2.getData().add(new XYChart.Data(1.8, 134));
        series2.getData().add(new XYChart.Data(3.2, 236.2));
        series2.getData().add(new XYChart.Data(7.4, 114.1));
        series2.getData().add(new XYChart.Data(3.5, 323));
        series2.getData().add(new XYChart.Data(9.3, 29.9));
        series2.getData().add(new XYChart.Data(8.1, 287.4));

        sc.getData().addAll(series1, series2);
        Scene scene  = new Scene(sc, 500, 400);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

