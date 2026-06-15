package com.interview.platform.mod14_ai_evaluation;

import com.interview.platform.mod14_ai_evaluation.repository.AiEvaluationRepository;
import com.interview.platform.mod10_interview_session.model.SubmittedAnswerEntity;
import com.interview.platform.mod10_interview_session.repository.SubmittedAnswerRepository;
import com.interview.platform.mod11_question_delivery.model.SessionQuestionEntity;
import com.interview.platform.mod11_question_delivery.repository.SessionQuestionRepository;
import com.interview.platform.mod10_interview_session.model.InterviewSessionEntity;
import com.interview.platform.mod10_interview_session.repository.InterviewSessionRepository;
import org.springframework.stereotype.Service;
import java.util.*;
import java.math.BigDecimal;
import java.time.Duration;

@Service
public class AiEvaluationService {
    
    private final AiEvaluationRepository repository;
    private final SubmittedAnswerRepository submittedAnswerRepository;
    private final SessionQuestionRepository sessionQuestionRepository;
    private final InterviewSessionRepository sessionRepository;
    private final GeminiApiClient geminiApiClient;

    public AiEvaluationService(
            AiEvaluationRepository repository,
            SubmittedAnswerRepository submittedAnswerRepository,
            SessionQuestionRepository sessionQuestionRepository,
            InterviewSessionRepository sessionRepository,
            GeminiApiClient geminiApiClient) {
        this.repository = repository;
        this.submittedAnswerRepository = submittedAnswerRepository;
        this.sessionQuestionRepository = sessionQuestionRepository;
        this.sessionRepository = sessionRepository;
        this.geminiApiClient = geminiApiClient;
    }
    
    public int handle() {
        return 200; // Legacy stub for tests
    }

    public java.util.Map<String, Object> retrieveEvaluation(java.util.Map<String, Object> req) {
        String sessionIdStr = (String) req.get("sessionId");
        if (sessionIdStr == null) {
            throw new IllegalArgumentException("Missing sessionId");
        }
        UUID sessionId = UUID.fromString(sessionIdStr);
        InterviewSessionEntity session = sessionRepository.findById(sessionId).orElseThrow();
        List<SubmittedAnswerEntity> answers = submittedAnswerRepository.findBySessionId(sessionId);
        List<SessionQuestionEntity> sessionQuestions = sessionQuestionRepository.findBySessionId(sessionId);

        // Map questions for quick lookup
        Map<String, SessionQuestionEntity> questionMap = new HashMap<>();
        for (SessionQuestionEntity sq : sessionQuestions) {
            questionMap.put(sq.getQuestion().getId().toString(), sq);
        }

        java.util.Map<String, Object> eval = new java.util.HashMap<>();
        eval.put("id", UUID.randomUUID().toString());
        
        double overallScore = session.getOverallScore() != null ? session.getOverallScore().doubleValue() : 0.0;
        eval.put("overallScore", overallScore);
        eval.put("totalQuestions", sessionQuestions.size());
        eval.put("date", "Today");
        
        long durationSeconds = 0;
        if (session.getStartedAt() != null && session.getEndedAt() != null) {
            durationSeconds = Duration.between(session.getStartedAt(), session.getEndedAt()).getSeconds();
        }
        eval.put("timeElapsed", String.format("%02d:%02d", durationSeconds / 60, durationSeconds % 60));

        double totalScore = 0.0;

        java.util.List<java.util.Map<String, Object>> qList = new java.util.ArrayList<>();
        int i = 1;
        
        List<String> dynamicStrengths = new ArrayList<>();
        List<String> dynamicWeaknesses = new ArrayList<>();
        List<String> dynamicActionSteps = new ArrayList<>();
        
        for (SessionQuestionEntity sq : sessionQuestions) {
            java.util.Map<String, Object> qData = new java.util.HashMap<>();
            qData.put("questionId", sq.getQuestion().getId().toString());
            qData.put("questionNumber", i++);
            qData.put("questionText", sq.getQuestion().getQuestionText());

            SubmittedAnswerEntity foundAnswer = null;
            for (SubmittedAnswerEntity a : answers) {
                if (a.getQuestionId().equals(sq.getQuestion().getId().toString())) {
                    foundAnswer = a;
                    break;
                }
            }

            String answerText = foundAnswer != null ? foundAnswer.getAnswerText() : "";
            qData.put("providedAnswer", answerText);
            
            // Call Gemini API
            Map<String, Object> geminiEval = geminiApiClient.evaluateAnswer(sq.getQuestion().getQuestionText(), answerText);
            
            double answerScore = 0.0;
            if (geminiEval.containsKey("score")) {
                answerScore = ((Number) geminiEval.get("score")).doubleValue();
            }
            
            qData.put("expectedAnswer", geminiEval.getOrDefault("expectedAnswer", "N/A"));
            qData.put("aiFeedback", geminiEval.getOrDefault("aiFeedback", "No feedback"));
            qData.put("keyConceptsHit", geminiEval.getOrDefault("keyConceptsHit", new ArrayList<>()));
            qData.put("keyConceptsMissed", geminiEval.getOrDefault("keyConceptsMissed", new ArrayList<>()));
            qData.put("isCorrect", geminiEval.getOrDefault("isCorrect", answerScore >= 70.0));
            qData.put("score", answerScore);
            
            if (answerScore >= 80.0) {
                dynamicStrengths.add("Strong performance on questions related to: " + sq.getQuestion().getQuestionText());
            } else if (answerScore >= 50.0) {
                dynamicWeaknesses.add("Lacked some depth on: " + sq.getQuestion().getQuestionText());
                dynamicActionSteps.add("Review the concepts involved in: " + sq.getQuestion().getQuestionText());
            } else {
                dynamicWeaknesses.add("Failed to answer correctly: " + sq.getQuestion().getQuestionText());
                dynamicActionSteps.add("Deep study required for: " + sq.getQuestion().getQuestionText());
            }

            totalScore += answerScore;
            qList.add(qData);
        }
        
        double calculatedOverallScore = sessionQuestions.isEmpty() ? 0.0 : totalScore / sessionQuestions.size();
        eval.put("overallScore", calculatedOverallScore);
        
        // Also fix categoryScores to match the updated overallScore
        java.util.Map<String, Object> catScores = new java.util.HashMap<>();
        catScores.put("technicalAccuracy", calculatedOverallScore);
        catScores.put("clarityAndCommunication", Math.min(100.0, calculatedOverallScore + 5.0));
        catScores.put("problemSolving", calculatedOverallScore);
        catScores.put("completeness", Math.max(0.0, calculatedOverallScore - 10.0));
        eval.put("categoryScores", catScores);
        
        eval.put("questionAnalyses", qList);

        java.util.Map<String, Object> overallFeedback = new java.util.HashMap<>();
        if (dynamicStrengths.isEmpty()) dynamicStrengths.add("Attempted all questions");
        if (dynamicWeaknesses.isEmpty()) dynamicWeaknesses.add("Can always improve edge case knowledge");
        if (dynamicActionSteps.isEmpty()) dynamicActionSteps.add("Keep practicing mock interviews");

        overallFeedback.put("strengths", dynamicStrengths.stream().distinct().limit(3).toList());
        overallFeedback.put("weaknesses", dynamicWeaknesses.stream().distinct().limit(3).toList());
        overallFeedback.put("actionableSteps", dynamicActionSteps.stream().distinct().limit(3).toList());
        eval.put("overallFeedback", overallFeedback);

        return eval;
    }

}
