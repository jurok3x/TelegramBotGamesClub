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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class FeignWeatherService implements WeatherService {

    @Value("${weather.city}")
    private String city;
    @Value("${openweather.token}")
    private String weatherToken;
    private final FeignOpenWeatherClient weatherClient;

    @Override
    public String getCurrentWeatherReport() {
        OpenWeatherResponse weatherResponse = weatherClient.getCurrentWeather(prepareParameters());
        WeatherResponseDto  weatherResponseDto = OpenWeatherResponseMapper.toResponseDto(weatherResponse);
        return extractCurrentWeatherReport(weatherResponseDto);
    }

    private String extractCurrentWeatherReport(WeatherResponseDto  weatherResponseDto){
        try{
            return WeatherUtils.prepareCurrentWeatherReport(weatherResponseDto);
        } catch(IllegalArgumentException ex){
            log.error("Error parsing current weather response");
            return "Помилка отримання прогнозу погоди";
        }
    }

    @Override
    public String get12HWeatherReport() {
        WeatherParameters params = prepareParameters(5);
        OpenWeatherForecastResponse forecastResponse = weatherClient.getWeatherForecast(params);
        List<WeatherResponseDto> weatherResponsesDto = OpenWeatherResponseMapper.toCollectionDto(forecastResponse);
        return extract12HWeatherReport(weatherResponsesDto);
    }

    private String extract12HWeatherReport(List<WeatherResponseDto> weatherResponsesDto){
        try{
            return WeatherUtils.prepare12HWeatherReport(weatherResponsesDto);
        } catch(IllegalArgumentException ex){
            log.error("Error parsing weather 112 hours response");
            return "Помилка отримання прогнозу погоди";
        }
    }

    @Override
    public String get5DWeatherReport() {
        WeatherParameters params = prepareParameters(40);
        OpenWeatherForecastResponse forecastResponse = weatherClient.getWeatherForecast(params);
        List<WeatherResponseDto> weatherResponsesDto = OpenWeatherResponseMapper.toCollectionDto(forecastResponse);
        return extract5DWeatherReport(weatherResponsesDto);
    }

    private String extract5DWeatherReport(List<WeatherResponseDto> weatherResponsesDto){
        try{
            return WeatherUtils.prepare5DWeatherReport(weatherResponsesDto);
        } catch(IllegalArgumentException ex){
            log.error("Error parsing weather 5 days response");
            return "Помилка отримання прогнозу погоди";
        }
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
