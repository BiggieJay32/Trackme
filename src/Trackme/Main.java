package Trackme;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("Trackme Home");
        Parent[] root = {FXMLLoader.load(getClass().getResource("homepage.fxml")),
            FXMLLoader.load(getClass().getResource("notification.fxml"))};
        Scene scene = new Scene(root[0]);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        //FileHandler file = new FileHandler("user_file.txt");
        launch(args);
    }
}
