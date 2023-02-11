package com.ykotsiuba.soloveibot.entity.weather;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenWeatherResponse {

    private Coordinates coord;
    private Weather[] weather;
    private WeatherMain main;
    private Wind wind;
    private Long dt;
    private LocalSystem sys;
    private Integer timezone;
    private Clouds clouds;
    private Integer id;
    private String name;
    private Integer cod;
    private Integer visibility;
    private String base;
    
}