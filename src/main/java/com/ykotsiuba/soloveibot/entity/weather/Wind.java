package com.ykotsiuba.soloveibot.entity.weather;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wind {
    
    private Double speed;
    private Double deg;
    private Double gust;

}
