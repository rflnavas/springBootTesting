package com.rnr.app.controller;

import com.rnr.app.data.Main;
import com.rnr.app.data.WeatherData;
import com.rnr.app.data.WeatherSimpleMain;
import com.rnr.app.data.response.WeatherBasicResponse;
import com.rnr.app.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weatherApi")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/getCurrentTemperature/{city}")
    public ResponseEntity<WeatherSimpleMain> getCurrentTemperature(@PathVariable String city) {
        try {
            WeatherBasicResponse weather = weatherService.getTemperature(city);
            return ResponseEntity.ok(weather.getMain());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/all/{city}")
    public ResponseEntity<WeatherData> getAllAboutCity(@PathVariable String city) {
        WeatherData weather = weatherService.getWeatherData(city);
        return ResponseEntity.ok(weather);
    }

}