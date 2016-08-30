package com.maybe.baidu.pojo;

/**
 * Created by Maybe on 2016/8/30.
 * Maybe has infinite possibilities
 */
public class Circle {
    private Point center;
    private double radius;

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Circle() {
    }

    public Circle(double radius, Point center) {
        this.radius = radius;
        this.center = center;
    }
}
