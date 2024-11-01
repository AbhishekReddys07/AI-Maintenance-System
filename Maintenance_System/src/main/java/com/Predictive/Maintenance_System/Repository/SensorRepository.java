package com.Predictive.Maintenance_System.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Predictive.Maintenance_System.Models.Sensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
    // You can add custom query methods here if needed
}
