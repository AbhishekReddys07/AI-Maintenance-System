package com.Predictive.Maintenance_System.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Predictive.Maintenance_System.Models.Machine;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {
    // Additional query methods can be defined here if needed
}
