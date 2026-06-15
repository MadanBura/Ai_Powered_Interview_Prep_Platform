package com.interview.platform.mod06_question_repository.repository;

import com.interview.platform.mod06_question_repository.model.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface QuestionRepository extends JpaRepository<QuestionEntity, UUID> {
    java.util.List<QuestionEntity> findByBulkUploadId(UUID bulkUploadId);
    java.util.List<QuestionEntity> findByTechnologyId(UUID technologyId);
    java.util.List<QuestionEntity> findByTechnologyIdAndStatus(UUID technologyId, String status);
}
