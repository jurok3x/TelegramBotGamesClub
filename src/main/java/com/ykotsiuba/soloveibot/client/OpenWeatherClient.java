package com.ykotsiuba.soloveibot.client;

import com.ykotsiuba.soloveibot.entity.weather.OpenWeatherForecastResponse;
import com.ykotsiuba.soloveibot.entity.weather.OpenWeatherResponse;

public interface OpenWeatherClient {
    
    OpenWeatherResponse getCurrentWeather();
    OpenWeatherForecastResponse getWeatherForecast();

}
