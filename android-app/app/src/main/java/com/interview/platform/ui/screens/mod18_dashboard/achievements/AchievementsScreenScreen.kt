package com.interview.platform.ui.screens.mod18_dashboard.achievements

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
import androidx.navigation.NavController
import com.interview.platform.ui.AppRoutes
import com.interview.platform.ui.components.PrepCoachBottomNav
import com.interview.platform.ui.theme.*

// Token aliases for backward-compat with private composables
private val Background get() = PrepAiBackground
private val Primary get() = PrepAiPrimary
private val PrimaryContainer get() = PrepAiPrimaryContainer
private val OnPrimaryContainer get() = PrepAiOnPrimaryContainer
private val Secondary get() = PrepAiSecondary
private val SecondaryContainer get() = PrepAiSecondaryContainer
private val OnSecondaryContainer get() = PrepAiOnSecondaryContainer
private val Tertiary get() = PrepAiTertiary
private val SurfaceContainerLowest get() = PrepAiSurfaceContainerLowest
private val SurfaceContainerLow get() = PrepAiSurfaceContainerLow
private val SurfaceContainerHigh get() = PrepAiSurfaceContainerHigh
private val OutlineVariant get() = PrepAiOutlineVariant
private val PrimaryFixed get() = PrepAiPrimaryFixed
private val OnPrimary get() = PrepAiOnPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AchievementsScreenScreen(
    navController: NavController,
    onNavigateForward: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Interview Prep", color = Primary, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Primary)
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
//        bottomBar = {
//            PrepCoachBottomNav(currentTab = AppRoutes.profile, navController = navController)
//        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
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
                        Icon(Icons.Default.EmojiEvents, contentDescription = null, tint = Primary, modifier = Modifier.size(40.dp))
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        "No Achievements Yet",
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "Complete interviews and improve your scores to unlock badges and rank up on the leaderboard.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Secondary,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun BadgeCard(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    isLocked: Boolean
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = SurfaceContainerLow),
        border = BorderStroke(1.dp, OutlineVariant.copy(alpha = 0.5f)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(
                        if (isLocked) OutlineVariant.copy(alpha = 0.3f) else SecondaryContainer,
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    icon,
                    contentDescription = null,
                    tint = if (isLocked) Secondary else Primary,
                    modifier = Modifier.size(40.dp)
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    title,
                    style = MaterialTheme.typography.labelLarge,
                    color = if (isLocked) Secondary else MaterialTheme.colorScheme.onSurface,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
                Text(
                    subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = Secondary,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun LeaderboardItem(
    rank: String,
    rankColor: Color,
    initials: String,
    name: String,
    role: String,
    score: String,
    isUser: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (isUser) SecondaryContainer else Color.Transparent)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            rank,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = rankColor,
            modifier = Modifier.width(32.dp)
        )
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(if (isUser) Primary else SecondaryContainer, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                initials,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                color = if (isUser) OnPrimary else OnSecondaryContainer
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(
                name,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                role,
                style = MaterialTheme.typography.bodySmall,
                color = if (isUser) OnSecondaryContainer else Secondary
            )
        }
        Text(
            score,
            style = MaterialTheme.typography.labelLarge,
            color = if (isUser) PrimaryContainer else Primary
        )
    }
}

@Composable
fun TimelineItem(title: String, subtitle: String, isLast: Boolean) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(IntrinsicSize.Min)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(SurfaceContainerLow, CircleShape)
                    .border(4.dp, SurfaceContainerLow, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(Primary, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.Check,
                        contentDescription = null,
                        tint = OnPrimary,
                        modifier = Modifier.size(10.dp)
                    )
                }
            }
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .weight(1f)
                        .background(OutlineVariant.copy(alpha = 0.5f))
                )
            }
        }
        Column(modifier = Modifier.padding(start = 16.dp, bottom = if (isLast) 0.dp else 24.dp)) {
            Text(
                title,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(subtitle, style = MaterialTheme.typography.bodySmall, color = Secondary)
        }
    }
}
