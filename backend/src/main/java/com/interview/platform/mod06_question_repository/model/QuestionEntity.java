package com.interview.platform.mod06_question_repository.model;

import com.interview.platform.mod04_technology_management.model.TechnologyEntity;
import com.interview.platform.mod07_bulk_upload.model.BulkUploadEntity;
import jakarta.persistence.*;
import java.util.UUID;
import java.math.BigDecimal;

@Entity
@Table(name = "questions")
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "question_text", nullable = false, columnDefinition = "TEXT")
    private String questionText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "technology_id")
    private TechnologyEntity technology;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bulk_upload_id")
    private BulkUploadEntity bulkUpload;

    @Column
    private String difficulty;

    @Column
    private String type;

    @Column
    private String category;

    @Column(name = "confidence_score")
    private BigDecimal confidenceScore;

    @Column
    private String status;

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getQuestionText() { return questionText; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }
    public TechnologyEntity getTechnology() { return technology; }
    public void setTechnology(TechnologyEntity technology) { this.technology = technology; }
    public BulkUploadEntity getBulkUpload() { return bulkUpload; }
    public void setBulkUpload(BulkUploadEntity bulkUpload) { this.bulkUpload = bulkUpload; }
    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public BigDecimal getConfidenceScore() { return confidenceScore; }
    public void setConfidenceScore(BigDecimal confidenceScore) { this.confidenceScore = confidenceScore; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
