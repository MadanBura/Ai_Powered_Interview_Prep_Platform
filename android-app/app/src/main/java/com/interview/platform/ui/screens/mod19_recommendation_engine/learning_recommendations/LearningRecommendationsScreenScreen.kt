package com.interview.platform.ui.screens.mod19_recommendation_engine.learning_recommendations

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

private data class LearningModule(
    val title: String,
    val description: String,
    val duration: String,
    val level: String,
    val difficulty: String,
    val progress: Float,
    val icon: ImageVector,
    val iconBgColor: Color,
    val buttonText: String,
    val isPrimaryButton: Boolean = true,
    val priorityBadge: String? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearningRecommendationsScreenScreen(
    onNavigateForward: () -> Unit,
    viewModel: LearningRecommendationsScreenViewModel = androidx.hilt.navigation.compose.hiltViewModel()
) {
    val categories = listOf("At Focus Area", "Behavioral", "Technical")
    var selectedCategory by remember { mutableStateOf("At Focus Area") }

    val modules = listOf(
        LearningModule(
            title = "High-Availability System Design",
            description = "Dive deep into load balancing, database sharding, and caching strategies. This module addresses the 'Scalability' gap identified in your last Mock Interview with stripe metrics.",
            duration = "4.2 Hrs",
            level = "Expert",
            difficulty = "Expert",
            progress = 0f,
            icon = Icons.Default.Cloud,
            iconBgColor = Color(0xFF1E3A5F),
            buttonText = "Resume Lesson",
            isPrimaryButton = true,
            priorityBadge = "PRIORITY"
        ),
        LearningModule(
            title = "Advanced React Patterns",
            description = "Master Compound Components, HOCs, and Render Props to build reusable UI libraries.",
            duration = "1.2 Hours",
            level = "Intermediate",
            difficulty = "Intermediate",
            progress = 0.8f,
            icon = Icons.Default.Layers,
            iconBgColor = PrimaryContainer,
            buttonText = "Start Practice",
            isPrimaryButton = true
        ),
        LearningModule(
            title = "The STAR Method Lab",
            description = "Refine your storytelling for leadership and conflict resolution questions using the STAR framework.",
            duration = "45 Mins",
            level = "Beginner",
            difficulty = "Beginner",
            progress = 0.25f,
            icon = Icons.Default.Psychology,
            iconBgColor = SecondaryContainer,
            buttonText = "Open Lab",
            isPrimaryButton = false
        ),
        LearningModule(
            title = "Concurrency in Go",
            description = "Explore Goroutines and Channels for efficient parallel processing in backend services.",
            duration = "3.5 Hours",
            level = "Advanced",
            difficulty = "Advanced",
            progress = 0f,
            icon = Icons.Default.Terminal,
            iconBgColor = SurfaceContainerHigh,
            buttonText = "View Module",
            isPrimaryButton = false
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Learning Path", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold, color = Primary))
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
            // Header Section
            item {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        "Ready to level up, Alex?",
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.ExtraBold)
                    )
                    Text(
                        "Based on your recent Mock System Design Interview, we've identified key growth areas in distributed scaling and React performance optimization. Follow this path to bridge the gaps.",
                        style = MaterialTheme.typography.bodySmall,
                        color = Secondary
                    )
                    Spacer(Modifier.height(4.dp))

                    // Skill & Progress row
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Circular Path Score
                        Box(contentAlignment = Alignment.Center, modifier = Modifier.size(80.dp)) {
                            CircularProgressIndicator(
                                progress = { 0.64f },
                                modifier = Modifier.fillMaxSize(),
                                strokeWidth = 7.dp,
                                color = Primary,
                                trackColor = SurfaceVariant
                            )
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("64%", style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.ExtraBold), color = Primary)
                                Text("Path Score", style = MaterialTheme.typography.labelSmall, color = Secondary, fontSize = 8.sp)
                            }
                        }

                        // Skill tags
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Surface(color = PrimaryContainer, shape = RoundedCornerShape(20.dp)) {
                                Text("Visial Better Proficiency", style = MaterialTheme.typography.labelSmall, color = OnPrimaryContainer, modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp))
                            }
                            Surface(color = SecondaryContainer, shape = RoundedCornerShape(20.dp)) {
                                Text("Progress: 84%", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold), color = OnSecondaryContainer, modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp))
                            }
                        }
                    }

                    // Filter chips
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(categories) { cat ->
                            FilterChip(
                                selected = selectedCategory == cat,
                                onClick = { selectedCategory = cat },
                                label = { Text(cat, style = MaterialTheme.typography.labelSmall) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = Primary,
                                    selectedLabelColor = OnPrimary,
                                    containerColor = SurfaceContainerLow,
                                    labelColor = Secondary
                                ),
                                border = FilterChipDefaults.filterChipBorder(
                                    enabled = true, selected = selectedCategory == cat,
                                    borderColor = OutlineVariant, selectedBorderColor = Primary
                                )
                            )
                        }
                    }
                }
            }

            // Module cards
            items(modules) { module ->
                LearningModuleCard(module = module, onAction = onNavigateForward)
            }
        }
    }
}

