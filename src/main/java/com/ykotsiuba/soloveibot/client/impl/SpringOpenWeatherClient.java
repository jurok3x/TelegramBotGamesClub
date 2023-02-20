package com.ykotsiuba.soloveibot.client.impl;

import com.ykotsiuba.soloveibot.client.OpenWeatherClient;
import com.ykotsiuba.soloveibot.entity.weather.OpenWeatherForecastResponse;
import com.ykotsiuba.soloveibot.entity.weather.OpenWeatherResponse;

import lombok.RequiredArgsConstructor;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Component
@PropertySource("classpath:/openweather/weather_api.properties")
@RequiredArgsConstructor
public class SpringOpenWeatherClient implements OpenWeatherClient {

    @Value("${weather.language}")
    private String language;
    @Value("${weather.city}")
    private String city;
    @Value("${weather.units}")
    private String units;
    @Value("${openweather.token}")
    private String weatherToken;
    private final RestTemplate restTemplate;

    @Override
    public OpenWeatherResponse getCurrentWeather() {
        ResponseEntity<OpenWeatherResponse> response = restTemplate.getForEntity(prepareUri(),
                OpenWeatherResponse.class);
        return response.getBody();
    }

    @Override
    public OpenWeatherForecastResponse getWeatherForecast(Integer count) {
        ResponseEntity<OpenWeatherForecastResponse> response = restTemplate.getForEntity(prepareUri(count),
                OpenWeatherForecastResponse.class);
        return response.getBody();
    }

    private URI prepareUri() {
        URI uri = null;
        try {
            URIBuilder builder = new URIBuilder();
            builder.setScheme("https");
            builder.setHost("api.openweathermap.org");
            builder.setPathSegments("data", "2.5", "weather");
            builder.addParameter("q", city);
            builder.addParameter("lang", language);
            builder.addParameter("units", units);
            builder.addParameter("appid", weatherToken);
            uri = builder.build();
        } catch (URISyntaxException e) {
            System.out.println(String.format("Error building URI. Reason: %s", e.getMessage()));
        }
        return uri;
    }

    private URI prepareUri(Integer count) {
        URI uri = null;
        try {
            URIBuilder builder = new URIBuilder();
            builder.setScheme("https");
            builder.setHost("api.openweathermap.org");
            builder.setPathSegments("data", "2.5", "forecast");
            builder.addParameter("q", city);
            builder.addParameter("lang", language);
            builder.addParameter("units", units);
            builder.addParameter("appid", weatherToken);
            builder.addParameter("cnt", count.toString());
            uri = builder.build();
        } catch (URISyntaxException e) {
            System.out.println(String.format("Error building URI. Reason: %s", e.getMessage()));
        }
        return uri;
    }

}


