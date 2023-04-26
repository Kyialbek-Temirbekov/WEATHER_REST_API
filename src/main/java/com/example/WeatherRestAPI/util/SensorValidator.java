package com.example.WeatherRestAPI.util;

import com.example.WeatherRestAPI.dto.SensorDTO;
import com.example.WeatherRestAPI.services.SensorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@AllArgsConstructor
@Component
public class SensorValidator implements Validator {
    private final SensorService sensorService;

    @Override
    public boolean supports(Class<?> clazz) {
        return SensorDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensor = (SensorDTO) target;
        if(sensorService.show(sensor.getName()).isPresent())
            errors.rejectValue("name", "", "Sensor with this name already exist");
    }
}
