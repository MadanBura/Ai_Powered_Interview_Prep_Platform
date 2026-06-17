package com.interview.platform.mod19_recommendation_engine.repository;

import com.interview.platform.mod19_recommendation_engine.model.UserBadgeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserBadgeRepository extends JpaRepository<UserBadgeEntity, UUID> {
    List<UserBadgeEntity> findAllByUserIdOrderByEarnedAtDesc(UUID userId);
}
