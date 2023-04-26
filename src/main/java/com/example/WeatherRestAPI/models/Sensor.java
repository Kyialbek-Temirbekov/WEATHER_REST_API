package com.example.WeatherRestAPI.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sensor")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Sensor {
    @Id
    @Column(name = "name")
    private String name;
    @Column(name = "registered_at")
    private LocalDateTime registeredAt;
    @OneToMany(mappedBy = "sensor")
    @JsonManagedReference
    private List<Measure> measures;
}
