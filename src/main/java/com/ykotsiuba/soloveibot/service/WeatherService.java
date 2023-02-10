package com.ykotsiuba.soloveibot.service;

import com.ykotsiuba.soloveibot.entity.weather.OpenWeatherResponse;

public interface WeatherService {
    
    String sendReport(OpenWeatherResponse response);

}
