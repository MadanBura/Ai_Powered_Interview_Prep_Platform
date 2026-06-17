package com.interview.platform.mod19_recommendation_engine.repository;

import com.interview.platform.mod19_recommendation_engine.model.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TopicRepository extends JpaRepository<TopicEntity, UUID> {
    List<TopicEntity> findByTechAndLevel(String tech, String level);
    Optional<TopicEntity> findByTitle(String title);
}
