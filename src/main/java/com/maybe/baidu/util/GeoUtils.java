package com.maybe.baidu.util;

import com.maybe.baidu.pojo.Circle;
import com.maybe.baidu.pojo.Point;
import com.maybe.baidu.pojo.Polyline;
import com.maybe.baidu.pojo.Bounds;

/**
 * Created by Maybe on 2016/8/29.
 * Maybe has infinite possibilities
 */
public class GeoUtils {
    private double a = 6370996.81;

    /**
     * Is point in rect boolean.
     *
     * @param point     the point
     * @param bounds the bounds
     * @return the boolean
     */
    boolean isPointInRect(Point point, Bounds bounds) {
        if ((point == null) || (bounds == null)) {
            return false;
        }
        Point southWest = bounds.getSouthWest();
        Point northEast = bounds.getNorthEast();
        return (point.getLongitude() >= southWest.getLongitude()
                && point.getLongitude() <= northEast.getLongitude()
                && point.getLatitude() >= southWest.getLatitude()
                && point.getLatitude() <= northEast.getLatitude());

    }


    boolean isPointInCircle(Point point, Circle circle) {
        if ((point == null) || (circle == null)) {
            return false;
        }

        Point center = circle.getCenter();
        double radius = circle.getRadius();
        double f = getDistance(point, center);
        if (f <= radius) {
            return true;
        } else {
            return false;
        }
    }
    boolean  isPointOnPolyline (Point point, Polyline polyline) {
//        if ((point == null) || (polyline == null)) {
//            return false;
//        }
//        Bounds bounds = polyline.getBounds();
//        if (!this.isPointInRect(point, bounds)) {
//            return false
//        }
//        var m = h.getPath();
//        for (var k = 0; k < m.length - 1; k++) {
//            var l = m[k];
//            var j = m[k + 1];
//            if (f.lng >= Math.min(l.lng, j.lng) && f.lng <= Math.max(l.lng, j.lng) && f.lat >= Math.min(l.lat, j.lat) && f.lat <= Math.max(l.lat, j.lat)) {
//                var g = (l.lng - f.lng) * (j.lat - f.lat) - (j.lng - f.lng) * (l.lat - f.lat);
//                if (g < 2e-10 && g > -2e-10) {
//                    return true
//                }
//            }
//        }
        return false;
    }


    double getDistance(Point pointStart, Point pointEnd) {
        if ((pointStart == null) || (pointEnd == null)) {
            return -1;
        }
        pointStart.setLongitude(c(pointStart.getLongitude(), -180, 180));
        pointStart.setLatitude(d(pointStart.getLatitude(), -74, 74));
        pointEnd.setLongitude(c(pointEnd.getLongitude(), -180, 180));
        pointEnd.setLatitude(d(pointEnd.getLatitude(), -74, 74));
        double f, e, i, g;
        f = degreeToRad(pointStart.getLongitude());
        i = degreeToRad(pointStart.getLatitude());
        e = degreeToRad(pointEnd.getLongitude());
        g = degreeToRad(pointEnd.getLatitude());
        return a * Math.acos((Math.sin(i) * Math.sin(g) + Math.cos(i) * Math.cos(g) * Math.cos(e - f)));
    }

    private double c(double g, double f, double e) {
        while (g > e) {
            g -= e - f;
        }
        while (g < f) {
            g += e - f;
        }
        return g;
    }

    private double d(double g, double f, double e) {
//        if (f != null) {
        g = Math.max(g, f);
//        }
//        if (e != null) {
        g = Math.min(g, e);
//        }
        return g;
    }


    private double degreeToRad(double e) {
        return Math.PI * e / 180;
    }

}
