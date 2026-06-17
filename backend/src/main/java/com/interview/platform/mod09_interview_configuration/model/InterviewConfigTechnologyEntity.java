package com.interview.platform.mod09_interview_configuration.model;

import com.interview.platform.mod04_technology_management.model.TechnologyEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "interview_config_technologies")
@IdClass(InterviewConfigTechnologyId.class)
public class InterviewConfigTechnologyEntity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "config_id", referencedColumnName = "id")
    private InterviewConfigEntity config;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "technology_id", referencedColumnName = "id")
    private TechnologyEntity technology;

    // Getters and Setters
    public InterviewConfigEntity getConfig() { return config; }
    public void setConfig(InterviewConfigEntity config) { this.config = config; }
    public TechnologyEntity getTechnology() { return technology; }
    public void setTechnology(TechnologyEntity technology) { this.technology = technology; }
}
