package com.interview.platform.ui.screens.mod18_dashboard

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.interview.platform.ui.AppRoutes
import com.interview.platform.ui.components.PrepCoachBottomNav
import com.interview.platform.ui.theme.*

// ─────────────────────── Mock Data (matching Stitch designs) ─────────────────

private data class RecentScore(
    val role: String,
    val date: String,
    val duration: String,
    val score: Int,
    val tag: String,
    val tagColor: Color
)

private data class Recommendation(
    val title: String,
    val duration: String,
    val type: String,
    val iconRes: androidx.compose.ui.graphics.vector.ImageVector,
    val iconBg: Color
)

private val mockRecentScores = listOf(
    RecentScore("Backend Engineer", "Dec 12", "45 mins", 80, "Technical", PrepAiSecondaryContainer),
    RecentScore("Product Designer", "Dec 10", "30 mins", 92, "Behavioral", PrepAiTertiaryFixed)
)

private val mockRecommendations = listOf(
    Recommendation("Mastering React Hooks", "15 min read", "Technical", Icons.Default.Code, PrepAiSecondaryContainer),
    Recommendation("The STAR Method Guide", "10 min read", "Behavioral", Icons.Default.Psychology, PrepAiPrimaryFixed),
    Recommendation("Negotiating Your Salary", "8 min read", "Career", Icons.Default.Work, PrepAiTertiaryFixed)
)

private val perfData = listOf(0.60f, 0.65f, 0.70f, 0.85f, 0.80f)
private val perfLabels = listOf("Mon", "Tue", "Wed", "Thu", "Fri")

// ─────────────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeDashboardScreen(
    navController: NavController,
    viewModel: HomeDashboardViewModel = androidx.hilt.navigation.compose.hiltViewModel(),
    onStartInterview: () -> Unit = { navController.navigate(AppRoutes.departmentSelection) },
    onResumeInterview: () -> Unit = { navController.navigate(AppRoutes.interviewSession) },
    onStartForYouInterview: () -> Unit = {}
) {
    val userName by viewModel.userName.collectAsState()
    val isNewUser by viewModel.isNewUser.collectAsState()
    val userTechnologies by viewModel.userTechnologies.collectAsState()
    val userExperienceLevel by viewModel.userExperienceLevel.collectAsState()
    val recentScores by viewModel.recentScores.collectAsState()
    
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
                            modifier = Modifier.size(28.dp)
                        )
                        Text(
                            "PrepCoach AI",
                            style = MaterialTheme.typography.titleLarge.copy(
                                color = PrepAiPrimary,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 22.sp
                            )
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
                    containerColor = PrepAiSurface
                ),
                modifier = Modifier.border(
                    width = 1.dp,
                    color = PrepAiOutlineVariant.copy(alpha = 0.5f)
                )
            )
        },
