package com.Predictive.Maintenance_System.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Predictive.Maintenance_System.Models.SensorData;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, Long> {
    // Additional query methods can be defined here if needed
}
