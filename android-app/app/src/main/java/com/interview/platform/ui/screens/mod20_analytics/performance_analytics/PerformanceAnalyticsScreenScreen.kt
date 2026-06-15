package com.interview.platform.ui.screens.mod20_analytics.performance_analytics

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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.interview.platform.ui.theme.*

private val Background    get() = PrepAiBackground
private val Primary       get() = PrepAiPrimary
private val OnPrimary     get() = PrepAiOnPrimary
private val PrimaryContainer get() = PrepAiPrimaryContainer
private val OnPrimaryContainer get() = PrepAiOnPrimaryContainer
private val Secondary     get() = PrepAiSecondary
private val SecondaryContainer get() = PrepAiSecondaryContainer
private val OnSecondaryContainer get() = PrepAiOnSecondaryContainer
private val Tertiary      get() = PrepAiTertiary
private val SurfaceContainerLowest get() = PrepAiSurfaceContainerLowest
private val SurfaceContainerHighest get() = PrepAiSurfaceContainerHighest
private val SurfaceContainerHigh get() = PrepAiSurfaceContainerHigh
private val SurfaceContainer get() = PrepAiSurfaceContainer
private val OnSurface     get() = PrepAiOnSurface
private val OnSurfaceVariant get() = PrepAiOnSurfaceVariant
private val OutlineVariant get() = PrepAiOutlineVariant

// ── Stitch data models ────────────────────────────────────────────────────────

private data class DayBar(val day: String, val heightFraction: Float, val isToday: Boolean = false)
private data class SkillCard(val label: String, val score: Int, val icon: ImageVector, val barColor: Color, val description: String)

