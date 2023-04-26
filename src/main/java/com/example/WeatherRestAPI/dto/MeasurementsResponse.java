package com.example.WeatherRestAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class MeasurementsResponse {
    private List<MeasureDTO> measurements;
}
