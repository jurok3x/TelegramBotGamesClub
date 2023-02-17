package com.ykotsiuba.soloveibot.service.impl;

import com.ykotsiuba.soloveibot.client.impl.FeignOpenWeatherClient;
import com.ykotsiuba.soloveibot.entity.dto.WeatherResponseDto;
import com.ykotsiuba.soloveibot.entity.request.Language;
import com.ykotsiuba.soloveibot.entity.request.WeatherParameters;
import com.ykotsiuba.soloveibot.entity.request.WeatherUnits;
import com.ykotsiuba.soloveibot.entity.weather.OpenWeatherForecastResponse;
import com.ykotsiuba.soloveibot.entity.weather.OpenWeatherResponse;
import com.ykotsiuba.soloveibot.mapper.OpenWeatherResponseMapper;
import com.ykotsiuba.soloveibot.service.WeatherService;
import com.ykotsiuba.soloveibot.util.WeatherUtils;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FeignWeatherService implements WeatherService {

    @Value("${weather.city}")
    private String city;
    @Value("${openweather.token}")
    private String weatherToken;
    private final FeignOpenWeatherClient weatherClient;

    @Override
    public String getCurrentWeatherReport() {
        OpenWeatherResponse weatherResponse = weatherClient.getWeather(prepareParameters());
        WeatherResponseDto  weatherResponseDto = OpenWeatherResponseMapper.toResponseDto(weatherResponse);
        return WeatherUtils.prepareCurrentWeatherReport(weatherResponseDto);
    }

    @Override
    public String get12HWeatherReport() {
        WeatherParameters params = prepareParameters(4);
        OpenWeatherForecastResponse forecastResponse = weatherClient.getForecast(params);
        List<WeatherResponseDto> weatherResponsesDto = OpenWeatherResponseMapper.toCollectionDto(forecastResponse);
        return WeatherUtils.prepare12HWeatherReport(weatherResponsesDto);
    }

    @Override
    public String get5DWeatherReport() {
        return null;
    }

    private WeatherParameters prepareParameters() {
        return WeatherParameters.builder()
                .units(WeatherUnits.METRIC.name().toLowerCase())
                .lang(Language.UA.name().toLowerCase())
                .appid(weatherToken)
                .q(city)
                .build();
    }
    
    private WeatherParameters prepareParameters(int count) {
        return WeatherParameters.builder()
                .units(WeatherUnits.METRIC.name().toLowerCase())
                .lang(Language.UA.name().toLowerCase())
                .appid(weatherToken)
                .q(city)
                .cnt(count)
                .build();
    }
}
