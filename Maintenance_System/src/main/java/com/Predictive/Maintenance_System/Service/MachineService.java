package com.Predictive.Maintenance_System.Service;
import com.Predictive.Maintenance_System.Models.Machine;
import com.Predictive.Maintenance_System.Models.SensorData;
import com.Predictive.Maintenance_System.Repository.MachineRepository;
import com.Predictive.Maintenance_System.Repository.SensorDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MachineService {

    @Autowired
    private MachineRepository machineRepository;

    @Autowired
    private SensorDataRepository sensorDataRepository;

    // Create or Update (Upsert) Machine
    public Machine saveMachine(Machine machine) {
        return machineRepository.save(machine);
    }

    // Retrieve all Machines
    public List<Machine> getAllMachines() {
        return machineRepository.findAll();
    }

    // Retrieve a single Machine by ID
    public Optional<Machine> getMachineById(Long id) {
        return machineRepository.findById(id);
    }

    // Update Machine by ID
    public Machine updateMachine(Long id, Machine updatedMachine) {
        return machineRepository.findById(id)
            .map(existingMachine -> {
                existingMachine.setName(updatedMachine.getName());  // Update fields as necessary
                existingMachine.setLocation(updatedMachine.getLocation());
                existingMachine.setStatus(updatedMachine.getStatus());
                return machineRepository.save(existingMachine);
            })
            .orElseThrow(() -> new RuntimeException("Machine not found with id: " + id));
    }

    // Delete Machine by ID
    public void deleteMachine(Long id) {
        if (machineRepository.existsById(id)) {
            machineRepository.deleteById(id);
        } else {
            throw new RuntimeException("Machine not found with id: " + id);
        }
    }

    // Add Sensor Data to a Machine
    public Machine addSensorDataToMachine(Long machineId, SensorData sensorData) {
        Machine machine = machineRepository.findById(machineId)
            .orElseThrow(() -> new RuntimeException("Machine not found with id: " + machineId));
        
        machine.addSensorData(sensorData);  // Add sensor data to the machine
        return machineRepository.save(machine);  // Save the updated machine
    }
}
