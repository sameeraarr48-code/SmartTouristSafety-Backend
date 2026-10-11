package com.smarttourist.safety.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.smarttourist.safety.entity.Incident;
import com.smarttourist.safety.repository.IncidentRepository;

@Service
public class IncidentService {

    private final IncidentRepository repository;
    private final AIService aiService;

    public IncidentService(
            IncidentRepository repository,
            AIService aiService) {
        this.repository = repository;
        this.aiService = aiService;
    }

    public List<Incident> getAllIncidents() {
        return repository.findAll();
    }

    public Incident getIncidentById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Incident not found"));
    }

    public Incident reportIncident(Incident incident) {

        incident.setReportedAt(LocalDateTime.now());
        incident.setStatus("REPORTED");

        // Automatically classify incident priority
        String priority = aiService.classifyIncident(
                incident.getDescription());

        incident.setPriority(priority);

        return repository.save(incident);
    }

    public Incident updateIncidentStatus(
            Long id, String status) {

        Incident incident = getIncidentById(id);
        incident.setStatus(status);

        return repository.save(incident);
    }

    public void deleteIncident(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Incident not found");
        }

        repository.deleteById(id);
    }
}
