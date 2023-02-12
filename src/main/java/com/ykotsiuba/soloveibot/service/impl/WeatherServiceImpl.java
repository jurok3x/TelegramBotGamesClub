package com.ykotsiuba.soloveibot.service.impl;

import com.ykotsiuba.soloveibot.client.OpenWeatherClient;
import com.ykotsiuba.soloveibot.entity.Emoji;
import com.ykotsiuba.soloveibot.entity.dto.WeatherResponseDto;
import com.ykotsiuba.soloveibot.mapper.OpenWeatherResponseMapper;
import com.ykotsiuba.soloveibot.service.WeatherService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {
    
    private static final int DEGREE_SIGN = 0x00B0;
    private static final String CITY = "Івано-Франківськ";
    private final OpenWeatherClient whetherClient;
    private StringBuilder report;

    @Override
    public String sendReport() {
        init();
        WeatherResponseDto weatherDto = OpenWeatherResponseMapper.toResponseDto(whetherClient.getCurrentWeather());
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

    private void init() {
        report = new StringBuilder();
    }
    
    private void getCityReport(String date) {
        report.append(String.format("Погода в місті %s на %s:%n", CITY, date));
    }
    
    private void getTemparatureReport(Double temperature) {
        report.append(String.format("Температура повітря%s : %.2f %sc%n", Emoji.THERMOMETER.getEmogi(),
                temperature, String.valueOf(Character.toChars(DEGREE_SIGN))));
    }
    
    private void getWeatherConditionReport(String condition, String icon) {
        report.append(String.format("Погодні умови: %s%s%n", condition, icon));
    }
    
    private void getHumidityReport(Integer humidity) {
        report.append(String.format("Відносна вологість%s : %d%%%n", Emoji.HUMIDITY.getEmogi(),
                humidity));
    }
    
    private void getCloudsReport(Integer clouds) {
        report.append(String.format("Хмарність %s: %d%%%n", Emoji.BROKEN_CLOUDS.getEmogi(),
                clouds));
    }
    
    private void getWindReport(Double windSpeed) {
        report.append(String.format("Швидкість вітру %s: %.2f м/с%n", Emoji.WIND.getEmogi(),
                windSpeed));
    }
    
    private void getPressureReport(Double pressure) {
        report.append(String.format("Атмосферний тиск: %.2f мм.рт.ст%n", pressure));
    }
    
    private void getSunReport(String sunrise, String sunset) {
        report.append(String.format("Схід сонця%s: %s%nЗахід сонця%s: %s", Emoji.SUNRISE.getEmogi(),
                sunrise, Emoji.SUNSET.getEmogi(), sunset));
    }

}
