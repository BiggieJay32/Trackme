package Trackme;

/**
 * Created by Bob on 11/13/2016.
 */
public class Points {
    private int x, y;
    private String desc;

    Points(int x, int y, String d){
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
