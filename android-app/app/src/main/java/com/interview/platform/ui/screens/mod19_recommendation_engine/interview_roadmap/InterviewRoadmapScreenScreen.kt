package com.interview.platform.ui.screens.mod19_recommendation_engine.interview_roadmap

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.interview.platform.data.remote.dto.mod19_recommendation_engine.RoadmapMilestoneDto
import com.interview.platform.ui.theme.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


import java.time.Instant
import java.time.ZoneId
// Colors inspired by Figma
private val FigmaPrimary = Color(0xFF0F9D58) // Vivid Green
private val FigmaBackground = Color(0xFFF8F9FA) // Light grey/white
private val FigmaCardSurface = Color(0xFFFFFFFF)
private val FigmaTextPrimary = Color(0xFF202124)
private val FigmaTextSecondary = Color(0xFF5F6368)
private val FigmaBorder = Color(0xFFE8EAED)
private val FigmaPlayIcon = Color(0xFF0F9D58)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InterviewRoadmapScreenScreen(
    onNavigateToTopic: (String, String) -> Unit,
    onStartInterview: () -> Unit,
    onStartDirectInterview: () -> Unit,
    onNavigateToCatalog: () -> Unit,
    viewModel: InterviewRoadmapScreenViewModel = androidx.hilt.navigation.compose.hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var currentWeekIndex by remember { mutableStateOf(0) }

    val lifecycleOwner = androidx.compose.ui.platform.LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = androidx.lifecycle.LifecycleEventObserver { _, event ->
            if (event == androidx.lifecycle.Lifecycle.Event.ON_RESUME) {
                viewModel.refresh()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Learning Path",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = FigmaTextPrimary
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* back */ }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = FigmaTextPrimary)
                    }
                },
                actions = {
                    IconButton(onClick = { /* settings */ }) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings", tint = FigmaTextPrimary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = FigmaBackground),
            )
        },
        containerColor = FigmaBackground
    ) { paddingValues ->
        when {
            uiState is InterviewRoadmapScreenUiState.Empty -> {
                EmptyRoadmapContent(modifier = Modifier.padding(paddingValues), onNavigateToCatalog = onNavigateToCatalog)
            }
            uiState is InterviewRoadmapScreenUiState.Success -> {
                val roadmapData = (uiState as InterviewRoadmapScreenUiState.Success).data
                val userRoadmap = (uiState as InterviewRoadmapScreenUiState.Success).userRoadmap
                
                if (roadmapData == null) {
                    EmptyRoadmapContent(modifier = Modifier.padding(paddingValues), onNavigateToCatalog = onNavigateToCatalog)
                } else {
                    val totalCount = roadmapData.items.size
                    val completedTopics = userRoadmap?.completedTopics ?: emptyList()
                    val completedCount = completedTopics.size
                    val allCompleted = totalCount > 0 && completedCount >= totalCount
                    val overallProgress = if (totalCount > 0) completedCount.toFloat() / totalCount else 0f
                    
                    val weeks = roadmapData.items.chunked(7)
                    if (currentWeekIndex >= weeks.size) currentWeekIndex = weeks.size - 1
                    if (currentWeekIndex < 0) currentWeekIndex = 0

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        contentPadding = PaddingValues(bottom = 32.dp)
                    ) {
                        // Hero / Goal Card
                        item {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(16.dp))
                                        .background(FigmaPrimary)
                                        .padding(24.dp)
                                ) {
                                    Column {
                                        Text(
                                            "Current Goal",
                                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                            color = Color.White.copy(alpha = 0.9f)
                                        )
                                        Spacer(Modifier.height(4.dp))
                                        Text(
                                            "Mastering ${roadmapData.title.removePrefix("Roadmap for ").removePrefix("Roadmap - ")}",
                                            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                                            color = Color.White
                                        )
                                        Spacer(Modifier.height(12.dp))
                                        Text(
                                            "You've completed ${(overallProgress * 100).toInt()}% of this week's plan. Keep the momentum going to stay ahead of your technical interviews.",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = Color.White.copy(alpha = 0.85f),
                                            lineHeight = 16.sp
                                        )
                                        Spacer(Modifier.height(24.dp))
                                        
                                        // Circular Progress
                                        Box(
                                            contentAlignment = Alignment.Center,
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            Box(contentAlignment = Alignment.Center, modifier = Modifier.size(96.dp)) {
                                                CircularProgressIndicator(
                                                    progress = { 1f },
                                                    modifier = Modifier.fillMaxSize(),
                                                    strokeWidth = 8.dp,
                                                    color = Color.White.copy(alpha = 0.3f),
                                                )
                                                CircularProgressIndicator(
                                                    progress = { overallProgress },
                                                    modifier = Modifier.fillMaxSize(),
                                                    strokeWidth = 8.dp,
                                                    color = Color.White,
                                                )
                                                Text(
                                                    "${(overallProgress * 100).toInt()}%",
                                                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                                    color = Color.White
                                                )
                                            }
                                        }
                                        Spacer(Modifier.height(24.dp))
                                        Button(
                                            onClick = {
                                                val activeIndex = completedTopics.size
                                                if (activeIndex < roadmapData.items.size) {
                                                    val activeTitle = roadmapData.items[activeIndex].title
                                                    onNavigateToTopic(userRoadmap?.id?.toString() ?: "active", activeTitle)
                                                }
                                            },
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = Color.White,
                                                contentColor = FigmaPrimary
                                            ),
                                            shape = RoundedCornerShape(8.dp),
                                            modifier = Modifier.fillMaxWidth().height(48.dp)
                                        ) {
                                            Text("Start Today's Goal", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                                        }
                                    }
                                }
                            }
                        }

                        // Weekly Plan Header
                        item {
                            val startDate = userRoadmap?.startedAt.toLocalDateTimeOrNow()
                            val weekStart = startDate.plusDays((currentWeekIndex * 7).toLong())
                            val weekEnd = weekStart.plusDays(6)
                            val formatter = DateTimeFormatter.ofPattern("MMM d")
                            
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    "Weekly Plan",
                                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
                                    color = FigmaTextPrimary
                                )
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    IconButton(
                                        onClick = { if (currentWeekIndex > 0) currentWeekIndex-- },
                                        modifier = Modifier.size(24.dp)
                                    ) {
                                        Icon(Icons.Default.ChevronLeft, null, tint = FigmaTextSecondary)
                                    }
                                    Spacer(Modifier.width(8.dp))
                                    Text(
                                        "${weekStart.format(formatter)} - ${weekEnd.format(formatter)}",
                                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                                        color = FigmaTextPrimary
                                    )
                                    Spacer(Modifier.width(8.dp))
                                    IconButton(
                                        onClick = { if (currentWeekIndex < weeks.size - 1) currentWeekIndex++ },
                                        modifier = Modifier.size(24.dp)
                                    ) {
                                        Icon(Icons.Default.ChevronRight, null, tint = FigmaTextSecondary)
                                    }
                                }
                            }
                        }

                        // Day Cards
                        if (weeks.isNotEmpty()) {
                            val currentWeekItems = weeks[currentWeekIndex]
                            val startDate = userRoadmap?.startedAt.toLocalDateTimeOrNow()
                            
                            itemsIndexed(currentWeekItems) { index, milestone ->
                                val globalIndex = (currentWeekIndex * 7) + index
                                val dayDate = startDate.plusDays(globalIndex.toLong())
                                val isCompleted = completedTopics.contains(milestone.title)
                                val isActive = !isCompleted && globalIndex == completedTopics.size
                                val isLocked = !isCompleted && !isActive
                                
                                DayCard(
                                    dayIndex = index + 1,
                                    date = dayDate,
                                    title = milestone.title,
                                    isCompleted = isCompleted,
                                    isActive = isActive,
                                    isLocked = isLocked,
                                    estimatedTime = "45 min", // Mocked as per Figma
                                    onClick = {
                                        if (!isLocked) {
                                            if (milestone.title.contains("Day 7", ignoreCase = true) || milestone.title.contains("Assessment", ignoreCase = true)) {
                                                onStartDirectInterview()
                                            } else {
                                                onNavigateToTopic(userRoadmap?.id?.toString() ?: "active", milestone.title)
                                            }
                                        }
                                    }
                                )
                            }
                        }
                        
                        // Final Assessment Mock Day (Day 7)
                        if (weeks.isNotEmpty() && weeks[currentWeekIndex].size < 7) {
                            val dummyDays = 7 - weeks[currentWeekIndex].size
                            for (i in 1..dummyDays) {
                                item {
                                    val globalIndex = (currentWeekIndex * 7) + weeks[currentWeekIndex].size + i - 1
                                    val startDate = userRoadmap?.startedAt.toLocalDateTimeOrNow()
                                    val dayDate = startDate.plusDays(globalIndex.toLong())
                                    
                                    DayCard(
                                        dayIndex = weeks[currentWeekIndex].size + i,
                                        date = dayDate,
                                        title = if (i == dummyDays) "Final Assessment" else "Review",
                                        isCompleted = false,
                                        isActive = false,
                                        isLocked = true,
                                        estimatedTime = if (i == dummyDays) "Assessment" else "30 min",
                                        onClick = {}
                                    )
                                }
                            }
                        }

                        // Tailored for You Section
                        item {
                            Spacer(Modifier.height(16.dp))
                            Text(
                                "Tailored for You",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
                                color = FigmaTextPrimary,
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                            )
                        }

                        item {
                            TailoredCard(
                                icon = Icons.Default.Psychology,
                                iconBg = Color(0xFFD2E3FC),
                                iconTint = Color(0xFF174EA6),
                                title = "Behavioral Question Bank",
                                subtitle = "Top 50 STAR method questions for senior engineering roles.",
                                tag1 = "Behavioral",
                                tag2 = "Case Study"
                            )
                        }

                        item {
                            TailoredCard(
                                icon = Icons.Default.Code,
                                iconBg = Color(0xFF81C995).copy(alpha = 0.5f),
                                iconTint = FigmaPrimary,
                                title = "Coding Challenge Set",
                                subtitle = "Focusing on Dynamic Programming and recursion patterns.",
                                tag1 = "Technical",
                                tag2 = "LeetCode Style"
                            )
                        }

                        // Start Interview button - only when all chapters done
                        if (allCompleted) {
                            item {
                                Spacer(Modifier.height(24.dp))
                                Button(
                                    onClick = onStartInterview,
                                    colors = ButtonDefaults.buttonColors(containerColor = FigmaPrimary),
                                    shape = RoundedCornerShape(8.dp),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp)
                                        .height(52.dp)
                                ) {
                                    Icon(Icons.Default.PlayArrow, null, modifier = Modifier.size(20.dp))
                                    Spacer(Modifier.width(8.dp))
                                    Text("Start Interview", fontWeight = FontWeight.Bold)
                                }
                            }
                        }

                        // Always show a "Browse other Roadmaps" button at the bottom
                        item {
                            Spacer(Modifier.height(16.dp))
                            OutlinedButton(
                                onClick = onNavigateToCatalog,
                                border = BorderStroke(1.dp, FigmaPrimary),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                                    .height(52.dp)
                            ) {
                                Text("Browse other Roadmaps", fontWeight = FontWeight.Bold, color = FigmaPrimary)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DayCard(
    dayIndex: Int,
    date: LocalDateTime,
    title: String,
    isCompleted: Boolean,
    isActive: Boolean,
    isLocked: Boolean,
    estimatedTime: String,
    onClick: () -> Unit
) {
    val formatter = DateTimeFormatter.ofPattern("EEE, d")
    val borderColor = if (isActive) FigmaPrimary else FigmaBorder
    val borderWidth = if (isActive) 2.dp else 1.dp
    val bgColor = FigmaCardSurface
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .clickable(enabled = !isLocked, onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = bgColor),
        border = BorderStroke(borderWidth, borderColor),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        date.format(formatter),
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                        color = if (isActive) FigmaPrimary else FigmaTextSecondary
                    )
                    if (isActive) {
                        Spacer(Modifier.width(8.dp))
                        Surface(
                            color = FigmaPrimary,
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                "ACTIVE",
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, fontSize = 9.sp),
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }
                }
                val displayTitle = if (title.startsWith("Day", ignoreCase = true)) title else "Day $dayIndex: $title"
                Text(
                    displayTitle,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = FigmaTextPrimary
                )
            }
            
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (isCompleted) {
                    Icon(Icons.Default.CheckCircle, null, tint = FigmaPrimary, modifier = Modifier.size(20.dp))
                    Spacer(Modifier.width(4.dp))
                    Text("Done", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold), color = FigmaPrimary)
                } else if (isActive) {
                    Icon(Icons.Default.PlayCircleOutline, null, tint = FigmaPlayIcon, modifier = Modifier.size(20.dp))
                    Spacer(Modifier.width(4.dp))
                    Text(estimatedTime, style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold), color = FigmaTextPrimary)
                } else {
                    if (estimatedTime == "Assessment") {
                        Icon(Icons.Default.Event, null, tint = FigmaTextSecondary, modifier = Modifier.size(16.dp))
                    } else {
                        Icon(Icons.Default.Schedule, null, tint = FigmaTextSecondary, modifier = Modifier.size(16.dp))
                    }
                    Spacer(Modifier.width(4.dp))
                    Text(estimatedTime, style = MaterialTheme.typography.labelSmall, color = FigmaTextSecondary)
                }
            }
        }
    }
}

