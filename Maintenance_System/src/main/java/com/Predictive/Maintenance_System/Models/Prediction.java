package com.Predictive.Maintenance_System.Models;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Prediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timestamp;
    private String predictionResult;
    private double failureProbability;

    @ManyToOne
    @JoinColumn(name = "sensor_data_id")
    private SensorData sensorData;

    

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

	public String getPredictionResult() {
		return predictionResult;
	}

	public void setPredictionResult(String predictionResult) {
		this.predictionResult = predictionResult;
	}

	public double getFailureProbability() {
		return failureProbability;
	}

	public void setFailureProbability(double failureProbability) {
		this.failureProbability = failureProbability;
	}

	public SensorData getSensorData() {
		return sensorData;
	}

	public void setSensorData(SensorData sensorData) {
		this.sensorData = sensorData;
	}

    
}
