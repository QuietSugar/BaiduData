package com.maybe.baidu.pojo;

/**
 * 点
 *
 * Created by Maybe on 2016/8/29.
 * Maybe has infinite possibilities
 */
public class Point {
    /**
     * 经度
     */
    private double latitude;
    /**
     * 纬度
     */
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Point() {
    }

    public Point(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
