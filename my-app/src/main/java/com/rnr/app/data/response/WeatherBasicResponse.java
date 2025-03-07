package com.rnr.app.data.response;

import com.rnr.app.data.WeatherSimpleMain;

public class WeatherBasicResponse {

  private WeatherSimpleMain main;

  public WeatherBasicResponse() {

  }

  public WeatherBasicResponse(WeatherSimpleMain main) {
    this.main = main;
  }

  public WeatherSimpleMain getMain() {
    return main;
  }

  public void setMain(WeatherSimpleMain main) {
    this.main = main;
  }
}