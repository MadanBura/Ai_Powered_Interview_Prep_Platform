package com.interview.platform.ui.screens.mod17_reporting.interview_history

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.interview.platform.ui.theme.*
import androidx.compose.ui.text.style.TextOverflow

private val Background get() = PrepAiBackground
private val Primary get() = PrepAiPrimary
private val PrimaryContainer get() = PrepAiPrimaryContainer
private val OnPrimaryContainer get() = PrepAiOnPrimaryContainer
private val Secondary get() = PrepAiSecondary
private val SecondaryContainer get() = PrepAiSecondaryContainer
private val OnSecondaryContainer get() = PrepAiOnSecondaryContainer
private val Tertiary get() = PrepAiTertiary
private val TertiaryContainer get() = PrepAiTertiaryContainer
private val OnTertiaryContainer get() = PrepAiOnTertiaryContainer
private val SurfaceContainerLowest get() = PrepAiSurfaceContainerLowest
private val SurfaceContainerHigh get() = PrepAiSurfaceContainerHigh
private val SurfaceContainer get() = PrepAiSurfaceContainer
private val OutlineVariant get() = PrepAiOutlineVariant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InterviewHistoryScreenScreen(
    onNavigateForward: () -> Unit,
    viewModel: InterviewHistoryScreenViewModel = hiltViewModel()
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("All") }
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Interview History", color = Primary, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Primary)
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.FilterList, contentDescription = "Filter", tint = Secondary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = SurfaceContainerLowest,
                    scrolledContainerColor = SurfaceContainerLowest
                ),
                modifier = Modifier.border(width = 1.dp, color = OutlineVariant.copy(alpha = 0.5f))
            )
        },
        containerColor = Background,
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateForward, containerColor = Primary, contentColor = Color.White) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Search & Filter
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Search roles or companies...") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Secondary) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = SurfaceContainerLowest,
                        unfocusedContainerColor = SurfaceContainer,
                        focusedBorderColor = Primary,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(12.dp)
                )

                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    val filters = listOf("All", "Technical", "Behavioral", "System Design")
                    items(filters.size) { index ->
                        val filter = filters[index]
                        val isSelected = filter == selectedFilter
                        Surface(
                            color = if (isSelected) Primary else SurfaceContainerHigh,
                            shape = CircleShape,
                            modifier = Modifier.clickable { selectedFilter = filter }
                        ) {
                            Text(
                                text = filter,
                                style = MaterialTheme.typography.labelLarge,
                                color = if (isSelected) Color.White else Secondary,
                                modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
                            )
                        }
                    }
                }
            }

            // Metrics Cards
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
                border = BorderStroke(1.dp, OutlineVariant.copy(alpha = 0.5f)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    modifier = Modifier.padding(20.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Average Readiness", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.onSurface)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Your score has improved by 12% this month.", style = MaterialTheme.typography.bodySmall, color = Secondary)
                    }
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.size(64.dp)) {
                        CircularProgressIndicator(progress = { 0.82f }, modifier = Modifier.fillMaxSize(), color = Primary, trackColor = SurfaceContainerHigh, strokeWidth = 6.dp, strokeCap = androidx.compose.ui.graphics.StrokeCap.Round)
                        Text("82%", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), color = Primary)
                    }
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Primary),
                shape = RoundedCornerShape(16.dp)
            ) {
                Box(modifier = Modifier.fillMaxWidth().padding(20.dp)) {
                    Column {
                        Text("Total Prep", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), color = Color.White)
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text("14.5", style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold), color = Color.White)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("hrs", style = MaterialTheme.typography.titleMedium, color = Color.White, modifier = Modifier.padding(bottom = 4.dp))
                        }
                    }
                    Icon(
                        Icons.Default.Timer,
                        contentDescription = null,
                        tint = Color.White.copy(alpha = 0.2f),
                        modifier = Modifier.align(Alignment.CenterEnd).size(80.dp).offset(x = 16.dp, y = 16.dp)
                    )
                }
            }

            Text("RECENT SESSIONS", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, letterSpacing = 1.sp), color = Secondary)

            when (val state = uiState) {
                is InterviewHistoryScreenUiState.Loading -> {
                    Box(modifier = Modifier.fillMaxWidth().padding(top = 32.dp), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = Primary)
                    }
                }
                is InterviewHistoryScreenUiState.Empty -> {
                    // Empty State
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(top = 32.dp),
                        colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
                        border = BorderStroke(1.dp, OutlineVariant),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(32.dp).fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Box(
                                modifier = Modifier.size(80.dp).background(PrimaryContainer, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.History, contentDescription = null, tint = Primary, modifier = Modifier.size(40.dp))
                            }
                            Spacer(modifier = Modifier.height(24.dp))
                            Text(
                                "No History Yet",
                                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                "Your past interview sessions will appear here once you complete them.",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Secondary,
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(32.dp))
                            Button(
                                onClick = onNavigateForward,
                                colors = ButtonDefaults.buttonColors(containerColor = Primary),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.height(48.dp)
                            ) {
                                Text("Start Your First Interview")
                            }
                        }
                    }
                }
                is InterviewHistoryScreenUiState.Error -> {
                    Text(state.message, color = MaterialTheme.colorScheme.error)
                }
                is InterviewHistoryScreenUiState.Success -> {
                    val data = state.data
                    val filteredData = if (selectedFilter == "All") data else data.filter {
                        // Assuming the "type" mapping logic:
                        // Department / Role -> Filter mapping
                        // This is a naive filter for demo since roles vary
                        it.role?.contains(selectedFilter, ignoreCase = true) == true
                    }

                    if (filteredData.isEmpty()) {
                        Box(modifier = Modifier.fillMaxWidth().padding(top = 32.dp), contentAlignment = Alignment.Center) {
                            Text("No interviews match this filter.", color = Secondary)
                        }
                    } else {
                        filteredData.forEach { session ->
                            SessionCard(
                                title = session.role ?: "Unknown Role",
                                date = session.startedAt?.substring(0, 10) ?: "Unknown Date",
                                duration = session.duration ?: "Unknown",
                                type = session.status ?: "COMPLETED",
                                icon = Icons.Default.Code,
                                iconBg = PrimaryContainer,
                                iconTint = Primary,
                                score = session.score?.toString() ?: "--",
                                scoreColor = if ((session.score ?: 0) >= 80) Color(0xFF4CAF50) else if ((session.score ?: 0) >= 60) Color(0xFFFF9800) else Color(0xFFF44336),
                                badgeText = if ((session.score ?: 0) >= 80) "Excellent" else "Needs Work",
                                badgeBg = if ((session.score ?: 0) >= 80) Color(0xFFE8F5E9) else Color(0xFFFFEBEE),
                                badgeColor = if ((session.score ?: 0) >= 80) Color(0xFF2E7D32) else Color(0xFFC62828)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SessionCard(title: String, date: String, duration: String, type: String, icon: androidx.compose.ui.graphics.vector.ImageVector, iconBg: Color, iconTint: Color, score: String, scoreColor: Color, badgeText: String, badgeBg: Color, badgeColor: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
        border = BorderStroke(1.dp, OutlineVariant.copy(alpha = 0.5f)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                Box(modifier = Modifier.size(48.dp).background(iconBg, RoundedCornerShape(12.dp)), contentAlignment = Alignment.Center) {
                    Icon(icon, contentDescription = null, tint = iconTint)
                }
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(title, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.onSurface, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                Icon(Icons.Default.CalendarToday, contentDescription = null, tint = Secondary, modifier = Modifier.size(14.dp))
                                Text(date, style = MaterialTheme.typography.bodySmall, color = Secondary, maxLines = 1, overflow = TextOverflow.Ellipsis)
                            }
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                Icon(Icons.Default.Schedule, contentDescription = null, tint = Secondary, modifier = Modifier.size(14.dp))
                                Text(duration, style = MaterialTheme.typography.bodySmall, color = Secondary, maxLines = 1, overflow = TextOverflow.Ellipsis)
                            }
                        }
                        Surface(color = iconBg, shape = RoundedCornerShape(4.dp)) {
                            Text(type.uppercase(), style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, fontWeight = FontWeight.Bold), color = iconTint, modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp))
                        }
                    }
                }
            }
            Column(horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(score, style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold), color = scoreColor)
                    Text("/100", style = MaterialTheme.typography.bodySmall, color = Secondary, modifier = Modifier.padding(bottom = 4.dp))
                }
                Surface(color = badgeBg, shape = RoundedCornerShape(12.dp)) {
                    Text(badgeText, style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, fontWeight = FontWeight.Bold), color = badgeColor, modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp))
                }
            }
        }
    }
}
