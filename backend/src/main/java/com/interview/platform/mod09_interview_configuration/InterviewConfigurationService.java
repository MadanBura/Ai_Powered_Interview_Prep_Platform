package com.interview.platform.mod09_interview_configuration;

import com.interview.platform.mod09_interview_configuration.repository.InterviewConfigRepository;
import org.springframework.stereotype.Service;

@Service
public class InterviewConfigurationService {
    
    private final InterviewConfigRepository repository;

    public InterviewConfigurationService(InterviewConfigRepository repository) {
        this.repository = repository;
    }
    
    public int handle() {
        return 200; // Legacy stub for tests
    }

    public java.util.Map<String, Object> fetchOptions(java.util.Map<String, Object> req) {
        return new java.util.HashMap<>();
    }

}
