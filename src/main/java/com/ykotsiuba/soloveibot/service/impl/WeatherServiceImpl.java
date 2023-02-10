package com.ykotsiuba.soloveibot.service.impl;

import com.ykotsiuba.soloveibot.entity.Emoji;
import com.ykotsiuba.soloveibot.entity.weather.Clouds;
import com.ykotsiuba.soloveibot.entity.weather.LocalSystem;
import com.ykotsiuba.soloveibot.entity.weather.Weather;
import com.ykotsiuba.soloveibot.entity.weather.WeatherMain;
import com.ykotsiuba.soloveibot.entity.weather.OpenWeatherResponse;
import com.ykotsiuba.soloveibot.entity.weather.Wind;
import com.ykotsiuba.soloveibot.service.WeatherService;

import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class WeatherServiceImpl implements WeatherService {
    
    private static final String CITY = "Івано-Франківськ";
    private StringBuilder report;

    @Override
    public String sendReport(OpenWeatherResponse response) {
        report = new StringBuilder();
        getCityReport(response.getDt());
        getTemparatureReport(response.getMain());
        getWeatherConditionReport(response.getWeather()[0]);
        getHumidityReport(response.getMain());
        getCloudsReport(response.getClouds());
        getWindReport(response.getWind());
        getSunReport(response.getSys());
        return report.toString();
    }
    
    private void getCityReport(Long dayTime) {
        Date date = new Date(dayTime);  
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
        report.append(String.format("Погода в місті %s на %s:%n", CITY, dateFormat.format(date)));
    }
    
    private void getTemparatureReport(WeatherMain main) {
        String degreeSign = new String(Character.toChars(0x00B0));
        report.append(String.format("Температура повітря%s : %.2f %sc%n", Emoji.THERMOMETER.getEmogi(),
                main.getTemp(), degreeSign));
    }
    
    private void getWeatherConditionReport(Weather weather) {
        report.append(String.format("Погодні умови: %s%s%n", weather.getDescription(),
                getWeatherEmoji(weather.getIcon())));
    }
    
    private void getHumidityReport(WeatherMain main) {
        report.append(String.format("Відносна вологість%s : %d%%%n", Emoji.HUMIDITY.getEmogi(),
                main.getHumidity()));
    }
    
    private void getCloudsReport(Clouds clouds) {
        report.append(String.format("Хмарність %s: %d%%%n", Emoji.BROKEN_CLOUDS.getEmogi(),
                clouds.getAll()));
    }
    
    private void getWindReport(Wind wind) {
        report.append(String.format("Швидкість вітру %s: %.2f м/с%n", Emoji.WIND.getEmogi(),
                wind.getSpeed()));
    }
    
    private void getSunReport(LocalSystem system) {
        Date sunrise = new Date(system.getSunrise()); 
        Date sunset = new Date(system.getSunset());
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss"); 
        report.append(String.format("Схід сонця%s: %s%nЗахід сонця%s: %s", Emoji.SUNRISE.getEmogi(),
                dateFormat.format(sunrise), Emoji.SUNSET.getEmogi(), dateFormat.format(sunset)));
    }
    
    private String getWeatherEmoji(String icon) {
        if(icon.contains("01")) {
            return Emoji.CLEAR_SKY.getEmogi();
        }
        if (icon.contains("02")) {
            return Emoji.FEW_CLOUDS.getEmogi();
        }
        if (icon.contains("03")) {
            return Emoji.SCATTERED_CLOUDS.getEmogi();
        }
        if (icon.contains("04")) {
            return Emoji.BROKEN_CLOUDS.getEmogi();
        }
        if (icon.contains("09")) {
            return Emoji.SHOWER_RAIN.getEmogi();
        }
        if (icon.contains("10")) {
            return Emoji.RAIN.getEmogi();
        }
        if (icon.contains("11")) {
            return Emoji.THUNDERSTORM.getEmogi();
        }
        if (icon.contains("13")) {
            return Emoji.SNOW.getEmogi();
        }
        if (icon.contains("50")) {
            return Emoji.MIST.getEmogi();
        } else {
            return Emoji.DEFAULT.getEmogi();
        }
    }

}
