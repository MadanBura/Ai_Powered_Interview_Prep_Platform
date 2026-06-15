package com.interview.platform.mod07_bulk_upload;

import com.interview.platform.mod07_bulk_upload.repository.BulkUploadRepository;
import org.springframework.stereotype.Service;

@Service
public class DocumentUploadPipelineService {
    
    private final BulkUploadRepository repository;

    public DocumentUploadPipelineService(BulkUploadRepository repository) {
        this.repository = repository;
    }
    
    public int handle() {
        return 200; // Legacy stub for tests
    }

    public java.util.Map<String, Object> triggerIngestion(java.util.Map<String, Object> req) {
        return new java.util.HashMap<>();
    }

    public java.util.Map<String, Object> pollTaskStatus(java.util.Map<String, Object> req) {
        return new java.util.HashMap<>();
    }

}
