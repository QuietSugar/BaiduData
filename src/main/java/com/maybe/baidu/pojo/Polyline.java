package com.maybe.baidu.pojo;

import java.util.ArrayList;

/**
 * Created by Maybe on 2016/8/30.
 * Maybe has infinite possibilities
 */
public class Polyline {
    private ArrayList<Point> Path;

    public ArrayList<Point> getPath() {
        return Path;
    }

    public void setPath(ArrayList<Point> path) {
        Path = path;
    }

    public Polyline(ArrayList<Point> path) {
        Path = path;
    }

    public Bounds getBounds() {
        double maxLatitude = this.Path.get(0).getLatitude();
        double minLatitude = this.Path.get(0).getLatitude();

        double maxLongitude = this.Path.get(0).getLongitude();
        double minLongitude = this.Path.get(0).getLongitude();
        for (Point point : this.Path) {
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
