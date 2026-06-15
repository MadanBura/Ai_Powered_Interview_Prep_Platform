package com.interview.platform.mod09_interview_configuration.model;

import com.interview.platform.mod03_department_management.model.DepartmentEntity;
import com.interview.platform.mod05_experience_level_management.model.ExperienceLevelEntity;
import com.interview.platform.mod26_ai_prompt_management.model.AiPersonaEntity;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "interview_configs")
public class InterviewConfigEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private DepartmentEntity department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "experience_level_id")
    private ExperienceLevelEntity experienceLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ai_persona_id")
    private AiPersonaEntity aiPersona;

    @Column(name = "duration_min", nullable = false)
    private Integer durationMin;

    @Column(name = "rigor_level")
    private String rigorLevel;

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public DepartmentEntity getDepartment() { return department; }
    public void setDepartment(DepartmentEntity department) { this.department = department; }
    public ExperienceLevelEntity getExperienceLevel() { return experienceLevel; }
    public void setExperienceLevel(ExperienceLevelEntity experienceLevel) { this.experienceLevel = experienceLevel; }
    public AiPersonaEntity getAiPersona() { return aiPersona; }
    public void setAiPersona(AiPersonaEntity aiPersona) { this.aiPersona = aiPersona; }
    public Integer getDurationMin() { return durationMin; }
    public void setDurationMin(Integer durationMin) { this.durationMin = durationMin; }
    public String getRigorLevel() { return rigorLevel; }
    public void setRigorLevel(String rigorLevel) { this.rigorLevel = rigorLevel; }
}