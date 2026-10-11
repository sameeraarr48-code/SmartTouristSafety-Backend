package com.smarttourist.safety.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.smarttourist.safety.entity.EmergencyService;

public interface EmergencyServiceRepository
        extends JpaRepository<EmergencyService, Long> {
}