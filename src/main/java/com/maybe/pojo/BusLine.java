package com.maybe.pojo;

import java.util.List;

/**
 * Created by Maybe on 2016/6/27
 * Maybe has infinite possibilities
 */
public class BusLine {
    private String company;

    private String endTime;

    private String line_name;

    private String startTime;

    private List<LngAndLat> line;

    private List<Station> stations;


    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLine_name() {
        return line_name;
    }

    public void setLine_name(String line_name) {
        this.line_name = line_name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public List<LngAndLat> getLine() {
        return line;
    }

    public void setLine(List<LngAndLat> line) {
        this.line = line;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }


    @Override
    public String toString() {
        return "BusLine{" +
                "company='" + company + '\'' +
                ", endTime='" + endTime + '\'' +
                ", line_name='" + line_name + '\'' +
                ", startTime='" + startTime + '\'' +
                ", line=" + line +
                ", stations=" + stations.toString() +
                '}';
    }
}
