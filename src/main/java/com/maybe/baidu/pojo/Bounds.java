package com.maybe.baidu.pojo;

/**
 * 矩形
 * <p/>
 * Created by Maybe on 2016/8/29.
 * Maybe has infinite possibilities
 */
public class Bounds {

    //sw表示矩形区域的西南角，参数ne表示矩形区域的东北角。
    private Point southWest;
    private Point northEast;

    public Point getSouthWest() {
        return southWest;
    }

    public void setSouthWest(Point southWest) {
        this.southWest = southWest;
    }

    public Point getNorthEast() {
        return northEast;
    }

    public void setNorthEast(Point northEast) {
        this.northEast = northEast;
    }

    public Bounds() {
    }

    public Bounds(Point southWest, Point northEast) {
        this.southWest = southWest;
        this.northEast = northEast;
    }
}
