
package com.smarttourist.safety.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.smarttourist.safety.entity.SafetyInfo;
import com.smarttourist.safety.repository.SafetyInfoRepository;

@RestController
@RequestMapping("/api/safety")
public class SafetyInfoController {

    private final SafetyInfoRepository repository;

    public SafetyInfoController(SafetyInfoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<SafetyInfo> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public SafetyInfo getById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Safety information not found"));
    }

    @PostMapping
    public SafetyInfo add(@RequestBody SafetyInfo info) {
        return repository.save(info);
    }

    @PutMapping("/{id}")
    public SafetyInfo update(
            @PathVariable Long id,
            @RequestBody SafetyInfo info) {

        SafetyInfo old = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Safety information not found"));

        old.setRiskLevel(info.getRiskLevel());
        old.setSafetyInstructions(info.getSafetyInstructions());
        old.setHazardousAreas(info.getHazardousAreas());
        old.setEmergencyContact(info.getEmergencyContact());
        old.setWeatherConditions(info.getWeatherConditions());

        return repository.save(old);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
