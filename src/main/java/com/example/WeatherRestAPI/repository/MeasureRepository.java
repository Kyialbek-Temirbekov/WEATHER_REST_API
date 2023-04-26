package com.example.WeatherRestAPI.repository;

import com.example.WeatherRestAPI.models.Measure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasureRepository extends JpaRepository<Measure, Integer> {
}
