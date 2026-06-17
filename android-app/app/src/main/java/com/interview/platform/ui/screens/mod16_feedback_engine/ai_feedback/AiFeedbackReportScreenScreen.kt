package com.interview.platform.ui.screens.mod16_feedback_engine.ai_feedback

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.interview.platform.ui.theme.*
import com.interview.platform.domain.model.mod14_ai_evaluation.EvaluationResult

private val Background get() = PrepAiBackground
private val Primary get() = PrepAiPrimary
private val PrimaryContainer get() = PrepAiPrimaryContainer
private val Secondary get() = PrepAiSecondary
private val SecondaryContainer get() = PrepAiSecondaryContainer
private val Tertiary get() = PrepAiTertiary
private val TertiaryContainer get() = PrepAiTertiaryContainer
private val OutlineVariant get() = PrepAiOutlineVariant
private val SurfaceContainerLowest get() = PrepAiSurfaceContainerLowest
private val SurfaceContainerHigh get() = PrepAiSurfaceContainerHigh
private val SurfaceContainerHighest get() = PrepAiSurfaceContainerHighest
private val SurfaceContainerLow get() = PrepAiSurfaceContainerLow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AiFeedbackReportScreen(
    onNavigateHome: () -> Unit,
    onNavigateBack: () -> Unit = {},
    viewModel: AiFeedbackReportScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Icon(Icons.Default.Psychology, contentDescription = null, tint = Primary, modifier = Modifier.size(28.dp))
                        Text("PrepAI", color = Primary, fontWeight = FontWeight.Bold)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.Notifications, contentDescription = "Notifications", tint = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = SurfaceContainerLowest,
                    scrolledContainerColor = SurfaceContainerLowest
                ),
                modifier = Modifier.border(width = 1.dp, color = OutlineVariant.copy(alpha = 0.5f))
            )
        },
        containerColor = Background,
    ) { paddingValues ->
        when (uiState) {
            is AiFeedbackReportScreenUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = Primary)
                }
            }
            is AiFeedbackReportScreenUiState.Success -> {
                val result = (uiState as AiFeedbackReportScreenUiState.Success).result
                ReportContent(
                    result = result,
                    onNavigateHome = onNavigateHome,
                    modifier = Modifier.padding(paddingValues)
                )
            }
            is AiFeedbackReportScreenUiState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text((uiState as AiFeedbackReportScreenUiState.Error).message, color = MaterialTheme.colorScheme.error)
                }
            }
            else -> {}
        }
    }
}

@Composable
private fun ReportContent(
    result: EvaluationResult,
    onNavigateHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Hero Section
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text("EVALUATION COMPLETED", style = MaterialTheme.typography.labelMedium.copy(letterSpacing = 1.sp), color = Primary)
                Text("AI Coach Report", style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.onSurface)
                Text("Mock Interview Prep • ${result.date}", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Button(
                onClick = { /* TODO */ },
                colors = ButtonDefaults.buttonColors(containerColor = Primary),
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Download, contentDescription = null, modifier = Modifier.size(18.dp))
                    Text("Download PDF Report")
                }
            }
        }

        // Overall Score Ring
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
            border = BorderStroke(1.dp, OutlineVariant),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Overall Readiness", style = MaterialTheme.typography.titleLarge, modifier = Modifier.fillMaxWidth())
                
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(160.dp).padding(vertical = 16.dp)
                ) {
                    CircularProgressIndicator(
                        progress = { 1f },
                        modifier = Modifier.fillMaxSize(),
                        color = SurfaceContainerHighest,
                        strokeWidth = 12.dp,
                    )
                    CircularProgressIndicator(
                        progress = { (result.overallScore / 100).toFloat() },
                        modifier = Modifier.fillMaxSize(),
                        color = Primary,
                        strokeWidth = 12.dp,
                        strokeCap = StrokeCap.Round
                    )
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("${result.overallScore.toInt()}%", style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.onSurface)
                        Text("Ready", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
                Text("You're performing above 75% of candidates.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }

        // AI Detailed Summary
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.8f)),
            border = BorderStroke(1.dp, OutlineVariant.copy(alpha = 0.8f)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(40.dp).background(PrimaryContainer, CircleShape), contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.SmartToy, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimaryContainer)
                    }
                    Text("Coach's Executive Summary", style = MaterialTheme.typography.titleLarge)
                }

                Text(
                    "You demonstrated strong technical proficiency during the architectural portion. Your explanations were precise and exhibited a senior-level depth of understanding.\n\nHowever, when transitioning to the behavioral segment, your responses were slightly disorganized. Consider using the STAR method more consistently.\n\nYour communication style is engaging and confident. Focus your next session on refining the 'Action' part of your stories.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Row(horizontalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxWidth()) {
                    SummaryHighlightCard(
                        title = "STRENGTH",
                        desc = "Tech Architecture",
                        icon = Icons.Default.TrendingUp,
                        color = Primary,
                        modifier = Modifier.weight(1f)
                    )
                    SummaryHighlightCard(
                        title = "IMPROVEMENT",
                        desc = "Behavioral Flow",
                        icon = Icons.Default.Warning,
                        color = Tertiary,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        // Pro Tips
        Text("Personalized Pro Tips", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(top = 8.dp))
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            result.overallFeedback.actionableSteps.forEach { step ->
                ProTipCard(step)
            }
        }

        // Skill Mastery Breakdown
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = SurfaceContainerHigh),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("Skill Mastery Breakdown", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
                SkillMasteryRow("Technical Depth", result.categoryScores.technicalAccuracy, Primary)
                SkillMasteryRow("Communication", result.categoryScores.clarityAndCommunication, Tertiary)
                SkillMasteryRow("Problem Solving", result.categoryScores.problemSolving, Primary)
            }
        }

        Button(
            onClick = onNavigateHome,
            modifier = Modifier.fillMaxWidth().height(48.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = SecondaryContainer, contentColor = MaterialTheme.colorScheme.onSecondaryContainer)
        ) {
            Text("Back to Home")
        }
    }
}

@Composable
private fun SummaryHighlightCard(
    title: String,
    desc: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Color,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(SurfaceContainerLow, RoundedCornerShape(8.dp))
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = color)
        Column {
            Text(title, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(desc, style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.onSurface)
        }
    }
}

@Composable
private fun ProTipCard(tip: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
        border = BorderStroke(1.dp, OutlineVariant),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(modifier = Modifier.size(48.dp).background(SecondaryContainer, RoundedCornerShape(8.dp)), contentAlignment = Alignment.Center) {
                Icon(Icons.Default.Lightbulb, contentDescription = null, tint = MaterialTheme.colorScheme.onSecondaryContainer)
            }
            Column {
                Text("Actionable Advice", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurface)
                Spacer(modifier = Modifier.height(4.dp))
                Text(tip, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

@Composable
private fun SkillMasteryRow(label: String, score: Double, color: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.width(110.dp)
        )
        androidx.compose.foundation.layout.Box(
            modifier = Modifier.weight(1f).height(10.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(SurfaceContainerHighest)
        ) {
            androidx.compose.foundation.layout.Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth((score / 100).toFloat())
                    .clip(RoundedCornerShape(5.dp))
                    .background(color)
            )
        }
        Text(
            "${score.toInt()}%",
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
            color = color
        )
    }
}
