package com.interview.platform.mod19_recommendation_engine.repository;

import com.interview.platform.mod19_recommendation_engine.model.UserRoadmapEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;
import java.util.UUID;

@Repository
public interface UserRoadmapRepository extends JpaRepository<UserRoadmapEntity, UUID> {
    Optional<UserRoadmapEntity> findByUserIdAndStatus(UUID userId, String status);
    List<UserRoadmapEntity> findAllByUserId(UUID userId);
}
