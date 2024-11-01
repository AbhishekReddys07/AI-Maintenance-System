package com.Predictive.Maintenance_System.Controllers;

import org.springframework.web.bind.annotation.*;

import com.Predictive.Maintenance_System.Models.Sensor;
import com.Predictive.Maintenance_System.Service.SensorProducer;
import com.Predictive.Maintenance_System.Service.SensorService;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;
    private final SensorProducer sensorProducer;

    
    public SensorController(SensorService sensorService, SensorProducer sensorProducer) {
        this.sensorService = sensorService;
        this.sensorProducer = sensorProducer;
    }

    // Create a new sensor
    @PostMapping
    public Sensor createSensor(@RequestBody Sensor sensor) {
        Sensor createdSensor = sensorService.createSensor(sensor);
        // Send sensor data to Kafka
        sensorProducer.sendMessage("sensor-data", createdSensor.toString());
        return createdSensor;
    }

    // Get all sensors
    @GetMapping
    public List<Sensor> getAllSensors() {
        return sensorService.getAllSensors();
    }

    // Get sensor by ID
    @GetMapping("/{id}")
    public Sensor getSensorById(@PathVariable Long id) {
        return sensorService.getSensorById(id).orElseThrow(() -> new RuntimeException("Sensor not found"));
    }

    // Update sensor
    @PutMapping("/{id}")
    public Sensor updateSensor(@PathVariable Long id, @RequestBody Sensor sensorDetails) {
        return sensorService.updateSensor(id, sensorDetails);
    }

    // Delete sensor
    @DeleteMapping("/{id}")
    public void deleteSensor(@PathVariable Long id) {
        sensorService.deleteSensor(id);
    }
}
