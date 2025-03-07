package com.rnr.app.data;

public class WeatherSimpleMain {
  private double temp;

  public WeatherSimpleMain() {

  }

  public WeatherSimpleMain(double temp) {
    this.temp = temp;
  }

  public double getTemp() {
    return temp;
  }

  public void setTemp(double temp) {
    this.temp = temp;
  }
}