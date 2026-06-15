package com.interview.platform.mod12_voice_recording.repository;

import com.interview.platform.mod12_voice_recording.model.VoiceRecordingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface VoiceRecordingRepository extends JpaRepository<VoiceRecordingEntity, UUID> {
}
