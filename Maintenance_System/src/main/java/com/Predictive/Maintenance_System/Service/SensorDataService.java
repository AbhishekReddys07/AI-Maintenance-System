package com.Predictive.Maintenance_System.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Predictive.Maintenance_System.Models.SensorData;
import com.Predictive.Maintenance_System.Repository.SensorDataRepository;

import java.util.List;

@Service
public class SensorDataService {

    @Autowired
    private SensorDataRepository sensorDataRepository;

    public SensorData saveSensorData(SensorData sensorData) {
        return sensorDataRepository.save(sensorData);
    }

    public List<SensorData> getAllSensorData() {
        return sensorDataRepository.findAll();
    }

    public SensorData getSensorDataById(Long id) {
        return sensorDataRepository.findById(id).orElse(null);
    }

    // Other necessary methods can be added here
}
