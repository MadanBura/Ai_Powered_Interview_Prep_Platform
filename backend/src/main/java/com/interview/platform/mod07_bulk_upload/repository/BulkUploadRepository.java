package com.interview.platform.mod07_bulk_upload.repository;

import com.interview.platform.mod07_bulk_upload.model.BulkUploadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface BulkUploadRepository extends JpaRepository<BulkUploadEntity, UUID> {
}
