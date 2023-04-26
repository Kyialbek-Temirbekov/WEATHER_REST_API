package com.example.WeatherRestAPI.util;

public class SensorNotCreatedException extends RuntimeException{
    public SensorNotCreatedException(String msg) {
        super(msg);
    }
}
