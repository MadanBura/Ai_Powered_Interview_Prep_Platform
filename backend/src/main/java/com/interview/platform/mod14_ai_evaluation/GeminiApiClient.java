package com.interview.platform.mod14_ai_evaluation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.platform.config.GeminiConfig;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class GeminiApiClient {

    private final GeminiConfig config;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public GeminiApiClient(GeminiConfig config) {
        this.config = config;
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public Map<String, Object> evaluateAnswer(String question, String answer) {
        String prompt = "You are an expert technical interviewer. Evaluate the candidate's answer to the following question.\n\n" +
                "Question: " + question + "\n" +
                "Candidate's Answer: " + (answer == null || answer.trim().isEmpty() ? "[NO ANSWER PROVIDED/SKIPPED]" : answer) + "\n\n" +
                "Respond ONLY with a valid JSON object matching this exact structure, with no markdown formatting or extra text:\n" +
                "{\n" +
                "  \"score\": 85.0,\n" +
                "  \"isCorrect\": true,\n" +
                "  \"aiFeedback\": \"Specific feedback string\",\n" +
                "  \"expectedAnswer\": \"The ideal answer string\",\n" +
                "  \"keyConceptsHit\": [\"concept1\", \"concept2\"],\n" +
                "  \"keyConceptsMissed\": [\"concept3\"]\n" +
                "}";

        Map<String, Object> requestBody = new HashMap<>();
        List<Map<String, Object>> contents = new ArrayList<>();
        Map<String, Object> content = new HashMap<>();
        List<Map<String, Object>> parts = new ArrayList<>();
        Map<String, Object> part = new HashMap<>();
        part.put("text", prompt);
        parts.add(part);
        content.put("parts", parts);
        contents.add(content);
        requestBody.put("contents", contents);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        String urlWithKey = config.getUrl() + "?key=" + config.getKey();

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(urlWithKey, entity, String.class);
            JsonNode rootNode = objectMapper.readTree(response.getBody());
            String textResponse = rootNode.path("candidates").get(0).path("content").path("parts").get(0).path("text").asText();
            
            // Clean markdown json formatting if Gemini includes it
            textResponse = textResponse.replaceAll("```json", "").replaceAll("```", "").trim();
            
            return objectMapper.readValue(textResponse, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
            // Fallback object on failure
            Map<String, Object> fallback = new HashMap<>();
            fallback.put("score", 0.0);
            fallback.put("isCorrect", false);
            fallback.put("aiFeedback", "Failed to analyze answer using AI. Please try again.");
            fallback.put("expectedAnswer", "N/A");
            fallback.put("keyConceptsHit", new ArrayList<>());
            fallback.put("keyConceptsMissed", new ArrayList<>());
            return fallback;
        }
    }
}
