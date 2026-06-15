package com.interview.platform.ui.screens.mod14_ai_evaluation.question_wise_analysis

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.sp
import com.interview.platform.ui.theme.*
import com.interview.platform.domain.model.mod14_ai_evaluation.EvaluationResult
import com.interview.platform.domain.model.mod14_ai_evaluation.QuestionAnalysis

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
private val SurfaceContainerLow get() = PrepAiSurfaceContainerLow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionWiseAnalysisScreen(
    onNavigateForward: () -> Unit,
    onNavigateBack: () -> Unit = {},
    viewModel: QuestionWiseAnalysisScreenViewModel = hiltViewModel()
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
            is QuestionWiseAnalysisScreenUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = Primary)
                }
            }
            is QuestionWiseAnalysisScreenUiState.Success -> {
                val result = (uiState as QuestionWiseAnalysisScreenUiState.Success).result
                AnalysisContent(
                    result = result,
                    onNavigateForward = onNavigateForward,
                    modifier = Modifier.padding(paddingValues)
                )
            }
            is QuestionWiseAnalysisScreenUiState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text((uiState as QuestionWiseAnalysisScreenUiState.Error).message, color = MaterialTheme.colorScheme.error)
                }
            }
            else -> {}
        }
    }
}

@Composable
private fun AnalysisContent(
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
        // Headline
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Analytics, contentDescription = null, tint = Primary)
                Text("PERFORMANCE REVIEW", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold), color = Secondary)
            }
            Text("Question Analysis", style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.onSurface)
            Text("Review your session performance question by question. Drill down into AI-generated feedback.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }

        // Summary Card with circular donut
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
            border = BorderStroke(1.dp, OutlineVariant),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("OVERALL SCORE", style = MaterialTheme.typography.labelSmall.copy(letterSpacing = androidx.compose.ui.unit.TextUnit.Unspecified), color = Secondary)
                Box(contentAlignment = Alignment.Center, modifier = Modifier.size(120.dp)) {
                    CircularProgressIndicator(progress = { 1f }, modifier = Modifier.fillMaxSize(), color = SurfaceContainerHigh, strokeWidth = 10.dp)
                    CircularProgressIndicator(progress = { (result.overallScore / 100).toFloat() }, modifier = Modifier.fillMaxSize(), color = Primary, strokeWidth = 10.dp)
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("${result.overallScore.toInt()}%", style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold), color = Primary)
                        Text("Top 15%", style = MaterialTheme.typography.labelSmall, color = Secondary)
                    }
                }
                Text("Top 15% of candidates in Senior Engineering roles", style = MaterialTheme.typography.bodySmall, color = Secondary, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
            }
        }

        // Session Highlights
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Session Highlights", style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold))
            Text("Your technical depth was exceptional in the System Design section, scoring a 92%. Focus on 'Behavioral Alignment' for your next session.", style = MaterialTheme.typography.bodySmall, color = Secondary)
            Spacer(Modifier.height(4.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Surface(color = Primary.copy(alpha = 0.12f), shape = RoundedCornerShape(20.dp), border = BorderStroke(1.dp, Primary.copy(alpha = 0.3f))) {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)) {
                        Icon(Icons.Default.CheckCircle, null, tint = Primary, modifier = Modifier.size(12.dp))
                        Spacer(Modifier.width(4.dp))
                        Text("Strong Tech Foundation", style = MaterialTheme.typography.labelSmall, color = Primary)
                    }
                }
                Surface(color = Tertiary.copy(alpha = 0.1f), shape = RoundedCornerShape(20.dp), border = BorderStroke(1.dp, Tertiary.copy(alpha = 0.3f))) {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)) {
                        Icon(Icons.Default.Warning, null, tint = Tertiary, modifier = Modifier.size(12.dp))
                        Spacer(Modifier.width(4.dp))
                        Text("Improved Clarity", style = MaterialTheme.typography.labelSmall, color = Tertiary)
                    }
                }
            }
        }

        // Questions List
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            result.questionAnalyses.forEach { analysis ->
                QuestionCard(analysis)
            }
        }

        // Next Steps CTA
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
            border = BorderStroke(1.dp, OutlineVariant),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp).fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("Ready for the next round?", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.onSurface)
                Text("We've updated your learning path based on these results.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Button(
                    onClick = onNavigateForward,
                    modifier = Modifier.fillMaxWidth().height(44.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Primary)
                ) {
                    Text("View Recommendations", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
private fun QuestionCard(analysis: QuestionAnalysis) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded },
        colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
        border = BorderStroke(1.dp, if (expanded) Primary else OutlineVariant),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.animateContentSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.weight(1f)) {
                    Box(
                        modifier = Modifier.size(32.dp).background(SurfaceContainerHigh, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("${analysis.questionNumber}", style = MaterialTheme.typography.titleMedium, color = Secondary)
                    }
                    Column {
                        Text(analysis.questionText, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurface)
                        Row(horizontalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.padding(top = 8.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                Icon(Icons.Default.Grade, contentDescription = null, tint = PrimaryContainer, modifier = Modifier.size(16.dp))
                                Text("${analysis.score.toInt()}/100", style = MaterialTheme.typography.labelMedium, color = PrimaryContainer)
                            }
                        }
                    }
                }
                Icon(
                    if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            AnimatedVisibility(visible = expanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(SurfaceContainerLow)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Verification logic display
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("Provided Answer", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold), color = Secondary)
                        Text(analysis.providedAnswer, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
                    }

                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("Expected Answer", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold), color = Primary)
                        Text(analysis.expectedAnswer, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }

                    Divider(color = OutlineVariant)

                    // AI Feedback
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = Secondary, modifier = Modifier.size(18.dp))
                            Text("AI FEEDBACK SUMMARY", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold), color = Secondary)
                        }
                        Text(analysis.aiFeedback, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
                    }
                }
            }
        }
    }
}
