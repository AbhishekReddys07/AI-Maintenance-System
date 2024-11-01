package com.Predictive.Maintenance_System.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Predictive.Maintenance_System.Models.ProcessedSensorData;

@Repository
public interface ProcessedSensorDataRepository extends JpaRepository<ProcessedSensorData, Long> {
}
