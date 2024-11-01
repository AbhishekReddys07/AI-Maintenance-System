package com.Predictive.Maintenance_System.Controllers;

import com.Predictive.Maintenance_System.Models.ProcessedSensorData;
import com.Predictive.Maintenance_System.Service.ProcessedSensorDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/processed-sensor-data")
public class ProcessedSensorDataController {

    private final ProcessedSensorDataService processedSensorDataService;

    public ProcessedSensorDataController(ProcessedSensorDataService processedSensorDataService) {
        this.processedSensorDataService = processedSensorDataService;
    }

    // Create or Update ProcessedSensorData (POST)
    @PostMapping
    public ResponseEntity<ProcessedSensorData> createOrUpdateSensorData(@RequestBody ProcessedSensorData processedData) {
        ProcessedSensorData savedData = processedSensorDataService.saveProcessedData(processedData);
        return ResponseEntity.ok(savedData);
    }

    // Retrieve all ProcessedSensorData (GET)
    @GetMapping
    public ResponseEntity<List<ProcessedSensorData>> getAllProcessedSensorData() {
        List<ProcessedSensorData> dataList = processedSensorDataService.getAllProcessedData();
        return ResponseEntity.ok(dataList);
    }

    // Retrieve a single ProcessedSensorData by ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<ProcessedSensorData> getProcessedSensorDataById(@PathVariable Long id) {
        Optional<ProcessedSensorData> data = processedSensorDataService.getProcessedDataById(id);
        return data.map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // Update a single ProcessedSensorData by ID (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<ProcessedSensorData> updateProcessedSensorData(
            @PathVariable Long id, @RequestBody ProcessedSensorData updatedData) {
        try {
            ProcessedSensorData updated = processedSensorDataService.updateProcessedData(id, updatedData);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a single ProcessedSensorData by ID (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProcessedSensorData(@PathVariable Long id) {
        try {
            processedSensorDataService.deleteProcessedData(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
