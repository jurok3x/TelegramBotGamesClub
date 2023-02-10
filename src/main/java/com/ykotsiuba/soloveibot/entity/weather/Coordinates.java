package com.ykotsiuba.soloveibot.entity.weather;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coordinates {
    
    private Double lon;
    private Double lat;
   
}
