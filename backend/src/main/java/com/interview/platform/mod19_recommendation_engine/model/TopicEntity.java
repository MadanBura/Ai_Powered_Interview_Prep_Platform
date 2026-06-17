package com.interview.platform.mod19_recommendation_engine.model;

import jakarta.persistence.*;
import java.util.UUID;
import java.time.LocalDateTime;

@Entity
@Table(name = "topics")
public class TopicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "char(36)")
    private UUID id;

    private String tech;
    private String level;
    private String title;

    @Column(columnDefinition = "json")
    private String subtopics;

    @Column(columnDefinition = "text")
    private String codeSnippet;

    @Column(columnDefinition = "json")
    private String pros;

    @Column(columnDefinition = "json")
    private String cons;

    @Column(columnDefinition = "json")
    private String useCases;

    @Column(columnDefinition = "json")
    private String tags;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getTech() { return tech; }
    public void setTech(String tech) { this.tech = tech; }
    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getSubtopics() { return subtopics; }
    public void setSubtopics(String subtopics) { this.subtopics = subtopics; }
    public String getCodeSnippet() { return codeSnippet; }
    public void setCodeSnippet(String codeSnippet) { this.codeSnippet = codeSnippet; }
    public String getPros() { return pros; }
    public void setPros(String pros) { this.pros = pros; }
    public String getCons() { return cons; }
    public void setCons(String cons) { this.cons = cons; }
    public String getUseCases() { return useCases; }
    public void setUseCases(String useCases) { this.useCases = useCases; }
    public String getTags() { return tags; }
    public void setTags(String tags) { this.tags = tags; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
