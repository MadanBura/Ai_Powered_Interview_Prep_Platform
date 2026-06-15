package com.interview.platform.mod22_audit_logs.repository;

import com.interview.platform.mod22_audit_logs.model.AuditLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface AuditLogRepository extends JpaRepository<AuditLogEntity, UUID> {
}
