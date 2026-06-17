package com.interview.platform.ui.screens.mod01_authentication.welcome

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Stitch Design Tokens — PrepCoach AI Welcome
private val Primary = Color(0xFF006B2C)
private val PrimaryContainer = Color(0xFF00873A)
private val OnPrimary = Color.White
private val OnPrimaryContainer = Color(0xFFF7FFF2)
private val PrimaryFixed = Color(0xFF7FFC97)
private val PrimaryFixedDim = Color(0xFF62DF7D)
private val Background = Color(0xFFF7F9FB)
private val Surface = Color(0xFFF7F9FB)
private val SurfaceContainerLowest = Color(0xFFFFFFFF)
private val SurfaceContainerHigh = Color(0xFFE6E8EA)
private val SurfaceContainerHighest = Color(0xFFE0E3E5)
private val OnSurface = Color(0xFF191C1E)
private val OnSurfaceVariant = Color(0xFF3E4A3D)
private val Secondary = Color(0xFF515F74)
private val SecondaryFixed = Color(0xFFD5E3FC)
private val TertiaryContainer = Color(0xFFC74668)
private val OutlineVariant = Color(0xFFBDCABA)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen(
    onGetStarted: () -> Unit = {},
    onLogIn: () -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        contentPadding = PaddingValues(0.dp)
    ) {
        // Top Navigation Bar
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(64.dp)
                    .background(Surface.copy(alpha = 0.8f)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Icon(Icons.Default.School, contentDescription = null, tint = Primary, modifier = Modifier.size(28.dp))
                    Text("PrepCoach AI", style = MaterialTheme.typography.titleLarge.copy(
                        color = Primary, fontWeight = FontWeight.SemiBold, fontSize = 22.sp
                    ))
                }
            }
        }

        // Hero Section
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 32.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Pill badge
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50.dp))
                        .background(PrimaryFixed)
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        "Personalized AI Interview Coaching",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = Color(0xFF002109), fontSize = 11.sp,
                            fontWeight = FontWeight.Bold, letterSpacing = 0.5.sp
                        )
                    )
                }

                // Headline
                Text(
                    "Ready to land your dream job?",
                    style = MaterialTheme.typography.displaySmall.copy(
                        color = OnSurface, fontWeight = FontWeight.SemiBold,
                        fontSize = 28.sp, letterSpacing = (-0.5).sp, lineHeight = 36.sp
                    )
                )

                // Body
                Text(
                    "Practice with our AI coach and get real-time feedback on your performance, body language, and technical accuracy.",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Secondary, fontSize = 14.sp, lineHeight = 20.sp
                    )
                )

                // CTA Buttons
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Button(
                        onClick = onGetStarted,
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Primary)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text("Get Started", style = MaterialTheme.typography.labelLarge.copy(
                                color = OnPrimary, fontWeight = FontWeight.SemiBold, fontSize = 14.sp
                            ))
                            Icon(Icons.Default.ArrowForward, contentDescription = null, tint = OnPrimary, modifier = Modifier.size(18.dp))
                        }
                    }
                    OutlinedButton(
                        onClick = onLogIn,
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Secondary),
                        border = androidx.compose.foundation.BorderStroke(1.dp, OutlineVariant)
                    ) {
                        Text("Log In", style = MaterialTheme.typography.labelLarge.copy(
                            color = Secondary, fontWeight = FontWeight.SemiBold, fontSize = 14.sp
                        ))
                    }
                }

                // Illustration area — gradient + floating cards
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(24.dp))
                        .background(
                            Brush.radialGradient(
                                colors = listOf(PrimaryFixed.copy(alpha = 0.3f), Background),
                                radius = 600f
                            )
                        )
                        .border(1.dp, SurfaceContainerHigh, RoundedCornerShape(24.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // AI coaching visual icon
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .clip(CircleShape)
                                .background(PrimaryContainer),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.School, contentDescription = null,
                                tint = OnPrimary, modifier = Modifier.size(48.dp))
                        }

                        Text("AI-Powered Coaching", style = MaterialTheme.typography.titleMedium.copy(
                            color = OnSurface, fontWeight = FontWeight.SemiBold
                        ))

                        // Floating insight card
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(SurfaceContainerLowest.copy(alpha = 0.9f))
                                .border(1.dp, SurfaceContainerHigh, RoundedCornerShape(12.dp))
                                .padding(16.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                Box(
                                    modifier = Modifier.size(40.dp).clip(CircleShape).background(PrimaryContainer),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(Icons.Default.Favorite, contentDescription = null, tint = OnPrimaryContainer, modifier = Modifier.size(22.dp))
                                }
                                Column {
                                    Text("Real-time Insight", style = MaterialTheme.typography.labelSmall.copy(
                                        color = OnSurfaceVariant, fontSize = 11.sp, letterSpacing = 0.5.sp
                                    ))
                                    Text("Perfect your pitch!", style = MaterialTheme.typography.bodyMedium.copy(
                                        color = Primary, fontWeight = FontWeight.SemiBold
                                    ))
                                }
                            }
                        }

                        // Confidence score floating card
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(SurfaceContainerLowest.copy(alpha = 0.9f))
                                .border(1.dp, SurfaceContainerHigh, RoundedCornerShape(12.dp))
                                .padding(16.dp)
                        ) {
                            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                    Text("Confidence Score", style = MaterialTheme.typography.labelSmall.copy(color = OnSurfaceVariant))
                                    Text("88%", style = MaterialTheme.typography.labelLarge.copy(color = Primary, fontWeight = FontWeight.Bold))
                                }
                                LinearProgressIndicator(
                                    progress = { 0.88f },
                                    modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp)),
                                    color = Primary,
                                    trackColor = SurfaceContainerHigh
                                )
                            }
                        }
                    }

                    // Accent badge
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .offset((-8).dp, 8.dp)
                            .size(48.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Primary)
                            .shadow(8.dp, RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.TrendingUp, contentDescription = null, tint = OnPrimary, modifier = Modifier.size(28.dp))
                    }
                }
            }
        }

        // Trust Signals
        item {
            HorizontalDivider(color = OutlineVariant)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                listOf(
                    Triple("50k+", "Active Users", Icons.Default.People),
                    Triple("94%", "Success Rate", Icons.Default.TrendingUp),
                    Triple("4.9/5", "User Rating", Icons.Default.Star)
                ).forEach { (value, label, icon) ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(value, style = MaterialTheme.typography.titleLarge.copy(
                            color = OnSurface, fontWeight = FontWeight.Bold, fontSize = 20.sp
                        ))
                        Text(label, style = MaterialTheme.typography.bodySmall.copy(
                            color = Secondary, fontSize = 12.sp
                        ))
                    }
                }
            }
        }

        // Feature Cards — Bento style
        item {
            Column(
                modifier = Modifier.background(SurfaceContainerLowest).padding(vertical = 32.dp),
            ) {
                Text(
                    "Why choose PrepCoach AI?",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        color = OnSurface, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Our systematic approach ensures you're ready for any challenge.",
                    style = MaterialTheme.typography.bodyMedium.copy(color = Secondary, textAlign = TextAlign.Center),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))

                Column(modifier = Modifier.padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    // AI Simulations — large feature card
                    Box(
                        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(24.dp))
                            .background(SurfaceContainerHigh).padding(24.dp)
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            Icon(Icons.Default.Chat, contentDescription = null, tint = Primary, modifier = Modifier.size(36.dp))
                            Text("Dynamic AI Simulations", style = MaterialTheme.typography.titleMedium.copy(
                                color = OnSurface, fontWeight = FontWeight.SemiBold
                            ))
                            Text("Interactive voice and video simulations that adapt to your answers in real-time, just like a real interviewer.",
                                style = MaterialTheme.typography.bodySmall.copy(color = Secondary))
                        }
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        // Detailed Analytics
                        Box(
                            modifier = Modifier.weight(1f).clip(RoundedCornerShape(24.dp))
                                .background(PrimaryContainer).padding(24.dp)
                        ) {
                            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                                Icon(Icons.Default.BarChart, contentDescription = null, tint = Color.White, modifier = Modifier.size(32.dp))
                                Text("Detailed Analytics", style = MaterialTheme.typography.titleSmall.copy(color = Color.White, fontWeight = FontWeight.SemiBold))
                                Text("Track your progress with sentiment analysis and pace tracking.",
                                    style = MaterialTheme.typography.bodySmall.copy(color = Color.White.copy(alpha = 0.8f)))
                            }
                        }
                        // Interview Library
                        Box(
                            modifier = Modifier.weight(1f).clip(RoundedCornerShape(24.dp))
                                .background(TertiaryContainer).padding(24.dp)
                        ) {
                            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                                Icon(Icons.Default.Book, contentDescription = null, tint = Color.White, modifier = Modifier.size(32.dp))
                                Text("Interview Library", style = MaterialTheme.typography.titleSmall.copy(color = Color.White, fontWeight = FontWeight.SemiBold))
                                Text("1,000+ industry-specific questions for tech, finance, and marketing.",
                                    style = MaterialTheme.typography.bodySmall.copy(color = Color.White.copy(alpha = 0.8f)))
                            }
                        }
                    }
                }
            }
        }

        // Footer
        item {
            Box(modifier = Modifier.fillMaxWidth().background(Surface).padding(vertical = 24.dp, horizontal = 16.dp)) {
                Text(
                    "© 2024 PrepCoach AI. Built for the future of work.",
                    style = MaterialTheme.typography.bodySmall.copy(color = Secondary),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
