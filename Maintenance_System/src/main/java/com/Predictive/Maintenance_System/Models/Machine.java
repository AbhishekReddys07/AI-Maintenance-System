package com.Predictive.Maintenance_System.Models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private String status;

    @OneToMany(mappedBy = "machine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SensorData> sensorDataList = new ArrayList<>();

    // Helper method to add sensor data
    public void addSensorData(SensorData sensorData) {
        sensorDataList.add(sensorData);
        sensorData.setMachine(this);  // Set the reverse relationship
    }

    // Helper method to remove sensor data
    public void removeSensorData(SensorData sensorData) {
        sensorDataList.remove(sensorData);
        sensorData.setMachine(null);  // Break the reverse relationship
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SensorData> getSensorDataList() {
        return sensorDataList;
    }

    public void setSensorDataList(List<SensorData> sensorDataList) {
        this.sensorDataList = sensorDataList;
    }
}
