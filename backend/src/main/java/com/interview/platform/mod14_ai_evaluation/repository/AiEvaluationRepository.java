package com.interview.platform.mod14_ai_evaluation.repository;

import com.interview.platform.mod14_ai_evaluation.model.AiEvaluationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface AiEvaluationRepository extends JpaRepository<AiEvaluationEntity, UUID> {
}
