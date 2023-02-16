package com.ykotsiuba.soloveibot.utils;

import com.ykotsiuba.soloveibot.entity.Emoji;
import com.ykotsiuba.soloveibot.entity.dto.WeatherResponseDto;
import com.ykotsiuba.soloveibot.entity.weather.OpenWeatherResponse;
import com.ykotsiuba.soloveibot.mapper.OpenWeatherResponseMapper;

public class WeatherUtils {

    private static final int DEGREE_SIGN = 0x00B0;
    private static final String CITY = "Івано-Франківськ";

    private static StringBuilder report;

    public static String prepareCurrentWeatherReport(OpenWeatherResponse response) {
        report = new StringBuilder();
        WeatherResponseDto weatherDto = OpenWeatherResponseMapper.toResponseDto(response);
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

    public static String prepare12HWeatherReport(OpenWeatherResponse response) {
        return null;

    }

    public static String prepare5DWeatherReport(OpenWeatherResponse response) {
        return null;
    }

    private static void getCityReport(String date) {
        report.append(String.format("Погода в місті %s на %s:%n", CITY, date));
    }

    private static void getTemparatureReport(Double temperature) {
        report.append(String.format("Температура повітря%s : %.2f %sc%n", Emoji.THERMOMETER.getEmogi(),
                temperature, String.valueOf(Character.toChars(DEGREE_SIGN))));
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
}
