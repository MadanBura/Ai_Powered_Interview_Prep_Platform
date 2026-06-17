package com.interview.platform.mod04_technology_management.repository;

import com.interview.platform.mod04_technology_management.model.TechnologyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

import java.util.Optional;

public interface TechnologyRepository extends JpaRepository<TechnologyEntity, UUID> {
    Optional<TechnologyEntity> findByName(String name);
}
