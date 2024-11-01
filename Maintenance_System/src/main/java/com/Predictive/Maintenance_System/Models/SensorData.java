package com.Predictive.Maintenance_System.Models;

import java.time.LocalDateTime;
import java.util.Arrays;

import jakarta.persistence.*;

@Entity
public class SensorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timestamp;
    private double temperature;
    private double vibration;
    private double pressure;

    @ManyToOne
    @JoinColumn(name = "machine_id")
    private Machine machine;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        // Optional: Validate temperature range
        if (temperature < -50 || temperature > 150) {
            throw new IllegalArgumentException("Temperature out of range");
        }
        this.temperature = temperature;
    }

    public double getVibration() {
        return vibration;
    }

    public void setVibration(double vibration) {
        // Optional: Validate vibration level
        if (vibration < 0) {
            throw new IllegalArgumentException("Vibration cannot be negative");
        }
        this.vibration = vibration;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        // Optional: Validate pressure range
        if (pressure < 0) {
            throw new IllegalArgumentException("Pressure cannot be negative");
        }
        this.pressure = pressure;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    // Method to calculate average values (if needed)
    public static double calculateAverageTemperature(SensorData[] sensorData) {
        return Arrays.stream(sensorData)
                .mapToDouble(SensorData::getTemperature)
                .average()
                .orElse(0);
    }

    // Method to check if sensor data is within safe limits
    public boolean isDataWithinSafeLimits() {
        return (temperature >= 0 && temperature <= 100) && (vibration <= 5.0) && (pressure >= 0);
    }
}
