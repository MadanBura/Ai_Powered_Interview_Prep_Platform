package com.interview.platform.ui.screens.mod19_recommendation_engine.practice_plan

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
private val SurfaceContainerLow get() = PrepAiSurfaceContainerLow
private val SurfaceContainerHigh get() = PrepAiSurfaceContainerHigh
private val SurfaceVariant get() = PrepAiSurfaceVariant
private val OutlineVariant get() = PrepAiOutlineVariant
private val PrimaryFixed get() = PrepAiPrimaryFixed
private val OnPrimaryFixedVariant get() = PrepAiOnPrimaryFixedVariant

private data class DayPlan(
    val weekLabel: String,
    val date: String,
    val title: String,
    val status: DayStatus,
    val duration: String
)
private enum class DayStatus { COMPLETED, ACTIVE, UPCOMING, ASSESSMENT }

private data class TailoredResource(
    val icon: ImageVector,
    val iconBgColor: Color,
    val iconColor: Color,
    val title: String,
    val subtitle: String,
    val tag1: String,
    val tag2: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PracticePlanScreenScreen(
    onNavigateToRoadmap: () -> Unit,
    onNavigateToLearningRecs: () -> Unit,
    onNavigateToTechRecs: () -> Unit,
    viewModel: PracticePlanScreenViewModel = androidx.hilt.navigation.compose.hiltViewModel()
) {
    val days = listOf(
        DayPlan("Week, 23", "Mon, 23", "Day 1: Intro", DayStatus.COMPLETED, "Done"),
        DayPlan("Tue, 24", "Tue, 24", "Day 2: Coding", DayStatus.ACTIVE, "45 min"),
        DayPlan("Wed, 25", "Wed, 25", "Day 3: Behav.", DayStatus.UPCOMING, "30 min"),
        DayPlan("Thu, 26", "Thu, 26", "Day 4: System", DayStatus.UPCOMING, "1 hr"),
        DayPlan("Fri, 27", "Fri, 27", "Day 5: Mock", DayStatus.UPCOMING, "1.5 hr"),
        DayPlan("Sat, 28", "Sat, 28", "Day 6: Review", DayStatus.UPCOMING, "40 min"),
        DayPlan("Sun, 29", "Sun, 29", "Day 7: Final", DayStatus.ASSESSMENT, "Assessment")
    )

    val tailored = listOf(
        TailoredResource(
            Icons.Default.Psychology, SecondaryContainer, OnSecondaryContainer,
            "Behavioral Question Bank",
            "Top 50 STAR method questions for senior engineering roles.",
            "Behavioral", "View Bank"
        ),
        TailoredResource(
            Icons.Default.Code, PrimaryFixed, OnPrimaryFixedVariant,
            "Coding Challenge Set",
            "Focusing on Dynamic Programming and recursion patterns.",
            "Technical", "Last Done: Today"
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Learning Path",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold, color = Primary)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Primary)
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings", tint = Primary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = SurfaceContainerLowest),
                modifier = Modifier.border(width = 1.dp, color = OutlineVariant.copy(alpha = 0.5f))
            )
        },
        containerColor = Background
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            contentPadding = PaddingValues(bottom = 32.dp)
        ) {
            // Hero / Current Goal Card
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(listOf(Primary, Primary.copy(alpha = 0.75f)))
                        )
                        .padding(20.dp)
                ) {
                    Column {
                        Surface(
                            color = Color.White.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                "Current Goal",
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }
                        Spacer(Modifier.height(8.dp))
                        Text(
                            "Mastering Behavioral Hooks",
                            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.ExtraBold),
                            color = Color.White
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            "You've completed 41% of this week's plan. Keep the momentum to stay ahead of the technical interviews.",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.85f)
                        )
                        Spacer(Modifier.height(16.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(20.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center, modifier = Modifier.size(72.dp)) {
                                CircularProgressIndicator(
                                    progress = { 0.41f },
                                    modifier = Modifier.fillMaxSize(),
                                    strokeWidth = 6.dp,
                                    color = Color.White,
                                    trackColor = Color.White.copy(alpha = 0.3f)
                                )
                                Text(
                                    "40%",
                                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                    color = Color.White
                                )
                            }
                            Column {
                                Text("3 / 7 Days Done", style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold), color = Color.White)
                                Text("4 more days to complete goal", style = MaterialTheme.typography.bodySmall, color = Color.White.copy(alpha = 0.8f))
                            }
                        }
                        Spacer(Modifier.height(16.dp))
                        Button(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Primary),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.fillMaxWidth().height(44.dp)
                        ) {
                            Text("Start Today's Goal", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            // Weekly Plan Header
            item {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Weekly Plan",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    Surface(color = SurfaceContainerLow, shape = RoundedCornerShape(8.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Icon(Icons.Default.ChevronLeft, null, tint = Secondary, modifier = Modifier.size(18.dp))
                            Text(
                                "Oct 20 - Sep 09",
                                style = MaterialTheme.typography.labelSmall,
                                color = Secondary,
                                modifier = Modifier.padding(horizontal = 4.dp)
                            )
                            Icon(Icons.Default.ChevronRight, null, tint = Secondary, modifier = Modifier.size(18.dp))
                        }
                    }
                }
            }

            // Day items
            items(days) { day ->
                PlanDayRow(day = day)
            }

            // Tailored for You
            item {
                Spacer(Modifier.height(8.dp))
                Text(
                    "Tailored for You",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }

            items(tailored) { resource ->
                TailoredResourceCard(resource = resource)
            }
        }
    }
}

