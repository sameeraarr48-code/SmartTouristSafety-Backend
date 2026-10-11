package com.smarttourist.safety.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.smarttourist.safety.entity.Destination;

public interface DestinationRepository
        extends JpaRepository<Destination, Long> {
}