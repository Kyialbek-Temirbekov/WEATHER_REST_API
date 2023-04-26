package com.example.WeatherRestAPI.controllers;

import com.example.WeatherRestAPI.dto.SensorDTO;
import com.example.WeatherRestAPI.models.Sensor;
import com.example.WeatherRestAPI.services.SensorService;
import com.example.WeatherRestAPI.util.ErrorsUtil;
import com.example.WeatherRestAPI.util.SensorErrorResponse;
import com.example.WeatherRestAPI.util.SensorNotCreatedException;
import com.example.WeatherRestAPI.util.SensorValidator;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensors")
@AllArgsConstructor
public class SensorController implements ErrorsUtil {
    private final SensorService sensorService;
    private final SensorValidator sensorValidator;
    private final ModelMapper modelMapper;
    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> register(@RequestBody @Valid SensorDTO sensorDTO,
                                               BindingResult bindingResult) {
        sensorValidator.validate(sensorDTO, bindingResult);
        if(bindingResult.hasErrors()) {
            throw new SensorNotCreatedException(getErrors(bindingResult));
        }
        sensorService.save(convertToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotCreatedException e) {
        SensorErrorResponse response = new SensorErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}
