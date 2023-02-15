package com.ykotsiuba.soloveibot.entity.weather;

import lombok.Data;

import java.util.List;
@Data
public class OpenWeatherForecastResponse {

    private List<OpenWeatherResponse> list;

}
