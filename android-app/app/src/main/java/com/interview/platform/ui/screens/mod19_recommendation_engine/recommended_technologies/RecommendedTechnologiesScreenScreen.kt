package com.interview.platform.ui.screens.mod19_recommendation_engine.recommended_technologies

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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

enum class Priority { HIGH, MODERATE, TRENDING, ESSENTIAL }

private data class TechRecommendation(
    val title: String,
    val description: String,
    val priority: Priority,
    val priorityLabel: String,
    val icon: ImageVector,
    val iconBgColor: Color,
    val trendText: String,
    val buttonText: String,
    val isPrimaryButton: Boolean = true,
    val category: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecommendedTechnologiesScreenScreen(
    onNavigateForward: () -> Unit,
    viewModel: RecommendedTechnologiesScreenViewModel = androidx.hilt.navigation.compose.hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    val filterOptions = listOf("All Skills", "Technical", "Behavioral", "Case Study")
    var selectedFilter by remember { mutableStateOf("All Skills") }

    val recommendations = remember(uiState) {
        when (val state = uiState) {
            is RecommendedTechnologiesScreenUiState.Success -> {
                state.roadmaps.map { roadmap ->
                    TechRecommendation(
                        title = roadmap.title,
                        description = roadmap.description,
                        priority = Priority.HIGH,
                        priorityLabel = "ESSENTIAL",
                        icon = Icons.Default.Description,
                        iconBgColor = SecondaryContainer,
                        trendText = "Based on your target profile",
                        buttonText = "Start Path",
                        isPrimaryButton = true,
                        category = "Technical"
                    )
                }
            }
            else -> emptyList()
        }
    }

    val filtered = if (selectedFilter == "All Skills") recommendations
    else recommendations.filter { it.category.equals(selectedFilter, ignoreCase = true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Skill Ups", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold, color = Primary))
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
            // Header
            item {
                Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(
                        "Recommended Tech",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold)
                    )
                    Text(
                        "Based on your recent interview performance and the Senior Backend Engineer roles, we recommend focusing on these high-impact technologies.",
                        style = MaterialTheme.typography.bodySmall,
                        color = Secondary
                    )
                    Spacer(Modifier.height(4.dp))
                    // Filter chips
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(filterOptions) { option ->
                            FilterChip(
                                selected = selectedFilter == option,
                                onClick = { selectedFilter = option },
                                label = { Text(option, style = MaterialTheme.typography.labelSmall) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = Primary,
                                    selectedLabelColor = OnPrimary,
                                    containerColor = SurfaceContainerLow,
                                    labelColor = Secondary
                                ),
                                border = FilterChipDefaults.filterChipBorder(
                                    enabled = true, selected = selectedFilter == option,
                                    borderColor = OutlineVariant, selectedBorderColor = Primary
                                )
                            )
                        }
                    }
                }
            }

            // Tech cards
            items(filtered) { tech ->
                TechRecommendationCard(tech = tech, onAction = {
                    if (tech.isPrimaryButton) {
                        viewModel.startPath(tech.title, onNavigateForward)
                    } else {
                        onNavigateForward()
                    }
                })
            }

            // Full Roadmap Locked Card
            item {
                Spacer(Modifier.height(8.dp))
                Card(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Primary),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Icon(Icons.Default.Lock, null, tint = Color.White.copy(alpha = 0.8f), modifier = Modifier.size(20.dp))
                            Text("Full Roadmap", style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold), color = Color.White)
                        }
                        Text(
                            "A personalized 18-week intensive prep roadmap designed specifically for your career goals.",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.85f)
                        )
                        Button(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Primary),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.fillMaxWidth().height(40.dp)
                        ) {
                            Text("Unlock this Path", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            // Progress Summary
            item {
                Spacer(Modifier.height(8.dp))
                Card(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
                    border = BorderStroke(1.dp, OutlineVariant.copy(alpha = 0.5f)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("Your Progress Summary", style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold))
                        Text(
                            "You've completed 4 out of 5 skills identified for your target profile.",
                            style = MaterialTheme.typography.bodySmall,
                            color = Secondary
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center, modifier = Modifier.size(56.dp)) {
                                CircularProgressIndicator(
                                    progress = { 0.80f },
                                    modifier = Modifier.fillMaxSize(),
                                    strokeWidth = 5.dp,
                                    color = Primary,
                                    trackColor = SurfaceVariant
                                )
                                Text("80%", style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold), color = Primary)
                            }
                            Column {
                                Text("4 / 5 Skills Completed", style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold))
                                Text("On track for your target profile!", style = MaterialTheme.typography.bodySmall, color = Secondary)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TechRecommendationCard(tech: TechRecommendation, onAction: () -> Unit) {
    val badgeColor = when (tech.priority) {
        Priority.HIGH -> Color(0xFFE53935)
        Priority.MODERATE -> Color(0xFFF57C00)
        Priority.TRENDING -> Primary
        Priority.ESSENTIAL -> Color(0xFF2E7D32)
    }

    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 5.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
        border = BorderStroke(1.dp, OutlineVariant.copy(alpha = 0.5f)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            // Icon + Title + Badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier.size(36.dp).clip(CircleShape).background(tech.iconBgColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(tech.icon, null, tint = OnSecondaryContainer, modifier = Modifier.size(20.dp))
                    }
                    Text(tech.title, style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold))
                }
                Surface(color = badgeColor, shape = RoundedCornerShape(4.dp)) {
                    Text(
                        tech.priorityLabel,
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, fontSize = 8.sp),
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }

            // Description
            Text(tech.description, style = MaterialTheme.typography.bodySmall, color = Secondary, maxLines = 3)

            // Trend insight
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                Icon(Icons.Default.CheckCircle, null, tint = Primary, modifier = Modifier.size(12.dp))
                Text(tech.trendText, style = MaterialTheme.typography.labelSmall, color = Secondary)
            }

            // Action button
            if (tech.isPrimaryButton) {
                Button(
                    onClick = onAction,
                    colors = ButtonDefaults.buttonColors(containerColor = Primary),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth().height(36.dp)
                ) {
                    Text(tech.buttonText, style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold))
                }
            } else {
                OutlinedButton(
                    onClick = onAction,
                    border = BorderStroke(1.dp, OutlineVariant),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth().height(36.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.onSurface)
                ) {
                    Text(tech.buttonText, style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold))
                }
            }
        }
    }
}
