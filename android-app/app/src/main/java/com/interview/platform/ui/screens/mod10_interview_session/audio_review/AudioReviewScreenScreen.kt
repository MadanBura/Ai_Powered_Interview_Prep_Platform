package com.interview.platform.ui.screens.mod10_interview_session.audio_review

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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.interview.platform.ui.theme.*

private val Background get() = PrepAiBackground
private val Primary get() = PrepAiPrimary
private val PrimaryContainer get() = PrepAiPrimaryContainer
private val OnPrimary get() = PrepAiOnPrimary
private val OnPrimaryContainer get() = PrepAiOnPrimaryContainer
private val Secondary get() = PrepAiSecondary
private val SecondaryContainer get() = PrepAiSecondaryContainer
private val OnSecondaryContainer get() = PrepAiOnSecondaryContainer
private val SurfaceContainerLowest get() = PrepAiSurfaceContainerLowest
private val SurfaceContainer get() = PrepAiSurfaceContainer
private val OutlineVariant get() = PrepAiOutlineVariant
private val Error get() = PrepAiError
private val OnError get() = PrepAiOnError
private val ErrorContainer get() = PrepAiErrorContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AudioReviewScreen(
    onNavigateForward: () -> Unit,
    onNavigateBack: () -> Unit = {},
    onNavigateReRecord: () -> Unit = {},
    viewModel: AudioReviewScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val state = uiState as? AudioReviewScreenUiState.Success ?: return

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
//        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Navigation Back Context
            Row(modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp), verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onNavigateBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Secondary)
                }
                Text("Question 2 of 5: Behavioral", style = MaterialTheme.typography.labelLarge, color = Secondary)
            }

            // Review Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
                border = BorderStroke(1.dp, OutlineVariant),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text("Review Your Answer", style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.onSurface, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Listen back to your response before submitting it for AI analysis.", style = MaterialTheme.typography.bodyLarge, color = Secondary, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)

                    Spacer(modifier = Modifier.height(32.dp))

                    // Audio Display Area
                    Box(modifier = Modifier.fillMaxWidth().background(SurfaceContainer, RoundedCornerShape(12.dp)).padding(24.dp)) {
                        Column {
                            // Progress Visualization Waveform mock
                            Row(modifier = Modifier.fillMaxWidth().height(96.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                                val bars = 30
                                for (i in 0 until bars) {
                                    val isActive = (i / bars.toFloat()) <= state.progress
                                    val h = (20..80).random()
                                    Box(modifier = Modifier.width(4.dp).height(h.dp).background(if (isActive) Primary else OutlineVariant, RoundedCornerShape(2.dp)))
                                }
                            }

                            Spacer(modifier = Modifier.height(24.dp))

                            // Controls Row
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                                Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
                                    Box(modifier = Modifier.size(48.dp).background(Primary, CircleShape).clickable { viewModel.togglePlayPause() }, contentAlignment = Alignment.Center) {
                                        Icon(if (state.isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow, contentDescription = null, tint = OnPrimary)
                                    }
                                    Column {
                                        Text(state.currentTimeStr, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.onSurface)
                                        Text("Total: ${state.totalTimeStr}", style = MaterialTheme.typography.bodySmall, color = Secondary)
                                    }
                                }

                                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    IconButton(onClick = { /*TODO*/ }) {
                                        Icon(Icons.Default.VolumeUp, contentDescription = "Volume", tint = Secondary)
                                    }
                                    IconButton(onClick = { /*TODO*/ }) {
                                        Icon(Icons.Default.Settings, contentDescription = "Settings", tint = Secondary)
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    // Action Buttons Cluster
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxWidth()) {
                        Button(
                            onClick = { viewModel.submitAction(onNavigateForward) },
                            modifier = Modifier.fillMaxWidth().height(48.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Primary),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(Icons.Default.Send, contentDescription = null, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Submit Answer", style = MaterialTheme.typography.labelLarge)
                        }

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            OutlinedButton(
                                onClick = onNavigateReRecord,
                                modifier = Modifier.weight(1f).height(48.dp),
                                border = BorderStroke(1.dp, OutlineVariant),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Icon(Icons.Default.Refresh, contentDescription = null, tint = Secondary, modifier = Modifier.size(20.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Re-record", style = MaterialTheme.typography.labelLarge, color = Secondary)
                            }

                            OutlinedButton(
                                onClick = { /*TODO*/ },
                                modifier = Modifier.weight(1f).height(48.dp),
                                border = BorderStroke(1.dp, Error),
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = Error),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Icon(Icons.Default.DeleteOutline, contentDescription = null, modifier = Modifier.size(20.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Delete", style = MaterialTheme.typography.labelLarge)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Pro-tip
            Box(modifier = Modifier.fillMaxWidth().background(SecondaryContainer.copy(alpha = 0.3f), RoundedCornerShape(8.dp)).border(1.dp, SecondaryContainer, RoundedCornerShape(8.dp)).padding(16.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.Top) {
                    Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = OnSecondaryContainer)
                    Column {
                        Text("Pro Tip", style = MaterialTheme.typography.labelLarge, color = OnSecondaryContainer)
                        Text("AI scoring prioritizes clarity and structured answers (STAR method). Make sure your key results are mentioned clearly.", style = MaterialTheme.typography.bodySmall, color = Secondary)
                    }
                }
            }
        }
    }
}
