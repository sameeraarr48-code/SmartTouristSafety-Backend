
package com.smarttourist.safety.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.smarttourist.safety.entity.Trip;
import com.smarttourist.safety.service.TripService;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    private final TripService service;

    public TripController(TripService service) {
        this.service = service;
    }

    @GetMapping
    public List<Trip> getAll() {
        return service.getAllTrips();
    }

    @GetMapping("/{id}")
    public Trip getById(@PathVariable Long id) {
        return service.getTripById(id);
    }

    @PostMapping
    public Trip startTrip(@RequestBody Trip trip) {
        return service.startTrip(trip);
    }

    @PatchMapping("/{id}/status")
    public Trip updateStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> request) {
        return service.updateTripStatus(id, request.get("status"));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteTrip(id);
    }
}
