package com.ykotsiuba.soloveibot.entity.weather;

public class Wind {
    
    @Override
    public String toString() {
        return "Wind [speed=" + speed + ", deg=" + deg + ", gust=" + gust + "]";
    }
    private Double speed;
    private Double deg;
    private Double gust;
    public Wind(Double speed, Double deg, Double gust) {
        super();
        this.speed = speed;
        this.deg = deg;
        this.gust = gust;
    }
    public Double getSpeed() {
        return speed;
    }
    public void setSpeed(Double speed) {
        this.speed = speed;
    }
    public Double getDeg() {
        return deg;
    }
    public void setDeg(Double deg) {
        this.deg = deg;
    }
    public Double getGust() {
        return gust;
    }
    public void setGust(Double gust) {
        this.gust = gust;
    }
    public Wind() {
       
    }

}
