package com.maybe.baidu.pojo;

import java.util.ArrayList;

/**
 * 折线  与多边形非常相似
 *
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
        double maxLatitude = this.path.get(0).getLatitude();
        double minLatitude = this.path.get(0).getLatitude();
        double maxLongitude = this.path.get(0).getLongitude();
        double minLongitude = this.path.get(0).getLongitude();
        for (Point point : this.path) {
            if (point.getLatitude() > maxLatitude) {
                maxLatitude = point.getLatitude();
            }
            if (point.getLatitude() < minLatitude) {
                minLatitude = point.getLatitude();
            }
            if (point.getLongitude() > maxLongitude) {
                maxLongitude = point.getLongitude();
            }
            if (point.getLongitude() < minLongitude) {
                minLongitude = point.getLongitude();
            }
        }
        return new Bounds(new Point(minLatitude, minLongitude), new Point(maxLatitude, maxLongitude));
    }
}
