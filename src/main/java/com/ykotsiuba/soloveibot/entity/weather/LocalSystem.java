package com.ykotsiuba.soloveibot.entity.weather;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalSystem {

    private String country;
    private Long sunrise;
    private Long sunset;
    
}
