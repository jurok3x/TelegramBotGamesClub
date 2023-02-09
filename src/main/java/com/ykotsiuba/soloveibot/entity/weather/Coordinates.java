package com.ykotsiuba.soloveibot.entity.weather;

public class Coordinates {
    
    private Double lon;
    private Double lat;
    public Coordinates() {
    }
    public Coordinates(Double lon, Double lat) {
        super();
        this.lon = lon;
        this.lat = lat;
    }
    public Double getLon() {
        return lon;
    }
    public void setLon(Double lon) {
        this.lon = lon;
    }
    public Double getLat() {
        return lat;
    }
    public void setLat(Double lat) {
        this.lat = lat;
    }

}
