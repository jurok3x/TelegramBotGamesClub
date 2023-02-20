package com.ykotsiuba.soloveibot.entity.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WeatherParameters {

    private String q;
    private String units;
    private String lang;
    private String appid;
    private Integer cnt;
}
