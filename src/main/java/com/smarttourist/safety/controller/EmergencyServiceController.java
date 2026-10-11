
package com.smarttourist.safety.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.smarttourist.safety.entity.EmergencyService;
import com.smarttourist.safety.service.EmergencyServiceService;

@RestController
@RequestMapping("/api/emergency-services")
@CrossOrigin(origins = "http://localhost:5173")
public class EmergencyServiceController {

    private final EmergencyServiceService service;

    public EmergencyServiceController(
            EmergencyServiceService service) {
        this.service = service;
    }

    @GetMapping
    public List<EmergencyService> getAll() {
        return service.getAllServices();
    }

    @GetMapping("/{id}")
    public EmergencyService getById(@PathVariable Long id) {
        return service.getServiceById(id);
    }

    @PostMapping
    public EmergencyService add(
            @RequestBody EmergencyService emergencyService) {
        return service.addService(emergencyService);
    }

    @PutMapping("/{id}")
    public EmergencyService update(
            @PathVariable Long id,
            @RequestBody EmergencyService emergencyService) {
        return service.updateService(id, emergencyService);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteService(id);
    }
}
