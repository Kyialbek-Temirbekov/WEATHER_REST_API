package com.example.WeatherRestAPI.util;

import com.example.WeatherRestAPI.dto.MeasureDTO;
import com.example.WeatherRestAPI.services.SensorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@AllArgsConstructor
@Component
public class MeasureValidator implements Validator {
    private final SensorService sensorService;

    @Override
    public boolean supports(Class<?> clazz) {
        return MeasureDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasureDTO measureDTO = (MeasureDTO) target;
        if(sensorService.show(measureDTO.getSensor().getName()).isEmpty())
            errors.rejectValue("sensor", "", "Sensor with this name doesn't exist");
    }
}
