package com.rnr.app.data;

public enum WeatherParameter {
    QUERY("q"),
    APP_ID("appid"),
    UNITS("units");

    final String parameterName;

    WeatherParameter(String parameterName) {
        this.parameterName = parameterName;
    }

    public String parameterName() {
        return parameterName;
    }

    public String withValue(String value) {
        return this.parameterName + "=" + value;
    }
}
