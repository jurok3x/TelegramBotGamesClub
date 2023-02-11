package com.ykotsiuba.soloveibot.client.impl;

import com.ykotsiuba.soloveibot.client.OpenWeatherClientService;
import com.ykotsiuba.soloveibot.entity.weather.OpenWeatherResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@PropertySource("classpath:/openweather/weather_api.properties")
@RequiredArgsConstructor
public class OpenWeatherSpringClientService implements OpenWeatherClientService{
    
    @Value("weather.link")
    private String link;
    @Value("weather.language")
    private String language;
    @Value("weather.city")
    private String city;
    @Value("weather.units")
    private String units;
    @Value("openweather.token")
    private String weatherToken;
    private final RestTemplate restTemplate;

    @Override
    public OpenWeatherResponse getWeatherResponse() {
        ResponseEntity<OpenWeatherResponse> response = restTemplate.getForEntity(prepareUrl(), OpenWeatherResponse.class);
        return response.getBody();
    }

    private String prepareUrl() {
        return String.format(link, city, language, units, weatherToken);
    }

}
