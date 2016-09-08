package com.maybe.baidu.util;

import com.maybe.baidu.pojo.*;

import java.util.ArrayList;

/**
 * Created by Maybe on 2016/8/29.
 * Maybe has infinite possibilities
 */
public class GeoUtils {
    private final static double earthRadius = 6370996.81;

    /**
     * Is point in rect boolean.
     *
     * @param point  the point
     * @param bounds the bounds
     * @return the boolean
     */
    public boolean isPointInRect(Point point, Bounds bounds) {
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
    public boolean isPointInCircle(Point point, Circle circle) {
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
    public boolean isPointOnPolyline(Point point, Polyline polyline) {
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
            if (point.getLongitude() >= Math.min(l.getLongitude(), j.getLongitude())
                    && point.getLongitude() <= Math.max(l.getLongitude(), j.getLongitude())
                    && point.getLatitude() >= Math.min(l.getLatitude(), j.getLatitude())
                    && point.getLatitude() <= Math.max(l.getLatitude(), j.getLatitude())) {
                double g = (l.getLongitude() - point.getLongitude()) * (j.getLatitude() - point.getLatitude()) - (j.getLongitude() - point.getLongitude()) * (l.getLatitude() - point.getLatitude());
                if (g < 2e-10 && g > -2e-10) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isPointInPolygon(Point o, Polygon l) {
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
    public double getDistance(Point pointStart, Point pointEnd) {
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
        //球体上弧线距离计算
        return earthRadius * Math.acos((Math.sin(i) * Math.sin(g) + Math.cos(i) * Math.cos(g) * Math.cos(e - f)));
    }

    public double getPolylineDistance(Polyline f) {
        if (f != null) {
            ArrayList<Point> l = f.getPath();
            if (l.size() < 2) {
                return 0;
            }
            double j = 0;
            for (int h = 0; h < l.size() - 1; h++) {
                Point k = l.get(h);
                Point g = l.get(h + 1);
                double e = this.getDistance(k, g);
                j += e;
            }
            return j;
        } else {
            return 0;
        }
    }

    public double getPolygonArea(Polygon t) {
        if (t == null) {
            return 0;
        }
        ArrayList<Point> R;

        R = t.getPath();

        if (R.size() < 3) {
            return 0;
        }
        double w = 0;
        double D = 0;
        double C = 0;
        double L = 0;
        double J = 0;
        double F = 0;
        double E = 0;
        double S = 0;
        double H = 0;
        double p = 0;
        double T = 0;
        double I = 0;
        double q = 0;
        double e = 0;
        double M = 0;
        double v = 0;
        double K = 0;
        double N = 0;
        double s = 0;
        double O = 0;
        double l = 0;
        double g = 0;
        double z = 0;
        double Q = 0;
        double G = 0;
        double j = 0;
        double A = 0;
        double o = 0;
        double m = 0;
        double y = 0;
        double x = 0;
        double h = 0;
        double k = 0;
        double f = 0;
        double n = earthRadius;
        int B = R.size();
        for (int P = 0; P < B; P++) {
            if (P == 0) {
                D = R.get(B - 1).getLongitude() * Math.PI / 180;
                C = R.get(B - 1).getLatitude() * Math.PI / 180;
                L = R.get(0).getLongitude() * Math.PI / 180;
                J = R.get(0).getLatitude() * Math.PI / 180;
                F = R.get(1).getLongitude() * Math.PI / 180;
                E = R.get(1).getLatitude() * Math.PI / 180;
            } else {
                if (P == B - 1) {
                    D = R.get(B - 2).getLongitude() * Math.PI / 180;
                    C = R.get(B - 2).getLatitude() * Math.PI / 180;
                    L = R.get(B - 1).getLongitude() * Math.PI / 180;
                    J = R.get(B - 1).getLatitude() * Math.PI / 180;
                    F = R.get(0).getLongitude() * Math.PI / 180;
                    E = R.get(0).getLatitude() * Math.PI / 180;
                } else {
                    D = R.get(P - 1).getLongitude() * Math.PI / 180;
                    C = R.get(P - 1).getLatitude() * Math.PI / 180;
                    L = R.get(P).getLongitude() * Math.PI / 180;
                    J = R.get(P).getLatitude() * Math.PI / 180;
                    F = R.get(P + 1).getLongitude() * Math.PI / 180;
                    E = R.get(P + 1).getLatitude() * Math.PI / 180;
                }
            }
            S = Math.cos(J) * Math.cos(L);
            H = Math.cos(J) * Math.sin(L);
            p = Math.sin(J);
            T = Math.cos(C) * Math.cos(D);
            I = Math.cos(C) * Math.sin(D);
            q = Math.sin(C);
            e = Math.cos(E) * Math.cos(F);
            M = Math.cos(E) * Math.sin(F);
            v = Math.sin(E);
            K = (S * S + H * H + p * p) / (S * T + H * I + p * q);
            N = (S * S + H * H + p * p) / (S * e + H * M + p * v);
            s = K * T - S;
            O = K * I - H;
            l = K * q - p;
            g = N * e - S;
            z = N * M - H;
            Q = N * v - p;
            m = (g * s + z * O + Q * l) / (Math.sqrt(g * g + z * z + Q * Q) * Math.sqrt(s * s + O * O + l * l));
            m = Math.acos(m);
            G = z * l - Q * O;
            j = 0 - (g * l - Q * s);
            A = g * O - z * s;
            if (S != 0) {
                o = G / S;
            } else {
                if (H != 0) {
                    o = j / H;
                } else {
                    o = A / p;
                }
            }
            if (o > 0) {
                y += m;
                k++;
            } else {
                x += m;
                h++;
            }
        }
        double u,
                r;
        u = y + (2 * Math.PI * h - x);
        r = (2 * Math.PI * k - y) + x;
        if (y > x) {
            if ((u - (B - 2) * Math.PI) < 1) {
                f = u;
            } else {
                f = r;
            }
        } else {
            if ((r - (B - 2) * Math.PI) < 1) {
                f = r;
            } else {
                f = u;
            }
        }
        w = (f - (B - 2) * Math.PI) * n * n;
        return w;
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
