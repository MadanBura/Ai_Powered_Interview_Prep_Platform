package com.interview.platform.ui.screens.mod10_interview_session.session

import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Lightbulb
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
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast
import androidx.core.content.ContextCompat
import android.content.pm.PackageManager

import com.interview.platform.ui.theme.*

private val Background get() = PrepAiBackground
private val Primary get() = PrepAiPrimary
private val OnPrimary get() = PrepAiOnPrimary
private val Secondary get() = PrepAiSecondary
private val SurfaceContainerHigh get() = PrepAiSurfaceContainerHigh
private val SurfaceContainerLowest get() = PrepAiSurfaceContainerLowest
private val OutlineVariant get() = PrepAiOutlineVariant
private val Error get() = PrepAiError

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InterviewSessionScreen(
    onNavigateForward: () -> Unit,
    onNavigateTypeAnswer: () -> Unit = {},
    onNavigateSkip: () -> Unit = {},
    viewModel: InterviewSessionScreenViewModel = hiltViewModel(),
    onNavigateCompleted: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    
    LaunchedEffect(uiState) {
        if (uiState is InterviewSessionScreenUiState.Completed) {
            onNavigateCompleted()
        }
    }
    
    if (uiState is InterviewSessionScreenUiState.Loading) {
        Box(modifier = Modifier.fillMaxSize().background(Background), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = Primary)
        }
        return
    }
    
    if (uiState is InterviewSessionScreenUiState.Error) {
        val errorMessage = (uiState as InterviewSessionScreenUiState.Error).message
        Box(modifier = Modifier.fillMaxSize().background(Background), contentAlignment = Alignment.Center) {
            Text(text = "Error: $errorMessage", color = Error)
        }
        return
    }
    
    val state = uiState as? InterviewSessionScreenUiState.Success ?: return

    val context = LocalContext.current
    
    val speechRecognizer = remember {
        SpeechRecognizer.createSpeechRecognizer(context).apply {
            setRecognitionListener(object : RecognitionListener {
                override fun onReadyForSpeech(params: Bundle?) {}
                override fun onBeginningOfSpeech() {}
                override fun onRmsChanged(rmsdB: Float) {}
                override fun onBufferReceived(buffer: ByteArray?) {}
                override fun onEndOfSpeech() {}
                override fun onError(error: Int) {
                    viewModel.stopRecording()
                    Toast.makeText(context, "Voice recognition stopped", Toast.LENGTH_SHORT).show()
                }
                override fun onResults(results: Bundle?) {
                    val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                    if (!matches.isNullOrEmpty()) {
                        viewModel.setFinalTranscript(matches[0])
                    } else {
                        viewModel.stopRecording()
                    }
                }
                override fun onPartialResults(partialResults: Bundle?) {
                    val matches = partialResults?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                    if (!matches.isNullOrEmpty()) {
                        viewModel.appendPartialTranscript(matches[0])
                    }
                }
                override fun onEvent(eventType: Int, params: Bundle?) {}
            })
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            viewModel.startRecording()
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
            }
            speechRecognizer.startListening(intent)
        } else {
            Toast.makeText(context, "Microphone permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    val onStartRecording = {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
            viewModel.startRecording()
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
            }
            speechRecognizer.startListening(intent)
        } else {
            permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
        }
    }

    val onStopRecording = {
        speechRecognizer.stopListening()
    }
    
    DisposableEffect(Unit) {
        onDispose {
            speechRecognizer.destroy()
        }
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
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (state.phase) {
                SessionPhase.IDLE -> IdlePhase(state, viewModel, onStartRecording)
                SessionPhase.RECORDING -> RecordingPhase(state, viewModel, onStopRecording)
                SessionPhase.REVIEW -> ReviewPhase(state, viewModel, onStartRecording)
            }
        }
    }
}

