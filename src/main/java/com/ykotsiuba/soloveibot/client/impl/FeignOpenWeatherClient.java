package com.ykotsiuba.soloveibot.client.impl;

import com.ykotsiuba.soloveibot.entity.request.WeatherParameters;
import com.ykotsiuba.soloveibot.entity.weather.OpenWeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
        value = "open-weather",
        url = "https://api.openweathermap.org"
        )
public interface FeignOpenWeatherClient {

        @GetMapping("/data/2.5/weather")
        OpenWeatherResponse getWeather(@SpringQueryMap WeatherParameters params);

        @GetMapping("/data/2.5/forecast")
        List<OpenWeatherResponse> getForecast(@SpringQueryMap WeatherParameters params);

}
