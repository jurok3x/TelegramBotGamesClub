package com.ykotsiuba.soloveibot.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Emoji {
    
    CLEAR_SKY(0x2600), FEW_CLOUDS(0x1F324), SCATTERED_CLOUDS(0x26C5), BROKEN_CLOUDS(0x2601), SHOWER_RAIN(0x1F327),
    RAIN(0x1F326), THUNDERSTORM(0x26C8), SNOW(0x2744), MIST(0x1F301), WIND(0x1F4A8), HUMIDITY(0x1F4A7), THERMOMETER(0x1F321),
    SUNRISE(0x1F305), SUNSET(0x1F307), DEFAULT(0x2753);

    private final int symbol;
    
    public String getEmogi() {
        return new String(Character.toChars(symbol));
    }

}
