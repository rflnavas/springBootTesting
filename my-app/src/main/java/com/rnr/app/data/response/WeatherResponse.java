package com.rnr.app.data.response;

import com.rnr.app.data.WeatherData;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeatherResponse {

    private WeatherData weatherData;

}
