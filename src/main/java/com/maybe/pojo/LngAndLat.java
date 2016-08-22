package com.maybe.pojo;

/**
 * Created by Maybe on 2016/6/27
 * Maybe has infinite possibilities
 */
public class LngAndLat {
    private Double lng;
    private Double lat;

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "LngAndLat{" +
                "lng=" + lng +
                ", lat=" + lat +
                '}';
    }

    public LngAndLat() {
    }

    public LngAndLat(Double lng, Double lat) {
        this.lng = lng;
        this.lat = lat;
    }
}
