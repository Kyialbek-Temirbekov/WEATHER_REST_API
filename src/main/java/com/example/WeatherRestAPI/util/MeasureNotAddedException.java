package com.example.WeatherRestAPI.util;

public class MeasureNotAddedException extends RuntimeException {
    public MeasureNotAddedException(String msg) {
        super(msg);
    }
}
