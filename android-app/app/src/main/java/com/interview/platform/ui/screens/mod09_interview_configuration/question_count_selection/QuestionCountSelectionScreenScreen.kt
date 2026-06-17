package com.interview.platform.ui.screens.mod09_interview_configuration.question_count_selection

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.interview.platform.ui.theme.*

private val Background get() = PrepAiBackground
private val Primary get() = PrepAiPrimary
private val PrimaryContainer get() = PrepAiPrimaryContainer
private val OnPrimaryContainer get() = PrepAiOnPrimaryContainer
private val Secondary get() = PrepAiSecondary
private val SecondaryContainer get() = PrepAiSecondaryContainer
private val OnSecondaryContainer get() = PrepAiOnSecondaryContainer
private val OutlineVariant get() = PrepAiOutlineVariant
private val SurfaceContainerLowest get() = PrepAiSurfaceContainerLowest
private val SurfaceContainerLow get() = PrepAiSurfaceContainerLow
private val SurfaceContainer get() = PrepAiSurfaceContainer
private val SurfaceContainerHigh get() = PrepAiSurfaceContainerHigh
private val PrimaryFixedDim get() = PrepAiPrimaryFixedDim
private val OnPrimaryFixed get() = PrepAiOnPrimaryFixed
private val TertiaryFixed get() = PrepAiTertiaryFixed
private val OnTertiaryFixedVariant get() = PrepAiOnTertiaryFixed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionCountSelectionScreenScreen(
    onNavigateForward: () -> Unit,
    viewModel: QuestionCountSelectionScreenViewModel = androidx.hilt.navigation.compose.hiltViewModel()
) {
    var questionCount by remember { mutableFloatStateOf(12f) }

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
                    containerColor = SurfaceContainerLowest,
                    scrolledContainerColor = SurfaceContainerLowest
                ),
                modifier = Modifier.border(width = 1.dp, color = OutlineVariant.copy(alpha = 0.5f))
            )
        },
        containerColor = Background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
                border = BorderStroke(1.dp, OutlineVariant),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    // Header Section
                    Text("Number of Questions", style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.onSurface, textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Tailor your practice session by selecting the length of your mock interview.", style = MaterialTheme.typography.bodyLarge, color = Secondary, textAlign = TextAlign.Center)
                    
                    Spacer(modifier = Modifier.height(32.dp))

                    // Prominent Display
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(SurfaceContainerLow, RoundedCornerShape(8.dp))
                            .border(1.dp, OutlineVariant, RoundedCornerShape(8.dp))
                            .padding(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Text(questionCount.toInt().toString(), style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.Bold), color = PrimaryContainer, lineHeight = 1.em)
                                Text("Questions", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(bottom = 8.dp))
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Surface(color = PrimaryFixedDim, shape = RoundedCornerShape(16.dp)) {
                                Row(modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                    Icon(Icons.Default.Schedule, contentDescription = null, tint = OnPrimaryFixed, modifier = Modifier.size(18.dp))
                                    Text("~${(questionCount * 2).toInt()} minutes", color = OnPrimaryFixed, style = MaterialTheme.typography.labelLarge)
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    // Slider Control
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("5 Questions", style = MaterialTheme.typography.labelLarge, color = Secondary)
                            Text("20 Questions", style = MaterialTheme.typography.labelLarge, color = Secondary)
                        }
                        Slider(
                            value = questionCount,
                            onValueChange = { questionCount = it },
                            valueRange = 5f..20f,
                            steps = 14,
                            colors = SliderDefaults.colors(
                                thumbColor = Primary,
                                activeTrackColor = Primary,
                                inactiveTrackColor = SurfaceContainerHigh
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    // Visual Feedback Bento
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier.fillMaxWidth().background(SurfaceContainer, RoundedCornerShape(8.dp)).padding(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(modifier = Modifier.background(SecondaryContainer, RoundedCornerShape(4.dp)).padding(8.dp)) {
                                Icon(Icons.Default.Bolt, contentDescription = null, tint = OnSecondaryContainer)
                            }
                            Column {
                                Text("Adaptive Focus", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.onSurface)
                                Text("Difficulty adjusts based on your previous performance.", style = MaterialTheme.typography.bodySmall, color = Secondary)
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth().background(SurfaceContainer, RoundedCornerShape(8.dp)).padding(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(modifier = Modifier.background(TertiaryFixed, RoundedCornerShape(4.dp)).padding(8.dp)) {
                                Icon(Icons.Default.Verified, contentDescription = null, tint = OnTertiaryFixedVariant)
                            }
                            Column {
                                Text("Certified Content", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.onSurface)
                                Text("Questions verified by industry-leading HR experts.", style = MaterialTheme.typography.bodySmall, color = Secondary)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    // Navigation Actions
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxWidth()) {
                        Button(
                            onClick = { viewModel.submitAction(questionCount.toInt(), onNavigateForward) },
                            modifier = Modifier.fillMaxWidth().height(56.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Primary),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text("Continue", style = MaterialTheme.typography.labelLarge)
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(20.dp))
                        }
                        
                        OutlinedButton(
                            onClick = { /*TODO*/ },
                            modifier = Modifier.fillMaxWidth().height(56.dp),
                            border = BorderStroke(1.dp, OutlineVariant),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = Secondary, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Back", style = MaterialTheme.typography.labelLarge, color = Secondary)
                        }
                    }
                }
            }
        }
    }
}
