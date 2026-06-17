package com.interview.platform.ui.screens.mod12_voice_recording.recording

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.interview.platform.ui.theme.*
import androidx.compose.foundation.Canvas
import kotlin.math.PI
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VoiceRecordingScreen(
    onNavigateForward: () -> Unit,
    onNavigateCancel: () -> Unit = {},
    viewModel: VoiceRecordingScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val state = uiState as? VoiceRecordingScreenUiState.Success ?: return

    val permissionLauncher = androidx.activity.compose.rememberLauncherForActivityResult(
        contract = androidx.activity.result.contract.ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            viewModel.startRecording()
        } else {
            onNavigateCancel()
        }
    }

    LaunchedEffect(Unit) {
        permissionLauncher.launch(android.Manifest.permission.RECORD_AUDIO)
    }

    // Blinking recording indicator
    val infiniteTransition = rememberInfiniteTransition(label = "recording_indicator")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(900, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "blink"
    )

    // Animated waveform phase
    val wavePhase by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = (2.0 * PI).toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "wave_phase"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            Icons.Default.Psychology,
                            contentDescription = null,
                            tint = PrepAiPrimary,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            "PrepCoach AI",
                            color = PrepAiPrimary,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            tint = PrepAiSecondary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PrepAiBackground,
                    scrolledContainerColor = PrepAiBackground
                ),
                modifier = Modifier.border(width = 1.dp, color = PrepAiOutlineVariant.copy(alpha = 0.5f))
            )
        },
        containerColor = PrepAiBackground
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Subtle atmospheric glow
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(280.dp)
                        .background(PrepAiPrimary.copy(alpha = 0.05f), CircleShape)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // ── Progress bar at top ────────────────────────────────────
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        "Question 1 of 15",
                        style = MaterialTheme.typography.labelMedium,
                        color = PrepAiSecondary
                    )
                    LinearProgressIndicator(
                        progress = { 1f / 15f },
                        modifier = Modifier
                            .weight(1f)
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        color = PrepAiPrimary,
                        trackColor = PrepAiSurfaceContainerHigh
                    )
                    Text(
                        "6%",
                        style = MaterialTheme.typography.labelMedium,
                        color = PrepAiPrimary,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // ── Active Session Label ───────────────────────────────────
                Surface(
                    color = PrepAiPrimaryContainer.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        "ACTIVE SESSION",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        ),
                        color = PrepAiPrimary,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    "Behavioral: \"Conflict Resolution\"",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    ),
                    color = PrepAiOnSurface,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    "Tell me about a time you had a disagreement with a colleague.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = PrepAiSecondary,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.weight(1f))

                // ── Animated Waveform ──────────────────────────────────────
                WaveformBars(
                    waveHeights = state.waveHeights,
                    phaseOffset = wavePhase,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(96.dp)
                )

                Spacer(modifier = Modifier.weight(1f))

                // ── Timer ──────────────────────────────────────────────────
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .background(PrepAiError, CircleShape)
                            .alpha(alpha)
                    )
                    Text(
                        "• ${state.elapsedTimeStr}",
                        style = MaterialTheme.typography.displaySmall.copy(
                            fontWeight = FontWeight.Light,
                            fontSize = 36.sp
                        ),
                        color = PrepAiOnSurface
                    )
                }

                Text(
                    "RECORDING LIVE",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.5.sp
                    ),
                    color = PrepAiSecondary,
                    modifier = Modifier.padding(top = 6.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))
            }

            // ── Controls Card (Floating at bottom) ─────────────────────────
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp)
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = PrepAiSurfaceContainerLowest),
                    border = BorderStroke(1.dp, PrepAiOutlineVariant),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        // AI Listening badge
                        Surface(
                            color = PrepAiPrimaryContainer,
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Icon(
                                    Icons.Default.Mic,
                                    contentDescription = null,
                                    tint = PrepAiOnPrimaryContainer,
                                    modifier = Modifier.size(18.dp)
                                )
                                Text(
                                    "AI is listening...",
                                    style = MaterialTheme.typography.labelLarge,
                                    color = PrepAiOnPrimaryContainer
                                )
                            }
                        }

                        // Stop & Analyze
                        Button(
                            onClick = { viewModel.submitAction(onNavigateForward) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(54.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = PrepAiError,
                                contentColor = PrepAiOnError
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(Icons.Default.Stop, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                "Stop & Analyze",
                                style = MaterialTheme.typography.titleMedium
                            )
                        }

                        // Cancel Interview
                        TextButton(
                            onClick = onNavigateCancel,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = null,
                                tint = PrepAiSecondary,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                "Cancel Interview",
                                style = MaterialTheme.typography.labelLarge,
                                color = PrepAiSecondary
                            )
                        }

                        // STAR Tip
                        HorizontalDivider(color = PrepAiOutlineVariant.copy(alpha = 0.4f))
                        Row(
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Icon(
                                Icons.Default.Lightbulb,
                                contentDescription = null,
                                tint = PrepAiPrimary,
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                "\"Try using the STAR method (Situation, Task, Action, Result) to structure your response.\"",
                                style = MaterialTheme.typography.bodySmall,
                                color = PrepAiSecondary
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * Canvas-based animated waveform bars.
 * Uses a precomputed sine table (avoiding any Compose ComplexDouble operator ambiguity)
 * animated via a phaseOffset float from InfiniteTransition.
 */
@Composable
private fun WaveformBars(
    waveHeights: List<Int>,
    phaseOffset: Float,
    modifier: Modifier = Modifier
) {
    val sinTable = remember {
        val tableSize = 64
        FloatArray(tableSize) { i ->
            val angle = (i.toDouble() / tableSize) * 2.0 * Math.PI
            ((Math.sin(angle) + 1.0) / 2.0).toFloat()
        }
    }
    val barCount = 28
    val barColor = PrepAiPrimary
    val floatHeights = remember(waveHeights) { waveHeights.map { it.toFloat() } }

    Canvas(modifier = modifier) {
        val gap = 4.dp.toPx()
        val barWidth = (size.width - gap * (barCount - 1)) / barCount
        val tableSize = sinTable.size

        repeat(barCount) { i ->
            val baseNorm = floatHeights.getOrElse(i % floatHeights.size) { 30f } / 80f
            val tableIndex = ((phaseOffset / (2f * PI.toFloat()) + i.toFloat() / barCount) * tableSize)
                .toInt()
                .let { ((it % tableSize) + tableSize) % tableSize }
            val sinFactor = sinTable[tableIndex]
            val barHeightFraction = (0.25f + 0.6f * baseNorm * sinFactor + 0.15f * baseNorm)
                .coerceIn(0.08f, 1f)
            val barHeightPx = size.height * barHeightFraction
            val x = i * (barWidth + gap)
            val y = (size.height - barHeightPx) / 2f

            drawRoundRect(
                color = barColor.copy(alpha = (0.4f + 0.6f * barHeightFraction).coerceIn(0.4f, 1f)),
                topLeft = androidx.compose.ui.geometry.Offset(x, y),
                size = androidx.compose.ui.geometry.Size(barWidth, barHeightPx),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(barWidth / 2f)
            )
        }
    }
}
