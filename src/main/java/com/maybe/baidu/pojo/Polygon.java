package com.maybe.baidu.pojo;

import java.util.ArrayList;

/**
 * 多边形
 * <p/>
 * Created by Maybe on 2016/9/5.
 * Maybe has infinite possibilities
 */
public class Polygon {
    private ArrayList<Point> path;

    public ArrayList<Point> getPath() {
        return path;
    }

    public void setPath(ArrayList<Point> path) {
        this.path = path;
    }

    public Polygon() {
    }

    public Polygon(ArrayList<Point> path) {
        this.path = path;
    }

    public Bounds getBounds() {
        return new Bounds(this.path);
    }
}
