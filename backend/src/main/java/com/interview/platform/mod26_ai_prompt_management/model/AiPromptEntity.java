package com.interview.platform.mod26_ai_prompt_management.model;

import com.interview.platform.mod01_authentication.model.UserEntity;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "ai_prompts")
public class AiPromptEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer version;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "model_target")
    private String modelTarget;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_id")
    private AiPersonaEntity persona;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private UserEntity createdBy;

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getVersion() { return version; }
    public void setVersion(Integer version) { this.version = version; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getModelTarget() { return modelTarget; }
    public void setModelTarget(String modelTarget) { this.modelTarget = modelTarget; }
    public AiPersonaEntity getPersona() { return persona; }
    public void setPersona(AiPersonaEntity persona) { this.persona = persona; }
    public UserEntity getCreatedBy() { return createdBy; }
    public void setCreatedBy(UserEntity createdBy) { this.createdBy = createdBy; }
}