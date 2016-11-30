package Trackme;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable
{
    @FXML private Button trackButton, notesButton, notificationButton, progressButton;

    @FXML
    private void handleTrackButton(ActionEvent event) throws Exception
    {
        Stage stage = (Stage) trackButton.getScene().getWindow();
        stage.setTitle("Trackers");
        Parent root = FXMLLoader.load(getClass().getResource("track.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleNoteButton(ActionEvent event) throws Exception
    {
        Stage stage = (Stage) notificationButton.getScene().getWindow();
        stage.setTitle("Notifications");
        Parent root = FXMLLoader.load(getClass().getResource("userInputPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}
}//End HomeController