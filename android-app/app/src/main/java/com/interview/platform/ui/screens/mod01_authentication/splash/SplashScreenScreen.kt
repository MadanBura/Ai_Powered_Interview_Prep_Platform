package com.interview.platform.ui.screens.mod01_authentication.splash

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.interview.platform.ui.theme.*

private val Background get() = PrepAiBackground
private val Primary get() = PrepAiPrimary
private val Secondary get() = PrepAiSecondary
private val SurfaceContainerLowest get() = PrepAiSurfaceContainerLowest


val SecondaryFixedDim = Color(0xFFB9C7DF)

@Composable
fun SplashScreenScreen(
    onNavigateToWelcome: () -> Unit,
    onNavigateToHome: () -> Unit,
    viewModel: SplashScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    val shimmerTranslateAnim = rememberInfiniteTransition()
    val translateAnim by shimmerTranslateAnim.animateFloat(
        initialValue = -1000f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    LaunchedEffect(uiState) {
        when (uiState) {
            is SplashScreenUiState.NavigateToHome -> onNavigateToHome()
            is SplashScreenUiState.NavigateToWelcome -> onNavigateToWelcome()
            else -> {}
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        // Decorative blurred circles in background
        Box(
            modifier = Modifier
                .offset(x = 100.dp, y = (-50).dp)
                .size(200.dp)
                .background(Color(0xFF7FFC97).copy(alpha = 0.2f), CircleShape)
        )
        Box(
            modifier = Modifier
                .offset(x = (-50).dp, y = 600.dp)
                .size(150.dp)
                .background(Color(0xFFD5E3FC).copy(alpha = 0.2f), CircleShape)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo Container
            Box(
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .size(96.dp)
                    .background(SurfaceContainerLowest, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.School,
                    contentDescription = "Logo",
                    tint = Primary,
                    modifier = Modifier.size(48.dp)
                )
            }

            // Brand Identity
            Text(
                text = "PrepCoach AI",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp,
                    letterSpacing = (-0.5).sp
                ),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Master your next interview with AI",
                style = MaterialTheme.typography.bodyMedium,
                color = Secondary
            )

            Spacer(modifier = Modifier.height(120.dp))

            // Loading Indicator (Shimmer Bar)
            Box(
                modifier = Modifier
                    .width(192.dp)
                    .height(4.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Color(0xFFE2E8F0))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .offset(x = translateAnim.dp)
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.White.copy(alpha = 0.6f),
                                    Color.Transparent
                                )
                            )
                        )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "INITIALIZING AI COACH",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 1.5.sp
                ),
                color = SecondaryFixedDim.copy(alpha = 0.6f)
            )
        }
    }
}
