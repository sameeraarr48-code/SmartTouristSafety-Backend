package com.smarttourist.safety.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.smarttourist.safety.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    User findByEmail(String email);
}