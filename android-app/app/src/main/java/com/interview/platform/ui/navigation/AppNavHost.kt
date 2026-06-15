package com.interview.platform.ui.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.interview.platform.ui.AppRoutes

import com.interview.platform.ui.screens.mod01_authentication.splash.SplashScreenScreen
import com.interview.platform.ui.screens.mod01_authentication.welcome.WelcomeScreen
import com.interview.platform.ui.screens.mod01_authentication.login.LoginScreenScreen
import com.interview.platform.ui.screens.mod01_authentication.otp_verification.OtpVerificationScreenScreen
import com.interview.platform.ui.screens.mod01_authentication.permissions.PermissionsScreenScreen
import com.interview.platform.ui.screens.mod02_user_profile.edit_profile.EditProfileScreen
import com.interview.platform.ui.screens.mod02_user_profile.profile.ProfileScreen
import com.interview.platform.ui.screens.mod03_department_management.department_selection.DepartmentSelectionScreen
import com.interview.platform.ui.screens.mod04_technology_management.technology_selection.TechnologySelectionScreen
import com.interview.platform.ui.screens.mod05_experience_level_management.experience_selection.ExperienceSelectionScreen
import com.interview.platform.ui.screens.mod09_interview_configuration.home.HomeDashboardScreenScreen
import com.interview.platform.ui.screens.mod09_interview_configuration.interview_summary.InterviewSummaryScreenScreen
import com.interview.platform.ui.screens.mod09_interview_configuration.question_count_selection.QuestionCountSelectionScreenScreen
import com.interview.platform.ui.screens.mod10_interview_session.audio_review.AudioReviewScreen
import com.interview.platform.ui.screens.mod10_interview_session.completed.InterviewCompletedScreen
import com.interview.platform.ui.screens.mod10_interview_session.session.InterviewSessionScreen
import com.interview.platform.ui.screens.mod11_question_delivery.question_submitted.QuestionSubmittedScreen
import com.interview.platform.ui.screens.mod12_voice_recording.recording.VoiceRecordingScreen
import com.interview.platform.ui.screens.mod13_speech_to_text.speech_to_text.SpeechToTextScreen
import com.interview.platform.ui.screens.mod14_ai_evaluation.detailed_evaluation.DetailedEvaluationScreen
import com.interview.platform.ui.screens.mod14_ai_evaluation.question_wise_analysis.QuestionWiseAnalysisScreen
import com.interview.platform.ui.screens.mod14_ai_evaluation.results_dashboard.ResultsDashboardScreenScreen
import com.interview.platform.ui.screens.mod14_ai_evaluation.strength_analysis.StrengthAnalysisScreen
import com.interview.platform.ui.screens.mod14_ai_evaluation.weakness_analysis.WeaknessAnalysisScreen
import com.interview.platform.ui.screens.mod16_feedback_engine.ai_feedback.AiFeedbackReportScreen
import com.interview.platform.ui.screens.mod17_reporting.interview_history.InterviewHistoryScreenScreen
import com.interview.platform.ui.screens.mod18_dashboard.HomeDashboardScreen
import com.interview.platform.ui.screens.mod18_dashboard.achievements.AchievementsScreenScreen
import com.interview.platform.ui.screens.mod19_recommendation_engine.interview_roadmap.InterviewRoadmapScreenScreen
import com.interview.platform.ui.screens.mod19_recommendation_engine.learning_recommendations.LearningRecommendationsScreenScreen
import com.interview.platform.ui.screens.mod19_recommendation_engine.practice_plan.PracticePlanScreenScreen
import com.interview.platform.ui.screens.mod19_recommendation_engine.recommended_technologies.RecommendedTechnologiesScreenScreen
import com.interview.platform.ui.screens.mod20_analytics.performance_analytics.PerformanceAnalyticsScreen

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route
    val authRoutes = listOf("splash", "welcome", "login", "otp/{email}", "permissions")
    val showBottomBar = currentRoute != null && currentRoute !in authRoutes

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    tonalElevation = 8.dp
                ) {
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                        label = { Text("Home") },
                        selected = currentRoute == AppRoutes.home,
                        onClick = { navController.navigate(AppRoutes.home) }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Chat, contentDescription = "Interviews") },
                        label = { Text("Interviews") },
                        selected = currentRoute == AppRoutes.departmentSelection,
                        onClick = { navController.navigate(AppRoutes.departmentSelection) }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Analytics, contentDescription = "Results") },
                        label = { Text("Results") },
                        selected = currentRoute == AppRoutes.interviewHistory,
                        onClick = { navController.navigate(AppRoutes.interviewHistory) }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Route, contentDescription = "Roadmap") },
                        label = { Text("Roadmap") },
                        selected = currentRoute in listOf(
                            AppRoutes.interviewRoadmap,
                            AppRoutes.learningRecommendations,
                            AppRoutes.practicePlan,
                            AppRoutes.recommendedTechnologies
                        ),
                        onClick = { navController.navigate(AppRoutes.interviewRoadmap) }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                        label = { Text("Profile") },
                        selected = currentRoute == AppRoutes.profile,
                        onClick = { navController.navigate(AppRoutes.profile) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppRoutes.splash,
            modifier = Modifier.padding(innerPadding).fillMaxSize()
        ) {

            composable(AppRoutes.splash) {
                SplashScreenScreen(
                    onNavigateToWelcome = {
                        navController.navigate(AppRoutes.welcome) {
                            popUpTo(AppRoutes.splash) { inclusive = true }
                        }
                    },
                    onNavigateToHome = {
                        navController.navigate(AppRoutes.home) {
                            popUpTo(AppRoutes.splash) { inclusive = true }
                        }
                    }
                )
            }

            composable(AppRoutes.welcome) {
                WelcomeScreen(
                    onNavigateForward = {
                        navController.navigate(AppRoutes.login) {
                            popUpTo(AppRoutes.welcome) { inclusive = true }
                        }
                    }
                )
            }

            composable(AppRoutes.login) {
                LoginScreenScreen(
                    onNavigateToOtp = { email ->
                        navController.navigate("otp/$email")
                    }
                )
            }

            composable(AppRoutes.otp) { backStackEntry ->
                val email = backStackEntry.arguments?.getString("email") ?: ""

                OtpVerificationScreenScreen(
                    email = email,
                    onNavigateToHome = {
                        navController.navigate(AppRoutes.permissions) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )
            }

            composable(AppRoutes.permissions) {
                PermissionsScreenScreen(
                    onNavigateForward = {
                        navController.navigate(AppRoutes.home) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )
            }

            composable(AppRoutes.home) {
                HomeDashboardScreen(
                    navController = navController,
                    onStartInterview = { navController.navigate(AppRoutes.departmentSelection) },
                    onResumeInterview = { navController.navigate(AppRoutes.interviewSession) },
                    onStartForYouInterview = { navController.navigate(AppRoutes.questionCountSelection) }
                )
            }



            composable(AppRoutes.profile) {
                ProfileScreen(
                    onNavigateForward = { navController.navigate(AppRoutes.editProfile) },
                    onNavigateToExperience = { navController.navigate(AppRoutes.editExperience) },
                    onNavigateToStack = { navController.navigate(AppRoutes.editTechStack) }
                )
            }

            composable(AppRoutes.editProfile) {
                EditProfileScreen(
                    onNavigateForward = { navController.navigate(AppRoutes.profile) },
                    onNavigateBack = { navController.popBackStack() }
                )
            }

            composable(AppRoutes.activeSessions) {
                // Not used in candidate app, but keeping route valid just in case
                HomeDashboardScreenScreen(onNavigateForward = { navController.navigate(AppRoutes.home) })
            }

            composable(AppRoutes.resultsDashboard) {
                ResultsDashboardScreenScreen(
                    onNavigateForward = { navController.navigate(AppRoutes.detailedEvaluation) },
                    onNavigateBack = { navController.popBackStack() }
                )
            }

            composable(AppRoutes.strengthAnalysis) {
                StrengthAnalysisScreen(onNavigateForward = { navController.navigate(AppRoutes.weaknessAnalysis) })
            }

            composable(AppRoutes.detailedEvaluation) {
                DetailedEvaluationScreen(
                    onNavigateForward = { navController.navigate(AppRoutes.questionWiseAnalysis) },
                    onNavigateBack = { navController.popBackStack() }
                )
            }

            composable(AppRoutes.weaknessAnalysis) {
                WeaknessAnalysisScreen(onNavigateForward = { navController.navigate(AppRoutes.aiFeedbackReport) })
            }

            composable(AppRoutes.questionWiseAnalysis) {
                QuestionWiseAnalysisScreen(
                    onNavigateForward = { navController.navigate(AppRoutes.aiFeedbackReport) },
                    onNavigateBack = { navController.popBackStack() }
                )
            }

            composable(AppRoutes.interviewHistory) {
                InterviewHistoryScreenScreen(onNavigateForward = { navController.navigate(AppRoutes.interviewSummary) })
            }

            composable(AppRoutes.achievements) {
                AchievementsScreenScreen(
                    navController = navController,
                    onNavigateForward = { navController.navigate(AppRoutes.profile) }
                )
            }

            composable(AppRoutes.aiFeedbackReport) {
                AiFeedbackReportScreen(
                    onNavigateHome = { navController.navigate(AppRoutes.home) },
                    onNavigateBack = { navController.popBackStack() }
                )
            }

            composable(AppRoutes.voiceRecording) {
                VoiceRecordingScreen(
                    onNavigateForward = { navController.navigate(AppRoutes.audioReview) },
                    onNavigateCancel = { navController.popBackStack() }
                )
            }

            composable(AppRoutes.speechToText) {
                SpeechToTextScreen(onNavigateForward = { navController.navigate(AppRoutes.questionSubmitted) })
            }

            composable(AppRoutes.performanceAnalytics) {
                PerformanceAnalyticsScreen(onNavigateForward = { navController.navigate(AppRoutes.achievements) })
            }

            composable(AppRoutes.interviewSummary) {
                InterviewSummaryScreenScreen(onNavigateForward = { navController.navigate(AppRoutes.interviewSession) })
            }

            composable(AppRoutes.interviewSession) {
                InterviewSessionScreen(
                    onNavigateForward = { navController.navigate(AppRoutes.voiceRecording) },
                    onNavigateTypeAnswer = { navController.navigate(AppRoutes.speechToText) }, // we'll use speechToText for typing answer for now
                    onNavigateSkip = { 
                        // Using hiltViewModel here directly or inside the screen is fine.
                        // Wait, skip is handled in screen, but we need onNavigateSkip just to advance UI or tell VM.
                        // Actually, InterviewSessionScreenViewModel.skipQuestion() should be called from the screen itself.
                    },
                    onNavigateCompleted = { 
                        navController.navigate(AppRoutes.interviewCompleted) {
                            popUpTo(AppRoutes.home) { inclusive = false }
                        }
                    }
                )
            }

            composable(AppRoutes.interviewCompleted) {
                InterviewCompletedScreen(
                    onNavigateForward = { navController.navigate(AppRoutes.resultsDashboard) },
                    onNavigateDashboard = { navController.navigate(AppRoutes.home) { popUpTo(AppRoutes.home) { inclusive = false } } }
                )
            }

            composable(AppRoutes.audioReview) {
                AudioReviewScreen(
                    onNavigateForward = { navController.navigate(AppRoutes.speechToText) },
                    onNavigateBack = { navController.popBackStack() },
                    onNavigateReRecord = { navController.navigate(AppRoutes.voiceRecording) { popUpTo(AppRoutes.voiceRecording) { inclusive = true } } }
                )
            }

            composable(AppRoutes.departmentSelection) {
                DepartmentSelectionScreen(
                    onNavigateForward = { navController.navigate(AppRoutes.technologySelection) },
                    onNavigateBack = { navController.popBackStack() }
                )
            }

            composable(AppRoutes.technologySelection) {
                TechnologySelectionScreen(
                    onNavigateForward = { navController.navigate(AppRoutes.experienceSelection) },
                    onNavigateBack = { navController.popBackStack() },
                    onNavigateHome = { navController.navigate(AppRoutes.home) { popUpTo(AppRoutes.home) { inclusive = false } } }
                )
            }

            composable(AppRoutes.onboardingTechStack) {
                TechnologySelectionScreen(
                    onNavigateForward = { 
                        navController.navigate(AppRoutes.home) { 
                            popUpTo(AppRoutes.home) { inclusive = false } 
                        } 
                    },
                    onNavigateBack = { navController.popBackStack() },
                    onNavigateHome = { navController.navigate(AppRoutes.home) { popUpTo(AppRoutes.home) { inclusive = false } } },
                    isOnboarding = true
                )
            }

            composable(AppRoutes.experienceSelection) {
                ExperienceSelectionScreen(
                    onNavigateForward = { navController.navigate(AppRoutes.questionCountSelection) },
                    onNavigateHome = { navController.navigate(AppRoutes.home) { popUpTo(AppRoutes.home) { inclusive = false } } },
                    onNavigateProfile = { navController.navigate(AppRoutes.profile) }
                )
            }

            composable(AppRoutes.onboardingExperience) {
                ExperienceSelectionScreen(
                    onNavigateForward = { navController.navigate(AppRoutes.onboardingTechStack) },
                    onNavigateHome = { navController.navigate(AppRoutes.home) { popUpTo(AppRoutes.home) { inclusive = false } } },
                    onNavigateProfile = { navController.navigate(AppRoutes.profile) },
                    isOnboarding = true
                )
            }

            composable(AppRoutes.editExperience) {
                ExperienceSelectionScreen(
                    onNavigateForward = { navController.popBackStack() },
                    onNavigateHome = { navController.navigate(AppRoutes.home) { popUpTo(AppRoutes.home) { inclusive = false } } },
                    onNavigateProfile = { navController.popBackStack() },
                    isFromProfile = true
                )
            }

            composable(AppRoutes.editTechStack) {
                TechnologySelectionScreen(
                    onNavigateForward = { navController.popBackStack() },
                    onNavigateBack = { navController.popBackStack() },
                    onNavigateHome = { navController.navigate(AppRoutes.home) { popUpTo(AppRoutes.home) { inclusive = false } } },
                    isFromProfile = true
                )
            }

            composable(AppRoutes.practicePlan) {
                PracticePlanScreenScreen(
                    onNavigateToRoadmap = { navController.navigate(AppRoutes.interviewRoadmap) },
                    onNavigateToLearningRecs = { navController.navigate(AppRoutes.learningRecommendations) },
                    onNavigateToTechRecs = { navController.navigate(AppRoutes.recommendedTechnologies) }
                )
            }

            composable(AppRoutes.recommendedTechnologies) {
                RecommendedTechnologiesScreenScreen(onNavigateForward = { 
                    navController.navigate(AppRoutes.interviewRoadmap) {
                        popUpTo(AppRoutes.recommendedTechnologies) { inclusive = true }
                    } 
                })
            }

            composable(AppRoutes.learningRecommendations) {
                LearningRecommendationsScreenScreen(onNavigateForward = { navController.navigate(AppRoutes.practicePlan) })
            }

            composable(AppRoutes.interviewRoadmap) {
                InterviewRoadmapScreenScreen(
                    onNavigateToTopic = { roadmapId, topicName -> 
                        navController.navigate("learningtopic/$roadmapId/$topicName") 
                    },
                    onStartInterview = { navController.navigate(AppRoutes.departmentSelection) },
                    onStartDirectInterview = { navController.navigate(AppRoutes.interviewSession) },
                    onNavigateToCatalog = {
                        navController.navigate(AppRoutes.recommendedTechnologies) {
                            popUpTo(AppRoutes.interviewRoadmap) { inclusive = true }
                        }
                    }
                )
            }

            composable(AppRoutes.learningTopic) { backStackEntry ->
                val roadmapId = backStackEntry.arguments?.getString("roadmapId") ?: ""
                val topicName = backStackEntry.arguments?.getString("topicName") ?: ""
                com.interview.platform.ui.screens.mod19_recommendation_engine.learning_topic.LearningTopicScreen(
                    roadmapId = roadmapId,
                    topicName = topicName,
                    onNavigateBack = { navController.popBackStack() }
                )
            }

            composable(AppRoutes.questionCountSelection) {
                QuestionCountSelectionScreenScreen(onNavigateForward = { navController.navigate(AppRoutes.interviewSummary) })
            }

            composable(AppRoutes.questionSubmitted) {
                QuestionSubmittedScreen(
                    onNavigateForward = { navController.navigate(AppRoutes.interviewSession) },
                    onNavigateDashboard = { navController.navigate(AppRoutes.home) { popUpTo(AppRoutes.home) { inclusive = false } } }
                )
            }

//            composable(AppRoutes.homeDashboard) {
//                HomeDashboardScreenScreen(onNavigateForward = { navController.navigate(AppRoutes.home) })
//            }
        }
    }
}
