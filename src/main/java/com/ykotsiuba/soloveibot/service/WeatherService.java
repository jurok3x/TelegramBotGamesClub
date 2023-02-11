package com.ykotsiuba.soloveibot.service;

import com.ykotsiuba.soloveibot.entity.dto.WeatherResponseDto;

public interface WeatherService {
    
    String sendReport(WeatherResponseDto weatherDto);

}
