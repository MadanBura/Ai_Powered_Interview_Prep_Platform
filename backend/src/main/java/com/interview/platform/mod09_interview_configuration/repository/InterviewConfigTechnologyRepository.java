package com.interview.platform.mod09_interview_configuration.repository;

import com.interview.platform.mod09_interview_configuration.model.InterviewConfigTechnologyEntity;
import com.interview.platform.mod09_interview_configuration.model.InterviewConfigTechnologyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewConfigTechnologyRepository extends JpaRepository<InterviewConfigTechnologyEntity, InterviewConfigTechnologyId> {
}
