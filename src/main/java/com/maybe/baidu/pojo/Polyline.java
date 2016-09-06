package com.maybe.baidu.pojo;

import java.util.ArrayList;

/**
 * 折线  与多边形非常相似
 * <p/>
 * Created by Maybe on 2016/8/30.
 * Maybe has infinite possibilities
 */
public class Polyline {
    private ArrayList<Point> path;

    public ArrayList<Point> getPath() {
        return path;
    }

    public void setPath(ArrayList<Point> path) {
        this.path = path;
    }

    public Polyline() {
    }

    public Polyline(ArrayList<Point> path) {
        this.path = path;
    }

    public Bounds getBounds() {
        return new Bounds(this.path);
    }
}
