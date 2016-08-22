package com.maybe.pojo;

/**
 * Created by Maybe on 2016/6/27
 * Maybe has infinite possibilities
 */
public class Station {
    private String name;
    private LngAndLat position;

    private String _uid;

    public Station() {
    }

    public LngAndLat getPosition() {
        return position;
    }

    public void setPosition(LngAndLat position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String get_uid() {
        return _uid;
    }

    public void set_uid(String _uid) {
        this._uid = _uid;
    }

    @Override
    public String toString() {
        return "Station{" +
                "name='" + name + '\'' +
                ", position=" + position +
                ", _uid='" + _uid + '\'' +
                '}';
    }

    public Station(String _uid, String name, LngAndLat position) {
        this._uid = _uid;
        this.name = name;
        this.position = position;
    }
}
