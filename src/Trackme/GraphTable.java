package Trackme;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Bob on 11/26/2016.
 */


public class GraphTable {

    private static String ans = "hello";

    public static String display(ObservableList<String> obs){
     Stage stage = new Stage();
     stage.initModality(Modality.APPLICATION_MODAL);
     stage.setTitle("Choose Graph");
     stage.setMinWidth(400);

     ListView<String> listView = new ListView<>();
     listView.setItems(obs);

     Button open = new Button("open"), delete = new Button("delete");
     open.setOnAction(e->{
        ans = listView.getSelectionModel().getSelectedItem();
        stage.close();
     });

     delete.setOnAction(e->{
         PointToTable.deleteAll(listView.getSelectionModel().getSelectedItem());
         listView.getItems().remove(listView.getSelectionModel().getSelectedItem());
     });

     VBox layout = new VBox(10);
     layout.getChildren().addAll(listView,open,delete);
     layout.setAlignment(Pos.CENTER);
     Scene scene = new Scene(layout);
     stage.setScene(scene);
     stage.showAndWait();
     return ans;

    }
}
