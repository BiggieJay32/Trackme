package Trackme;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/*The class responsible providing the user with a reminder to update their class*/
public class NotificationController implements Initializable
{
    @FXML public Button yesButton, noButton;

    @FXML
    private void handleYesButton(ActionEvent event) throws Exception
    {
        Stage stage = (Stage) yesButton.getScene().getWindow();
        stage.setTitle("Trackme Home");
        Parent root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleNoButton(ActionEvent event) throws Exception
    {
        Stage stage = (Stage) noButton.getScene().getWindow();
        stage.setTitle("Trackme Home");
        Parent root = FXMLLoader.load(getClass().getResource("track.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }
}
