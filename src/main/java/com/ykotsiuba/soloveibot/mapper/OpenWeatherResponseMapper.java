package com.ykotsiuba.soloveibot.mapper;

import com.ykotsiuba.soloveibot.entity.Emoji;
import com.ykotsiuba.soloveibot.entity.dto.WeatherResponseDto;
import com.ykotsiuba.soloveibot.entity.weather.OpenWeatherForecastResponse;
import com.ykotsiuba.soloveibot.entity.weather.OpenWeatherResponse;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class OpenWeatherResponseMapper {
    
    public static WeatherResponseDto toResponseDto(OpenWeatherResponse response) {
        return WeatherResponseDto.builder()
                .clouds(response.getClouds().getAll())
                .pressure(convertPressure(response.getMain().getPressure()))
                .date(toDateString(response.getDt()))
                .condition(response.getWeather()[0].getDescription())
                .humidity(response.getMain().getHumidity())
                .icon(getWeatherEmoji(response.getWeather()[0].getIcon()))
                .sunrise(toTimeString(response.getSys().getSunrise()))
                .sunset(toTimeString(response.getSys().getSunset()))
                .temperature(response.getMain().getTemp())
                .windSpeed(response.getWind().getSpeed())
                .build();
    }

    public static List<WeatherResponseDto> toCollectionDto(OpenWeatherForecastResponse response) {
        return response.getList().stream().map(OpenWeatherResponseMapper::toResponseDto).toList();
    }
    
    private static Double convertPressure(Integer pressure) {
        return pressure * 100 / 133.32239023154;
    }
    
    private static String toDateString(Long dt) {
        Date date = new Date(dt * 1000);  
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
        return dateFormat.format(date);
    }
    
    private static String toTimeString(Long dt) {
        Date date = new Date(dt * 1000);  
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");  
        return dateFormat.format(date);
    }
    
    private static String getWeatherEmoji(String icon) {
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
