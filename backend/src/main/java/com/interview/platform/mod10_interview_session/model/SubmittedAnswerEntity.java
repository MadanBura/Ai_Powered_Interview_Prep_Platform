package com.interview.platform.mod10_interview_session.model;

import jakarta.persistence.*;
import java.util.UUID;
import java.time.Instant;

@Entity
@Table(name = "submitted_answers")
public class SubmittedAnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id")
    private InterviewSessionEntity session;

    @Column(name = "question_id")
    private String questionId;

    @Column(columnDefinition = "TEXT")
    private String answerText;

    @Column
    private Instant submittedAt;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public InterviewSessionEntity getSession() { return session; }
    public void setSession(InterviewSessionEntity session) { this.session = session; }

    public String getQuestionId() { return questionId; }
    public void setQuestionId(String questionId) { this.questionId = questionId; }

    public String getAnswerText() { return answerText; }
    public void setAnswerText(String answerText) { this.answerText = answerText; }

    public Instant getSubmittedAt() { return submittedAt; }
    public void setSubmittedAt(Instant submittedAt) { this.submittedAt = submittedAt; }
}
