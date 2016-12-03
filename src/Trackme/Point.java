package Trackme;


/* This class provides an easy way for us to manage points within our graphs.
* Each point contains an x and y axis as well as a description.*/


public class Point {
    private int x, y;
    private String desc;

    Point(int x, int y, String d){
        this.x = x;
        this.y = y;
        this.desc = d;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
