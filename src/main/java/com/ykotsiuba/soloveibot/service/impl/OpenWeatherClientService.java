package com.ykotsiuba.soloveibot.service.impl;

import com.ykotsiuba.soloveibot.entity.weather.OpenWeatherResponse;
import com.ykotsiuba.soloveibot.service.APIClientService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenWeatherClientService implements APIClientService{
    
    private static final String LANGUAGE = "ua";
    private static final String CITY = "Ivano-Frankivsk,ua";
    private static final String UNITS = "metric";
    private static final String WEATHER_TOKEN = "051a029e530b502978441902c3723f47";
    private RestTemplate restTemplate;

    @Override
    public OpenWeatherResponse getWeatherResponse() {
        restTemplate = new RestTemplate();
        ResponseEntity<OpenWeatherResponse> response = restTemplate.getForEntity(prepareUrl(), OpenWeatherResponse.class);
        return response.getBody();
    }

    private String prepareUrl() {
        return String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&lang=%s&units=%s&APPID=%s",
                CITY, LANGUAGE, UNITS, WEATHER_TOKEN);
    }

}