private val weekBars = listOf(
    DayBar("MON", 0.45f),
    DayBar("TUE", 0.60f),
    DayBar("WED", 0.55f),
    DayBar("THU", 0.78f),
    DayBar("FRI", 0.82f),
    DayBar("SAT", 0.90f, isToday = true),
    DayBar("SUN", 0.00f)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerformanceAnalyticsScreen(
    onNavigateForward: () -> Unit,
    viewModel: PerformanceAnalyticsScreenViewModel = androidx.hilt.navigation.compose.hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    val skillCards = listOf(
        SkillCard("Technical",     88, Icons.Default.Terminal,         Primary,          "Strong logic skills. Focus more on complexity analysis in future sessions."),
        SkillCard("Communication", 72, Icons.Default.RecordVoiceOver,   Tertiary,         "Good pacing. Try to reduce filler words like \"um\" and \"actually\"."),
        SkillCard("Confidence",    94, Icons.Default.Psychology,        PrimaryContainer, "Excellent presence and eye contact. You seem very comfortable with the topics.")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Interview Prep",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold, color = Primary
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* back */ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Primary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SurfaceContainerLowest),
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
                .padding(horizontal = 16.dp)
                .padding(top = 24.dp, bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // ── Header ──────────────────────────────────────────────────────
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Performance Trends",
                    style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                    color = OnSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "Analyze your growth across key interview pillars. Consistency is the foundation of confidence.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Secondary
                )
            }

            // ── Weekly Score Momentum (bar chart) ──────────────────────────
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
                border = androidx.compose.foundation.BorderStroke(1.dp, OutlineVariant)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("Weekly Score Momentum",
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
                                color = OnSurface)
                            Text("Overall average trend across all categories",
                                style = MaterialTheme.typography.bodySmall, color = Secondary)
                        }
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(50.dp))
                                .background(Primary.copy(alpha = 0.1f))
                                .padding(horizontal = 12.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(Icons.Default.TrendingUp, contentDescription = null,
                                tint = Primary, modifier = Modifier.size(16.dp))
                            Text("+12% vs LW",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold, color = Primary))
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Grid lines + bars
                    Box(modifier = Modifier.fillMaxWidth().height(160.dp)) {
                        // Horizontal grid lines
                        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
                            listOf("100", "75", "50", "25", "").forEach { label ->
                                Row(verticalAlignment = Alignment.Bottom) {
                                    Text(label, style = MaterialTheme.typography.labelSmall,
                                        color = Secondary.copy(alpha = 0.5f),
                                        modifier = Modifier.width(28.dp))
                                    HorizontalDivider(color = OutlineVariant.copy(alpha = 0.3f), thickness = 0.5.dp)
                                }
                            }
                        }
                        // Bars
                        Row(
                            modifier = Modifier.fillMaxSize().padding(start = 28.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            weekBars.forEach { bar ->
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Bottom,
                                    modifier = Modifier.fillMaxHeight().weight(1f)
                                ) {
                                    val barColor = when {
                                        bar.heightFraction == 0f -> OutlineVariant.copy(alpha = 0.4f)
                                        bar.isToday             -> Primary
                                        else                    -> SecondaryContainer
                                    }
                                    Spacer(modifier = Modifier.weight(1f - bar.heightFraction))
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth(0.6f)
                                            .fillMaxHeight(bar.heightFraction)
                                            .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                                            .background(barColor)
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        bar.day,
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            fontSize = 9.sp,
                                            fontWeight = if (bar.isToday) FontWeight.Bold else FontWeight.Medium,
                                            color = if (bar.isToday) Primary else Secondary
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // ── Weekly Comparison + Sessions Today ─────────────────────────
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                // Comparison card — Primary filled
                Card(
                    modifier = Modifier.weight(1.7f),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Primary)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text("Weekly Comparison",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                            color = OnPrimary)
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Column {
                                Text("Last Week",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = OnPrimary.copy(alpha = 0.8f))
                                Text("74%",
                                    style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                                    color = OnPrimary)
                            }
                            Icon(Icons.Default.ArrowForward, contentDescription = null,
                                tint = OnPrimary.copy(alpha = 0.8f),
                                modifier = Modifier.size(28.dp).padding(bottom = 4.dp))
                            Column(horizontalAlignment = Alignment.End) {
                                Text("Current",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = OnPrimary.copy(alpha = 0.8f))
                                Text("86%",
                                    style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                                    color = OnPrimary)
                            }
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        HorizontalDivider(color = OnPrimary.copy(alpha = 0.2f))
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "\"Your technical accuracy has improved by 18 points this week.\"",
                            style = MaterialTheme.typography.bodySmall.copy(fontStyle = androidx.compose.ui.text.font.FontStyle.Italic),
                            color = OnPrimary.copy(alpha = 0.9f)
                        )
                    }
                }

                // Sessions today chip
                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceContainerHighest),
                    border = androidx.compose.foundation.BorderStroke(1.dp, OutlineVariant)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(16.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Sessions Today",
                            style = MaterialTheme.typography.labelMedium,
                            color = OnSurfaceVariant)
                        Text("04",
                            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                            color = OnSurface)
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(Primary.copy(alpha = 0.1f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Schedule, contentDescription = null,
                                tint = Primary, modifier = Modifier.size(22.dp))
                        }
                    }
                }
            }

            // ── Category Breakdown (3 cards) ──────────────────────────────
            skillCards.forEach { skill ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
                    border = androidx.compose.foundation.BorderStroke(1.dp, OutlineVariant)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(SecondaryContainer),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(skill.icon, contentDescription = null,
                                    tint = OnSecondaryContainer, modifier = Modifier.size(22.dp))
                            }
                            Text(skill.label,
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                                color = OnSurface)
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text("${skill.score}",
                                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                                color = OnSurface)
                            Text("/ 100",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Secondary,
                                modifier = Modifier.padding(bottom = 3.dp))
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .clip(CircleShape)
                                .background(OutlineVariant.copy(alpha = 0.3f))
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth(skill.score / 100f)
                                    .clip(CircleShape)
                                    .background(skill.barColor)
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(skill.description,
                            style = MaterialTheme.typography.bodySmall,
                            color = Secondary)
                    }
                }
            }

            // ── Next Milestone — glassmorphism insight card ─────────────────
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
                border = androidx.compose.foundation.BorderStroke(1.dp, OutlineVariant.copy(alpha = 0.5f))
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    // Decorative background blob
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .align(Alignment.TopEnd)
                            .offset(x = 40.dp, y = (-40).dp)
                            .clip(CircleShape)
                            .background(Primary.copy(alpha = 0.05f))
                    )
                    Column(modifier = Modifier.padding(24.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .clip(CircleShape)
                                    .background(Primary.copy(alpha = 0.1f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.EmojiEvents, contentDescription = null,
                                    tint = Primary, modifier = Modifier.size(26.dp))
                            }
                            Text("Next Milestone: Expert Level",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = OnSurface)
                        }
                        Text(
                            "Based on your current trajectory, you are only 5 technical interviews away from reaching the \"Expert\" tier. " +
                            "We recommend focusing on Distributed Systems next week.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Secondary
                        )
                        Button(
                            onClick = onNavigateForward,
                            colors = ButtonDefaults.buttonColors(containerColor = Primary),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.fillMaxWidth().height(48.dp)
                        ) {
                            Icon(Icons.Default.Route, contentDescription = null,
                                modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Explore Roadmap",
                                style = MaterialTheme.typography.labelLarge)
                        }
                    }
                }
            }
        }
    }
}
