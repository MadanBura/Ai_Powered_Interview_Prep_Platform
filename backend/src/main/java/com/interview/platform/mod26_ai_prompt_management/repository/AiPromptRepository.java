package com.interview.platform.mod26_ai_prompt_management.repository;

import com.interview.platform.mod26_ai_prompt_management.model.AiPromptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface AiPromptRepository extends JpaRepository<AiPromptEntity, UUID> {
}
