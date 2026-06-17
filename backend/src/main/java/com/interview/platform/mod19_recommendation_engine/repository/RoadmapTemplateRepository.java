package com.interview.platform.mod19_recommendation_engine.repository;

import com.interview.platform.mod19_recommendation_engine.model.RoadmapTemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoadmapTemplateRepository extends JpaRepository<RoadmapTemplateEntity, UUID> {
    Optional<RoadmapTemplateEntity> findByTechnologyIdAndExperienceLevelId(UUID technologyId, UUID experienceLevelId);
}
