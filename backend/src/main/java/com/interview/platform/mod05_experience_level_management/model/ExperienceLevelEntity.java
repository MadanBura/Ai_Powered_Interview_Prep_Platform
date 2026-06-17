package com.interview.platform.mod05_experience_level_management.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "experience_levels")
public class ExperienceLevelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String label;

    @Column(name = "rank_val", nullable = false)
    private Integer rankVal;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public Integer getRankVal() { return rankVal; }
    public void setRankVal(Integer rankVal) { this.rankVal = rankVal; }
}