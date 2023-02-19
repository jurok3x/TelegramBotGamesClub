package com.ykotsiuba.soloveibot.mapper;

import com.ykotsiuba.soloveibot.entity.Emoji;
import com.ykotsiuba.soloveibot.entity.dto.WeatherResponseDto;
import com.ykotsiuba.soloveibot.entity.weather.OpenWeatherForecastResponse;
import com.ykotsiuba.soloveibot.entity.weather.OpenWeatherResponse;
import com.ykotsiuba.soloveibot.util.StringUtils;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OpenWeatherResponseMapper {
    
    public static WeatherResponseDto toResponseDto(OpenWeatherResponse response) {
        return WeatherResponseDto.builder()
                .clouds(response.getClouds().getAll())
                .pressure(convertPressure(response.getMain().getPressure()))
                .date(toLocalDateTime(response.getDt()))
                .condition(response.getWeather().get(0).getDescription())
                .humidity(response.getMain().getHumidity())
                .icon(getWeatherEmoji(response.getWeather().get(0).getIcon()))
                .sunrise(toLocalDateTime(response.getSys().getSunrise()))
                .sunset(toLocalDateTime(response.getSys().getSunset()))
                .temperature(response.getMain().getTemp())
                .windSpeed(response.getWind().getSpeed())
                .build();
    }

    public static List<WeatherResponseDto> toCollectionDto(OpenWeatherForecastResponse response) {
        return response.getList().stream()
                .map(OpenWeatherResponseMapper::toResponseDto)
                .collect(Collectors.toList());
    }
    
    private static Double convertPressure(Integer pressure) {
        if(pressure == null) {
            return null;
        }
        return pressure * 100 / 133.32239023154;
    }

    private static LocalDateTime toLocalDateTime(Long dt) {
        if(dt == null) {
            return null;
        }
        Instant instant = Instant.ofEpochSecond(dt);
        return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
    
    private static String getWeatherEmoji(String icon) {
        if(StringUtils.isBlank(icon)) {
            return null;
        }
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
