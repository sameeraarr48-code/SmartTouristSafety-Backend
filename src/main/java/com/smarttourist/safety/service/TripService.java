
package com.smarttourist.safety.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.smarttourist.safety.entity.Trip;
import com.smarttourist.safety.repository.TripRepository;

@Service
public class TripService {

    private final TripRepository repository;

    public TripService(TripRepository repository) {
        this.repository = repository;
    }

    public List<Trip> getAllTrips() {
        return repository.findAll();
    }

    public Trip getTripById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trip not found"));
    }

    public Trip startTrip(Trip trip) {
        if (trip.getPlannedReturnTime() == null ||
                !trip.getPlannedReturnTime().isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException(
                    "Planned return time must be in the future");
        }

        trip.setStartTime(LocalDateTime.now());
        trip.setStatus("ACTIVE");

        return repository.save(trip);
    }

    public Trip updateTripStatus(Long id, String status) {
        Trip trip = getTripById(id);
        trip.setStatus(status);
        return repository.save(trip);
    }

    public void deleteTrip(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Trip not found");
        }

        repository.deleteById(id);
    }
}
