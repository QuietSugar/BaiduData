package com.maybe.pojo;

/**
 * 相邻站点之间的距离以及坐公交所使用的时间（数据来自于百度地图）
 * Created by hadoop on 2016/9/14.
 * Maybe has infinite possibilities
 */
public class StationDT {
    private String lineName;
    private String startStation;
    private String endStation;
    private int distance;
    private int time;

    public StationDT() {
    }

    public StationDT(String startStation, int flag, String lineName, int distance, int time, String endStation) {
        this.startStation = startStation;
        this.lineName = lineName;
        this.distance = distance;
        this.time = time;
        this.endStation = endStation;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    @Override
    public String toString() {
        return "StationDT{" +
                "lineName='" + lineName + '\'' +
                ", startStation='" + startStation + '\'' +
                ", endStation='" + endStation + '\'' +
                ", distance=" + distance +
                ", time=" + time +
                '}';
    }
}
