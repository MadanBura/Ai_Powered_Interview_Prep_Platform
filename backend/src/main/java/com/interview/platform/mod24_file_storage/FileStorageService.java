package com.interview.platform.mod24_file_storage;

import com.interview.platform.mod24_file_storage.repository.FileAttachmentRepository;
import org.springframework.stereotype.Service;

@Service
public class FileStorageService {
    
    private final FileAttachmentRepository repository;

    public FileStorageService(FileAttachmentRepository repository) {
        this.repository = repository;
    }
    
    public int handle() {
        return 200; // Legacy stub for tests
    }

    public java.util.Map<String, Object> generateUploadUrl(java.util.Map<String, Object> req) {
        return new java.util.HashMap<>();
    }

    public java.util.Map<String, Object> deleteFileStorage(String id) {
        repository.deleteById(java.util.UUID.fromString(id));
        java.util.Map<String, Object> res = new java.util.HashMap<>();
        res.put("deleted", true);
        return res;
    }

}
