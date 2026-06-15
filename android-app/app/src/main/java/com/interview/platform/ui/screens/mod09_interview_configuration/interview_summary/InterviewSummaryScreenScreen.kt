package com.interview.platform.ui.screens.mod09_interview_configuration.interview_summary

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
private val SecondaryContainer get() = PrepAiSecondaryContainer
private val OnSecondaryContainer get() = PrepAiOnSecondaryContainer
private val OutlineVariant get() = PrepAiOutlineVariant
private val SurfaceContainerLowest get() = PrepAiSurfaceContainerLowest
private val SurfaceContainerLow get() = PrepAiSurfaceContainerLow
private val PrimaryFixed get() = PrepAiPrimaryFixed
private val OnPrimaryFixed get() = PrepAiOnPrimaryFixed
private val OnPrimaryFixedVariant get() = PrepAiOnPrimaryFixedVariant


private val Blue50 = Color(0xFFEFF6FF)
private val Blue600 = Color(0xFF2563EB)
private val Purple50 = Color(0xFFFAF5FF)
private val Purple600 = Color(0xFF9333EA)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InterviewSummaryScreenScreen(
    onNavigateForward: () -> Unit,
    viewModel: InterviewSummaryScreenViewModel = androidx.hilt.navigation.compose.hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

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
        containerColor = Background,
//        bottomBar = {
//            NavigationBar(
//                containerColor = SurfaceContainerLowest,
//                contentColor = Secondary,
//                tonalElevation = 0.dp,
//                modifier = Modifier.border(width = 1.dp, color = OutlineVariant.copy(alpha = 0.5f))
//            ) {
//                NavigationBarItem(selected = false, onClick = { }, icon = { Icon(Icons.Default.Home, null) }, label = { Text("Home") })
//                NavigationBarItem(
//                    selected = true,
//                    onClick = { },
//                    icon = { Icon(Icons.Default.Chat, null) },
//                    label = { Text("Interviews") },
//                    colors = NavigationBarItemDefaults.colors(indicatorColor = PrimaryContainer, selectedIconColor = OnPrimaryContainer)
//                )
//                NavigationBarItem(selected = false, onClick = { }, icon = { Icon(Icons.Default.Analytics, null) }, label = { Text("Results") })
//                NavigationBarItem(selected = false, onClick = { }, icon = { Icon(Icons.Default.Route, null) }, label = { Text("Roadmap") })
//                NavigationBarItem(selected = false, onClick = { }, icon = { Icon(Icons.Default.Person, null) }, label = { Text("Profile") })
//            }
           //}
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Hero Section
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp)) {
                Text("Interview Ready?", style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.onSurface, modifier = Modifier.padding(bottom = 8.dp))
                Text("Review your session details below before we begin. This AI-powered interview will simulate a real-world scenario.", style = MaterialTheme.typography.bodyLarge, color = Secondary, textAlign = TextAlign.Center)
            }

            // Bento Grid Summary
            Column(verticalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxWidth()) {
                // Main Detail Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
                    border = BorderStroke(1.dp, OutlineVariant),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                            Surface(color = SecondaryContainer, shape = RoundedCornerShape(16.dp)) {
                                Text("Active Session", style = MaterialTheme.typography.labelLarge, color = OnSecondaryContainer, modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp))
                            }
                            Icon(Icons.Default.Verified, contentDescription = null, tint = Primary)
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(if (uiState is InterviewSummaryScreenUiState.Success) "${(uiState as InterviewSummaryScreenUiState.Success).experienceLevel} ${(uiState as InterviewSummaryScreenUiState.Success).technology} Engineer" else "Software Engineer", style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.onSurface)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("This session is tailored for ${(uiState as? InterviewSummaryScreenUiState.Success)?.experienceLevel?.lowercase() ?: "your"} expectations, focusing on architecture, ${(uiState as? InterviewSummaryScreenUiState.Success)?.technology ?: "your stack"} performance patterns, and collaborative problem-solving.", style = MaterialTheme.typography.bodyMedium, color = Secondary)
                        
                        Spacer(modifier = Modifier.height(32.dp))
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            Box(modifier = Modifier.size(48.dp).background(PrimaryFixed, CircleShape), contentAlignment = Alignment.Center) {
                                Icon(Icons.Default.Engineering, contentDescription = null, tint = OnPrimaryFixed)
                            }
                            Column {
                                Text("Engineering Dept.", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.onSurface)
                                Text("Technical Stream", style = MaterialTheme.typography.bodySmall, color = Secondary)
                            }
                        }
                    }
                }

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    // Technology Card
                    Card(
                        modifier = Modifier.weight(1f),
                        colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
                        border = BorderStroke(1.dp, OutlineVariant),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                            Box(modifier = Modifier.size(40.dp).background(Blue50, RoundedCornerShape(8.dp)).padding(bottom = 8.dp), contentAlignment = Alignment.Center) {
                                Icon(Icons.Default.Terminal, contentDescription = null, tint = Blue600)
                            }
                            Text("Technology", style = MaterialTheme.typography.labelLarge, color = Secondary)
                            Text((uiState as? InterviewSummaryScreenUiState.Success)?.technology ?: "React", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium), color = MaterialTheme.colorScheme.onSurface)
                        }
                    }

                    // Level Card
                    Card(
                        modifier = Modifier.weight(1f),
                        colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
                        border = BorderStroke(1.dp, OutlineVariant),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                            Box(modifier = Modifier.size(40.dp).background(Purple50, RoundedCornerShape(8.dp)).padding(bottom = 8.dp), contentAlignment = Alignment.Center) {
                                Icon(Icons.Default.MilitaryTech, contentDescription = null, tint = Purple600)
                            }
                            Text("Level", style = MaterialTheme.typography.labelLarge, color = Secondary)
                            Text((uiState as? InterviewSummaryScreenUiState.Success)?.experienceLevel ?: "Senior", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium), color = MaterialTheme.colorScheme.onSurface)
                        }
                    }
                }

                // Questions Count Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = PrimaryContainer),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(24.dp)) {
                            Text("Estimated Scope", style = MaterialTheme.typography.labelLarge, color = OnPrimaryContainer.copy(alpha = 0.9f))
                            Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Text((uiState as? InterviewSummaryScreenUiState.Success)?.questionCount?.toString() ?: "15", style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold), color = OnPrimaryContainer)
                                Text("Questions", style = MaterialTheme.typography.titleLarge, color = OnPrimaryContainer, modifier = Modifier.padding(bottom = 8.dp))
                            }
                            val duration = ((uiState as? InterviewSummaryScreenUiState.Success)?.questionCount ?: 15) * 3
                            Text("Approx. $duration-${duration + 15} minutes duration", style = MaterialTheme.typography.bodySmall, color = OnPrimaryContainer.copy(alpha = 0.8f))
                        }
                        Icon(
                            Icons.Default.Quiz,
                            contentDescription = null,
                            tint = Color.White.copy(alpha = 0.1f),
                            modifier = Modifier.align(Alignment.CenterEnd).size(120.dp).offset(x = 24.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Preparation Section
            Column(verticalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxWidth()) {
                // Image Placeholder
                Box(modifier = Modifier.fillMaxWidth().height(180.dp).clip(RoundedCornerShape(12.dp)).background(SurfaceContainerLowest).border(1.dp, OutlineVariant, RoundedCornerShape(12.dp)), contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.PhotoCamera, contentDescription = null, tint = OutlineVariant, modifier = Modifier.size(48.dp))
                }

                // Tips Section
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = SurfaceContainerLow),
                    border = BorderStroke(1.dp, OutlineVariant),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Icon(Icons.Default.Lightbulb, contentDescription = null, tint = Primary)
                            Text("Quick Tips", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium), color = MaterialTheme.colorScheme.onSurface)
                        }
                        
                        @Composable
                        fun TipRow(icon: androidx.compose.ui.graphics.vector.ImageVector, text: String) {
                            Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                Icon(icon, contentDescription = null, tint = Secondary, modifier = Modifier.size(20.dp).padding(top = 2.dp))
                                Text(text, style = MaterialTheme.typography.bodyMedium, color = Secondary)
                            }
                        }
                        
                        TipRow(Icons.Default.VolumeOff, "Ensure you're in a quiet place with no interruptions.")
                        TipRow(Icons.Default.Videocam, "Check your lighting and camera angle for clarity.")
                        TipRow(Icons.Default.Wifi, "Confirm a stable internet connection for real-time AI processing.")
                        TipRow(Icons.Default.Timer, "Take a deep breath. You can pause between questions if needed.")
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Primary Action
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                val isLoading = (uiState as? InterviewSummaryScreenUiState.Success)?.isLoading == true
                
                Button(
                    onClick = { viewModel.submitAction(onSuccess = onNavigateForward) },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Primary),
                    shape = RoundedCornerShape(8.dp),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
                    enabled = !isLoading
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(modifier = Modifier.size(24.dp), color = MaterialTheme.colorScheme.onPrimary)
                    } else {
                        Text("Start Interview", style = MaterialTheme.typography.labelLarge)
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(Icons.Default.PlayArrow, contentDescription = null)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text("By starting, you agree to our AI session recording terms.", style = MaterialTheme.typography.bodySmall, color = Secondary, textAlign = TextAlign.Center)
            }
        }
    }
}