@Composable
private fun LearningModuleCard(module: LearningModule, onAction: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
        border = BorderStroke(1.dp, if (module.priorityBadge != null) Color(0xFFE53935).copy(alpha = 0.4f) else OutlineVariant.copy(alpha = 0.5f)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            // Priority badge
            if (module.priorityBadge != null) {
                Surface(color = Color(0xFFE53935), shape = RoundedCornerShape(4.dp)) {
                    Text(
                        module.priorityBadge,
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                }
            }

            // Icon + Title row
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.Top) {
                Box(
                    modifier = Modifier.size(44.dp).clip(RoundedCornerShape(10.dp)).background(module.iconBgColor),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(module.icon, null, tint = if (module.iconBgColor == PrimaryContainer) OnPrimaryContainer else Primary, modifier = Modifier.size(24.dp))
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(module.title, style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold))
                    Text(module.description, style = MaterialTheme.typography.bodySmall, color = Secondary, maxLines = 3)
                }
            }

            // Meta info
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        Icon(Icons.Default.AccessTime, null, tint = Secondary, modifier = Modifier.size(12.dp))
                        Text(module.duration, style = MaterialTheme.typography.labelSmall, color = Secondary)
                    }
                    Surface(
                        color = when (module.difficulty) {
                            "Expert" -> Color(0xFFE53935).copy(alpha = 0.15f)
                            "Advanced" -> Color(0xFFF57C00).copy(alpha = 0.15f)
                            "Intermediate" -> PrimaryContainer
                            else -> SurfaceContainerLow
                        },
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            module.difficulty,
                            style = MaterialTheme.typography.labelSmall,
                            color = when (module.difficulty) {
                                "Expert" -> Color(0xFFE53935)
                                "Advanced" -> Color(0xFFF57C00)
                                else -> Primary
                            },
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }

                if (module.isPrimaryButton) {
                    Button(
                        onClick = onAction,
                        colors = ButtonDefaults.buttonColors(containerColor = Primary),
                        shape = RoundedCornerShape(6.dp),
                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp),
                        modifier = Modifier.height(32.dp)
                    ) {
                        Text(module.buttonText, style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold))
                    }
                } else {
                    OutlinedButton(
                        onClick = onAction,
                        border = BorderStroke(1.dp, Primary),
                        shape = RoundedCornerShape(6.dp),
                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp),
                        modifier = Modifier.height(32.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Primary)
                    ) {
                        Text(module.buttonText, style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold))
                    }
                }
            }

            // Progress bar (only if started)
            if (module.progress > 0f) {
                LinearProgressIndicator(
                    progress = { module.progress },
                    modifier = Modifier.fillMaxWidth().height(4.dp).clip(RoundedCornerShape(2.dp)),
                    color = Primary,
                    trackColor = SurfaceVariant
                )
            }
        }
    }
}
