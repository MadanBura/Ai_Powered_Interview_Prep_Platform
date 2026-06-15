package com.interview.platform.mod14_ai_evaluation.model;

import com.interview.platform.mod11_question_delivery.model.SessionQuestionEntity;
import com.interview.platform.mod26_ai_prompt_management.model.AiPromptEntity;
import jakarta.persistence.*;
import java.util.UUID;
import java.math.BigDecimal;

@Entity
@Table(name = "ai_evaluations")
public class AiEvaluationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_question_id")
    private SessionQuestionEntity sessionQuestion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prompt_id")
    private AiPromptEntity prompt;

    @Column(name = "model_used")
    private String modelUsed;

    @Column(name = "avg_score_correlation")
    private BigDecimal avgScoreCorrelation;

    @Column(name = "processing_time_ms")
    private Integer processingTimeMs;

    @Column(name = "sentiment_score")
    private BigDecimal sentimentScore;

    @Column(columnDefinition = "JSON")
    private String strengths;

    @Column(columnDefinition = "JSON")
    private String weaknesses;

    @Column(columnDefinition = "JSON")
    private String recommendations;

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public SessionQuestionEntity getSessionQuestion() { return sessionQuestion; }
    public void setSessionQuestion(SessionQuestionEntity sessionQuestion) { this.sessionQuestion = sessionQuestion; }
    public AiPromptEntity getPrompt() { return prompt; }
    public void setPrompt(AiPromptEntity prompt) { this.prompt = prompt; }
    public String getModelUsed() { return modelUsed; }
    public void setModelUsed(String modelUsed) { this.modelUsed = modelUsed; }
    public BigDecimal getAvgScoreCorrelation() { return avgScoreCorrelation; }
    public void setAvgScoreCorrelation(BigDecimal avgScoreCorrelation) { this.avgScoreCorrelation = avgScoreCorrelation; }
    public Integer getProcessingTimeMs() { return processingTimeMs; }
    public void setProcessingTimeMs(Integer processingTimeMs) { this.processingTimeMs = processingTimeMs; }
    public BigDecimal getSentimentScore() { return sentimentScore; }
    public void setSentimentScore(BigDecimal sentimentScore) { this.sentimentScore = sentimentScore; }
    public String getStrengths() { return strengths; }
    public void setStrengths(String strengths) { this.strengths = strengths; }
    public String getWeaknesses() { return weaknesses; }
    public void setWeaknesses(String weaknesses) { this.weaknesses = weaknesses; }
    public String getRecommendations() { return recommendations; }
    public void setRecommendations(String recommendations) { this.recommendations = recommendations; }
}