@Composable
fun IdlePhase(state: InterviewSessionScreenUiState.Success, viewModel: InterviewSessionScreenViewModel, onStartRecording: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Progress Bar Header
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Question ${state.currentQuestionIndex} of ${state.totalQuestions}", style = MaterialTheme.typography.labelLarge, color = Secondary)
                val progressPercent = ((state.currentQuestionIndex.toFloat() / state.totalQuestions.toFloat()) * 100).toInt()
                Text("$progressPercent% Complete", style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold), color = Primary)
            }
            Spacer(modifier = Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = { state.currentQuestionIndex.toFloat() / state.totalQuestions.toFloat() },
                modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp)),
                color = Primary,
                trackColor = SurfaceContainerHigh
            )
        }

        Spacer(modifier = Modifier.height(64.dp))

        // Circular Countdown Timer
        Box(contentAlignment = Alignment.Center, modifier = Modifier.size(96.dp)) {
            val progress = state.remainingTimeSeconds.toFloat() / state.totalTimeSeconds.toFloat()
            CircularProgressIndicator(
                progress = { 1f },
                modifier = Modifier.fillMaxSize(),
                color = SurfaceContainerHigh,
                strokeWidth = 4.dp
            )
            CircularProgressIndicator(
                progress = { progress },
                modifier = Modifier.fillMaxSize(),
                color = Primary,
                strokeWidth = 4.dp
            )
            Text("${state.remainingTimeSeconds}", style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold), color = Primary)
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Question Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
            border = BorderStroke(1.dp, OutlineVariant),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    state.questionText,
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    state.questionHint,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Secondary,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Bottom Controls
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Primary, CircleShape)
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = onStartRecording, modifier = Modifier.fillMaxSize()) {
                    Icon(Icons.Default.Mic, contentDescription = null, tint = OnPrimary, modifier = Modifier.size(40.dp))
                }
            }
            Text("Hold to Speak", color = Primary, style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold), modifier = Modifier.padding(top = 8.dp))

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = { viewModel.toggleTypingMode() }) {
                    Icon(Icons.Default.Keyboard, contentDescription = null, tint = Secondary)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Type Answer", color = Secondary, style = MaterialTheme.typography.labelLarge)
                }

                TextButton(onClick = { viewModel.skipQuestion() }) {
                    Text("Skip Question", color = Secondary, style = MaterialTheme.typography.labelLarge)
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(Icons.Default.FastForward, contentDescription = null, tint = Secondary)
                }
            }
        }
    }
}

@Composable
fun RecordingPhase(state: InterviewSessionScreenUiState.Success, viewModel: InterviewSessionScreenViewModel, onStopRecording: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("ACTIVE SESSION", style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold, letterSpacing = 1.5.sp), color = Primary)
        Spacer(modifier = Modifier.height(8.dp))
        Text(state.topic, style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.onSurface, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(8.dp))
        Text(state.questionText, style = MaterialTheme.typography.bodyMedium, color = Secondary, textAlign = TextAlign.Center)
        
        Spacer(modifier = Modifier.weight(1f))
        
        WaveformAnimation()
        
        Spacer(modifier = Modifier.weight(1f))
        
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(modifier = Modifier.size(12.dp).background(Error, CircleShape))
            Text(
                text = String.format("%02d:%02d", state.recordingTimeSeconds / 60, state.recordingTimeSeconds % 60),
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        Text("RECORDING LIVE", style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 1.5.sp), color = Secondary)
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
            border = BorderStroke(1.dp, OutlineVariant.copy(alpha = 0.5f)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .background(Primary, RoundedCornerShape(16.dp))
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Icon(Icons.Default.Mic, contentDescription = null, tint = OnPrimary, modifier = Modifier.size(16.dp))
                        Text("AI is listening...", color = OnPrimary, style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold))
                    }
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Button(
                    onClick = onStopRecording,
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Error),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(Icons.Default.Stop, contentDescription = null, tint = Color.White)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Stop & Analyze", style = MaterialTheme.typography.labelLarge.copy(color = Color.White, fontWeight = FontWeight.Bold))
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                TextButton(onClick = { viewModel.skipQuestion() }) {
                    Icon(Icons.Default.Close, contentDescription = null, tint = Secondary, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Cancel Interview", color = Secondary)
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                Divider(color = OutlineVariant.copy(alpha = 0.5f))
                Spacer(modifier = Modifier.height(16.dp))
                
                Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Icon(Icons.Outlined.Lightbulb, contentDescription = null, tint = Primary, modifier = Modifier.size(20.dp))
                    Text("\"Try using the STAR method (Situation, Task, Action, Result) to structure your response.\"", style = MaterialTheme.typography.bodySmall.copy(fontStyle = androidx.compose.ui.text.font.FontStyle.Italic), color = Secondary)
                }
            }
        }
    }
}

