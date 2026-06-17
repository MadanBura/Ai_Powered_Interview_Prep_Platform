package com.interview.platform.mod12_voice_recording;

import com.interview.platform.mod12_voice_recording.repository.VoiceRecordingRepository;
import org.springframework.stereotype.Service;

@Service
public class VoiceRecordingService {
    
    private final VoiceRecordingRepository repository;

    public VoiceRecordingService(VoiceRecordingRepository repository) {
        this.repository = repository;
    }
    
    public int handle() {
        return 200; // Legacy stub for tests
    }

    public java.util.Map<String, Object> requestUploadUrl(java.util.Map<String, Object> req) {
        return new java.util.HashMap<>();
    }

}