//        bottomBar = {
//            PrepCoachBottomNav(currentTab = AppRoutes.home, navController = navController)
//        },
        containerColor = PrepAiBackground
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(top = 24.dp, bottom = 24.dp)
        ) {

            // ── Hero Greeting ──────────────────────────────────────────────
            item {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        if (userName.isBlank()) "Hello!" else "Hello, $userName!",
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = PrepAiOnBackground,
                            fontSize = 32.sp
                        )
                    )
                    Text(
                        if (isNewUser) "Welcome to your personal interview coach. Complete your profile and check your roadmap below to get started!"
                        else "You're on track to master your upcoming interview. Let's keep the momentum going.",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = PrepAiSecondary,
                            fontSize = 14.sp
                        )
                    )
                }
            }

            // ── Quick Action Cards ─────────────────────────────────────────
            item {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    // Resume Interview — Primary filled
                    if (!isNewUser) {
                        Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(PrepAiPrimary)
                            .clickable(onClick = onResumeInterview)
                            .padding(24.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Icon(
                                Icons.Default.PlayArrow,
                                contentDescription = null,
                                tint = PrepAiOnPrimary,
                                modifier = Modifier.size(28.dp)
                            )
                            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                                Text(
                                    "Resume Interview",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        color = PrepAiOnPrimary,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                )
                                Text(
                                    "Mid-level Frontend Role — 12 mins left",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = PrepAiPrimaryFixedDim,
                                        fontSize = 12.sp
                                    )
                                )
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Icon(
                                    Icons.Default.ArrowForward,
                                    contentDescription = null,
                                    tint = PrepAiOnPrimary
                                )
                            }
                        }
                    }
                    }

                    // Start New Interview — Outlined card
                    if (userTechnologies.isNotEmpty() && userExperienceLevel.isNotEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(PrepAiSurfaceContainerLowest)
                                .border(1.dp, PrepAiOutlineVariant, RoundedCornerShape(12.dp))
                                .clickable(onClick = onStartInterview)
                                .padding(24.dp)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Icon(
                                    Icons.Default.Add,
                                    contentDescription = null,
                                    tint = PrepAiPrimary,
                                    modifier = Modifier.size(28.dp)
                                )
                                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                                    Text(
                                        "Start New Interview",
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            color = PrepAiOnSurface,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    )
                                    Text(
                                        "Pick a role and industry",
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            color = PrepAiSecondary,
                                            fontSize = 12.sp
                                        )
                                    )
                                }
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    Icon(
                                        Icons.Default.ArrowForward,
                                        contentDescription = null,
                                        tint = PrepAiPrimary
                                    )
                                }
                            }
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(PrepAiPrimaryContainer)
                                .border(1.dp, PrepAiPrimary.copy(alpha = 0.2f), RoundedCornerShape(12.dp))
                                .clickable(onClick = { navController.navigate(AppRoutes.onboardingExperience) })
                                .padding(24.dp)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Icon(Icons.Default.AccountCircle, contentDescription = null, tint = PrepAiPrimary, modifier = Modifier.size(28.dp))
                                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                                    Text("Complete Your Profile", style = MaterialTheme.typography.titleMedium.copy(color = PrepAiOnSurface, fontWeight = FontWeight.SemiBold))
                                    Text("Add your experience and tech stack to start practicing.", style = MaterialTheme.typography.bodySmall.copy(color = PrepAiOnSurface.copy(alpha = 0.8f), fontSize = 12.sp))
                                }
                            }
                        }
                    }
                }
            }

            // ── Recent Scores (Hide if empty) ─────────────────────────
            if (recentScores.isNotEmpty()) {
                item {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "Recent Scores",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    color = PrepAiOnSurface,
                                    fontSize = 20.sp
                                )
                            )
                            TextButton(onClick = { navController.navigate(AppRoutes.interviewHistory) }) {
                                Text(
                                    "View Details",
                                    color = PrepAiPrimary,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 14.sp
                                )
                            }
                        }

                        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            recentScores.take(2).forEach { score ->
                                val dateStr = score.startedAt?.split("T")?.firstOrNull() ?: "Unknown Date"
                                val scoreVal = score.score ?: 0
                                ScoreCard(
                                    RecentScore(
                                        role = score.role ?: "Interview",
                                        date = dateStr,
                                        duration = score.duration ?: "-",
                                        score = scoreVal,
                                        tag = score.status ?: "Completed",
                                        tagColor = if (scoreVal >= 80) PrepAiPrimary else if (scoreVal >= 50) PrepAiSecondary else PrepAiError
                                    )
                                )
                            }
                        }
                    }
                }
            }

            // ── Performance Trend Chart ────────────────────────────────────
            if (!isNewUser) {
                item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = PrepAiSurfaceContainerLowest),
                    border = CardDefaults.outlinedCardBorder()
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp).fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "Performance Trend",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = FontWeight.Medium,
                                color = PrepAiOnSurface,
                                fontSize = 14.sp
                            ),
                            modifier = Modifier.align(Alignment.Start)
                        )
                        if (recentScores.size >= 2) {
                            val data = recentScores.take(5).reversed().map { (it.score ?: 0) / 100f }
                            val labels = recentScores.take(5).reversed().map { 
                                it.startedAt?.split("T")?.firstOrNull()?.takeLast(5) ?: ""
                            }
                            BarChart(data = data, labels = labels)
                        } else {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(96.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("Not enough data yet", style = MaterialTheme.typography.bodyMedium, color = PrepAiSecondary)
                            }
                        }
                    }
                }
            }

            }
            // ── New User Roadmap ───────────────────────────────────────────
            if (isNewUser) {
                item {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text(
                            "Your Interview Roadmap",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = PrepAiOnSurface,
                                fontSize = 20.sp
                            )
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(if (userTechnologies.isEmpty()) PrepAiPrimaryContainer else PrepAiSurfaceContainerLowest)
                                .border(1.dp, if (userTechnologies.isEmpty()) PrepAiPrimary.copy(alpha = 0.2f) else PrepAiOutlineVariant, RoundedCornerShape(12.dp))
                                .clickable(onClick = { if (userTechnologies.isEmpty()) navController.navigate(AppRoutes.onboardingExperience) else onStartInterview() })
                                .padding(24.dp)
                        ) {
                            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                    Icon(if (userTechnologies.isEmpty()) Icons.Default.Flag else Icons.Default.PlayArrow, contentDescription = null, tint = PrepAiPrimary, modifier = Modifier.size(28.dp))
                                    Text(if (userTechnologies.isEmpty()) "Step 1: Setup Profile" else "Step 2: Take First Mock Interview", style = MaterialTheme.typography.titleMedium, color = if (userTechnologies.isEmpty()) PrepAiOnPrimaryContainer else PrepAiOnSurface)
                                }
                                Text(if (userTechnologies.isEmpty()) "Add your Experience and Target Roles to receive tailored practice plans." else "Apply your $userTechnologies skills in a simulated interview.", style = MaterialTheme.typography.bodyMedium, color = if (userTechnologies.isEmpty()) PrepAiOnPrimaryContainer.copy(alpha = 0.8f) else PrepAiSecondary)
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                                    Icon(Icons.Default.ArrowForward, contentDescription = null, tint = PrepAiPrimary)
                                }
                            }
                        }
                    }
                }
            }

            // ── For You Recommendations ────────────────────────────────────
            item {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        "For You",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = PrepAiOnSurface,
                            fontSize = 20.sp
                        )
                    )
                    
                    if (userTechnologies.isNotEmpty() && userExperienceLevel.isNotEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(PrepAiPrimaryContainer)
                                .border(1.dp, PrepAiPrimary.copy(alpha = 0.2f), RoundedCornerShape(12.dp))
                                .clickable(onClick = { viewModel.startForYouInterview(onStartForYouInterview) })
                                .padding(24.dp)
                        ) {
                            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                    Icon(Icons.Default.Star, contentDescription = null, tint = PrepAiPrimary, modifier = Modifier.size(28.dp))
                                    Text("$userExperienceLevel $userTechnologies Interview", style = MaterialTheme.typography.titleMedium, color = PrepAiOnPrimaryContainer)
                                }
                                Text("A curated mock interview targeting your selected stack and experience level.", style = MaterialTheme.typography.bodyMedium, color = PrepAiOnPrimaryContainer.copy(alpha = 0.8f))
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                                    Icon(Icons.Default.PlayArrow, contentDescription = "Start Template", tint = PrepAiPrimary)
                                }
                            }
                        }
                    } else {
                        // Use mockRecommendations as random general recommendations
                        mockRecommendations.take(3).forEach { rec ->
                            RecommendationItem(rec)
                        }
                    }
                }
            }
        }
    }
}

