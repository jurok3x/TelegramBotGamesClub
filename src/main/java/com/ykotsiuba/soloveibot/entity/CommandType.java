package com.ykotsiuba.soloveibot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CommandType {
    
    CURRENT_WEATHER("weather", "Надсилає поточні погодні умови у місті Івано-Франківськ."),
    WEATHER_12H_FORECAST("weather_12h_forecast", "Надсилає прогноз погоди на найближчі 12 годин у місті Івано-Франківськ."),
    WEATHER_5D_FORECAST("weather_5d_forecast", "Надсилає прогноз погоди на найближчі 5 днів у місті Івано-Франківськ.");
    
    private final String name;
    private final String description;

}
