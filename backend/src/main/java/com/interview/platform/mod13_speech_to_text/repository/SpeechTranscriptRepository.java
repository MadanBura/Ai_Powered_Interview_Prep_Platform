package com.interview.platform.mod13_speech_to_text.repository;

import com.interview.platform.mod13_speech_to_text.model.SpeechTranscriptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SpeechTranscriptRepository extends JpaRepository<SpeechTranscriptEntity, UUID> {
}
