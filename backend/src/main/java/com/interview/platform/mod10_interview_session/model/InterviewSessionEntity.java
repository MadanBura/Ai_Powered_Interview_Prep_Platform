package com.interview.platform.mod10_interview_session.model;

import com.interview.platform.mod01_authentication.model.UserEntity;
import com.interview.platform.mod09_interview_configuration.model.InterviewConfigEntity;
import jakarta.persistence.*;
import java.util.UUID;
import java.time.Instant;
import java.math.BigDecimal;

@Entity
@Table(name = "interview_sessions")
public class InterviewSessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "config_id")
    private InterviewConfigEntity config;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_user_id")
    private UserEntity candidateUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interviewer_user_id")
    private UserEntity interviewerUser;

    @Column(name = "started_at")
    private Instant startedAt;

    @Column(name = "ended_at")
    private Instant endedAt;

    @Column
    private String status;

    @Column(name = "overall_score")
    private BigDecimal overallScore;

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public InterviewConfigEntity getConfig() { return config; }
    public void setConfig(InterviewConfigEntity config) { this.config = config; }
    public UserEntity getCandidateUser() { return candidateUser; }
    public void setCandidateUser(UserEntity candidateUser) { this.candidateUser = candidateUser; }
    public UserEntity getInterviewerUser() { return interviewerUser; }
    public void setInterviewerUser(UserEntity interviewerUser) { this.interviewerUser = interviewerUser; }
    public Instant getStartedAt() { return startedAt; }
    public void setStartedAt(Instant startedAt) { this.startedAt = startedAt; }
    public Instant getEndedAt() { return endedAt; }
    public void setEndedAt(Instant endedAt) { this.endedAt = endedAt; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public BigDecimal getOverallScore() { return overallScore; }
    public void setOverallScore(BigDecimal overallScore) { this.overallScore = overallScore; }
}