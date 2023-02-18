package com.ykotsiuba.soloveibot.entity.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class WeatherResponseDto {
    
    private Double temperature;
    private Double windSpeed;
    private Integer clouds;
    private Integer humidity;
    private Double pressure;
    private LocalDate date;
    private LocalTime sunrise;
    private LocalTime sunset;
    private String condition;
    private String icon;

}
