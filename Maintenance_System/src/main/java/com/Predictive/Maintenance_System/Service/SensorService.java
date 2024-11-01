package com.Predictive.Maintenance_System.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Predictive.Maintenance_System.Models.Sensor;
import com.Predictive.Maintenance_System.Repository.SensorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SensorService {
	  @Autowired
    private final SensorRepository sensorRepository;

  
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    // Create a new sensor
    public Sensor createSensor(Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    // Get all sensors
    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }

    // Get sensor by ID
    public Optional<Sensor> getSensorById(Long id) {
        return sensorRepository.findById(id);
    }

    // Update sensor
    public Sensor updateSensor(Long id, Sensor sensorDetails) {
        Sensor sensor = sensorRepository.findById(id).orElseThrow(() -> new RuntimeException("Sensor not found"));
        sensor.setName(sensorDetails.getName());
        sensor.setValue(sensorDetails.getValue());
        return sensorRepository.save(sensor);
    }

    // Delete sensor
    public void deleteSensor(Long id) {
        sensorRepository.deleteById(id);
    }
}