// ─────────────────────────── Sub-composables ─────────────────────────────────

@Composable
private fun ScoreCard(scoreData: RecentScore) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(PrepAiSurfaceContainerLowest)
            .border(1.dp, PrepAiOutlineVariant, RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Score ring
        Box(
            modifier = Modifier.size(64.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                progress = { scoreData.score / 100f },
                modifier = Modifier.fillMaxSize(),
                color = PrepAiPrimary,
                trackColor = PrepAiSurfaceContainerHigh,
                strokeWidth = 6.dp,
                strokeCap = StrokeCap.Round
            )
            Text(
                "${scoreData.score}%",
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = PrepAiPrimary,
                    fontSize = 13.sp
                )
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                scoreData.role,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Medium,
                    color = PrepAiOnSurface,
                    fontSize = 14.sp
                )
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(
                    Icons.Default.CalendarToday,
                    contentDescription = null,
                    tint = PrepAiSecondary,
                    modifier = Modifier.size(12.dp)
                )
                Text(
                    "${scoreData.date} • ${scoreData.duration}",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = PrepAiSecondary,
                        fontSize = 12.sp
                    )
                )
            }
        }

        Surface(
            color = scoreData.tagColor,
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(
                scoreData.tag.uppercase(),
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp
                ),
                color = PrepAiOnSecondaryContainer,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
            )
        }
    }
}

@Composable
private fun BarChart(data: List<Float>, labels: List<String>) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(96.dp)
        ) {
            val barWidth = (size.width - (data.size - 1) * 8.dp.toPx()) / data.size
            data.forEachIndexed { idx, value ->
                val barHeight = size.height * value
                val x = idx * (barWidth + 8.dp.toPx())
                val y = size.height - barHeight
                drawRoundRect(
                    color = if (idx == data.size - 1) PrepAiPrimary
                    else PrepAiPrimary.copy(alpha = 0.3f + value * 0.3f),
                    topLeft = Offset(x, y),
                    size = Size(barWidth, barHeight),
                    cornerRadius = CornerRadius(4.dp.toPx())
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            labels.forEach { label ->
                Text(
                    label,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = PrepAiSecondary,
                        fontSize = 12.sp
                    )
                )
            }
        }
    }
}

@Composable
private fun RecommendationItem(rec: Recommendation) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(PrepAiSurfaceContainerLowest)
            .border(1.dp, PrepAiOutlineVariant, RoundedCornerShape(12.dp))
            .clickable {}
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(rec.iconBg),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                rec.iconRes,
                contentDescription = null,
                tint = PrepAiPrimary,
                modifier = Modifier.size(36.dp)
            )
        }
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                rec.title,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Medium,
                    color = PrepAiOnSurface,
                    fontSize = 14.sp
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                "${rec.duration} • ${rec.type}",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = PrepAiSecondary,
                    fontSize = 12.sp
                )
            )
        }
    }
}

// Shared Bottom Nav — now delegated to PrepCoachBottomNav composable
// (Kept here for backwards compatibility if any code references PrepCoachBottomNav from this package)
@Composable
fun PrepCoachBottomNav(currentRoute: String, navController: NavController) {
    com.interview.platform.ui.components.PrepCoachBottomNav(
        currentTab = currentRoute,
        navController = navController
    )
}
