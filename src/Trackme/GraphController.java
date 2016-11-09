package Trackme;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.TextField;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jorda on 10/25/2016.
 */
public class GraphController implements Initializable
{
    @FXML private TextField graphTitle1;
    @FXML private LineChart userGraph1;
    private String s = graphTitle1.getText();


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        userGraph1.setTitle(s);
    }
}
