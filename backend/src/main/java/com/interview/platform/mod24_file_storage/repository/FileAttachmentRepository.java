package com.interview.platform.mod24_file_storage.repository;

import com.interview.platform.mod24_file_storage.model.FileAttachmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface FileAttachmentRepository extends JpaRepository<FileAttachmentEntity, UUID> {
}
