package com.interview.platform.mod09_interview_configuration.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class InterviewConfigTechnologyId implements Serializable {
    private UUID config;
    private UUID technology;

    public InterviewConfigTechnologyId() {}

    public InterviewConfigTechnologyId(UUID config, UUID technology) {
        this.config = config;
        this.technology = technology;
    }

    public UUID getConfig() { return config; }
    public void setConfig(UUID config) { this.config = config; }
    public UUID getTechnology() { return technology; }
    public void setTechnology(UUID technology) { this.technology = technology; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InterviewConfigTechnologyId that = (InterviewConfigTechnologyId) o;
        return Objects.equals(config, that.config) &&
               Objects.equals(technology, that.technology);
    }

    @Override
    public int hashCode() {
        return Objects.hash(config, technology);
    }
}
