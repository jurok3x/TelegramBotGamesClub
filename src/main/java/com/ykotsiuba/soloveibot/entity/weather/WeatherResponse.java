package com.ykotsiuba.soloveibot.entity.weather;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class WeatherResponse {
    
    private static final String CITY = "Івано-Франківськ";
    private Coordinates coord;
    private Weather[] weather;
    private WeatherMain main;
    private Wind wind;
    private Long dt;
    private LocalSystem sys;
    private Integer timezone;
    private Clouds clouds;
    private Integer id;
    private String name;
    private Integer cod;
    private Integer visibility;
    private String base;
    
    @Override
    public String toString() {
        return "WeatherResponse [weather=" + Arrays.toString(weather) + ", main=" + main + ", wind=" + wind + ", dt="
                + dt + ", clouds=" + clouds + "]";
    }
    public WeatherResponse() {}
    public WeatherResponse(Coordinates coord, Weather[] weather, WeatherMain main, Wind wind, Long dt,
            LocalSystem sys, Integer timezone, Clouds clouds, Integer id, String name, Integer cod, Integer visibility,
            String base) {
        this.coord = coord;
        this.weather = weather;
        this.main = main;
        this.wind = wind;
        this.dt = dt * 1000;
        this.sys = sys;
        this.timezone = timezone;
        this.clouds = clouds;
        this.id = id;
        this.name = name;
        this.cod = cod;
        this.visibility = visibility;
        this.base = base;
    }
    public Coordinates getCoord() {
        return coord;
    }
    public void setCoord(Coordinates coord) {
        this.coord = coord;
    }
    public Weather[] getWeather() {
        return weather;
    }
    public void setWeather(Weather[] weather) {
        this.weather = weather;
    }
    public WeatherMain getMain() {
        return main;
    }
    public void setMain(WeatherMain main) {
        this.main = main;
    }
    public Wind getWind() {
        return wind;
    }
    public void setWind(Wind wind) {
        this.wind = wind;
    }
    public Long getDt() {
        return dt;
    }
    public void setDt(Long dt) {
        this.dt = dt  * 1000;
    }
    public LocalSystem getSys() {
        return sys;
    }
    public void setSys(LocalSystem sys) {
        this.sys = sys;
    }
    public Integer getTimezone() {
        return timezone;
    }
    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }
    public Clouds getClouds() {
        return clouds;
    }
    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getCod() {
        return cod;
    }
    public void setCod(Integer cod) {
        this.cod = cod;
    }
    public Integer getVisibility() {
        return visibility;
    }
    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }
    public String getBase() {
        return base;
    }
    public void setBase(String base) {
        this.base = base;
    }
    
    public String sendReport() {
        StringBuilder report = new StringBuilder();
        getCityReport(report);
        getTemparatureReport(report);
        getWeatherConditionReport(report);
        getHumidityReport(report);
        getCloudsReport(report);
        getWindReport(report);
        getSunReport(report);
        return report.toString();
    }
    
    private void getCityReport(StringBuilder report) {
        Date date = new Date(dt);  
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
        report.append(String.format("Погода в місті %s на %s:%n", CITY, dateFormat.format(date)));
    }
    
    private void getTemparatureReport(StringBuilder report) {
        String degreeSign = new String(Character.toChars(0x00B0));
        report.append(String.format("Температура повітря%s : %.2f %sc%n", Emoji.THERMOMETER.getEmogi(),
                main.getTemp(), degreeSign));
    }
    
    private void getWeatherConditionReport(StringBuilder report) {
        report.append(String.format("Погодні умови: %s%s%n", weather[0].getDescription(),
                getWeatherEmoji(weather[0].getIcon())));
    }
    
    private void getHumidityReport(StringBuilder report) {
        report.append(String.format("Відносна вологість%s : %d%%%n", Emoji.HUMIDITY.getEmogi(),
                main.getHumidity()));
    }
    
    private void getCloudsReport(StringBuilder report) {
        report.append(String.format("Хмарність %s: %d%%%n", Emoji.BROKEN_CLOUDS.getEmogi(),
                clouds.getAll()));
    }
    
    private void getWindReport(StringBuilder report) {
        report.append(String.format("Швидкість вітру %s: %.2f м/с%n", Emoji.WIND.getEmogi(),
                wind.getSpeed()));
    }
    
    private void getSunReport(StringBuilder report) {
        Date sunrise = new Date(sys.getSunrise()); 
        Date sunset = new Date(sys.getSunset());
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss"); 
        report.append(String.format("Схід сонця%s: %s Захід%s: %s", Emoji.SUNRISE.getEmogi(),
                dateFormat.format(sunrise), Emoji.SUNSET.getEmogi(), dateFormat.format(sunset)));
    }
    
    private String getWeatherEmoji(String icon) {
        String emoji = null;
        switch (icon) {
        case "01d":
            emoji = Emoji.CLEAR_SKY.getEmogi();
            break;
        case "02d":
            emoji = Emoji.FEW_CLOUDS.getEmogi();
            break;
        case "03d":
            emoji = Emoji.SCATTERED_CLOUDS.getEmogi();
            break;
        case "04d":
            emoji = Emoji.BROKEN_CLOUDS.getEmogi();
            break;
        case "09d":
            emoji = Emoji.SHOWER_RAIN.getEmogi();
            break;
        case "10d":
            emoji = Emoji.RAIN.getEmogi();
            break;
        case "11d":
            emoji = Emoji.THUNDERSTORM.getEmogi();
            break;
        case "13d":
            emoji = Emoji.SNOW.getEmogi();
            break;
        case "50d":
            emoji = Emoji.MIST.getEmogi();
            break;
        default:
            emoji = Emoji.DEFAULT.getEmogi();
            break;
        }
        return emoji;
    }

}
