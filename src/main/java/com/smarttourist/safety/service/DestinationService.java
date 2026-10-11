
package com.smarttourist.safety.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smarttourist.safety.entity.Destination;
import com.smarttourist.safety.repository.DestinationRepository;

@Service
public class DestinationService {

    private final DestinationRepository repository;

    public DestinationService(DestinationRepository repository) {
        this.repository = repository;
    }

    public List<Destination> getAllDestinations() {
        return repository.findAll();
    }

    public Destination getDestinationById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Destination not found"));
    }

    public Destination addDestination(Destination destination) {
        return repository.save(destination);
    }

    public Destination updateDestination(Long id, Destination destination) {
        Destination old = getDestinationById(id);

        old.setName(destination.getName());
        old.setLocation(destination.getLocation());
        old.setDescription(destination.getDescription());
        old.setRiskLevel(destination.getRiskLevel());
        old.setImageUrl(destination.getImageUrl());

        return repository.save(old);
    }

    public void deleteDestination(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Destination not found");
        }

        repository.deleteById(id);
    }
}
