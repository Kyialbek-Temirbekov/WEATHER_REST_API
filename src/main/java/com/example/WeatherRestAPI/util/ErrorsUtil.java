package com.example.WeatherRestAPI.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public interface ErrorsUtil {
    default String getErrors(BindingResult bindingResult) {
        StringBuilder errorMsg = new StringBuilder();
        List<FieldError> errors = bindingResult.getFieldErrors();
        errors.forEach(error -> errorMsg.append(error.getField()).append(": ")
                .append(error.getDefaultMessage()).append("; "));
        return errorMsg.toString();
    }
}
