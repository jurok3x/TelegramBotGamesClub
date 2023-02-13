package com.ykotsiuba.soloveibot.entity.weather;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenWeatherResponse {

    private Coordinates coord;
    private List<Weather> weather;
    private WeatherMain main;
    private Wind wind;
    private Long dt;
    private LocalSystem sys;
    private Integer timezone;
    private Clouds clouds;
    private String name;
    private Integer visibility;
    private String base;
    
}
