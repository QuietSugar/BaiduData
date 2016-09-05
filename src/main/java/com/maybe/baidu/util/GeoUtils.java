package com.maybe.baidu.util;

import com.maybe.baidu.pojo.*;

import java.util.ArrayList;

/**
 * Created by Maybe on 2016/8/29.
 * Maybe has infinite possibilities
 */
public class GeoUtils {
    private double a = 6370996.81;

    /**
     * Is point in rect boolean.
     *
     * @param point  the point
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


    /**
     * Is point in circle boolean.
     *
     * @param point  the point
     * @param circle the circle
     * @return the boolean
     */
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

    /**
     * Is point on polyline boolean.
     * <p/>
     * TODO: 2016/9/5  首个点与末点的线未进行判断
     *
     * @param point    the point
     * @param polyline the polyline
     * @return the boolean
     */
    boolean isPointOnPolyline(Point point, Polyline polyline) {
        if ((point == null) || (polyline == null)) {
            return false;
        }
        Bounds bounds = polyline.getBounds();
        if (!this.isPointInRect(point, bounds)) {
            return false;
        }
        ArrayList<Point> path = polyline.getPath();
        for (int k = 0; k < path.size() - 1; k++) {
            Point l = path.get(k);
            Point j = path.get(k + 1);
            if (point.getLongitude() >= Math.min(l.getLongitude(), j.getLongitude()) && point.getLongitude() <= Math.max(l.getLongitude(), j.getLongitude()) && point.getLatitude() >= Math.min(l.getLatitude(), j.getLatitude()) && point.getLatitude() <= Math.max(l.getLatitude(), j.getLatitude())) {
                double g = (l.getLongitude() - point.getLongitude()) * (j.getLatitude() - point.getLatitude()) - (j.getLongitude() - point.getLongitude()) * (l.getLatitude() - point.getLatitude());
                if (g < 2e-10 && g > -2e-10) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean isPointInPolygon(Point o, Polygon l) {
        if ((o == null) || (l == null)) {
            return false;
        }
        Bounds k = l.getBounds();
        if (!this.isPointInRect(o, k)) {
            return false;
        }
        ArrayList<Point> t = l.getPath();
        int h = t.size();
        int j = 0;
        double g = 2e-10;
        Point s, q;
        Point e = o;
        s = t.get(0);
        for (int f = 1; f <= h; ++f) {
            if (e.equals(s)) {
                return true;
            }
            q = t.get(f % h);
            if (e.getLatitude() < Math.min(s.getLatitude(), q.getLatitude()) || e.getLatitude() > Math.max(s.getLatitude(), q.getLatitude())) {
                s = q;
                continue;
            }
            if (e.getLatitude() > Math.min(s.getLatitude(), q.getLatitude()) && e.getLatitude() < Math.max(s.getLatitude(), q.getLatitude())) {
                if (e.getLongitude() <= Math.max(s.getLongitude(), q.getLongitude())) {
                    if (s.getLatitude() == q.getLatitude() && e.getLongitude() >= Math.min(s.getLongitude(), q.getLongitude())) {
                        return true;
                    }
                    if (s.getLongitude() == q.getLongitude()) {
                        if (s.getLongitude() == e.getLongitude()) {
                            return true;
                        } else {
                            ++j;
                        }
                    } else {
                        double r = (e.getLatitude() - s.getLatitude()) * (q.getLongitude() - s.getLongitude()) / (q.getLatitude() - s.getLatitude()) + s.getLongitude();
                        if (Math.abs(e.getLongitude() - r) < g) {
                            return true;
                        }
                        if (e.getLongitude() < r) {
                            ++j;
                        }
                    }
                }
            } else {
                if (e.getLatitude() == q.getLatitude() && e.getLongitude() <= q.getLongitude()) {
                    Point m = t.get((f + 1) % h);
                    if (e.getLatitude() >= Math.min(s.getLatitude(), m.getLatitude()) && e.getLatitude() <= Math.max(s.getLatitude(), m.getLatitude())) {
                        ++j;
                    } else {
                        j += 2;
                    }
                }
            }
            s = q;
        }
        return j % 2 != 0;
    }


    /**
     * Gets distance.
     *
     * @param pointStart the point start
     * @param pointEnd   the point end
     * @return the distance
     */
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
