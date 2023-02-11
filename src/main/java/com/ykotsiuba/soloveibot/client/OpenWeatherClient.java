package com.ykotsiuba.soloveibot.client;

import com.ykotsiuba.soloveibot.entity.weather.OpenWeatherResponse;

public interface OpenWeatherClient {
    
    OpenWeatherResponse getWeatherResponse();

}
