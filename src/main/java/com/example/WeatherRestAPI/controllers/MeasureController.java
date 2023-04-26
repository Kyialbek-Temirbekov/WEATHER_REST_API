package com.example.WeatherRestAPI.controllers;

import com.example.WeatherRestAPI.dto.MeasureDTO;
import com.example.WeatherRestAPI.dto.MeasurementsResponse;
import com.example.WeatherRestAPI.models.Measure;
import com.example.WeatherRestAPI.services.MeasureService;
import com.example.WeatherRestAPI.util.ErrorsUtil;
import com.example.WeatherRestAPI.util.MeasureNotAddedException;
import com.example.WeatherRestAPI.util.MeasureValidator;
import com.example.WeatherRestAPI.util.SensorErrorResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
@AllArgsConstructor
public class MeasureController implements ErrorsUtil {
    private final MeasureService measureService;
    private final MeasureValidator measureValidator;
    private final ModelMapper modelMapper;

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasureDTO measureDTO,
                                          BindingResult bindingResult) {
        measureValidator.validate(measureDTO, bindingResult);
        if(bindingResult.hasErrors()) {
            throw new MeasureNotAddedException(getErrors(bindingResult));
        }
        measureService.save(convertToMeasure(measureDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping()
    public MeasurementsResponse index() {
        return new MeasurementsResponse(measureService.findAll().stream().map(this::convertToMeasureDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/rainyDaysCount")
    public Map<String, Long> rainDaysCount() {
        return Collections.singletonMap("Rainy Days Count", measureService.rainyDaysCount());
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(MeasureNotAddedException e) {
        SensorErrorResponse response = new SensorErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    private Measure convertToMeasure(MeasureDTO measureDTO) {
        return modelMapper
                .map(measureDTO, Measure.class);
    }
    private MeasureDTO convertToMeasureDTO(Measure measure) {
        return modelMapper
                .map(measure, MeasureDTO.class);
    }
}
