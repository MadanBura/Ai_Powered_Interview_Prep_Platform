package com.interview.platform.dashboard;

import com.interview.platform.mod01_authentication.repository.UserRepository;
import com.interview.platform.mod06_question_repository.repository.QuestionRepository;
import com.interview.platform.mod10_interview_session.repository.InterviewSessionRepository;
import com.interview.platform.mod10_interview_session.model.InterviewSessionEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.time.ZoneId;
import java.time.format.TextStyle;

@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin(origins = "*")
public class DashboardController {

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final InterviewSessionRepository sessionRepository;

    public DashboardController(UserRepository userRepository, QuestionRepository questionRepository, InterviewSessionRepository sessionRepository) {
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.sessionRepository = sessionRepository;
    }

    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> getDashboard() {
        long totalCandidates = userRepository.count();
        long totalQuestions = questionRepository.count();

        return ResponseEntity.ok(Map.of(
                "success", true,
                "data", Map.of(
                        "totalCandidates", totalCandidates,
                        "totalQuestions", totalQuestions
                )
        ));
    }

    @GetMapping("/analytics/platform")
    public ResponseEntity<Map<String, Object>> getPlatformMetrics() {
        long totalQuestions = questionRepository.count();
        long activeSessions = sessionRepository.findAllByStatus("IN_PROGRESS").size();

        return ResponseEntity.ok(Map.of(
                "success", true,
                "data", Map.of(
                        "metrics", Map.of(
                                "questions", totalQuestions,
                                "activeSessions", activeSessions
                        )
                )
        ));
    }

    @GetMapping("/dashboard/charts")
    public ResponseEntity<Map<String, Object>> getCharts() {
        List<InterviewSessionEntity> sessions = sessionRepository.findAll();
        Map<String, Integer> activityMap = new LinkedHashMap<>();
        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        for (String day : days) activityMap.put(day, 0);

        for (InterviewSessionEntity s : sessions) {
            if (s.getStartedAt() != null) {
                String dayOfWeek = s.getStartedAt().atZone(ZoneId.systemDefault()).getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
                // The keys in activityMap are Mon, Tue, etc.
                if (activityMap.containsKey(dayOfWeek)) {
                    activityMap.put(dayOfWeek, activityMap.get(dayOfWeek) + 1);
                }
            }
        }

        List<Map<String, Object>> activityData = new ArrayList<>();
        for (Map.Entry<String, Integer> e : activityMap.entrySet()) {
            activityData.add(Map.of("name", e.getKey(), "count", e.getValue()));
        }

        List<Map<String, Object>> accuracyData = new ArrayList<>(); // Empty if no AI eval data
        
        return ResponseEntity.ok(Map.of(
                "success", true,
                "data", Map.of(
                        "activityData", activityData,
                        "accuracyData", accuracyData
                )
        ));
    }

    @GetMapping("/dashboard/activity")
    public ResponseEntity<Map<String, Object>> getRecentActivity() {
        List<InterviewSessionEntity> recentSessions = sessionRepository.findTop5ByOrderByStartedAtDesc();
        List<Map<String, Object>> activities = new ArrayList<>();
        for (InterviewSessionEntity s : recentSessions) {
            String user = s.getCandidateUser() != null ? s.getCandidateUser().getEmail() : "Unknown User";
            activities.add(Map.of(
                "action", "Interview Session " + (s.getStatus() != null ? s.getStatus() : "STARTED"),
                "user", user,
                "status", "COMPLETED".equals(s.getStatus()) ? "SUCCESS" : "INFO",
                "time", s.getStartedAt() != null ? s.getStartedAt().toString() : "Just now"
            ));
        }

        return ResponseEntity.ok(Map.of(
                "success", true,
                "data", Map.of("activities", activities)
        ));
    }
}
