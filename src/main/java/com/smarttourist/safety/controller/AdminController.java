package com.smarttourist.safety.controller;

import java.util.List;

import com.smarttourist.safety.entity.User;
import com.smarttourist.safety.repository.UserRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smarttourist.safety.entity.Incident;
import com.smarttourist.safety.service.AdminService;
import java.util.Map;


@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {

    private final AdminService adminService;
    private final UserRepository userRepository;

    public AdminController(
        AdminService adminService,
        UserRepository userRepository) {
                            this.adminService = adminService;
                            this.userRepository = userRepository;
    }

    // View all incidents
    @GetMapping("/incidents")
    public List<Incident> getAllIncidents() {
        return adminService.getAllIncidents();
    }

    // Review a particular incident
    @GetMapping("/incidents/{id}")
    public Incident reviewIncident(@PathVariable Long id) {
        return adminService.reviewIncident(id);
    }

    
    @GetMapping("/dashboard/stats")
    public Map<String, Long> getDashboardStats() {
        return adminService.getDashboardStats();
    }

    @GetMapping("/users")
public List<UserResponse> getAllUsers() {
    return userRepository.findAll()
            .stream()
            .map(user -> new UserResponse(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getRole()))
            .toList();
}

public static class UserResponse {
    private final Long id;
    private final String name;
    private final String email;
    private final String role;

    public UserResponse(
            Long id, String name, String email, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
}
    
    // Update incident status
    @PatchMapping("/incidents/{id}/status")
    public Incident updateIncidentStatus(
            @PathVariable Long id,
            @RequestBody StatusRequest request) {

        return adminService.updateIncidentStatus(id, request.getStatus());
    }

    public static class StatusRequest {
        private String status;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}