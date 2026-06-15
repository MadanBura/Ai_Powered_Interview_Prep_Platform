import os

base_pkg = "/Users/apple/AI-Powered Interview Preparation Platform/app/src/main/java/com/interview/platform/ui"

screens = [
    # Phase 3
    ("mod10_interview_session", "session", "InterviewSessionScreen"),
    ("mod12_voice_recording", "recording", "VoiceRecordingScreen"),
    ("mod10_interview_session", "audio_review", "AudioReviewScreen"),
    ("mod13_speech_to_text", "speech_to_text", "SpeechToTextScreen"),
    ("mod11_question_delivery", "question_submitted", "QuestionSubmittedScreen"),
    ("mod10_interview_session", "completed", "InterviewCompletedScreen"),
    
    # Phase 4
    ("mod14_ai_evaluation", "results_dashboard", "ResultsDashboardScreen"),
    ("mod14_ai_evaluation", "detailed_evaluation", "DetailedEvaluationScreen"),
    ("mod14_ai_evaluation", "question_wise_analysis", "QuestionWiseAnalysisScreen"),
    ("mod14_ai_evaluation", "strength_analysis", "StrengthAnalysisScreen"),
    ("mod14_ai_evaluation", "weakness_analysis", "WeaknessAnalysisScreen"),
    ("mod16_feedback_engine", "ai_feedback", "AiFeedbackReportScreen"),
    
    # Phase 5
    ("mod19_recommendation_engine", "learning_recommendations", "LearningRecommendationsScreen"),
    ("mod19_recommendation_engine", "interview_roadmap", "InterviewRoadmapScreen"),
    ("mod19_recommendation_engine", "recommended_technologies", "RecommendedTechnologiesScreen"),
    ("mod19_recommendation_engine", "practice_plan", "PracticePlanScreen"),
    
    # Phase 6
    ("mod17_reporting", "interview_history", "InterviewHistoryScreen"),
    ("mod20_analytics", "performance_analytics", "PerformanceAnalyticsScreen"),
    ("mod18_dashboard", "achievements", "AchievementsScreen"),
]

def generate_screen_files(mod_dir, screen_dir, screen_name):
    pkg_path = f"com.interview.platform.ui.screens.{mod_dir}.{screen_dir}"
    os.makedirs(os.path.join(base_pkg, "screens", mod_dir, screen_dir), exist_ok=True)
    
    # UiState
    uistate_content = f"""package {pkg_path}

sealed class {screen_name}UiState {{
    object Loading : {screen_name}UiState()
    object Empty : {screen_name}UiState()
    data class Success(val data: String = "") : {screen_name}UiState()
    data class Error(val message: String) : {screen_name}UiState()
}}
"""
    with open(os.path.join(base_pkg, "screens", mod_dir, screen_dir, f"{screen_name}UiState.kt"), "w") as f:
        f.write(uistate_content)

    # ViewModel
    viewmodel_content = f"""package {pkg_path}

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class {screen_name}ViewModel @Inject constructor() : ViewModel() {{
    private val _uiState = MutableStateFlow<{screen_name}UiState>({screen_name}UiState.Success())
    val uiState: StateFlow<{screen_name}UiState> = _uiState.asStateFlow()
    
    fun submitAction() {{
        // Implementation placeholder
    }}
}}
"""
    with open(os.path.join(base_pkg, "screens", mod_dir, screen_dir, f"{screen_name}ViewModel.kt"), "w") as f:
        f.write(viewmodel_content)

    # Screen
    screen_content = f"""package {pkg_path}

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun {screen_name}(
    onNavigateForward: () -> Unit,
    viewModel: {screen_name}ViewModel = hiltViewModel()
) {{
    val uiState by viewModel.uiState.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {{
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {{
            Text(
                text = "{screen_name}",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(32.dp))
            
            when (uiState) {{
                is {screen_name}UiState.Loading -> CircularProgressIndicator()
                is {screen_name}UiState.Error -> Text("Error: ${{ (uiState as {screen_name}UiState.Error).message }}", color = MaterialTheme.colorScheme.error)
                is {screen_name}UiState.Empty -> Text("No data available.")
                is {screen_name}UiState.Success -> {{
                    Button(
                        onClick = onNavigateForward,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp) // Accessibility minimum
                    ) {{
                        Text("Continue")
                    }}
                }}
            }}
        }}
    }}
}}
"""
    with open(os.path.join(base_pkg, "screens", mod_dir, screen_dir, f"{screen_name}Screen.kt"), "w") as f:
        f.write(screen_content)

# Generate Phase 3 to 6 Screens
for mod_dir, screen_dir, screen_name in screens:
    generate_screen_files(mod_dir, screen_dir, screen_name)

print("Generated Android Phase 3 to 6 Scaffold successfully.")
