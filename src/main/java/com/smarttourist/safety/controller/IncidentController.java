
package com.smarttourist.safety.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.smarttourist.safety.entity.Incident;
import com.smarttourist.safety.service.IncidentService;

@RestController
@RequestMapping("/api/incidents")
@CrossOrigin(origins = "http://localhost:5173")
public class IncidentController {

    private final IncidentService service;

    public IncidentController(IncidentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Incident> getAll() {
        return service.getAllIncidents();
    }

    @GetMapping("/{id}")
    public Incident getById(@PathVariable Long id) {
        return service.getIncidentById(id);
    }

    @PostMapping
    public Incident report(@RequestBody Incident incident) {
        return service.reportIncident(incident);
    }

    @PatchMapping("/{id}/status")
    public Incident updateStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> request) {
        return service.updateIncidentStatus(id, request.get("status"));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteIncident(id);
    }
}
