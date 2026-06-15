package com.interview.platform.mod13_speech_to_text.model;

import com.interview.platform.mod12_voice_recording.model.VoiceRecordingEntity;
import jakarta.persistence.*;
import java.util.UUID;
import java.time.Instant;
import java.math.BigDecimal;

@Entity
@Table(name = "speech_transcripts")
public class SpeechTranscriptEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voice_recording_id")
    private VoiceRecordingEntity voiceRecording;

    @Column(name = "transcript_text", columnDefinition = "TEXT")
    private String transcriptText;

    @Column(name = "engine_used")
    private String engineUsed;

    @Column
    private BigDecimal confidence;

    @Column(name = "transcribed_at")
    private Instant transcribedAt;

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public VoiceRecordingEntity getVoiceRecording() { return voiceRecording; }
    public void setVoiceRecording(VoiceRecordingEntity voiceRecording) { this.voiceRecording = voiceRecording; }
    public String getTranscriptText() { return transcriptText; }
    public void setTranscriptText(String transcriptText) { this.transcriptText = transcriptText; }
    public String getEngineUsed() { return engineUsed; }
    public void setEngineUsed(String engineUsed) { this.engineUsed = engineUsed; }
    public BigDecimal getConfidence() { return confidence; }
    public void setConfidence(BigDecimal confidence) { this.confidence = confidence; }
    public Instant getTranscribedAt() { return transcribedAt; }
    public void setTranscribedAt(Instant transcribedAt) { this.transcribedAt = transcribedAt; }
}
