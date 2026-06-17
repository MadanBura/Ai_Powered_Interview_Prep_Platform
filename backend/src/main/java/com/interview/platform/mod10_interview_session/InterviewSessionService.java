package com.interview.platform.mod10_interview_session;

import com.interview.platform.mod01_authentication.model.UserEntity;
import com.interview.platform.mod01_authentication.repository.UserRepository;
import com.interview.platform.mod04_technology_management.model.TechnologyEntity;
import com.interview.platform.mod04_technology_management.repository.TechnologyRepository;
import com.interview.platform.mod05_experience_level_management.model.ExperienceLevelEntity;
import com.interview.platform.mod05_experience_level_management.repository.ExperienceLevelRepository;
import com.interview.platform.mod06_question_repository.model.QuestionEntity;
import com.interview.platform.mod06_question_repository.repository.QuestionRepository;
import com.interview.platform.mod09_interview_configuration.model.InterviewConfigEntity;
import com.interview.platform.mod09_interview_configuration.repository.InterviewConfigRepository;
import com.interview.platform.mod10_interview_session.model.InterviewSessionEntity;
import com.interview.platform.mod10_interview_session.model.SubmittedAnswerEntity;
import com.interview.platform.mod10_interview_session.repository.InterviewSessionRepository;
import com.interview.platform.mod10_interview_session.repository.SubmittedAnswerRepository;
import java.math.BigDecimal;
import com.interview.platform.mod11_question_delivery.model.SessionQuestionEntity;
import com.interview.platform.mod11_question_delivery.repository.SessionQuestionRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class InterviewSessionService {

    private final InterviewSessionRepository repository;
    private final UserRepository userRepository;
    private final TechnologyRepository technologyRepository;
    private final ExperienceLevelRepository experienceLevelRepository;
    private final QuestionRepository questionRepository;
    private final SessionQuestionRepository sessionQuestionRepository;
    private final InterviewConfigRepository interviewConfigRepository;
    private final com.interview.platform.mod19_recommendation_engine.repository.UserRoadmapRepository userRoadmapRepository;
    private final SubmittedAnswerRepository submittedAnswerRepository;

    public InterviewSessionService(
            InterviewSessionRepository repository,
            UserRepository userRepository,
            TechnologyRepository technologyRepository,
            ExperienceLevelRepository experienceLevelRepository,
            QuestionRepository questionRepository,
            SessionQuestionRepository sessionQuestionRepository,
            InterviewConfigRepository interviewConfigRepository,
            com.interview.platform.mod19_recommendation_engine.repository.UserRoadmapRepository userRoadmapRepository,
            SubmittedAnswerRepository submittedAnswerRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.technologyRepository = technologyRepository;
        this.experienceLevelRepository = experienceLevelRepository;
        this.questionRepository = questionRepository;
        this.sessionQuestionRepository = sessionQuestionRepository;
        this.interviewConfigRepository = interviewConfigRepository;
        this.userRoadmapRepository = userRoadmapRepository;
        this.submittedAnswerRepository = submittedAnswerRepository;
    }

    public int handle() {
        return 200; // Legacy stub for tests
    }

    @Transactional
    public Map<String, Object> initializeSession(Map<String, Object> req) {
        // Resolve authenticated user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = (auth != null) ? auth.getName() : null;
        Optional<UserEntity> userOpt = (email != null) ? userRepository.findByEmail(email) : Optional.empty();

        // Parse technology IDs from request
        List<String> technologyIds = new ArrayList<>();
        Object techIdsObj = req.get("technologyIds");
        if (techIdsObj instanceof List) {
            for (Object t : (List<?>) techIdsObj) {
                if (t != null) technologyIds.add(t.toString());
            }
        }

        // Parse experience level ID
        String experienceLevelId = req.get("experienceLevelId") != null ? req.get("experienceLevelId").toString() : null;

        // Resolve technology entity (use first one)
        TechnologyEntity technology = null;
        if (!technologyIds.isEmpty()) {
            try {
                technology = technologyRepository.findById(UUID.fromString(technologyIds.get(0))).orElse(null);
            } catch (IllegalArgumentException ignored) {
                // ID may be a name string - try name-based lookup
            }
        }
        // Fallback: if technology not found by ID, try to match by name from request
        if (technology == null) {
            Object techNameObj = req.get("technologyName");
            if (techNameObj != null) {
                String techName = techNameObj.toString();
                technology = technologyRepository.findAll().stream()
                    .filter(t -> t.getName().equalsIgnoreCase(techName))
                    .findFirst().orElse(null);
            }
        }

        // Resolve experience level entity
        ExperienceLevelEntity experienceLevel = null;
        if (experienceLevelId != null) {
            try {
                experienceLevel = experienceLevelRepository.findById(UUID.fromString(experienceLevelId)).orElse(null);
            } catch (IllegalArgumentException ignored) {}
        }

        // Find or create an interview config for this technology + experience level
        InterviewConfigEntity config = findOrCreateConfig(technology, experienceLevel);

        // Create session
        InterviewSessionEntity session = new InterviewSessionEntity();
        session.setConfig(config);
        if (userOpt.isPresent()) {
            session.setCandidateUser(userOpt.get());
        }
        session.setStartedAt(Instant.now());
        session.setStatus("in_progress");
        InterviewSessionEntity saved = repository.save(session);

        // Fetch user's completed topics from active roadmap
        List<String> completedTopics = new ArrayList<>();
        if (userOpt.isPresent()) {
            Optional<com.interview.platform.mod19_recommendation_engine.model.UserRoadmapEntity> roadmapOpt = userRoadmapRepository.findByUserIdAndStatus(userOpt.get().getId(), "IN_PROGRESS");
            if (roadmapOpt.isPresent() && roadmapOpt.get().getCompletedTopics() != null) {
                try {
                    com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
                    completedTopics = mapper.readValue(roadmapOpt.get().getCompletedTopics(), new com.fasterxml.jackson.core.type.TypeReference<List<String>>() {});
                } catch (Exception ignored) {}
            } else {
                // Also check COMPLETED roadmaps just in case they just finished it
                Optional<com.interview.platform.mod19_recommendation_engine.model.UserRoadmapEntity> completedOpt = userRoadmapRepository.findAllByUserId(userOpt.get().getId()).stream().filter(r -> "COMPLETED".equals(r.getStatus())).max(Comparator.comparing(com.interview.platform.mod19_recommendation_engine.model.UserRoadmapEntity::getCompletedAt));
                if (completedOpt.isPresent() && completedOpt.get().getCompletedTopics() != null) {
                    try {
                        com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
                        completedTopics = mapper.readValue(completedOpt.get().getCompletedTopics(), new com.fasterxml.jackson.core.type.TypeReference<List<String>>() {});
                    } catch (Exception ignored) {}
                }
            }
        }

        // Fetch questions for the technology and create session_questions
        List<QuestionEntity> questions = fetchQuestionsForTechnology(technology, completedTopics);
        for (QuestionEntity q : questions) {
            SessionQuestionEntity sq = new SessionQuestionEntity();
            sq.setSession(saved);
            sq.setQuestion(q);
            sq.setAskedAt(Instant.now());
            sessionQuestionRepository.save(sq);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("id", saved.getId().toString());
        result.put("status", saved.getStatus());
        result.put("startedAt", saved.getStartedAt().toString());
        return result;
    }

    private InterviewConfigEntity findOrCreateConfig(TechnologyEntity technology, ExperienceLevelEntity experienceLevel) {
        // Try to find existing config matching this technology + experience level
        List<InterviewConfigEntity> configs = interviewConfigRepository.findAll();
        for (InterviewConfigEntity c : configs) {
            boolean expMatch = (experienceLevel == null && c.getExperienceLevel() == null)
                    || (experienceLevel != null && c.getExperienceLevel() != null
                    && c.getExperienceLevel().getId().equals(experienceLevel.getId()));
            if (expMatch) {
                return c;
            }
        }

        // Create a new config
        InterviewConfigEntity config = new InterviewConfigEntity();
        config.setName((technology != null ? technology.getName() : "General") + " Interview");
        config.setDurationMin(60);
        config.setRigorLevel("medium");
        if (experienceLevel != null) config.setExperienceLevel(experienceLevel);
        return interviewConfigRepository.save(config);
    }

    private List<QuestionEntity> fetchQuestionsForTechnology(TechnologyEntity technology, List<String> completedTopics) {
        List<QuestionEntity> questions;
        if (technology == null) {
            questions = questionRepository.findAll();
        } else {
            questions = questionRepository.findByTechnologyId(technology.getId());
            if (questions.isEmpty()) {
                questions = questionRepository.findAll();
            }
        }
        
        // Filter questions by completed topics (using tags)
        if (!completedTopics.isEmpty()) {
            List<QuestionEntity> filtered = new ArrayList<>();
            for (QuestionEntity q : questions) {
                if (q.getCategory() != null && !q.getCategory().isEmpty()) {
                    // Check if any category matches any completed topic
                    boolean matches = completedTopics.stream().anyMatch(topic -> 
                        q.getCategory().toLowerCase().contains(topic.toLowerCase()) ||
                        topic.toLowerCase().contains(q.getCategory().toLowerCase())
                    );
                    if (matches) {
                        filtered.add(q);
                    }
                }
            }
            if (!filtered.isEmpty()) {
                questions = filtered;
            }
            // If filtered is empty, we fall back to all questions for this tech so they don't get an empty interview.
        }

        // Shuffle for variety
        Collections.shuffle(questions);
        return questions.stream().limit(10).collect(Collectors.toList());
    }

    public Map<String, Object> submitAnswer(Map<String, Object> req) {
        String sessionIdStr = (String) req.get("sessionId");
        String questionIdStr = (String) req.get("questionId");
        String answerText = (String) req.get("answerText");

        if (sessionIdStr == null || questionIdStr == null) {
            throw new IllegalArgumentException("Missing required fields");
        }

        UUID sessionId = UUID.fromString(sessionIdStr);
        InterviewSessionEntity session = repository.findById(sessionId).orElseThrow();

        SubmittedAnswerEntity answer = new SubmittedAnswerEntity();
        answer.setSession(session);
        answer.setQuestionId(questionIdStr);
        answer.setAnswerText(answerText);
        answer.setSubmittedAt(Instant.now());
        submittedAnswerRepository.save(answer);

        Map<String, Object> res = new HashMap<>();
        res.put("status", "Answer submitted");
        return res;
    }

    public Map<String, Object> completeSession(Map<String, Object> req) {
        String sessionIdStr = (String) req.get("sessionId");
        if (sessionIdStr == null) {
            throw new IllegalArgumentException("Missing sessionId");
        }
        UUID sessionId = UUID.fromString(sessionIdStr);
        InterviewSessionEntity session = repository.findById(sessionId).orElseThrow();
        
        session.setEndedAt(Instant.now());
        session.setStatus("COMPLETED");

        // Simple mock score generation based on number of answers
        List<SubmittedAnswerEntity> answers = submittedAnswerRepository.findBySessionId(sessionId);
        double mockScore = Math.min(100.0, answers.size() * 15.0 + 30.0);
        session.setOverallScore(BigDecimal.valueOf(mockScore));

        repository.save(session);

        Map<String, Object> res = new HashMap<>();
        res.put("status", "Session completed");
        res.put("evaluationId", sessionIdStr); // We map evaluationId directly to sessionId for simplicity
        return res;
    }

    public List<Map<String, Object>> getHistory(UUID userId) {
        List<InterviewSessionEntity> sessions = repository.findAllByCandidateUserIdOrderByStartedAtDesc(userId);
        return mapSessionsToHistory(sessions);
    }

    public List<Map<String, Object>> getHistoryByEmail(String email) {
        List<InterviewSessionEntity> sessions = repository.findAllByCandidateUserEmailOrderByStartedAtDesc(email);
        return mapSessionsToHistory(sessions);
    }

    private List<Map<String, Object>> mapSessionsToHistory(List<InterviewSessionEntity> sessions) {
        return sessions.stream().map(s -> {
            Map<String, Object> dto = new HashMap<>();
            dto.put("id", s.getId());
            dto.put("startedAt", s.getStartedAt());
            dto.put("endedAt", s.getEndedAt());
            dto.put("score", s.getOverallScore());
            dto.put("status", s.getStatus());

            if (s.getConfig() != null && s.getConfig().getDepartment() != null) {
                dto.put("role", s.getConfig().getDepartment().getName());
            } else {
                dto.put("role", "Unknown Role");
            }
            return dto;
        }).collect(Collectors.toList());
    }
}
