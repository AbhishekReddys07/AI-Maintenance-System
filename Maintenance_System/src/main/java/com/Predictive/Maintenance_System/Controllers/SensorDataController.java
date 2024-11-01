package com.Predictive.Maintenance_System.Controllers;

import com.Predictive.Maintenance_System.Models.ProcessedSensorData;
import com.Predictive.Maintenance_System.Service.ProcessedSensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sensor-data")
public class SensorDataController {

    private final ProcessedSensorDataService processedSensorDataService;

    @Autowired
    public SensorDataController(ProcessedSensorDataService processedSensorDataService) {
        this.processedSensorDataService = processedSensorDataService;
    }

    // Kafka Listener to consume sensor data messages
    @KafkaListener(topics = "sensor-data", groupId = "sensor-group")
    public void listen(String message) {
        System.out.println("Received message from Kafka: " + message);

        // Process the sensor data and save it to the database
        ProcessedSensorData processedData = new ProcessedSensorData();
        processedData.setValue(message);  // Store the Kafka message
        processedSensorDataService.saveProcessedData(processedData);
    }

    // API to create or update processed sensor data
    @PostMapping
    public ResponseEntity<ProcessedSensorData> createOrUpdateProcessedData(@RequestBody ProcessedSensorData processedData) {
        ProcessedSensorData savedData = processedSensorDataService.saveProcessedData(processedData);
        return ResponseEntity.ok(savedData);
    }

    // API to retrieve all processed sensor data
    @GetMapping
    public ResponseEntity<List<ProcessedSensorData>> getAllProcessedData() {
        List<ProcessedSensorData> dataList = processedSensorDataService.getAllProcessedData();
        return ResponseEntity.ok(dataList);
    }

    // API to retrieve a specific sensor data by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProcessedSensorData> getProcessedDataById(@PathVariable Long id) {
        Optional<ProcessedSensorData> data = processedSensorDataService.getProcessedDataById(id);
        return data.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // API to update processed sensor data by ID
    @PutMapping("/{id}")
    public ResponseEntity<ProcessedSensorData> updateProcessedData(@PathVariable Long id, @RequestBody ProcessedSensorData updatedData) {
        ProcessedSensorData updated = processedSensorDataService.updateProcessedData(id, updatedData);
        return ResponseEntity.ok(updated);
    }

    // API to delete processed sensor data by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProcessedData(@PathVariable Long id) {
        processedSensorDataService.deleteProcessedData(id);
        return ResponseEntity.noContent().build();
    }
}
