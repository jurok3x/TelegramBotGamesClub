package com.ykotsiuba.soloveibot.entity.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WeatherResponseDto {
    
    private Double temperature;
    private Double windSpeed;
    private Integer clouds;
    private Integer humidity;
    private Double pressure;
    private String date;
    private String sunrise;
    private String sunset;
    private String condition;
    private String icon;

}
