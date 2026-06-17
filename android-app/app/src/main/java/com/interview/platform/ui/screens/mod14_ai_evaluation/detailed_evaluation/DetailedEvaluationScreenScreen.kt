package com.interview.platform.ui.screens.mod14_ai_evaluation.detailed_evaluation

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailedEvaluationScreen(
    onNavigateForward: () -> Unit,
    onNavigateBack: () -> Unit = {},
    viewModel: DetailedEvaluationScreenViewModel = hiltViewModel()
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
            is DetailedEvaluationScreenUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = Primary)
                }
            }
            is DetailedEvaluationScreenUiState.Success -> {
                val result = (uiState as DetailedEvaluationScreenUiState.Success).result
                DetailedContent(
                    result = result,
                    onNavigateForward = onNavigateForward,
                    modifier = Modifier.padding(paddingValues)
                )
            }
            is DetailedEvaluationScreenUiState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text((uiState as DetailedEvaluationScreenUiState.Error).message, color = MaterialTheme.colorScheme.error)
                }
            }
            else -> {}
        }
    }
}

@Composable
private fun DetailedContent(
    result: EvaluationResult,
    onNavigateForward: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Header
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text("Detailed Analysis", style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.onSurface)
            Text("Comprehensive feedback based on your last mock interview session.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }

        // Skill Breakdown Section
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.8f)),
            border = BorderStroke(1.dp, OutlineVariant.copy(alpha = 0.8f)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(24.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text("Skill Breakdown", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onSurface)
                    Text("${result.overallScore.toInt()}% Overall", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold), color = Primary)
                }

                SkillBar("Problem Solving", result.categoryScores.problemSolving)
                SkillBar("Technical Accuracy", result.categoryScores.technicalAccuracy)
                SkillBar("Communication", result.categoryScores.clarityAndCommunication)
                SkillBar("Completeness", result.categoryScores.completeness)
            }
        }

        // Actionable Area - Top Strengths
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.8f)),
            border = BorderStroke(1.dp, OutlineVariant.copy(alpha = 0.8f)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Primary)
                    Text("Top Strengths", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onSurface)
                }

                result.overallFeedback.strengths.forEach { strength ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Primary.copy(alpha = 0.05f), RoundedCornerShape(8.dp))
                            .border(1.dp, Primary.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(Icons.Default.Verified, contentDescription = null, tint = Primary)
                        Column {
                            Text("Strength Identified", style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.onSurface)
                            Text(strength, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                }
            }
        }

        // Areas for Improvement
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.8f)),
            border = BorderStroke(1.dp, OutlineVariant.copy(alpha = 0.8f)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Warning, contentDescription = null, tint = Tertiary)
                    Text("Areas for Improvement", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onSurface)
                }

                result.overallFeedback.weaknesses.forEach { weakness ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Tertiary.copy(alpha = 0.05f), RoundedCornerShape(8.dp))
                            .border(1.dp, Tertiary.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(Icons.Default.Speed, contentDescription = null, tint = Tertiary) // Placeholder icon
                        Column {
                            Text("Attention Required", style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.onSurface)
                            Text(weakness, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                }
            }
        }
        
        // Improvement highlight chip
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Primary.copy(alpha = 0.08f)),
            border = BorderStroke(1.dp, Primary.copy(alpha = 0.3f)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.size(40.dp).background(Primary, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text("${result.overallScore.toInt()}%", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.ExtraBold), color = Color.White)
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        "Your 'Articulation' score improved by 12% since last week.",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                        color = Primary
                    )
                    TextButton(onClick = {}, contentPadding = PaddingValues(0.dp)) {
                        Text("View Timeline", style = MaterialTheme.typography.labelMedium, color = Primary)
                    }
                }
            }
        }

        Button(
            onClick = onNavigateForward,
            modifier = Modifier.fillMaxWidth().height(48.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Primary)
        ) {
            Text("Start Realistic Drills")
        }
    }
}

@Composable
private fun SkillBar(label: String, score: Double) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom) {
            Text(label, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text("${score.toInt()}%", style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold), color = Primary)
        }
        LinearProgressIndicator(
            progress = { (score / 100).toFloat() },
            modifier = Modifier.fillMaxWidth().height(12.dp).clip(RoundedCornerShape(6.dp)),
            color = Primary,
            trackColor = SurfaceContainerHigh
        )
    }
}
