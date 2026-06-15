package com.interview.platform.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.interview.platform.ui.AppRoutes
import com.interview.platform.ui.theme.*

/**
 * Shared bottom navigation bar matching the Stitch PrepAI design.
 * Five tabs: Home | Interviews | Results | Roadmap | Profile
 *
 * @param currentTab  One of: "home", "interviews", "results", "roadmap", "profile"
 * @param navController  Used for navigation on item clicks.
 */
@Composable
fun PrepCoachBottomNav(
    currentTab: String,
    navController: NavController
) {
    data class NavItem(val route: String, val label: String, val icon: ImageVector)

    val items = listOf(
        NavItem(AppRoutes.home, "Home", Icons.Default.Home),
        NavItem(AppRoutes.interviewSession, "Interviews", Icons.Default.Chat),
        NavItem(AppRoutes.interviewHistory, "Results", Icons.Default.Analytics),
        NavItem(AppRoutes.interviewRoadmap, "Roadmap", Icons.Default.Route),
        NavItem(AppRoutes.profile, "Profile", Icons.Default.Person)
    )

    NavigationBar(
        containerColor = PrepAiSurfaceContainerLowest,
        tonalElevation = 0.dp,
        modifier = Modifier.border(
            width = 1.dp,
            color = PrepAiOutlineVariant.copy(alpha = 0.6f),
            shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp)
        )
    ) {
        items.forEach { item ->
            val selected = currentTab == item.route
            NavigationBarItem(
                selected = selected,
                onClick = {
                    if (!selected) {
                        navController.navigate(item.route) {
                            popUpTo(AppRoutes.home) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        fontSize = 11.sp,
                        fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = PrepAiPrimary,
                    selectedTextColor = PrepAiPrimary,
                    indicatorColor = PrepAiPrimaryContainer.copy(alpha = 0.15f),
                    unselectedIconColor = PrepAiSecondary,
                    unselectedTextColor = PrepAiSecondary
                )
            )
        }
    }
}
