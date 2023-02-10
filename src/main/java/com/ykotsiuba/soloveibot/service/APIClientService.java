package com.ykotsiuba.soloveibot.service;

import com.ykotsiuba.soloveibot.entity.weather.OpenWeatherResponse;

public interface APIClientService {
    
    OpenWeatherResponse getWeatherResponse();

}
