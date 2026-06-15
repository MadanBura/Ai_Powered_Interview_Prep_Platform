package com.interview.platform.ui.screens.mod13_speech_to_text.speech_to_text

import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.unit.sp
import com.interview.platform.ui.theme.*

private val Background get() = PrepAiBackground
private val Primary get() = PrepAiPrimary
private val PrimaryContainer get() = PrepAiPrimaryContainer
private val OnPrimaryContainer get() = PrepAiOnPrimaryContainer
private val Secondary get() = PrepAiSecondary
private val SecondaryContainer get() = PrepAiSecondaryContainer
private val OnSecondaryContainer get() = PrepAiOnSecondaryContainer
private val SurfaceContainerLowest get() = PrepAiSurfaceContainerLowest
private val OutlineVariant get() = PrepAiOutlineVariant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpeechToTextScreen(
    onNavigateForward: () -> Unit,
    viewModel: SpeechToTextScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val state = uiState as? SpeechToTextScreenUiState.Success ?: return

    val permissionLauncher = androidx.activity.compose.rememberLauncherForActivityResult(
        contract = androidx.activity.result.contract.ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            viewModel.startListening()
        }
    }

    LaunchedEffect(Unit) {
        permissionLauncher.launch(android.Manifest.permission.RECORD_AUDIO)
    }

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
                    Surface(color = SecondaryContainer, shape = RoundedCornerShape(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)) {
                            Icon(Icons.Default.Timer, contentDescription = null, tint = OnSecondaryContainer, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("12:45", style = MaterialTheme.typography.labelLarge, color = OnSecondaryContainer)
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
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
        containerColor = Background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            // Context Header
            Column(modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp)) {
                Text("STEP ${state.currentStep} OF ${state.totalSteps}", style = MaterialTheme.typography.labelLarge, color = Primary, modifier = Modifier.padding(bottom = 8.dp))
                Text("Technical Question", style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.onSurface, modifier = Modifier.padding(bottom = 12.dp))
                Text("\"${state.questionText}\"", style = MaterialTheme.typography.titleLarge, color = Secondary, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic)
            }

            // Main Transcription Canvas
            Card(
                modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
                border = BorderStroke(1.dp, OutlineVariant),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    // Header
                    Row(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            if (state.isRecording) {
                                // Wave animation mock
                                Row(horizontalArrangement = Arrangement.spacedBy(2.dp), verticalAlignment = Alignment.CenterVertically) {
                                    val infiniteTransition = rememberInfiniteTransition()
                                    val scale1 by infiniteTransition.animateFloat(initialValue = 0.4f, targetValue = 1f, animationSpec = infiniteRepeatable(tween(400), RepeatMode.Reverse))
                                    val scale2 by infiniteTransition.animateFloat(initialValue = 1f, targetValue = 0.4f, animationSpec = infiniteRepeatable(tween(400), RepeatMode.Reverse))
                                    val scale3 by infiniteTransition.animateFloat(initialValue = 0.6f, targetValue = 1f, animationSpec = infiniteRepeatable(tween(500), RepeatMode.Reverse))
                                    
                                    Box(modifier = Modifier.width(4.dp).height((24 * scale1).dp).background(Primary, RoundedCornerShape(2.dp)))
                                    Box(modifier = Modifier.width(4.dp).height((24 * scale2).dp).background(Primary, RoundedCornerShape(2.dp)))
                                    Box(modifier = Modifier.width(4.dp).height((24 * scale3).dp).background(Primary, RoundedCornerShape(2.dp)))
                                }
                                Text("Recording Answer...", style = MaterialTheme.typography.labelLarge, color = Secondary)
                            } else {
                                Text("Not Recording", style = MaterialTheme.typography.labelLarge, color = Secondary)
                            }
                        }
                        
                        TextButton(onClick = { 
                            if (state.isRecording) {
                                viewModel.stopListening()
                            } else {
                                permissionLauncher.launch(android.Manifest.permission.RECORD_AUDIO) 
                            }
                        }) {
                            Icon(if (state.isRecording) Icons.Default.Stop else Icons.Default.Refresh, contentDescription = null, tint = Primary)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(if (state.isRecording) "Stop" else "Re-record", style = MaterialTheme.typography.labelLarge, color = Primary)
                        }
                    }

                    // Text Area
                    if (state.isEditing) {
                        OutlinedTextField(
                            value = state.transcribedText,
                            onValueChange = { viewModel.updateText(it) },
                            textStyle = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.onSurface),
                            modifier = Modifier.fillMaxWidth().heightIn(min = 240.dp)
                        )
                    } else {
                        Text(
                            state.transcribedText,
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.fillMaxWidth().heightIn(min = 240.dp)
                        )
                    }

                    Divider(color = OutlineVariant.copy(alpha = 0.5f), modifier = Modifier.padding(vertical = 16.dp))

                    // Footer
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            Icon(Icons.Default.Info, contentDescription = null, tint = OutlineVariant, modifier = Modifier.size(18.dp))
                            Text("Transcription accuracy: 98%", style = MaterialTheme.typography.bodySmall, color = Secondary)
                        }
                        TextButton(onClick = { viewModel.toggleEdit() }) {
                            Icon(Icons.Default.Edit, contentDescription = null, tint = Secondary)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(if (state.isEditing) "Done" else "Edit Text", style = MaterialTheme.typography.labelLarge, color = Secondary)
                        }
                    }
                }
            }

            // Action Cluster
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically) {
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.height(48.dp),
                    border = BorderStroke(2.dp, Secondary),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Save Draft", style = MaterialTheme.typography.titleMedium, color = Secondary)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = { viewModel.submitAction(onNavigateForward) },
                    modifier = Modifier.height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Primary),
                    shape = RoundedCornerShape(8.dp),
                    enabled = !state.isLoading
                ) {
                    if (state.isLoading) {
                        CircularProgressIndicator(modifier = Modifier.size(24.dp), color = MaterialTheme.colorScheme.onPrimary)
                    } else {
                        Text("Confirm & Submit", style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(Icons.Default.Send, contentDescription = null)
                    }
                }
            }

            Spacer(modifier = Modifier.height(64.dp))

            // Footer
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.VerifiedUser, contentDescription = null, tint = Secondary.copy(alpha = 0.4f), modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("ENTERPRISE SECURE SESSION", style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold), color = Secondary.copy(alpha = 0.4f), letterSpacing = 1.sp)
            }
        }
    }
}
