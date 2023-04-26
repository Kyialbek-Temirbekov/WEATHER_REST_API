package com.example.WeatherRestAPI.services;

import com.example.WeatherRestAPI.models.Sensor;
import com.example.WeatherRestAPI.repository.SensorRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class SensorService {
    private final SensorRepository sensorRepository;

    public void save(Sensor sensor) {
        enrichSensor(sensor);
        sensorRepository.save(sensor);
    }
    public Optional<Sensor> show(String name) {
        return sensorRepository.findByName(name);
    }
    private void enrichSensor(Sensor sensor) {
        sensor.setRegisteredAt(LocalDateTime.now());
    }
}
