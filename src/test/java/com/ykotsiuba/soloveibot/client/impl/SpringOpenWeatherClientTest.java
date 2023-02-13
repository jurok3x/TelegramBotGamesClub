package com.ykotsiuba.soloveibot.client.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.http.client.utils.URIBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;

class SpringOpenWeatherClientTest {

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void test() {
        assertEquals("https://api.openweathermap.org/data/2.5/weather?q=Ivano-Frankivsk%2Cua&lang=ua&units=metric&APPID=token",
                prepareUri().toString());
    }
    
    private URI prepareUri() {
        URI uri = null;
        try {
            URIBuilder builder = new URIBuilder();
            builder.setScheme("https");
            builder.setHost("api.openweathermap.org");
            builder.setPathSegments("data", "2.5", "weather");
            builder.addParameter("q", "Ivano-Frankivsk,ua");
            builder.addParameter("lang", "ua");
            builder.addParameter("units", "metric");
            builder.addParameter("APPID", "token");
            uri = builder.build();
        } catch (URISyntaxException e) {
            System.out.println(String.format("Error building URI. Reason: %s", e.getMessage()));
        }
        return uri;
    }

}
