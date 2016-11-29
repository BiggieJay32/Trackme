package Trackme;

import Trackme.NewGraphController;
import Trackme.OpenGraphController;
import Trackme.HomeController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.*;
import java.util.Scanner;
import static java.lang.System.out;

/**
 * Created by Clark on 10/17/2016.
 */


public class PointMain extends Application {

    OpenGraphController fileName = new OpenGraphController();
    String xVal, yVal, title, deleteData;
    //fileName.select();

    Stage window;
    VBox vBox = new VBox();
    Scene TableScene, GraphScene, scene;
    TableView<Point> table;
    TextField nameInput, xInput, yInput, remarkInput;
    ObservableList<Point> points = FXCollections.observableArrayList();
    BorderPane layout;


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        window = primaryStage;
        window.setTitle("Points Table");
        //getGraphNames();
        //System.out.println(OpenGraphController.file);

        //Menu bar
        Menu fileMenu = new Menu("File");

        MenuItem newFile = new MenuItem("New...");
        newFile.setOnAction(e -> {
            System.out.println("Create a new file...");
            NewGraphController.display("Trackme", "Create a new graph");
        });
        MenuItem openFile = new MenuItem("Graphs...");
        openFile.setOnAction(e -> {
            System.out.println("Choose a graph to open...");
            OpenGraphController G = new OpenGraphController();
            primaryStage.close();
            G.open("Trackme");
        });

        fileMenu.getItems().add(newFile);
        fileMenu.getItems().add(openFile);
        fileMenu.getItems().add(new MenuItem("Save..."));
        fileMenu.getItems().add(new SeparatorMenuItem());
        fileMenu.getItems().add(new MenuItem("Settings..."));
        fileMenu.getItems().add(new SeparatorMenuItem());
        fileMenu.getItems().add(new MenuItem("Exit..."));

