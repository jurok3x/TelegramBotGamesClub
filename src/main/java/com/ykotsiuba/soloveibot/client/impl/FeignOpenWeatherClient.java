package com.ykotsiuba.soloveibot.client.impl;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        value = "open-weather",
        url = "https://api.openweathermap.org"
        )
public interface FeignOpenWeatherClient {

}
