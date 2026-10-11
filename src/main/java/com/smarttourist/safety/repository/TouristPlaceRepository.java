package com.smarttourist.safety.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.smarttourist.safety.entity.TouristPlace;

public interface TouristPlaceRepository
        extends JpaRepository<TouristPlace, Long> {

    List<TouristPlace> findByLocationContainingIgnoreCase(String location);
}
