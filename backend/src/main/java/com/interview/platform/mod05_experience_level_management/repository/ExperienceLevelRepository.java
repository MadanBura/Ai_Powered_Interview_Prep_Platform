package com.interview.platform.mod05_experience_level_management.repository;

import com.interview.platform.mod05_experience_level_management.model.ExperienceLevelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

import java.util.Optional;

public interface ExperienceLevelRepository extends JpaRepository<ExperienceLevelEntity, UUID> {
    Optional<ExperienceLevelEntity> findByLabel(String label);
}
