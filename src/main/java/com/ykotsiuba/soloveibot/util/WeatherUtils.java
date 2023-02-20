package com.ykotsiuba.soloveibot.util;

import com.ykotsiuba.soloveibot.entity.Emoji;
import com.ykotsiuba.soloveibot.entity.dto.WeatherResponseDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class WeatherUtils {

    private static final String DAY_TIME_FORMAT = "dd MMMM HH:mm";
    private static final String DAY_MONTH_FORMAT = "dd MMMM YYYY";
    private static final String HOUR_FORMAT = "HH:mm";
    private static final int DEGREE_SIGN = 0x00B0;
    private static final String CITY = "Івано-Франківськ";
    private static final Locale LOCAL = new Locale("uk");
    private static final String CITY_REPORT = "Погода в місті %s на %s:%n";
    private static final String TEMPERATURE_REPORT = "Температура повітря%s : %s%n";
    private static final String CONDITION_REPORT = "Погодні умови: %s%s%n";
    private static final String HUMIDITY_REPORT = "Відносна вологість%s : %d%%%n";
    private static final String CLOUDS_REPORT = "Хмарність %s: %d%%%n";
    private static final String WIND_REPORT = "Швидкість вітру %s: %.2f м/с%n";
    private static final String PRESSURE_REPORT = "Атмосферний тиск: %.2f мм.рт.ст.%n";
    private static final String SUN_REPORT = "Схід сонця%s: %s%nЗахід сонця%s: %s";
    private static final String TWELVE_HOURS_REPORT = "Прогноз погоди в місті %s на 12 годин:";
    private static final String THREE_HOURS_REPORT = "%n%s:%n%s %s, %s %s";
    private static final String FIVE_DAY_REPORT = "Прогноз погоди в місті %s на 5 днів:";
    private static final String ONE_DAY_REPORT = "%n%s:%n%s %s%n%s";

    private static StringBuilder report;

    public static String prepareCurrentWeatherReport(WeatherResponseDto weatherDto) {
        report = new StringBuilder();
        appendReport(CITY_REPORT, CITY, toDateString(weatherDto.getDate(), DAY_MONTH_FORMAT));
        appendReport(TEMPERATURE_REPORT, Emoji.THERMOMETER.getEmogi(),
                formatTemperature(weatherDto.getTemperature()));
        appendReport(CONDITION_REPORT, weatherDto.getCondition(), weatherDto.getIcon());
        appendReport(HUMIDITY_REPORT, Emoji.HUMIDITY.getEmogi(),
                weatherDto.getHumidity());
        appendReport(CLOUDS_REPORT, Emoji.BROKEN_CLOUDS.getEmogi(),
                weatherDto.getClouds());
        appendReport(WIND_REPORT, Emoji.WIND.getEmogi(),
                weatherDto.getWindSpeed());
        appendReport(PRESSURE_REPORT, weatherDto.getPressure());
        appendReport(SUN_REPORT, Emoji.SUNRISE.getEmogi(),
                toDateString(weatherDto.getSunrise(), HOUR_FORMAT), Emoji.SUNSET.getEmogi(), toDateString(weatherDto.getSunset(), HOUR_FORMAT));
        return report.toString();
    }

    private static void appendReport(String format, Object... components) {
        if (components == null || StringUtils.isBlank(format)) {
            throw new IllegalArgumentException("Components array cannot be null");
        }
        for (Object component : components) {
            if (component == null) {
                throw new IllegalArgumentException("Component cannot be null");
            }
            if (StringUtils.isBlank(component.toString())) {
                throw new IllegalArgumentException("Component cannot be empty");
            }
        }
        report.append(String.format(Locale.ROOT, format, components));
    }
    
    private static String toDateString(LocalDateTime date, String format) {
        if(date == null || StringUtils.isBlank(format)){
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format, LOCAL);
        return date.format(formatter);
    }
    
    private static String formatTemperature(Double temperature) {
        return String.format(Locale.ROOT, "%s%.2f %sc", (temperature > 0) ? "+" : "",
                temperature, String.valueOf(Character.toChars(DEGREE_SIGN)));
    }
    
    public static String prepare12HWeatherReport(List<WeatherResponseDto> weatherDtoList) {
        report = new StringBuilder();
        appendReport(TWELVE_HOURS_REPORT, CITY);
        for(WeatherResponseDto weatherDto:weatherDtoList) {
            appendReport(THREE_HOURS_REPORT, toDateString(weatherDto.getDate(), DAY_TIME_FORMAT),
                    Emoji.THERMOMETER.getEmogi(), formatTemperature(weatherDto.getTemperature()), weatherDto.getCondition(), weatherDto.getIcon();
        }
        return report.toString();
    }

    public static String prepare5DWeatherReport(List<WeatherResponseDto> weatherDtoList) {
        report = new StringBuilder();
        appendReport(FIVE_DAY_REPORT, CITY);
        int firstDay = weatherDtoList.get(0).getDate().getDayOfMonth();
        for(int i = firstDay; i <= firstDay + 5; i++) {
            List<WeatherResponseDto> dailyList = filterByDayOfMonth(weatherDtoList, i);
            String temperatureRange = getMinMaxTemperature(dailyList);
            String dailyCondition = getEmojiList(dailyList);
            appendReport(ONE_DAY_REPORT, toDateString(dailyList.get(0).getDate(), DAY_MONTH_FORMAT),
                    Emoji.THERMOMETER.getEmogi(), temperatureRange, dailyCondition);
            report.append(String.format());
        }
        return report.toString();
    }

    private static List<WeatherResponseDto> filterByDayOfMonth(List<WeatherResponseDto> weatherDtoList, int dayOfMonth) {
        return weatherDtoList.stream()
                .filter(o -> o.getDate().getDayOfMonth() == dayOfMonth)
                .collect(Collectors.toList());
    }

    private static String getMinMaxTemperature(List<WeatherResponseDto> weatherDtoList) {
        OptionalDouble maxTemp = weatherDtoList.stream().mapToDouble(WeatherResponseDto::getTemperature).max();
        OptionalDouble  minTemp = weatherDtoList.stream().mapToDouble(WeatherResponseDto::getTemperature).min();
        if(maxTemp.isEmpty()){
            return "";
        }
        return String.format("%s .. %s", formatTemperature(minTemp.getAsDouble()), formatTemperature(maxTemp.getAsDouble()));
    }

    
    private static String getEmojiList(List<WeatherResponseDto> weatherDtoList) {
        return weatherDtoList.stream()
                .map(WeatherResponseDto::getIcon)
                .distinct()
                .collect(Collectors.joining(" "));
    }
}