package com.ykotsiuba.soloveibot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum CommandType {
    
    CURRENT_WEATHER("weather", "Надсилає поточні погодні умови у місті Івано-Франківськ."),
    WEATHER_12H_FORECAST("weather_12h_forecast", "Надсилає прогноз погоди на 12 годин у місті Івано-Франківськ."),
    WEATHER_5D_FORECAST("weather_5d_forecast", "Надсилає прогноз погоди на 5 днів у місті Івано-Франківськ.");
    
    private final String name;
    private final String description;
    
    public static Optional<CommandType> parseCommand(String command) {
        if (command.isBlank()) {
          return Optional.empty();
        }
        String formatName = command.trim().toLowerCase();
        return Stream.of(values()).filter(c -> c.name.equalsIgnoreCase(formatName)).findFirst();
      }

}
