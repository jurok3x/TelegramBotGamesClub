package com.ykotsiuba.soloveibot.entity.weather;

public class LocalSystem {

    private String country;
    private Long sunrise;
    private Long sunset;
    public LocalSystem() {
    }
    public LocalSystem(String country, Long sunrise, Long sunset) {
        super();
        this.country = country;
        this.sunrise = sunrise * 1000;
        this.sunset = sunset * 1000;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public Long getSunrise() {
        return sunrise;
    }
    public void setSunrise(Long sunrise) {
        this.sunrise = sunrise * 1000;
    }
    public Long getSunset() {
        return sunset;
    }
    public void setSunset(Long sunset) {
        this.sunset = sunset * 1000;
    }
    
}
