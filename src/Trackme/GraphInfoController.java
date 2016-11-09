package Trackme;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

//import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jorda on 10/25/2016.
 */
public class GraphInfoController implements Initializable
{
    @FXML private TextField graphTitle1;
    @FXML private Button Enter;
    @FXML private LineChart userGraph1;

    @FXML
    private void handleEnterButton(ActionEvent event) throws Exception
    {
        String s = graphTitle1.getText();
        System.out.println(s);
        Stage stage = (Stage) Enter.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("userGraph.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }
}
