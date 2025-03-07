package com.rnr.app;

import com.rnr.app.data.MeasureUnitType;
import com.rnr.app.data.WeatherData;
import com.rnr.app.data.WeatherParameter;
import com.rnr.app.data.WeatherSimpleMain;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//If profile does not exist it will go to the default one.
@ActiveProfiles("integration_test")
public class WeatherApplicationIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockitoBean
    private RestTemplate restTemplate;

    @Value("${weather.api.endpoint:EMPTY}")
    private String endpoint;

    @Value("${weather.api-key:EMPTY}")
    private String apiKey;

    @Value("${profile.test:EMPTY}")
    private String profile;

    /**
     * We cannot compare the temperature in this way as it changes daily.
     */
    @Test
    @Disabled
    void getCurrentTemperature1() {
        WeatherSimpleMain wsm = testRestTemplate.getForObject("/weatherApi/getCurrentTemperature/Madrid",
                WeatherSimpleMain.class);
        assertEquals(71.37, wsm.getTemp());
    }

    @Test
    void getCurrentTemperature2() {

        assumeFalse("EMPTY".equals(profile),
                "Skipped because profile is not set");
        double expectedTemperature = 27;
        WeatherData weatherData = ResourceHelper.entityFromResource("weather.json", WeatherData.class);
        String city = weatherData.getName();
        weatherData.getMain().setTemp(expectedTemperature);
        when(restTemplate.getForObject(endpoint +
                        String.format("?%s=%s&%s=%s&%s=%s",
                                WeatherParameter.APP_ID.parameterName(), apiKey,
                                WeatherParameter.QUERY.parameterName(), city,
                                WeatherParameter.UNITS.parameterName(), MeasureUnitType.METRIC.type()),
                WeatherData.class))
                .thenReturn(weatherData);
        WeatherData weatherDataActual = testRestTemplate.getForObject("/weatherApi/all/" + city,
                WeatherData.class);

        assertEquals(expectedTemperature, weatherDataActual.getMain().getTemp());
    }

}
