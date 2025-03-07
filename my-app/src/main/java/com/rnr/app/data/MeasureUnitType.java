package com.rnr.app.data;

public enum MeasureUnitType {
    METRIC("metric"),
    IMPERIAL("imperial");

    private final String type;

    MeasureUnitType(String type) {
        this.type = type;
    }

    public String type() {
        return type;
    }
}
