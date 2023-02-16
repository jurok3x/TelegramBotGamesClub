package com.ykotsiuba.soloveibot.service.impl;

import com.ykotsiuba.soloveibot.client.impl.FeignOpenWeatherClient;
import com.ykotsiuba.soloveibot.entity.request.Language;
import com.ykotsiuba.soloveibot.entity.request.WeatherParameters;
import com.ykotsiuba.soloveibot.entity.request.WeatherUnits;
import com.ykotsiuba.soloveibot.entity.weather.OpenWeatherResponse;
import com.ykotsiuba.soloveibot.service.WeatherService;
import com.ykotsiuba.soloveibot.utils.WeatherUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
        return WeatherUtils.prepareCurrentWeatherReport(weatherResponse);
    }

    @Override
    public String get12HWeatherReport() {
        return null;
    }

    @Override
    public String get5DWeatherReport() {
        return null;
    }

    private WeatherParameters prepareParameters() {
        return WeatherParameters.builder()
                .units(WeatherUnits.METRIC.name().toLowerCase())
                .language(Language.UA.name().toLowerCase())
                .appid(weatherToken)
                .q(city)
                .build();
    }
}
