package com.interview.platform.mod12_voice_recording.model;

import com.interview.platform.mod11_question_delivery.model.SessionQuestionEntity;
import jakarta.persistence.*;
import java.util.UUID;
import java.time.Instant;

@Entity
@Table(name = "voice_recordings")
public class VoiceRecordingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_question_id")
    private SessionQuestionEntity sessionQuestion;

    @Column(name = "storage_path")
    private String storagePath;

    @Column(name = "duration_seconds")
    private Integer durationSeconds;

    @Column
    private String format;

    @Column
    private String status;

    @Column(name = "recorded_at")
    private Instant recordedAt;

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public SessionQuestionEntity getSessionQuestion() { return sessionQuestion; }
    public void setSessionQuestion(SessionQuestionEntity sessionQuestion) { this.sessionQuestion = sessionQuestion; }
    public String getStoragePath() { return storagePath; }
    public void setStoragePath(String storagePath) { this.storagePath = storagePath; }
    public Integer getDurationSeconds() { return durationSeconds; }
    public void setDurationSeconds(Integer durationSeconds) { this.durationSeconds = durationSeconds; }
    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Instant getRecordedAt() { return recordedAt; }
    public void setRecordedAt(Instant recordedAt) { this.recordedAt = recordedAt; }
}