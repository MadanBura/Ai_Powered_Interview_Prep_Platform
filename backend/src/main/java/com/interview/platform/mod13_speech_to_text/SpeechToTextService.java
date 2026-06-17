package com.interview.platform.mod13_speech_to_text;

import com.interview.platform.mod13_speech_to_text.repository.SpeechTranscriptRepository;
import org.springframework.stereotype.Service;

@Service
public class SpeechToTextService {
    
    private final SpeechTranscriptRepository repository;

    public SpeechToTextService(SpeechTranscriptRepository repository) {
        this.repository = repository;
    }
    
    public int handle() {
        return 200; // Legacy stub for tests
    }

    public java.util.Map<String, Object> submitAudioForStt(java.util.Map<String, Object> req) {
        return new java.util.HashMap<>();
    }

}
