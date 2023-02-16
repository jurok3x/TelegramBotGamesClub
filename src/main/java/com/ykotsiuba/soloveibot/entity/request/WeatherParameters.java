package com.ykotsiuba.soloveibot.entity.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WeatherParameters {

    private String q;
    private String units;
    private String language;
    private String appid;
    private Integer cnt;
}