@Composable
fun ReviewPhase(state: InterviewSessionScreenUiState.Success, viewModel: InterviewSessionScreenViewModel, onStartRecording: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text("STEP ${state.currentQuestionIndex} OF ${state.totalQuestions}", style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold, letterSpacing = 1.5.sp), color = Primary)
        Spacer(modifier = Modifier.height(8.dp))
        Text(state.topic, style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.onSurface)
        Spacer(modifier = Modifier.height(8.dp))
        Text(state.questionText, style = MaterialTheme.typography.bodyMedium.copy(fontStyle = androidx.compose.ui.text.font.FontStyle.Italic), color = Secondary)
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Card(
            modifier = Modifier.fillMaxWidth().weight(1f),
            colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
            border = BorderStroke(1.dp, OutlineVariant.copy(alpha = 0.5f)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Icon(Icons.Default.GraphicEq, contentDescription = null, tint = Primary)
                        Text("Recording Answer...", style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold), color = Secondary)
                    }
                    TextButton(onClick = onStartRecording, contentPadding = PaddingValues(0.dp)) {
                        Icon(Icons.Default.Refresh, contentDescription = null, tint = Primary, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Re-record", color = Primary, style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold))
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                OutlinedTextField(
                    value = state.answerText,
                    onValueChange = { viewModel.updateAnswerText(it) },
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    textStyle = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSurface, lineHeight = 28.sp)
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        Icon(Icons.Default.Info, contentDescription = null, tint = Secondary, modifier = Modifier.size(16.dp))
                        Text("Transcription accuracy: 98%", style = MaterialTheme.typography.labelSmall, color = Secondary)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        Icon(Icons.Default.Edit, contentDescription = null, tint = Secondary, modifier = Modifier.size(16.dp))
                        Text("Edit Text", style = MaterialTheme.typography.labelSmall, color = Secondary)
                    }
                }
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        OutlinedButton(
            onClick = { /* Save draft logic */ },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, Secondary)
        ) {
            Text("Save Draft", color = MaterialTheme.colorScheme.onSurface, style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold))
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Button(
            onClick = { viewModel.submitAnswer() },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Primary),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Confirm & Submit", style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold))
            Spacer(modifier = Modifier.width(8.dp))
            Icon(Icons.Default.Send, contentDescription = null)
        }
    }
}

@Composable
fun WaveformAnimation() {
    val infiniteTransition = rememberInfiniteTransition()
    
    // Remember random targets so they don't reset on recomposition
    val count = 25
    val barProps = remember {
        List(count) {
            val targetHeight = (20..90).random().toFloat()
            val duration = (300..700).random()
            Pair(targetHeight, duration)
        }
    }

    Row(
        modifier = Modifier.height(100.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        barProps.forEachIndexed { index, props ->
            val height by infiniteTransition.animateFloat(
                initialValue = 10f,
                targetValue = props.first,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = props.second, easing = FastOutSlowInEasing),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "WaveformAnim$index"
            )
            Box(
                modifier = Modifier
                    .padding(horizontal = 2.dp)
                    .width(6.dp)
                    .height(height.dp)
                    .background(Primary, RoundedCornerShape(3.dp))
            )
        }
    }
}
