package Trackme;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.awt.TextField;
import java.net.URL;
import java.util.ResourceBundle;



/**
 * Created by Jorda on 11/30/2016.
 */
public class CreateGraphController implements Initializable
{
    @FXML private javafx.scene.control.TextField graphTitle, xTitle, yTitle, goalValue;
    @FXML private Button submitButton;

    @FXML public void submit()
    {
        PointToTable.createGraph(graphTitle.getText(), xTitle.getText(), yTitle.getText(), Integer.parseInt(goalValue.getText()));
        Stage stage = (Stage) submitButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
