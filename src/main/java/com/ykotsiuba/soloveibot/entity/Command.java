package com.ykotsiuba.soloveibot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Command {
    
    CURRENT_WEATHER("weather"), WEATHER_FORECAST("weather_forecast");
    
    private final String command;

}
