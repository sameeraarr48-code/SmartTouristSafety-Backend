package com.smarttourist.safety.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.smarttourist.safety.entity.TouristPlace;
import com.smarttourist.safety.repository.TouristPlaceRepository;

@RestController
@RequestMapping("/api/tourist-places")
@CrossOrigin(origins = "http://localhost:5173")
public class TouristPlaceController {

    private final TouristPlaceRepository repository;

    public TouristPlaceController(TouristPlaceRepository repository) {
        this.repository = repository;
    }

    // Get all famous places
    @GetMapping
    public List<TouristPlace> getAllPlaces() {
        return repository.findAll();
    }

    // Search famous places by city
    @GetMapping("/search")
    public List<TouristPlace> searchPlaces(
            @RequestParam String location) {
        return repository.findByLocationContainingIgnoreCase(location);
    }

    // Add a new tourist place
    @PostMapping
    public TouristPlace addPlace(@RequestBody TouristPlace place) {
        return repository.save(place);
    }
}
