
package com.smarttourist.safety.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.smarttourist.safety.entity.Destination;
import com.smarttourist.safety.service.DestinationService;

@RestController
@RequestMapping("/api/destinations")
public class DestinationController {

    private final DestinationService service;

    public DestinationController(DestinationService service) {
        this.service = service;
    }

    @GetMapping
    public List<Destination> getAll() {
        return service.getAllDestinations();
    }

    @GetMapping("/{id}")
    public Destination getById(@PathVariable Long id) {
        return service.getDestinationById(id);
    }

    @PostMapping
    public Destination add(@RequestBody Destination destination) {
        return service.addDestination(destination);
    }

    @PutMapping("/{id}")
    public Destination update(
            @PathVariable Long id,
            @RequestBody Destination destination) {
        return service.updateDestination(id, destination);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteDestination(id);
    }
}
