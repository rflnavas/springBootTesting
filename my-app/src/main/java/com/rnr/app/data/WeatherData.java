package com.rnr.app.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherData {
    private Coord coord;
    private List<Weather> weather;
    private String base;
    private Main main;
    private int visibility;
    private long dt;
    private Sys sys;
    private int timezone;
    private int id;
    private String name;
    private int cod;
}