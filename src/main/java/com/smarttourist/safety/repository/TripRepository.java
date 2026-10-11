package com.smarttourist.safety.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.smarttourist.safety.entity.Trip;

public interface TripRepository
        extends JpaRepository<Trip, Long> {
}