package Trackme;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import static java.lang.System.out;

/**
 * Created by Jorda on 11/8/2016.
 */
public class NewGraphController {

    public static void display(String title, String message) {
        final String[] gTitle = new String[1];
        final String[] gX = new String[1];
        final String[] gY = new String[1];
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        //Title of graph
        TextField graphTitle = new TextField();
        graphTitle.setPromptText("Graph Title");
        graphTitle.setMinWidth(100);

        //X
        TextField graphX = new TextField();
        graphX.setPromptText("X Title");

        //Y
        TextField graphY = new TextField();
        graphY.setPromptText("Y Title");


        Label label = new Label();
        label.setText(message);
        Button submit = new Button("Submit");
        submit.setOnAction(e -> {
            FileWriter fw = null;
            BufferedWriter bw = null;
            PrintWriter pw = null;
            gTitle[0] = graphTitle.getText();
            gX[0] = graphX.getText();
            gY[0] = graphY.getText();
            try{
                fw = new FileWriter("GraphInfo.txt", true);
                bw = new BufferedWriter(fw);
                pw = new PrintWriter(bw);
                pw.print(gTitle[0] +"_");
                pw.print(gX[0] +"_");
                pw.print(gY[0] +"_");
                pw.println();
                pw.close();
            }catch(IOException e1){
                out.println("Error");
            }
            //repeat
            try{
                fw = new FileWriter(Arrays.toString(gTitle) + ".txt", true);
                bw = new BufferedWriter(fw);
                pw = new PrintWriter(bw);
                //pw.println();
                pw.close();
            }catch(IOException e1){
                out.println("Error");
            }
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,graphTitle, graphX, graphY, submit);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
