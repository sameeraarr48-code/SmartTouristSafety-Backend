
package com.smarttourist.safety.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smarttourist.safety.entity.EmergencyService;
import com.smarttourist.safety.repository.EmergencyServiceRepository;

@Service
public class EmergencyServiceService {

    private final EmergencyServiceRepository repository;

    public EmergencyServiceService(
            EmergencyServiceRepository repository) {
        this.repository = repository;
    }

    public List<EmergencyService> getAllServices() {
        return repository.findAll();
    }

    public EmergencyService getServiceById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Emergency service not found"));
    }

    public EmergencyService addService(EmergencyService service) {
        return repository.save(service);
    }

    public EmergencyService updateService(
            Long id, EmergencyService service) {
        EmergencyService old = getServiceById(id);

        old.setName(service.getName());
        old.setServiceType(service.getServiceType());
        old.setLocation(service.getLocation());
        old.setContactNumber(service.getContactNumber());
        old.setAvailability(service.getAvailability());

        return repository.save(old);
    }

    public void deleteService(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Emergency service not found");
        }

        repository.deleteById(id);
    }
}
