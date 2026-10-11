
package com.smarttourist.safety.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.smarttourist.safety.entity.Incident;
import com.smarttourist.safety.repository.IncidentRepository;
import com.smarttourist.safety.repository.UserRepository;
import com.smarttourist.safety.repository.TripRepository;
import com.smarttourist.safety.repository.TouristPlaceRepository;

@Service
public class AdminService {

    private final IncidentRepository incidentRepository;
    private final UserRepository userRepository;
    private final TripRepository tripRepository;
    private final TouristPlaceRepository touristPlaceRepository;

    public AdminService(
            IncidentRepository incidentRepository,
            UserRepository userRepository,
            TripRepository tripRepository,
            TouristPlaceRepository touristPlaceRepository) {

        this.incidentRepository = incidentRepository;
        this.userRepository = userRepository;
        this.tripRepository = tripRepository;
        this.touristPlaceRepository = touristPlaceRepository;
    }

    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }

    public Incident reviewIncident(Long id) {
        return incidentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Incident not found"));
    }

    public Incident updateIncidentStatus(Long id, String status) {
        Incident incident = reviewIncident(id);
        incident.setStatus(status);

        return incidentRepository.save(incident);
    }

    public Map<String, Long> getDashboardStats() {
        Map<String, Long> stats = new LinkedHashMap<>();

        stats.put("totalUsers", userRepository.count());
        stats.put("totalIncidents", incidentRepository.count());
        stats.put("totalTrips", tripRepository.count());
        stats.put("totalPlaces", touristPlaceRepository.count());

        stats.put("reportedIncidents",
                incidentRepository.countByStatus("REPORTED"));

        stats.put("inProgressIncidents",
                incidentRepository.countByStatus("IN_PROGRESS"));

        stats.put("resolvedIncidents",
                incidentRepository.countByStatus("RESOLVED"));

        return stats;
    }
}
