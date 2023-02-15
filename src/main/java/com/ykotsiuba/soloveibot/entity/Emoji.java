package com.ykotsiuba.soloveibot.entity;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public enum Emoji {
    
    CLEAR_SKY(Arrays.asList(0x2600)), FEW_CLOUDS(Arrays.asList(0x1F324)), SCATTERED_CLOUDS(Arrays.asList(0x26C5)),
    BROKEN_CLOUDS(Arrays.asList(0x2601)), SHOWER_RAIN(Arrays.asList(0x1F327)),RAIN(Arrays.asList(0x1F327)), THUNDERSTORM(Arrays.asList(0x26C8)),
    SNOW(Arrays.asList(0x2744)), MIST(Arrays.asList(0x1F301)), WIND(Arrays.asList(0x1F4A8)), HUMIDITY(Arrays.asList(0x1F4A7)),
    THERMOMETER(Arrays.asList(0x1F321)), SUNRISE(Arrays.asList(0x1F305)), SUNSET(Arrays.asList(0x1F307)), DEFAULT(Arrays.asList(0x2753));

    private final List<Integer> symbols;
    
    public String getEmogi() {
        StringBuilder emoji = new StringBuilder();
        symbols.forEach(symbol -> emoji.append(Character.toChars(symbol)));
        return emoji.toString();
    }

}
