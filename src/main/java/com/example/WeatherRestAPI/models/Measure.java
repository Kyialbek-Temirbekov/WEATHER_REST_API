package com.example.WeatherRestAPI.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "measure")
@NoArgsConstructor
@Getter
@Setter
public class Measure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "value")
    private double value;
    @Column(name = "raining")
    private boolean raining;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sensor_name", referencedColumnName = "name")
    @JsonBackReference
    private Sensor sensor;
    @Column(name = "added_at")
    private LocalDateTime addedAt;
}