@Composable
fun TailoredCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconBg: Color,
    iconTint: Color,
    title: String,
    subtitle: String,
    tag1: String,
    tag2: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = FigmaCardSurface),
        border = BorderStroke(1.dp, FigmaBorder),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(iconBg, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, null, tint = iconTint, modifier = Modifier.size(24.dp))
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(title, style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold), color = FigmaTextPrimary)
                Spacer(Modifier.height(4.dp))
                Text(subtitle, style = MaterialTheme.typography.bodySmall, color = FigmaTextSecondary, lineHeight = 16.sp)
                Spacer(Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Surface(color = FigmaBackground, shape = RoundedCornerShape(16.dp)) {
                        Text(tag1, style = MaterialTheme.typography.labelSmall, color = FigmaTextSecondary, modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp))
                    }
                    Surface(color = FigmaBackground, shape = RoundedCornerShape(16.dp)) {
                        Text(tag2, style = MaterialTheme.typography.labelSmall, color = FigmaTextSecondary, modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyRoadmapContent(modifier: Modifier = Modifier, onNavigateToCatalog: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(Icons.Default.Route, contentDescription = null, tint = FigmaTextSecondary, modifier = Modifier.size(64.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Text("No roadmap generated yet", style = MaterialTheme.typography.titleLarge, color = FigmaTextPrimary)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Complete your first interview to generate your personalized learning roadmap.",
            style = MaterialTheme.typography.bodyMedium,
            color = FigmaTextSecondary,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = onNavigateToCatalog,
            colors = ButtonDefaults.buttonColors(containerColor = FigmaPrimary),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth(0.8f).height(48.dp)
        ) {
            Text("Go to Skill Ups", fontWeight = FontWeight.Bold)
        }
    }
}


private fun Any?.toLocalDateTimeOrNow(): LocalDateTime {
    return when (this) {
        is LocalDateTime -> this
        is String -> runCatching { LocalDateTime.parse(this) }
            .getOrElse { LocalDateTime.now() }
        is Long -> Instant.ofEpochMilli(this)
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime()
        else -> LocalDateTime.now()
    }
}
