package com.rnr.app.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rnr.app.ResourceHelper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeatherDataTest {

    @Test
    void deserializeWeatherData() throws IOException {
        File file = ResourceHelper.getFileFromResource("weather.json");
        System.out.println(file.getAbsolutePath());
        WeatherData weatherData = new ObjectMapper().readValue(file, WeatherData.class);
        assertAll(
            () -> assertEquals(18.71, weatherData.getMain().getTemp(),
                "Temperature is not what is expected"),
            () -> assertEquals("Seville", weatherData.getName(),
                "Must be Seville"),
            () -> assertEquals("ES", weatherData.getSys().getCountry(),
                "Must be ES")
        );

    }
}
