package com.rnr.app.service;

import com.rnr.app.data.MeasureUnitType;
import com.rnr.app.ResourceHelper;
import com.rnr.app.data.WeatherData;
import com.rnr.app.data.WeatherParameter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringJUnitConfig
@TestPropertySource(locations = "classpath:application.properties")
public class WeatherServiceTest {

    @Value("${weather.api.endpoint}")
    private String url;

    @InjectMocks
    private WeatherService weatherService;

    @Mock
    private RestTemplate restTemplate;

    @Value("${weather.api-key}")
    private String apiKey;

    @BeforeEach
    void setUp(){
        ReflectionTestUtils.setField(weatherService, "apiKey", apiKey);
        ReflectionTestUtils.setField(weatherService, "weatherUrl", url);
    }

    @Test
    void testGetWeather(){
        final String seville = "Seville";
        WeatherData weatherData = ResourceHelper.entityFromResource("weather.json", WeatherData.class);

        when(restTemplate.getForObject(url + "?" +
                 WeatherParameter.APP_ID.withValue(apiKey) +
                "&" + WeatherParameter.QUERY.withValue(seville) +
                "&" + WeatherParameter.UNITS.withValue(MeasureUnitType.METRIC.type()),
                WeatherData.class))
                .thenReturn(weatherData);
        WeatherData response = weatherService.getWeatherData(seville);
        assertEquals(weatherData, response);
    }
}
