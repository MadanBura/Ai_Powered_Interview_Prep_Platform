package com.interview.platform.mod19_recommendation_engine.dto;

import java.util.List;

public class TopicDto {
    private String title;
    private List<String> subtopics;
    private String codeSnippet;
    private List<String> pros;
    private List<String> cons;
    private List<String> useCases;
    private List<String> tags;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public List<String> getSubtopics() { return subtopics; }
    public void setSubtopics(List<String> subtopics) { this.subtopics = subtopics; }
    public String getCodeSnippet() { return codeSnippet; }
    public void setCodeSnippet(String codeSnippet) { this.codeSnippet = codeSnippet; }
    public List<String> getPros() { return pros; }
    public void setPros(List<String> pros) { this.pros = pros; }
    public List<String> getCons() { return cons; }
    public void setCons(List<String> cons) { this.cons = cons; }
    public List<String> getUseCases() { return useCases; }
    public void setUseCases(List<String> useCases) { this.useCases = useCases; }
    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }
}