        //Handle File Button

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                            //---Column--Setup---//
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Name columns
        TableColumn<Point, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("pointName"));
        //X columns
        TableColumn<Point, Double> xColumn = new TableColumn<>("X Value");
        xColumn.setMinWidth(100);
        xColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
        //Y columns
        TableColumn<Point, Double> yColumn = new TableColumn<>("Y Value");
        yColumn.setMinWidth(100);
        yColumn.setCellValueFactory(new PropertyValueFactory<>("y"));
        //Remark columns
        TableColumn<Point, String> remarksColumn = new TableColumn<>("Remarks");
        remarksColumn.setMinWidth(200);
        remarksColumn.setCellValueFactory(new PropertyValueFactory<>("Remarks"));

        //Name
        nameInput = new TextField();
        nameInput.setPromptText("Point Name");
        nameInput.setMinWidth(100);
        //X
        xInput = new TextField();
        xInput.setPromptText("X");
        //Y
        yInput = new TextField();
        yInput.setPromptText("Y");

        //Remarks
        remarkInput = new TextField();
        remarkInput.setPromptText("Remarks");
        remarkInput.setMinWidth(100);

        //Button
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addButtonClicked());
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteButtonClicked());
        Button inputButton = new Button("Input");
        Button graphButton = new Button("Graph");
        //graphButton.setOnAction(e -> window.setScene(GraphScene));
        graphButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    //Graph Visual
                    getGraphNames();
                    HBox hBox2 = new HBox();
                    hBox2.setSpacing(10);
                    hBox2.setPadding(new Insets(10, 10, 10, 10));
                    final NumberAxis xAxis = new NumberAxis();
                    final NumberAxis yAxis = new NumberAxis();
                    xAxis.setLabel(xVal);
                    yAxis.setLabel(yVal);
                    LineChart<Number, Number> lineChart = new LineChart<>(xAxis,yAxis);

                    lineChart.setTitle(title);
                    XYChart.Series series = new XYChart.Series(getXnY());
                    series.setName("First Data");
                    lineChart.getData().add(series);
                    hBox2.getChildren().addAll(lineChart, inputButton);
                    GraphScene = new Scene(hBox2, 600, 400);
                    window.setScene(GraphScene);
                    window.show();
                }
        });

        MenuBar menubar = new MenuBar();
        menubar.getMenus().addAll(fileMenu);
        layout = new BorderPane();
        layout.setTop(menubar);

        //Visual 1 Table Track
        HBox hBox = new HBox();
        //hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(nameInput, xInput, yInput, remarkInput, addButton, deleteButton, graphButton);
        table = new TableView<>();
        table.setItems(getPoints());
        table.getColumns().addAll(nameColumn, xColumn, yColumn, remarksColumn);
        TableScene = new Scene(hBox, 670, 400);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        vBox.getChildren().addAll(layout, table, hBox);
        Scene scene = new Scene(vBox);
        inputButton.setOnAction(e -> window.setScene(scene));
        window.setScene(scene);
        window.show();
    }// end of Start

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //Add Button
    public void addButtonClicked()
    {
        String name;
        String remarks;
        String graphTitle;
        Double xV, yV;
        Point point = new Point();
        point.setPointName(nameInput.getText());
        name = nameInput.getText();
        point.setX(Double.parseDouble(xInput.getText()));
        xV = Double.parseDouble(xInput.getText());
        point.setY(Double.parseDouble(yInput.getText()));
        yV = Double.parseDouble(yInput.getText());
        point.setRemarks(remarkInput.getText());
        remarks = remarkInput.getText();
        table.getItems().add(point);
        TextWrite(name, xV, yV, remarks);
        nameInput.clear();
        xInput.clear();
        yInput.clear();
        remarkInput.clear();

        /*//add tracker to Quick Access bar on homepage
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("homepage.fxml"));
        //AnchorPane frame = fxmlLoader.load();
        HomeController c = (HomeController) fxmlLoader.getController();
        c.quick.getItems().add(new MenuItem("TEST"));*/
        //HomeController.quick.getItems().add(new MenuItem("TEST"));
    }

    //Delete Button
    public void deleteButtonClicked()
    {

        ObservableList<Point> pointSelected, allPoints;
        allPoints = table.getItems();
        pointSelected = table.getSelectionModel().getSelectedItems();
        System.out.print(fileName.fileN);
        pointSelected.forEach(allPoints::remove);
/*
        TablePosition pos = table.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        Point item = table.getItems().get(row);
        TableColumn col = pos.getTableColumn();
        String data = (String) col.getCellObservableValue(item).getValue();
        System.out.println(data);
        deleteData = data;
        System.out.println(deleteData);
        //ObservableList<XYChart.Data> names = FXCollections.observableArrayList();
        //String xVal, yVal, title;
        */
       /* try{
            Scanner file = new Scanner(new File("[" + fileName.fileN + "].txt"));
            FileReader fr = new FileReader("[" + fileName.fileN + "].txt");
            BufferedReader br = new BufferedReader(fr);
            int count = 0;
            while(file.hasNextLine()){
                count++;
                file.nextLine();
            }
            while(count > 0)
            {
                String str= br.readLine();
                String parts[] = str.split("_");

                //title = parts[0];
                //xVal = parts[1];
                //yVal = parts[2];
                System.out.println(title + xVal + yVal);
                //points.add(new XYChart.Data(xValue, yValue));
                if(title == fileName.fileN)
                    break;
                count = count -1;
            }
            fr.close();
        }catch(IOException e)
        {
            out.println("File Not Found");
        }*/
        /*
        try{
            FileReader fr = new FileReader("test1.txt");
            BufferedReader br = new BufferedReader(fr);

        }catch(IOException e){
            out.print("Error");
        }
        */
    }//end of DeleteButton

    //Write to file function
    public void TextWrite(String name, double Xv, double Yv, String remarksss){
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;
        try{
            fw = new FileWriter("[" + fileName.fileN + "].txt", true);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
            pw.print(name+"_");
            pw.print(Xv+"_");
            pw.print(Yv+"_");
            pw.print(remarksss+"_");
            pw.println();
            pw.close();
        }catch(IOException e){
        out.println("Error");
        }
    }//end of TextWrite

    //Get all of the points from the text file and make it a Point(name,x,y,remarks) --> Table.
    public ObservableList<Point> getPoints()
    {

        String name, remarks;
        Double xVal, yVal;
        try{
            Scanner file = new Scanner(new File("[" + fileName.fileN + "].txt"));
            FileReader fr = new FileReader("[" + fileName.fileN + "].txt");
            BufferedReader br = new BufferedReader(fr);
            int count = 0;
            while (file.hasNextLine()) {
                count++;
                file.nextLine();
            }
            while(count > 0) {
                String str= br.readLine();
                String parts[] = str.split("_");
                name = parts[0];
                xVal = Double.parseDouble(parts[1]);
                yVal = Double.parseDouble(parts[2]);
                remarks = parts[3];
                points.add(new Point(name, xVal, yVal, remarks));
                count = count-1;
            }
            fr.close();
        }catch(IOException e){
                out.println("File Not Found");
        }

        return points;
    }//end of getPoints

    //Get all of the x and y value (Points) -- (return method for X & Y value) --> Graph Points.
    public ObservableList<XYChart.Data> getXnY(){
        ObservableList<XYChart.Data> points = FXCollections.observableArrayList();
        Double xValue, yValue;
        try{
            Scanner file = new Scanner(new File("[" + fileName.fileN + "].txt"));
            FileReader fr = new FileReader("[" + fileName.fileN + "].txt");
            BufferedReader br = new BufferedReader(fr);
            int count = 0;
            while(file.hasNextLine()){
                count++;
                file.nextLine();
            }
            while(count > 0)
            {
                String str= br.readLine();
                String parts[] = str.split("_");
                xValue = Double.parseDouble(parts[1]);
                yValue = Double.parseDouble(parts[2]);
                points.add(new XYChart.Data(xValue, yValue));
                count = count -1;
            }
            fr.close();
        }catch(IOException e)
        {
            out.println("File Not Found");
        }
        return points;
    }//end of XnY

    public void getGraphNames(){
        //ObservableList<XYChart.Data> names = FXCollections.observableArrayList();
        //String xVal, yVal, title;
        try{
            Scanner file = new Scanner(new File("GraphInfo.txt"));
            FileReader fr = new FileReader("GraphInfo.txt");
            BufferedReader br = new BufferedReader(fr);
            int count = 0;
            while(file.hasNextLine()){
                count++;
                file.nextLine();
            }
            while(count > 0)
            {
                String str= br.readLine();
                String parts[] = str.split("_");
                title = parts[0];
                xVal = parts[1];
                yVal = parts[2];
                System.out.println(title + xVal + yVal);
                //points.add(new XYChart.Data(xValue, yValue));
                if(title == fileName.fileN)
                    break;
                count = count -1;
            }
            fr.close();
        }catch(IOException e)
        {
            out.println("File Not Found");
        }
        //return points;
    }//end of XnY



}
