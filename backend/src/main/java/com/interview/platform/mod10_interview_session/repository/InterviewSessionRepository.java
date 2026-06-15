package com.interview.platform.mod10_interview_session.repository;

import com.interview.platform.mod10_interview_session.model.InterviewSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface InterviewSessionRepository extends JpaRepository<InterviewSessionEntity, UUID> {
    java.util.List<InterviewSessionEntity> findAllByStatus(String status);
    java.util.List<InterviewSessionEntity> findTop5ByOrderByStartedAtDesc();
    java.util.List<InterviewSessionEntity> findAllByCandidateUserIdOrderByStartedAtDesc(UUID candidateUserId);
    java.util.List<InterviewSessionEntity> findAllByCandidateUserEmailOrderByStartedAtDesc(String email);
}
