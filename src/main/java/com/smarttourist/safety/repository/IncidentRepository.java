package com.smarttourist.safety.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.smarttourist.safety.entity.Incident;

public interface IncidentRepository
        extends JpaRepository<Incident, Long> {
	
	long countByStatus(String status);
}