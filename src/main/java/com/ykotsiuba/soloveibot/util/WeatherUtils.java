package com.ykotsiuba.soloveibot.util;

import com.ykotsiuba.soloveibot.entity.Emoji;
import com.ykotsiuba.soloveibot.entity.dto.WeatherResponseDto;
import com.ykotsiuba.soloveibot.entity.weather.OpenWeatherResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class WeatherUtils {

    private static final int DEGREE_SIGN = 0x00B0;
    private static final String CITY = "Івано-Франківськ";

    private static StringBuilder report;

    public static String prepareCurrentWeatherReport(WeatherResponseDto weatherDto) {
        report = new StringBuilder();
        getCityReport(weatherDto.getDate());
        getTemparatureReport(weatherDto.getTemperature());
        getWeatherConditionReport(weatherDto.getCondition(), weatherDto.getIcon());
        getHumidityReport(weatherDto.getHumidity());
        getCloudsReport(weatherDto.getClouds());
        getWindReport(weatherDto.getWindSpeed());
        getPressureReport(weatherDto.getPressure());
        getSunReport(weatherDto.getSunrise(), weatherDto.getSunset());
        return report.toString();
    }

    private static void getCityReport(Long date) {
        report.append(String.format("Погода в місті %s на %s:%n", CITY, toDateString(date, "dd/MM/yyyy")));
    }
    
    private static String toDateString(Long dt, String format) {
        Date date = new Date(dt);  
        DateFormat dateFormat = new SimpleDateFormat(format);  
        return dateFormat.format(date);
    }

    private static void getTemparatureReport(Double temperature) {
        report.append(String.format("Температура повітря%s : %s%n", Emoji.THERMOMETER.getEmogi(),
                getTemparature(temperature)));
    }
    
    private static String getTemparature(Double temperature) {
        return String.format(Locale.ROOT, "%s%.2f %sc", (temperature > 0) ? "+" : "",
                temperature, String.valueOf(Character.toChars(DEGREE_SIGN)));
    }

    private static void getWeatherConditionReport(String condition, String icon) {
        report.append(String.format("Погодні умови: %s%s%n", condition, icon));
    }

    private static void getHumidityReport(Integer humidity) {
        report.append(String.format("Відносна вологість%s : %d%%%n", Emoji.HUMIDITY.getEmogi(),
                humidity));
    }

    private static void getCloudsReport(Integer clouds) {
        report.append(String.format("Хмарність %s: %d%%%n", Emoji.BROKEN_CLOUDS.getEmogi(),
                clouds));
    }

    private static void getWindReport(Double windSpeed) {
        report.append(String.format("Швидкість вітру %s: %.2f м/с%n", Emoji.WIND.getEmogi(),
                windSpeed));
    }

    private static void getPressureReport(Double pressure) {
        report.append(String.format("Атмосферний тиск: %.2f мм.рт.ст.%n", pressure));
    }

    private static void getSunReport(String sunrise, String sunset) {
        report.append(String.format("Схід сонця%s: %s%nЗахід сонця%s: %s", Emoji.SUNRISE.getEmogi(),
                sunrise, Emoji.SUNSET.getEmogi(), sunset));
    }
    
    public static String prepare12HWeatherReport(List<WeatherResponseDto> weatherDtoList) {
        report = new StringBuilder();
        report.append(String.format("Прогноз погоди в місті %s на 12 годин:", CITY));
        for(WeatherResponseDto weatherDto:weatherDtoList) {
            report.append(String.format("%n%s: %s %s, %s %s", toDateString(weatherDto.getDate(), "dd/MM HH:mm"),
                    Emoji.THERMOMETER.getEmogi(), getTemparature(weatherDto.getTemperature()), weatherDto.getCondition(), weatherDto.getIcon()));
        }
        return report.toString();
    }

    public static String prepare5DWeatherReport(OpenWeatherResponse response) {
        report = new StringBuilder();
        return report.toString();
    }
}
