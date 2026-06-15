package com.interview.platform.mod10_interview_session.repository;

import com.interview.platform.mod10_interview_session.model.SubmittedAnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SubmittedAnswerRepository extends JpaRepository<SubmittedAnswerEntity, UUID> {
    List<SubmittedAnswerEntity> findBySessionId(UUID sessionId);
}
