package com.interview.platform.ui.screens.mod11_question_delivery.question_submitted

import androidx.compose.animation.core.*
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.interview.platform.ui.theme.*

private val Background get() = PrepAiBackground
private val Primary get() = PrepAiPrimary
private val PrimaryContainer get() = PrepAiPrimaryContainer
private val OnPrimaryContainer get() = PrepAiOnPrimaryContainer
private val Secondary get() = PrepAiSecondary
private val SurfaceContainerLowest get() = PrepAiSurfaceContainerLowest
private val SurfaceContainer get() = PrepAiSurfaceContainer
private val SurfaceContainerHigh get() = PrepAiSurfaceContainerHigh
private val OutlineVariant get() = PrepAiOutlineVariant


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionSubmittedScreen(
    onNavigateForward: () -> Unit,
    onNavigateDashboard: () -> Unit = {},
    viewModel: QuestionSubmittedScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val state = uiState as? QuestionSubmittedScreenUiState.Success ?: return
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Icon(Icons.Default.School, contentDescription = null, tint = Primary)
                        Text("PrepCoach AI", color = Primary, fontWeight = FontWeight.Bold)
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.Notifications, contentDescription = "Notifications", tint = Secondary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Background,
                    scrolledContainerColor = Background
                ),
                modifier = Modifier.border(width = 1.dp, color = OutlineVariant.copy(alpha = 0.5f))
            )
        },
        containerColor = Background,
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateDashboard,
                containerColor = SurfaceContainerHigh,
                contentColor = Secondary,
                elevation = FloatingActionButtonDefaults.elevation(2.dp)
            ) {
                Row(modifier = Modifier.padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Icon(Icons.Default.Home, contentDescription = null)
                    Text("Dashboard", style = MaterialTheme.typography.labelLarge)
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Success Card
            Card(
                modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
                border = BorderStroke(1.dp, OutlineVariant),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp).fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    // Animated Checkmark Mock
                    Box(
                        modifier = Modifier.size(80.dp).background(Primary, CircleShape).clip(CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Check, contentDescription = "Success", tint = OnPrimaryContainer, modifier = Modifier.size(40.dp))
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("Answer Submitted!", style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.onSurface)
                        Text("Your response has been analyzed and recorded.", style = MaterialTheme.typography.bodyLarge, color = Secondary)
                    }

                    // Stats Bento-ish Grid
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        Card(
                            modifier = Modifier.weight(1f),
                            colors = CardDefaults.cardColors(containerColor = SurfaceContainer),
                            border = BorderStroke(1.dp, OutlineVariant)
                        ) {
                            Column(modifier = Modifier.padding(16.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Icon(Icons.Default.Timer, contentDescription = null, tint = Primary)
                                Text("Duration", style = MaterialTheme.typography.labelLarge, color = Secondary)
                                Text(state.durationStr, style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.onSurface)
                            }
                        }
                        
                        Card(
                            modifier = Modifier.weight(1f),
                            colors = CardDefaults.cardColors(containerColor = SurfaceContainer),
                            border = BorderStroke(1.dp, OutlineVariant)
                        ) {
                            Column(modifier = Modifier.padding(16.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Icon(Icons.Default.Description, contentDescription = null, tint = Primary)
                                Text("Word Count", style = MaterialTheme.typography.labelLarge, color = Secondary)
                                Text("${state.wordCount}", style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.onSurface)
                            }
                        }
                    }

                    // Score Progress Ring
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(PrimaryContainer.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                            .border(1.dp, PrimaryContainer.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center, modifier = Modifier.size(64.dp)) {
                            CircularProgressIndicator(progress = { 1f }, color = SurfaceContainerHigh, strokeWidth = 4.dp, modifier = Modifier.fillMaxSize())
                            val progress = state.scorePercent / 100f
                            CircularProgressIndicator(progress = { progress }, color = Primary, strokeWidth = 4.dp, modifier = Modifier.fillMaxSize())
                            Text("${state.scorePercent}%", style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold), color = Primary)
                        }
                        Column {
                            Text("AI Performance Score", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.onSurface)
                            Text("Excellent structure and clarity. Review for more technical depth.", style = MaterialTheme.typography.bodySmall, color = Secondary)
                        }
                    }

                    // Action Buttons
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxWidth()) {
                        Button(
                            onClick = { viewModel.submitAction(onNavigateForward) },
                            modifier = Modifier.fillMaxWidth().height(48.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Primary),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text("Next Question", style = MaterialTheme.typography.labelLarge)
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(Icons.Default.ArrowForward, contentDescription = null, modifier = Modifier.size(20.dp))
                        }

                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier.fillMaxWidth().height(48.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = SurfaceContainerHigh, contentColor = Secondary),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(Icons.Default.Visibility, contentDescription = null, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Review Answer", style = MaterialTheme.typography.labelLarge)
                        }
                    }
                }
            }

            // Footer Quote
            Text(
                "\"Preparation is the key to confidence. You're doing great—keep the momentum going!\"",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                color = Secondary.copy(alpha = 0.8f),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            
            Spacer(modifier = Modifier.height(64.dp))
        }
    }
}
