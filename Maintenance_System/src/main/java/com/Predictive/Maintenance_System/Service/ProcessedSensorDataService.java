package com.Predictive.Maintenance_System.Service;

import com.Predictive.Maintenance_System.Models.ProcessedSensorData;
import com.Predictive.Maintenance_System.Repository.ProcessedSensorDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProcessedSensorDataService {

    private final ProcessedSensorDataRepository processedSensorDataRepository;

    public ProcessedSensorDataService(ProcessedSensorDataRepository processedSensorDataRepository) {
        this.processedSensorDataRepository = processedSensorDataRepository;
    }

    // Create or Update (Upsert) ProcessedSensorData
    public ProcessedSensorData saveProcessedData(ProcessedSensorData processedData) {
        return processedSensorDataRepository.save(processedData);
    }

    // Retrieve all ProcessedSensorData
    public List<ProcessedSensorData> getAllProcessedData() {
        return processedSensorDataRepository.findAll();
    }

    // Retrieve a single ProcessedSensorData by ID
    public Optional<ProcessedSensorData> getProcessedDataById(Long id) {
        return processedSensorDataRepository.findById(id);
    }

    // Update ProcessedSensorData by ID
    public ProcessedSensorData updateProcessedData(Long id, ProcessedSensorData updatedData) {
        return processedSensorDataRepository.findById(id)
            .map(existingData -> {
                existingData.setValue(updatedData.getValue());  // Update fields as necessary
                return processedSensorDataRepository.save(existingData);
            })
            .orElseThrow(() -> new RuntimeException("Data not found with id: " + id));
    }

    // Delete ProcessedSensorData by ID
    public void deleteProcessedData(Long id) {
        if (processedSensorDataRepository.existsById(id)) {
            processedSensorDataRepository.deleteById(id);
        } else {
            throw new RuntimeException("Data not able to delete buy given id: " + id);
        }
    }
}
