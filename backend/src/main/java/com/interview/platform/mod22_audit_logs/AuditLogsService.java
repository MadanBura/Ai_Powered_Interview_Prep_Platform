package com.interview.platform.mod22_audit_logs;

import com.interview.platform.mod22_audit_logs.repository.AuditLogRepository;
import org.springframework.stereotype.Service;

@Service
public class AuditLogsService {
    
    private final AuditLogRepository repository;

    public AuditLogsService(AuditLogRepository repository) {
        this.repository = repository;
    }
    
    public int handle() {
        return 200; // Legacy stub for tests
    }

    public java.util.Map<String, Object> listAuditLogs(java.util.Map<String, Object> req) {
        java.util.List<com.interview.platform.mod22_audit_logs.model.AuditLogEntity> logs = repository.findAll();
        java.util.List<java.util.Map<String, Object>> logDtos = logs.stream().map(log -> {
            java.util.Map<String, Object> dto = new java.util.HashMap<>();
            dto.put("id", log.getId().toString());
            dto.put("action", log.getAction());
            dto.put("entityType", log.getEntityType());
            dto.put("entityId", log.getEntityId());
            dto.put("user", log.getUser() != null ? log.getUser().getEmail() : "System");
            return dto;
        }).collect(java.util.stream.Collectors.toList());
        java.util.Map<String, Object> response = new java.util.HashMap<>();
        response.put("logs", logDtos);
        return response;
    }

    public java.util.Map<String, Object> getAuditLogDetail(java.util.Map<String, Object> req) {
        return new java.util.HashMap<>();
    }

}
