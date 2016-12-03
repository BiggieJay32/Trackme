package Trackme;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


/*This class provides the scenes for which the graphs are actually displayed and can be manipulated.*/


public class trackController implements Initializable
{
    @FXML private ProgressBar progressBar;
    @FXML private ProgressIndicator progressIndicator;

    @FXML private TextField xIt, yIt,descIt;
    @FXML private LineChart<Number, Number> lineChart;
    @FXML private TableView<Point> tableTime;
    @FXML private TableColumn<Point,String> xCol,yCol,descCol;
    private Label lbl1;
    private XYChart.Series<Number, Number> series;
    private String currentName, currentX, currentY;
    @FXML private NumberAxis xAxis, yAxis;
    private ObservableList<Point> obs = FXCollections.observableArrayList();
    private PointToTable pt;
    private int goal, current;

    public void btn1(ActionEvent e)
    {
        pt.insertPoint(Integer.parseInt(xIt.getText()),Integer.parseInt(yIt.getText()),descIt.getText());
        makeIt();
        xIt.clear();
        yIt.clear();
        descIt.clear();
    }

    public void btn2(ActionEvent e)
    {
        pt.deletePoint(tableTime.getSelectionModel().getSelectedItem().getX());
        makeIt();
    }

    public void openGraphs(ActionEvent e)
    {
        currentName = GraphTable.display(PointToTable.pullFromGraphs());
        pt = new PointToTable(currentName);
        makeIt();
    }

    @FXML
    private void openNewGraph(ActionEvent event) throws Exception
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createGraph.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Graph Window");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void makeIt()
    {
        lineChart.getData().clear();
        lineChart.setTitle(currentName);
        currentX = pt.getXName();
        currentY = pt.getYName();
        goal = pt.getGoal();
        current = pt.getCurrent();
        progressCheck(goal, current);
        xAxis.setLabel(currentX);
        yAxis.setLabel(currentY);
        series = pt.dumpContent();
        lineChart.getData().addAll(series);
        obs = pt.tableIt();
        xCol.setCellValueFactory(new PropertyValueFactory<>("x"));
        yCol.setCellValueFactory(new PropertyValueFactory<>("y"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("desc"));
        tableTime.setItems(obs);
        for(final XYChart.Data<Number, Number> data: series.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, event ->
            {
                Tooltip.install(data.getNode(),new Tooltip(pt.getPointDescript( (int) data.getXValue() ) ) );
            });
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, event ->{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("More Info");
                alert.setHeaderText(null);
                alert.setContentText(pt.getPointDescript( (int) data.getXValue() ));
                alert.showAndWait();
            });
        }
    }//End makeIt

    public void progressCheck(int goal, int current)
    {
        float totalVal = ((float) current)/((float) goal);
        progressBar.setProgress(totalVal);
        progressIndicator.setProgress(totalVal);

        if(totalVal >= 1)
        {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("congrats.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                //stage.setTitle("G");
                stage.setScene(new Scene(root1));
                stage.show();
                Media sound = new Media(getClass().getResource("purpleCut.mp3").toString());
                MediaPlayer mediaPlayer = new MediaPlayer(sound);
                mediaPlayer.play();
                stage.setOnCloseRequest(e -> mediaPlayer.stop());

            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        PointToTable.graphBranch();
        currentName = GraphTable.display(PointToTable.pullFromGraphs());
        pt = new PointToTable(currentName);
        makeIt();

    }
}//End trackController
