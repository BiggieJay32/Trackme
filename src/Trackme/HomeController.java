package Trackme;

import Trackme.PointMain;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable
{
    @FXML
    public Button notification;
    public Button NoteButton;
    public Button SettingsButton;
    public MenuButton quick;

    /*public void handleGraphButton(ActionEvent event) throws Exception {
        try {

        } catch(Exception e) {
            e.printStackTrace();
        }
    }*/

    public void handleGraphButton(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("track.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Track Me");
        stage.setScene(new Scene(root, 600, 675));
        stage.show();
        /*Platform.runLater(new Runnable() {
            public void run() {
                try {
                    new PointMain().start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });*/
    }

    public void handleSetButton(ActionEvent event) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Graph.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Graph Window");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleNoteButton(ActionEvent event) throws Exception
    {
        Stage stage = (Stage) notification.getScene().getWindow();
        stage.setTitle("Specify Notification");
        //Parent root = FXMLLoader.load(getClass().getResource("graphInfo.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("userInputPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }
}
