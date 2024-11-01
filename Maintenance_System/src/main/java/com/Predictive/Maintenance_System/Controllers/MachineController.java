package com.Predictive.Maintenance_System.Controllers;

import com.Predictive.Maintenance_System.Models.Machine;
import com.Predictive.Maintenance_System.Models.SensorData;
import com.Predictive.Maintenance_System.Service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/machines")
public class MachineController {

    @Autowired
    private MachineService machineService;

    // Create or Update Machine (POST)
    @PostMapping
    public ResponseEntity<Machine> createOrUpdateMachine(@RequestBody Machine machine) {
        Machine savedMachine = machineService.saveMachine(machine);
        return ResponseEntity.ok(savedMachine);
    }

    // Retrieve all Machines (GET)
    @GetMapping
    public ResponseEntity<List<Machine>> getAllMachines() {
        List<Machine> machineList = machineService.getAllMachines();
        return ResponseEntity.ok(machineList);
    }

    // Retrieve a single Machine by ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Machine> getMachineById(@PathVariable Long id) {
        Optional<Machine> machine = machineService.getMachineById(id);
        return machine.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update Machine by ID (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Machine> updateMachine(@PathVariable Long id, @RequestBody Machine updatedMachine) {
        try {
            Machine updated = machineService.updateMachine(id, updatedMachine);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete Machine by ID (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMachine(@PathVariable Long id) {
        try {
            machineService.deleteMachine(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Add Sensor Data to a Machine (POST)
    @PostMapping("/{id}/sensorData")
    public ResponseEntity<Machine> addSensorDataToMachine(@PathVariable Long id, @RequestBody SensorData sensorData) {
        try {
            Machine updatedMachine = machineService.addSensorDataToMachine(id, sensorData);
            return ResponseEntity.ok(updatedMachine);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
