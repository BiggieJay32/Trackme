package Trackme;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.sql.*;
import java.util.ArrayList;

/*This class provides the necessary communication between our program and the SQLite Database*/

public class PointToTable {

    private String gName;

    public PointToTable(String g) {
        Connection c;
        gName = g;
        try {
            c = this.connect();
            String dec = "CREATE TABLE IF NOT EXISTS " + gName + " ("
                    + "	id INTEGER PRIMARY KEY, "
                    + "	Xpoint INTEGER NOT NULL, " +
                    " Ypoint INTEGER NOT NULL," +
                    " PointDesc TEXT);";

            c.createStatement().execute(dec);
            c.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Successfully activated.");
    }


    private static Connection connect() {
        Connection c = null;
        try {
            c = DriverManager.getConnection("jdbc:sqlite:point.db");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return c;
    }


    public static void graphBranch() {
        String dec1 = "CREATE TABLE IF NOT EXISTS GRAPHS ("
                + "	id INTEGER PRIMARY KEY, "
                + "GraphName TEXT UNIQUE NOT NULL, xName Text NOT NULL, yName Text NOT NULL, goal INTEGER);";
        try {
            Connection c = connect();
            c.createStatement().execute(dec1);
            c.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


    public static boolean createGraph(String gName, String xName, String yName, int goal) {
        boolean b = true;
        String dec2 = "INSERT INTO GRAPHS (GraphName, xName, yName, goal) VALUES (?, ?, ?, ?);";
        try {
            Connection c = connect();
            PreparedStatement p = c.prepareStatement(dec2);
            {
                p.setString(1, gName);
                p.setString(2, xName);
                p.setString(3, yName);
                p.setInt( 4,  goal);
                p.executeUpdate();
            }
            c.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            b = false;
        }
        return b;
    }


    public XYChart.Series<Number, Number> dumpContent() {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        try {
            Connection c = connect();
            ResultSet rs = c.createStatement().executeQuery("SELECT Xpoint, Ypoint FROM " + gName);

            while (rs.next()) {
                int x = rs.getInt(1);
                int y = rs.getInt(2);
                series.getData().add(new XYChart.Data<>(x, y));
            }

            c.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Successful");
        return series;
    }


    public void insertPoint(int Xpoint, int Ypoint, String pointDesc) {
        String sql = "INSERT INTO " + gName + " (Xpoint, Ypoint, pointDesc) " + "VALUES (?,?,?)";
        try {
            Connection c = connect();
            PreparedStatement p = c.prepareStatement(sql);
            {
                p.setInt(1, Xpoint);
                p.setInt(2, Ypoint);
                p.setString(3, pointDesc);
                p.executeUpdate();
            }
            c.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void deleteAll(String g) {
        String sql = "DELETE from GRAPHS where GraphName = ? ;";
        String sql2 = "DROP TABLE " + g + ";";
        try {
            Connection c = connect();
            PreparedStatement p = c.prepareStatement(sql);
            {
                p.setString(1, g);
                p.executeUpdate();
            }
            c.createStatement().execute(sql2);
            c.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void deletePoint(int x) {
        String sql = "DELETE from " + gName + " where Xpoint = ? ;";
        try {
            Connection c = connect();
            PreparedStatement p = c.prepareStatement(sql);
            {
                p.setInt(1, x);
                p.executeUpdate();
            }
            c.commit();
            c.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public ObservableList<Point> tableIt() {
        ArrayList<Point> pointses = new ArrayList<>();
        try {
            Connection c = connect();
            ResultSet rs = (c.createStatement().executeQuery("SELECT Xpoint, Ypoint, pointDesc FROM " + gName));


            while (rs.next()) {
                pointses.add(new Point(rs.getInt(1), rs.getInt(2), rs.getString(3)));
            }
            c.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Successful");
        return FXCollections.observableArrayList(pointses);
    }


    public static ObservableList<String> pullFromGraphs() {
        ArrayList<String> stringa = new ArrayList<>();

        try {
            Connection c = connect();
            ResultSet rs = c.createStatement().executeQuery("SELECT GraphName FROM GRAPHS");
            while (rs.next()) {
                String s = rs.getString(1);
                stringa.add(s);
            }
            c.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Successful");
        ObservableList<String> obs = FXCollections.observableArrayList(stringa);
        return obs;
    }


    public String getPointDescript(int x) {

        String sql = "SELECT PointDesc FROM " + gName + " WHERE Xpoint = ?";
        String s = "";
        ResultSet resultSet;
        try {
            Connection c = connect();
            PreparedStatement p = c.prepareStatement(sql);
            {
                p.setInt(1, x);
                resultSet = p.executeQuery();
                s = resultSet.getString(1);
            }
            c.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return s;
    }

    public String getXName() {
        String sql = "SELECT xName FROM GRAPHS where graphName = ? ";
        String s = "";
        ResultSet resultSet;
        try {
            Connection c = connect();
            PreparedStatement p = c.prepareStatement(sql);
            {
                p.setString(1, gName);
                resultSet = p.executeQuery();
                s = resultSet.getString(1);
            }
            c.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return s;
    }

    public String getYName() {
        String sql = "SELECT yName FROM GRAPHS where graphName = ? ";
        String s = "";
        ResultSet resultSet;
        try {
            Connection c = connect();
            PreparedStatement p = c.prepareStatement(sql);
            {
                p.setString(1, gName);
                resultSet = p.executeQuery();
                s = resultSet.getString(1);
            }
            c.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return s;
    }


    public int getGoal() {
        String sql = "SELECT goal FROM GRAPHS where graphName = ? ";
        int i = 3213213;
        ResultSet resultSet;
        try {
            Connection c = connect();
            PreparedStatement p = c.prepareStatement(sql);
            {
                p.setString(1, gName);
                resultSet = p.executeQuery();
                i = resultSet.getInt(1);
            }
            c.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return i;
    }


    public int getCurrent() {
        String sql = "SELECT MAX(Ypoint) FROM " + gName;
        int i = 0;
        ResultSet resultSet;
        try {
            Connection c = connect();
            resultSet = c.createStatement().executeQuery(sql);
             i = resultSet.getInt(1);
            c.close();

            } catch(SQLException e){
                System.out.println(e.getMessage());
            }
        return i;
        }
    }
