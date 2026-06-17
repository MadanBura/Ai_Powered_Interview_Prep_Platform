package com.interview.platform.mod11_question_delivery.model;

import com.interview.platform.mod06_question_repository.model.QuestionEntity;
import com.interview.platform.mod10_interview_session.model.InterviewSessionEntity;
import jakarta.persistence.*;
import java.util.UUID;
import java.time.Instant;
import java.math.BigDecimal;

@Entity
@Table(name = "session_questions")
public class SessionQuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id")
    private InterviewSessionEntity session;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private QuestionEntity question;

    @Column(name = "asked_at")
    private Instant askedAt;

    @Column(name = "answer_text", columnDefinition = "TEXT")
    private String answerText;

    @Column(name = "ai_score")
    private BigDecimal aiScore;

    @Column(name = "human_score")
    private BigDecimal humanScore;

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public InterviewSessionEntity getSession() { return session; }
    public void setSession(InterviewSessionEntity session) { this.session = session; }
    public QuestionEntity getQuestion() { return question; }
    public void setQuestion(QuestionEntity question) { this.question = question; }
    public Instant getAskedAt() { return askedAt; }
    public void setAskedAt(Instant askedAt) { this.askedAt = askedAt; }
    public String getAnswerText() { return answerText; }
    public void setAnswerText(String answerText) { this.answerText = answerText; }
    public BigDecimal getAiScore() { return aiScore; }
    public void setAiScore(BigDecimal aiScore) { this.aiScore = aiScore; }
    public BigDecimal getHumanScore() { return humanScore; }
    public void setHumanScore(BigDecimal humanScore) { this.humanScore = humanScore; }
}
