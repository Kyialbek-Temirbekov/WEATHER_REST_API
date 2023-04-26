package com.example.WeatherRestAPI.dto;

import com.example.WeatherRestAPI.models.Sensor;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MeasureDTO {
    @NotNull(message = "Value should not be empty")
    @Max(value = 100, message = "Value should not be greater than 100")
    @Min(value = -100, message = "Value should not be smaller than -100")
    private double value;
    @NotNull(message = "Raining should not be empty")
    private boolean raining;
    @NotNull(message = "Sensor should not be empty")
    private SensorDTO sensor;
}
