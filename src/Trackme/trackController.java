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
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class trackController implements Initializable
{
    @FXML private ProgressBar progressBar;
    @FXML private ProgressIndicator progressIndicator;
    @FXML private MenuItem newGraph;
    //final float value = 10.0f;

    public TextField xIt, yIt,descIt;
    public LineChart<Number, Number> lineChart;
    public TableView<Points> tableTime;
    public TableColumn<Points,String> xCol,yCol,descCol;
    public Label lbl1;
    public XYChart.Series<Number, Number> series;
    public String currentName;
    public ObservableList<Points> obs = FXCollections.observableArrayList();
    public PointToTable pt;

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
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        PointToTable.graphBranch();
        PointToTable.createGraph("A");
        PointToTable.createGraph("B");
        PointToTable.createGraph("C");
        currentName = GraphTable.display(PointToTable.pullFromGraphs());
        pt = new PointToTable(currentName);

        progressCheck(7, 3);

        makeIt();

    }
}//End trackController
