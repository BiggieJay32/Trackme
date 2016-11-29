package Trackme;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Bob on 11/13/2016.
 */
public class PointToTable {

    private String gName;



    public PointToTable(String g){
        Connection c;
        gName = g;

        try{
            c = this.connect();
            String dec = "CREATE TABLE IF NOT EXISTS " + gName + " ("
                    + "	id INTEGER PRIMARY KEY, "
                    + "	Xpoint INTEGER NOT NULL, " +
                    " Ypoint INTEGER NOT NULL," +
                    " PointDesc TEXT);";

            c.createStatement().execute(dec);
        }

        catch(SQLException e){
            System.out.println(e.getMessage());
        }

        System.out.println("Successfully activated.");
    }




    private static Connection connect() {
        Connection c = null;
        try{
            c = DriverManager.getConnection("jdbc:sqlite:points.db");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return c;
    }



    public static void graphBranch(){
        String dec1 = "CREATE TABLE IF NOT EXISTS GRAPHS ("
                + "	id INTEGER PRIMARY KEY, "
                + "GraphName TEXT UNIQUE NOT NULL);";
        try{
            Connection c = connect();
            c.createStatement().execute(dec1);
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }



    public static boolean createGraph(String s){
        boolean b = true;
        String dec2 = "INSERT INTO GRAPHS (GraphName) VALUES (?);";
        try{
            Connection c = connect();
            PreparedStatement p = c.prepareStatement(dec2);{
                p.setString(1,s);
                p.executeUpdate();
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            b = false;
        }
        return b;
    }



    public XYChart.Series<Number, Number>  dumpContent(){
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        try{
            ResultSet rs = connect().createStatement().executeQuery("SELECT Xpoint, Ypoint FROM " + gName);

            while(rs.next()){
                int x = rs.getInt(1);
                int y = rs.getInt(2);
                series.getData().add(new XYChart.Data<>( x, y));
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Successful");
        return series;
    }


    public void insertPoint(int Xpoint, int Ypoint, String pointDesc){
        String sql = "INSERT INTO " + gName + " (Xpoint, Ypoint, pointDesc) "  + "VALUES (?,?,?)";
        try{
            Connection c = connect();
            PreparedStatement p = c.prepareStatement(sql);{
                p.setInt(1, Xpoint);
                p.setInt(2, Ypoint);
                p.setString(3,pointDesc);
                p.executeUpdate();
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }


    public static void deleteAll(String g){
        String sql = "DELETE from GRAPHS where GraphName = ? ;";
        String sql2= "DROP TABLE " + g +";";
        try{
            Connection c = connect();
            PreparedStatement p = c.prepareStatement(sql);{
                p.setString(1, g);
                p.executeUpdate();
            }
            c.createStatement().execute(sql2);
        }

        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    public void deletePoint(int x){
        String sql = "DELETE from "+ gName +" where Xpoint = ? ;";
        try{
            Connection c = connect();
            PreparedStatement p = c.prepareStatement(sql);{
                p.setInt(1, x);
                p.executeUpdate();
            }
            c.commit();
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }




    public  ObservableList<Points> tableIt(){
        ArrayList<Points> pointses = new ArrayList<>();

        try{
            ResultSet rs = connect().createStatement().executeQuery("SELECT Xpoint, Ypoint, pointDesc FROM "+ gName);

            while(rs.next()){
                pointses.add(new Points(rs.getInt(1),rs.getInt(2),rs.getString(3)));
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Successful");
        return  FXCollections.observableArrayList(pointses);
    }




    public static ObservableList<String> pullFromGraphs(){
        ArrayList<String> stringa = new ArrayList<>();

        try{
            ResultSet rs = connect().createStatement().executeQuery("SELECT GraphName FROM GRAPHS");
            while(rs.next()){
                String s = rs.getString(1);
                stringa.add(s);
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Successful");
        ObservableList<String> obs = FXCollections.observableArrayList(stringa);
        return obs;
    }



    public String getPointDescript(int x){

        String sql = "SELECT PointDesc FROM " + gName + " WHERE Xpoint = ?";
        String s = "";
        ResultSet resultSet;
        try{
            Connection c = connect();
            PreparedStatement p = c.prepareStatement(sql);{
                p.setInt(1, x);
               resultSet =  p.executeQuery();
               s = resultSet.getString(1);
            };

        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return s;
    }

}
