package com.rnr.app.service;

import com.rnr.app.data.WeatherData;
import com.rnr.app.data.WeatherParameter;
import com.rnr.app.data.response.WeatherBasicResponse;
import com.rnr.app.data.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WeatherService {

    private static final String DEFAULT_UNIT = "metric";

    @Value("${weather.api-key}")
    private String apiKey;

    @Value("${weather.api.endpoint}")
    private String weatherUrl;

    @Autowired
    private RestTemplate restTemplate;

    public WeatherBasicResponse getTemperature(String city) {
        String units = "imperial";
        UriComponentsBuilder builder = prepareUriComponentBuilderWithApiKey(weatherUrl)
            .queryParam(WeatherParameter.QUERY.parameterName(), city)
            .queryParam(WeatherParameter.UNITS.parameterName(), units);

        return restTemplate.getForObject(builder.toUriString(), WeatherBasicResponse.class);
    }

    public WeatherData getWeatherData(String city) {
        UriComponentsBuilder builder = prepareUriComponentBuilderWithApiKey(weatherUrl)
                .queryParam(WeatherParameter.QUERY.parameterName(), city)
                .queryParam(WeatherParameter.UNITS.parameterName(), DEFAULT_UNIT);

        return restTemplate.getForObject(builder.toUriString(), WeatherData.class);
    }

    private UriComponentsBuilder prepareUriComponentBuilderWithApiKey(String url) {
        return UriComponentsBuilder.fromUriString(url)
                .queryParam(WeatherParameter.APP_ID.parameterName(), apiKey);
    }

}
