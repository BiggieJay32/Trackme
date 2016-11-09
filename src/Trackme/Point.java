package Trackme;

/**
 * Created by Clark on 10/17/2016.
 */
public class Point {
    private String pointName;
    private double x;
    private double y;
    private String Remarks;

    public Point()
    {
        this.pointName = "";
        this.x = 0;
        this.y = 0;
    }

    public Point(String name, double xValue, double yValue, String Remarks) {
        this.pointName = name;
        this.x = xValue;
        this.y = yValue;
        this.Remarks = Remarks;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }
}
