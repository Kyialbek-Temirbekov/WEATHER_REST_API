package com.example.WeatherRestAPI.services;

import com.example.WeatherRestAPI.dto.MeasureDTO;
import com.example.WeatherRestAPI.models.Measure;
import com.example.WeatherRestAPI.repository.MeasureRepository;
import com.example.WeatherRestAPI.repository.SensorRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class MeasureService {
    private final MeasureRepository measureRepository;
    private final SensorRepository sensorRepository;
    private final EntityManager entityManager;

    public void save(Measure measure) {
        enrichMeasure(measure);
        measureRepository.save(measure);
    }
    public List<Measure> findAll() {
        return measureRepository.findAll();
    }
    public long rainyDaysCount() {
        Session session = entityManager.unwrap(Session.class);
        long count = (Long)session.createQuery("select count(m.id) from Measure m")
                .getSingleResult();
        return count;
    }
    private void enrichMeasure(Measure measure) {
        measure.setAddedAt(LocalDateTime.now());
        measure.setSensor(sensorRepository.findByName(measure.getSensor().getName()).get());
    }
}
