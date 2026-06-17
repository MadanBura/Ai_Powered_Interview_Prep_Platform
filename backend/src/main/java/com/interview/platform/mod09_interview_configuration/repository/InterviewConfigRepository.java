package com.interview.platform.mod09_interview_configuration.repository;

import com.interview.platform.mod09_interview_configuration.model.InterviewConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface InterviewConfigRepository extends JpaRepository<InterviewConfigEntity, UUID> {
}
