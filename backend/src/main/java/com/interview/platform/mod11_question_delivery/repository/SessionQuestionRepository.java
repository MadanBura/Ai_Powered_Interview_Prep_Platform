package com.interview.platform.mod11_question_delivery.repository;

import com.interview.platform.mod11_question_delivery.model.SessionQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SessionQuestionRepository extends JpaRepository<SessionQuestionEntity, UUID> {
    java.util.List<SessionQuestionEntity> findBySessionId(UUID sessionId);
}
