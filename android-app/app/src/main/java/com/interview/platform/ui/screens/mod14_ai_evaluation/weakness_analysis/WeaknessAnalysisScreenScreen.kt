package com.interview.platform.ui.screens.mod14_ai_evaluation.weakness_analysis

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.interview.platform.ui.theme.*

private val Background get() = PrepAiBackground
private val Primary get() = PrepAiPrimary
private val Secondary get() = PrepAiSecondary
private val SecondaryContainer get() = PrepAiSecondaryContainer
private val OnSecondaryContainer get() = PrepAiOnSecondaryContainer
private val Tertiary get() = PrepAiTertiary
private val SurfaceContainerLowest get() = PrepAiSurfaceContainerLowest
private val OutlineVariant get() = PrepAiOutlineVariant


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeaknessAnalysisScreen(
    onNavigateForward: () -> Unit,
    viewModel: WeaknessAnalysisScreenViewModel = androidx.hilt.navigation.compose.hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Icon(Icons.Default.School, contentDescription = null, tint = Primary)
                        Text("PrepAI", color = Primary, fontWeight = FontWeight.Bold)
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.Notifications, contentDescription = "Notifications", tint = Secondary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = SurfaceContainerLowest,
                    scrolledContainerColor = SurfaceContainerLowest
                ),
                modifier = Modifier.border(width = 1.dp, color = OutlineVariant.copy(alpha = 0.5f))
            )
        },
        containerColor = Background
    ) { paddingValues ->
        if (uiState is WeaknessAnalysisScreenUiState.Empty) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(Icons.Default.Warning, contentDescription = null, tint = OutlineVariant, modifier = Modifier.size(64.dp))
                Spacer(modifier = Modifier.height(16.dp))
                Text("No weaknesses identified yet", style = MaterialTheme.typography.titleLarge, color = Secondary)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Complete an interview to see your weakness analysis.", style = MaterialTheme.typography.bodyMedium, color = Secondary, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
            }
        } else if (uiState is WeaknessAnalysisScreenUiState.Success) {
            val state = uiState as WeaknessAnalysisScreenUiState.Success
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Icon(Icons.Default.Warning, contentDescription = null, tint = Tertiary)
                        Text("AREAS FOR IMPROVEMENT", style = MaterialTheme.typography.labelLarge, color = Tertiary)
                    }
                    Text("Weakness Analysis", style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.onSurface)
                    Text("Review the areas where you can improve for your next session.", style = MaterialTheme.typography.bodyLarge, color = Secondary)
                }

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
                    border = BorderStroke(1.dp, OutlineVariant.copy(alpha = 0.5f)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        state.weaknesses.forEach { item ->
                            val icon = when (item.iconType) {
                                "Speed" -> Icons.Default.Speed
                                "VoiceOverOff" -> Icons.Default.VoiceOverOff
                                "PriorityHigh" -> Icons.Default.PriorityHigh
                                else -> Icons.Default.Warning
                            }
                            WeaknessItemComponent(icon, item.title, item.description)
                        }
                    }
                }
                
                Button(onClick = onNavigateForward, modifier = Modifier.fillMaxWidth().height(48.dp), colors = ButtonDefaults.buttonColors(containerColor = Primary), shape = RoundedCornerShape(8.dp)) {
                    Text("View AI Report")
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun WeaknessItemComponent(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String, desc: String) {
    Row(modifier = Modifier.fillMaxWidth().background(Tertiary.copy(alpha = 0.05f), RoundedCornerShape(8.dp)).border(1.dp, Tertiary.copy(alpha = 0.1f), RoundedCornerShape(8.dp)).padding(16.dp), horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.Top) {
        Box(modifier = Modifier.size(40.dp).background(Tertiary.copy(alpha = 0.1f), CircleShape), contentAlignment = Alignment.Center) {
            Icon(icon, contentDescription = null, tint = Tertiary, modifier = Modifier.size(20.dp))
        }
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(title, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.onSurface)
            Text(desc, style = MaterialTheme.typography.bodyMedium, color = Secondary)
        }
    }
}
