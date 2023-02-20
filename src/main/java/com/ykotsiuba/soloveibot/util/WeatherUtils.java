package com.ykotsiuba.soloveibot.util;

import com.ykotsiuba.soloveibot.entity.Emoji;
import com.ykotsiuba.soloveibot.entity.dto.WeatherResponseDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class WeatherUtils {

    private static final String DAY_TIME_FORMAT = "dd MMMM HH:mm";
    private static final String DAY_MONTH_FORMAT = "dd MMMM YYYY";
    private static final String HOUR_FORMAT = "HH:mm";
    private static final int DEGREE_SIGN = 0x00B0;
    private static final String CITY = "Івано-Франківськ";
    private static final Locale LOCAL = new Locale("uk");

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

    private static void getCityReport(LocalDateTime date) {
        report.append(String.format("Погода в місті %s на %s:%n", CITY, toDateString(date, DAY_MONTH_FORMAT)));
    }
    
    private static String toDateString(LocalDateTime date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format, LOCAL);
        return date.format(formatter);
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
        report.append(String.format(Locale.ROOT,"Швидкість вітру %s: %.2f м/с%n", Emoji.WIND.getEmogi(),
                windSpeed));
    }

    private static void getPressureReport(Double pressure) {
        report.append(String.format(Locale.ROOT,"Атмосферний тиск: %.2f мм.рт.ст.%n", pressure));
    }

    private static void getSunReport(LocalDateTime sunrise, LocalDateTime sunset) {
        report.append(String.format("Схід сонця%s: %s%nЗахід сонця%s: %s", Emoji.SUNRISE.getEmogi(),
                toDateString(sunrise, HOUR_FORMAT), Emoji.SUNSET.getEmogi(), toDateString(sunset, HOUR_FORMAT)));
    }
    
    public static String prepare12HWeatherReport(List<WeatherResponseDto> weatherDtoList) {
        report = new StringBuilder();
        report.append(String.format("Прогноз погоди в місті %s на 12 годин:", CITY));
        for(WeatherResponseDto weatherDto:weatherDtoList) {
            report.append(String.format("%n%s:%n%s %s, %s %s", toDateString(weatherDto.getDate(), DAY_TIME_FORMAT),
                    Emoji.THERMOMETER.getEmogi(), getTemparature(weatherDto.getTemperature()), weatherDto.getCondition(), weatherDto.getIcon()));
        }
        return report.toString();
    }

    public static String prepare5DWeatherReport(List<WeatherResponseDto> weatherDtoList) {
        report = new StringBuilder();
        report.append(String.format("Прогноз погоди в місті %s на 5 днів:", CITY));
        int firstDay = weatherDtoList.get(0).getDate().getDayOfMonth();
        for(int i = firstDay; i <= firstDay + 5; i++) {
            List<WeatherResponseDto> dailyList = filterByDayOfMonth(weatherDtoList, i);
            String temparatureRange = getMinMaxTemarature(dailyList);
            String dailyCondition = getEmojiList(dailyList);
            report.append(String.format("%n%s:%n%s %s%n%s", toDateString(dailyList.get(0).getDate(), DAY_MONTH_FORMAT),
                    Emoji.THERMOMETER.getEmogi(), temparatureRange, dailyCondition));
        }
        return report.toString();
    }

    private static String getMinMaxTemarature(List<WeatherResponseDto> weatherDtoList) {
        double maxTemp = weatherDtoList.stream().mapToDouble(response -> response.getTemperature()).max().getAsDouble();
        double minTemp = weatherDtoList.stream().mapToDouble(response -> response.getTemperature()).min().getAsDouble();
        return String.format("%s .. %s", getTemparature(minTemp), getTemparature(maxTemp));
    }

    private static List<WeatherResponseDto> filterByDayOfMonth(List<WeatherResponseDto> weatherDtoList, int dayOfMonth) {
        return weatherDtoList.stream()
                .filter(o -> o.getDate().getDayOfMonth() == dayOfMonth)
                .collect(Collectors.toList());
    }
    
    private static String getEmojiList(List<WeatherResponseDto> weatherDtoList) {
        return weatherDtoList.stream()
                .map(o -> o.getIcon())
                .distinct()
                .collect(Collectors.joining(" "));
    }
}
