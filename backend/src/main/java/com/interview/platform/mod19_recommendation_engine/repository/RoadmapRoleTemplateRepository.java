package com.interview.platform.mod19_recommendation_engine.repository;

import com.interview.platform.mod19_recommendation_engine.model.RoadmapRoleTemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoadmapRoleTemplateRepository extends JpaRepository<RoadmapRoleTemplateEntity, UUID> {
    Optional<RoadmapRoleTemplateEntity> findByRoleNameAndExperienceLevelId(String roleName, UUID experienceLevelId);
}
