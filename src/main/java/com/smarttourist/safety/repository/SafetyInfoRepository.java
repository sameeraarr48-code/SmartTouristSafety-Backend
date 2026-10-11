package com.smarttourist.safety.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.smarttourist.safety.entity.SafetyInfo;

public interface SafetyInfoRepository
        extends JpaRepository<SafetyInfo, Long> {
}