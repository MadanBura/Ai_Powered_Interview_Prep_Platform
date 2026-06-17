package com.interview.platform.mod26_ai_prompt_management.repository;

import com.interview.platform.mod26_ai_prompt_management.model.AiPersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface AiPersonaRepository extends JpaRepository<AiPersonaEntity, UUID> {
}
