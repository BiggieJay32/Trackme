package Trackme;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by Jorda on 10/12/2016.
 */
public class InputController implements Initializable
{
    @FXML private AnchorPane mainPane;
    @FXML private CheckBox morning, afternoon, evening, noteSound;
    @FXML private Button submit, backButton;
    @FXML private TextField hour1, hour2, hour3, min1, min2, min3, ampm1, ampm2, ampm3;
    private boolean soundStatus = false;

    @FXML
    private void handleBackButton(ActionEvent event) throws Exception
    {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setTitle("Trackme");
        Parent root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //Get the times the user specifies for the notification
    private String getChoice(TextField hour, TextField minn, TextField ampm)
    {
        String h = hour.getText();
        String m = minn.getText();
        String ap = ampm.getText();
        String time = h + ":" + m + ap;

        //Check to make sure user specifies a correct hour, minute, and AM/PM
        if(Integer.parseInt(h) > 12 || Integer.parseInt(h) < 0)
        {
            error();
            System.out.println("Don't do that!");
        }

        if(Integer.parseInt(m) > 59 || Integer.parseInt(m) < 0)
        {
            error();
            System.out.println("Don't do that!");
        }

        if(!ap.equals("AM") && !ap.equals("PM"))
        {
            error();
            System.out.println("Don't do that!");
        }

        return time;
    }//End getChoice

    //Handle specific notification boxes
    private void handleSubmit(CheckBox morning, CheckBox afternoon, CheckBox evening, CheckBox noteSound) throws IOException
    {
        System.out.println("Selected:\n");

        if(morning.isSelected())
        {
            String time1 = getChoice(hour1, min1, ampm1);
            System.out.println("Morning " + time1);
        }

        if(afternoon.isSelected())
        {
            String time2 = getChoice(hour2, min2, ampm2);
            System.out.println("Afternoon " + time2);
        }

        if(evening.isSelected())
        {
            String time3 = getChoice(hour3, min3, ampm3);
            System.out.println("Evening " + time3);
        }

        if(noteSound.isSelected())
        {
            soundStatus = true;
        }
    }

    //Method to show error if the user enters an incorrect time
    private void error()
    {
        Label error = new Label("[ERROR] Hour: 0-12, Minute: 0-59, AM/PM: AM or PM!");
        error.setStyle("-fx-text-fill: red;");
        error.setTranslateY(320);
        error.setTranslateX(225);
        mainPane.getChildren().add(error);
        FadeTransition fade = new FadeTransition(Duration.seconds(8), error);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.play();
    }//End error

    //Timer that uses users specific time, when that time arrives then notification will pop up
    private void timer() throws IOException
    {
        System.out.println("Inside Timer");
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("h:ma");
        if(ft.format(date).equals(getChoice(hour1, min1, ampm1)) || ft.format(date).equals(getChoice(hour2, min2, ampm2)) || ft.format(date).equals(getChoice(hour3, min3, ampm3)))
        {
            Stage stage = new Stage();
            stage.setTitle("Reminder");
            Parent root = FXMLLoader.load(getClass().getResource("notification.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            //Play notification audio
            if(soundStatus)
            {
                Media sound = new Media(getClass().getResource("notificationSound.mp3").toString());
                MediaPlayer mediaPlayer = new MediaPlayer(sound);
                mediaPlayer.play();
            }
        }
    }//End timer

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        submit.setOnAction(e -> {
            try {
                handleSubmit(morning, afternoon, evening, noteSound);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        Timeline timeline = new Timeline(new KeyFrame(
                Duration.minutes(1),
                ae -> {
                    try {
                        System.out.println("You are in Try\n");
                        timer();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }));
        timeline.setCycleCount((Animation.INDEFINITE));
        timeline.play();
    }
}//End InputController