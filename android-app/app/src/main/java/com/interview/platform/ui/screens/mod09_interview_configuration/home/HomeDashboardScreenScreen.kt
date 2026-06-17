package com.interview.platform.ui.screens.mod09_interview_configuration.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
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
import com.interview.platform.ui.theme.*

private val Background get() = PrepAiBackground
private val Primary get() = PrepAiPrimary
private val PrimaryContainer get() = PrepAiPrimaryContainer
private val OnPrimaryContainer get() = PrepAiOnPrimaryContainer
private val Secondary get() = PrepAiSecondary
private val SecondaryContainer get() = PrepAiSecondaryContainer
private val OutlineVariant get() = PrepAiOutlineVariant
private val SurfaceContainerLowest get() = PrepAiSurfaceContainerLowest
private val SurfaceContainerHigh get() = PrepAiSurfaceContainerHigh
private val PrimaryFixedDim get() = PrepAiPrimaryFixedDim


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeDashboardScreenScreen(onNavigateForward: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Icon(Icons.Default.School, contentDescription = null, tint = Primary, modifier = Modifier.size(28.dp)) // Using School as psychology alternative
                        Text("PrepCoach AI", color = Primary, fontWeight = FontWeight.SemiBold, fontSize = 24.sp)
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.Notifications, contentDescription = "Notifications", tint = Secondary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = SurfaceContainerLowest,
                    scrolledContainerColor = SurfaceContainerLowest
                ),
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
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Hero Greeting
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text("Welcome, Guest!", style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold))
                Text("Let's set up your profile and start preparing for your interviews.", style = MaterialTheme.typography.bodyLarge, color = Secondary)
            }

            // Quick Actions Grid
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

                // Start New Interview
                Card(
                    modifier = Modifier.fillMaxWidth().height(160.dp).clickable(onClick = onNavigateForward),
                    colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
                    border = BorderStroke(1.dp, OutlineVariant),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Box(modifier = Modifier.fillMaxSize().padding(24.dp)) {
                        Column(modifier = Modifier.align(Alignment.TopStart)) {
                            Icon(Icons.Default.AddTask, contentDescription = null, tint = Primary, modifier = Modifier.size(32.dp).padding(bottom = 8.dp))
                            Text("Start New Interview", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium), color = MaterialTheme.colorScheme.onSurface)
                            Text("Pick a role and industry", style = MaterialTheme.typography.bodySmall, color = Secondary)
                        }
                        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, tint = Primary, modifier = Modifier.align(Alignment.BottomEnd))
                    }
                }
            }

            // Recent Scores (Empty State)
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text("Recent Scores", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium))
                }
                
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
                    border = BorderStroke(1.dp, OutlineVariant),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(Icons.Default.Analytics, contentDescription = null, tint = OutlineVariant, modifier = Modifier.size(48.dp))
                        Text("No recent interviews", style = MaterialTheme.typography.titleMedium, color = Secondary)
                        Text("Take your first interview to see your scores here.", style = MaterialTheme.typography.bodyMedium, color = Secondary, modifier = Modifier.padding(bottom = 8.dp))
                    }
                }
            }

            // For You (Recommendations)
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text("For You", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium))
                
                @Composable
                fun RecCard(title: String, subtitle: String) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
                        border = BorderStroke(1.dp, OutlineVariant),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(modifier = Modifier.padding(16.dp).fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
                            Box(modifier = Modifier.size(80.dp).background(SecondaryContainer, RoundedCornerShape(8.dp)), contentAlignment = Alignment.Center) {
                                Icon(Icons.Default.Article, contentDescription = null, tint = Primary)
                            }
                            Column {
                                Text(title, style = MaterialTheme.typography.labelLarge)
                                Text(subtitle, style = MaterialTheme.typography.bodySmall, color = Secondary)
                            }
                        }
                    }
                }
                
                RecCard("Mastering React Hooks", "15 min read • Technical")
                RecCard("The STAR Method Guide", "10 min read • Behavioral")
                RecCard("Negotiating Your Salary", "8 min read • Career")
            }
        }
    }
}
