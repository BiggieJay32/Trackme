package Trackme;

import Trackme.NewGraphController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

/**
 * Created by Jorda on 11/8/2016.
 */
public class OpenGraphController
{
    TableView<Open> graphList;
    ObservableList<Open> openList = FXCollections.observableArrayList();

    public void open(String title) {
        Stage window = new Stage();
        graphList = new TableView<>();
        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        //Read text files from GraphInfo.txt

        //Tables and columns
        //Name columns
        TableColumn<Open, String> graphNameColumn = new TableColumn<>("Graph Name");
        graphNameColumn.setMinWidth(200);
        graphNameColumn.setCellValueFactory(new PropertyValueFactory<>("graphName"));
        //System.out.println(graphList);
        graphList.setItems(graphName());
        graphList.getColumns().addAll(graphNameColumn);
        //System.out.println(graphList);

        //Button graph = new Button("Graph1");

        //hbox
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        Button open = new Button("Open");
        open.setOnAction(e -> select());
        hBox.getChildren().addAll(open);


        VBox layout = new VBox(10);
        layout.getChildren().addAll(graphList, hBox);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    public void select()
    {
        String string;
        ObservableList<Open> pointSelected, allPoints;
        allPoints = graphList.getItems();
        pointSelected = graphList.getSelectionModel().getSelectedItems();
        //out.print(allPoints +" Hello");
        //pointSelected.forEach(allPoints::remove);
        System.out.println(pointSelected);
    }


    public ObservableList<Open> graphName()
    {
        String name;
        try {
            Scanner file = new Scanner(new File("GraphInfo.txt"));
            FileReader fr = new FileReader("GraphInfo.txt");
            BufferedReader br = new BufferedReader(fr);
            int count = 0;
            while (file.hasNextLine()) {
                count++;
                file.nextLine();
            }
            while (count > 0) {
                String str = br.readLine();
                String parts[] = str.split("_");
                name = parts[0];
                //System.out.println(name);
                openList.add(new Open(name));
                count = count - 1;
            }
            fr.close();
        } catch (IOException e) {
            out.println("File Not Found");
        }
        return openList;
    }
}