@Composable
private fun PlanDayRow(day: DayPlan) {
    val isCompleted = day.status == DayStatus.COMPLETED
    val isActive = day.status == DayStatus.ACTIVE
    val isAssessment = day.status == DayStatus.ASSESSMENT

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 3.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Date label
        Text(
            day.date,
            style = MaterialTheme.typography.labelSmall,
            color = Secondary,
            fontSize = 9.sp,
            modifier = Modifier.width(44.dp)
        )

        Spacer(Modifier.width(8.dp))

        // Status dot
        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(
                    when {
                        isCompleted -> Primary
                        isActive -> Primary
                        else -> OutlineVariant
                    }
                )
        )

        Spacer(Modifier.width(10.dp))

        // Content
        Card(
            modifier = Modifier.weight(1f),
            colors = CardDefaults.cardColors(
                containerColor = when {
                    isCompleted -> Primary.copy(alpha = 0.06f)
                    isActive -> SurfaceContainerLowest
                    else -> SurfaceContainerLow
                }
            ),
            border = BorderStroke(
                1.dp,
                when {
                    isActive -> Primary.copy(alpha = 0.6f)
                    isCompleted -> Primary.copy(alpha = 0.2f)
                    else -> OutlineVariant.copy(alpha = 0.4f)
                }
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        day.title,
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = if (isActive) FontWeight.Bold else FontWeight.Normal),
                        color = if (day.status == DayStatus.UPCOMING) Secondary else MaterialTheme.colorScheme.onSurface
                    )
                }
                Spacer(Modifier.width(8.dp))
                if (isCompleted) {
                    Icon(Icons.Default.CheckCircle, null, tint = Primary, modifier = Modifier.size(16.dp))
                } else if (isAssessment) {
                    Surface(color = SecondaryContainer, shape = RoundedCornerShape(4.dp)) {
                        Text(
                            "Assessment",
                            style = MaterialTheme.typography.labelSmall,
                            color = OnSecondaryContainer,
                            fontSize = 9.sp,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                } else {
                    Text(
                        day.duration,
                        style = MaterialTheme.typography.labelSmall,
                        color = if (isActive) Primary else Secondary,
                        fontSize = 10.sp
                    )
                }
            }
        }
    }

    Spacer(Modifier.height(4.dp))
}

@Composable
private fun TailoredResourceCard(resource: TailoredResource) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
        border = BorderStroke(1.dp, OutlineVariant.copy(alpha = 0.5f)),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier.size(36.dp).background(resource.iconBgColor, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(resource.icon, null, tint = resource.iconColor, modifier = Modifier.size(20.dp))
            }
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(resource.title, style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold))
                Text(resource.subtitle, style = MaterialTheme.typography.bodySmall, color = Secondary, maxLines = 2)
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    Surface(color = PrimaryContainer, shape = RoundedCornerShape(4.dp)) {
                        Text(resource.tag1, style = MaterialTheme.typography.labelSmall, color = OnPrimaryContainer, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
                    }
                    Surface(color = SurfaceContainerLow, shape = RoundedCornerShape(4.dp)) {
                        Text(resource.tag2, style = MaterialTheme.typography.labelSmall, color = Secondary, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
                    }
                }
            }
        }
    }
}
