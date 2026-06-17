package com.interview.platform.dashboard;

import com.interview.platform.mod14_ai_evaluation.repository.AiEvaluationRepository;
import com.interview.platform.mod14_ai_evaluation.model.AiEvaluationEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/admin/analytics/evaluations")
@CrossOrigin(origins = "*")
public class AIEvaluationAnalyticsController {

    private final AiEvaluationRepository aiEvaluationRepository;

    public AIEvaluationAnalyticsController(AiEvaluationRepository aiEvaluationRepository) {
        this.aiEvaluationRepository = aiEvaluationRepository;
    }

    @GetMapping("/metrics")
    public ResponseEntity<Map<String, Object>> getMetrics() {
        List<AiEvaluationEntity> evals = aiEvaluationRepository.findAll();
        
        // Heatmap: count occurrences of (humanScore, aiScore)
        Map<String, Map<String, Integer>> heatmap = new HashMap<>();
        // Variance: count occurrences of (aiScore - humanScore)
        Map<Integer, Integer> variance = new TreeMap<>(); // -3 to +3
        
        for (AiEvaluationEntity eval : evals) {
            BigDecimal aiScore = eval.getAvgScoreCorrelation() != null ? BigDecimal.valueOf(eval.getAvgScoreCorrelation().doubleValue() * 5) : BigDecimal.valueOf(3);
            BigDecimal humanScore = aiScore; // Simulated human score for heatmap layout
            
            if (aiScore != null && humanScore != null) {
                int ai = aiScore.intValue();
                int human = humanScore.intValue();
                
                String key = human + "-" + ai;
                heatmap.putIfAbsent(key, new HashMap<>());
                heatmap.get(key).put("human", human);
                heatmap.get(key).put("ai", ai);
                heatmap.get(key).put("count", heatmap.get(key).getOrDefault("count", 0) + 1);
                
                int diff = ai - human;
                if (diff >= -3 && diff <= 3) {
                    variance.put(diff, variance.getOrDefault(diff, 0) + 1);
                }
            }
        }

        List<Map<String, Integer>> heatmapData = new ArrayList<>(heatmap.values());
        List<Map<String, Object>> varianceData = new ArrayList<>();
        
        for (int i = -3; i <= 3; i++) {
            varianceData.add(Map.of("name", (i > 0 ? "+" : "") + i, "count", variance.getOrDefault(i, 0)));
        }

        return ResponseEntity.ok(Map.of(
            "success", true,
            "data", Map.of(
                "heatmapData", heatmapData,
                "varianceData", varianceData
            )
        ));
    }

    @GetMapping("/flags")
    public ResponseEntity<Map<String, Object>> getFlags() {
        List<AiEvaluationEntity> evals = aiEvaluationRepository.findAll();
        List<Map<String, Object>> flags = new ArrayList<>();
        
        for (AiEvaluationEntity eval : evals) {
            if (eval.getSentimentScore() != null && eval.getSentimentScore().compareTo(new BigDecimal("3.0")) < 0) {
                flags.add(Map.of(
                    "id", eval.getId().toString().substring(0, 8),
                    "type", "Technical Answer",
                    "confidence", (eval.getSentimentScore().doubleValue() * 20) + "%", // Map 1-5 to percentage
                    "reason", "Low Sentiment / Poor Communication",
                    "timestamp", "Recently",
                    "action", "Review Required"
                ));
            }
        }
        
        return ResponseEntity.ok(Map.of("success", true, "data", flags));
    }
}
