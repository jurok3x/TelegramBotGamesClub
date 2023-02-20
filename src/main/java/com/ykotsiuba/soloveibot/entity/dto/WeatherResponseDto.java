package com.ykotsiuba.soloveibot.entity.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class WeatherResponseDto {
    
    private Double temperature;
    private Double windSpeed;
    private Integer clouds;
    private Integer humidity;
    private Double pressure;
    private LocalDateTime date;
    private LocalDateTime sunrise;
    private LocalDateTime sunset;
    private String condition;
    private String icon;

}
