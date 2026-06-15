package com.interview.platform.ui.screens.mod05_experience_level_management.experience_selection

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.interview.platform.ui.theme.*

private val Background get() = PrepAiBackground
private val Primary get() = PrepAiPrimary
private val PrimaryContainer get() = PrepAiPrimaryContainer
private val OnPrimaryContainer get() = PrepAiOnPrimaryContainer
private val Secondary get() = PrepAiSecondary
private val OutlineVariant get() = PrepAiOutlineVariant
private val SurfaceContainerLowest get() = PrepAiSurfaceContainerLowest
private val SurfaceContainerHigh get() = PrepAiSurfaceContainerHigh


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExperienceSelectionScreen(
    onNavigateForward: () -> Unit,
    onNavigateHome: () -> Unit = {},
    onNavigateProfile: () -> Unit = {},
    isOnboarding: Boolean = false,
    isFromProfile: Boolean = false,
    viewModel: ExperienceSelectionScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedLevel by remember { mutableStateOf("Mid-Level") }

    LaunchedEffect(uiState) {
        if (uiState is ExperienceSelectionScreenUiState.Success) {
            val defaultExp = (uiState as ExperienceSelectionScreenUiState.Success).defaultExperience
            if (defaultExp.isNotEmpty() && selectedLevel == "Mid-Level") {
                selectedLevel = defaultExp
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Icon(Icons.Default.School, contentDescription = null, tint = Primary)
                        Text(if (isOnboarding || isFromProfile) "Experience Level" else "Preparing for which role?", color = Primary, fontWeight = FontWeight.SemiBold)
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.Notifications, contentDescription = "Notifications", tint = Secondary)
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.Person, contentDescription = "Profile", tint = Secondary)
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
        ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header Section
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                Text("Experience Level", style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold), textAlign = TextAlign.Center)
                Text(
                    "Tailor your interview preparation by selecting your current career stage. This helps our AI coach curate the most relevant questions and scenarios for you.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Secondary,
                    textAlign = TextAlign.Center
                )
            }

            // Experience Cards Grid
            val levels = listOf(
                Triple("Fresher", "Student or recent graduate looking to land their first professional role.", Icons.Default.FilterVintage), // sprout fallback
                Triple("Junior", "1-3 years of experience. Developing core skills and industry knowledge.", Icons.Default.DirectionsWalk),
                Triple("Mid-Level", "3-7 years of experience. Proven track record and specialized expertise.", Icons.Default.Star),
                Triple("Senior", "7+ years of experience. Leadership, architecture, and high-level strategy.", Icons.Default.WorkspacePremium) // medal fallback
            )

            // For mobile, column layout
            Column(verticalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxWidth()) {
                levels.forEach { (name, desc, icon) ->
                    val isSelected = selectedLevel == name
                    Card(
                        modifier = Modifier.fillMaxWidth().clickable { selectedLevel = name },
                        colors = CardDefaults.cardColors(containerColor = if (isSelected) OnPrimaryContainer else SurfaceContainerLowest),
                        border = BorderStroke(if (isSelected) 2.dp else 1.dp, if (isSelected) Primary else OutlineVariant),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(modifier = Modifier.padding(24.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                            Box(
                                modifier = Modifier
                                    .size(64.dp)
                                    .background(if (isSelected) Primary else SurfaceContainerHigh, CircleShape)
                                    .padding(bottom = 16.dp), // margin bottom mock
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(icon, contentDescription = null, tint = if (isSelected) Color.White else Secondary, modifier = Modifier.size(32.dp))
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(name, style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium), color = MaterialTheme.colorScheme.onSurface)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(desc, style = MaterialTheme.typography.bodyMedium, color = Secondary, textAlign = TextAlign.Center)
                            Spacer(modifier = Modifier.height(24.dp))
                            
                            // Radio indicator
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .border(2.dp, if (isSelected) Primary else OutlineVariant, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                if (isSelected) {
                                    Box(modifier = Modifier.size(12.dp).background(Primary, CircleShape))
                                }
                            }
                        }
                    }
                }
            }

            // Continue Button
            Button(
                onClick = { viewModel.submitAction(selectedLevel, isOnboarding, isFromProfile, onNavigateForward) },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Primary),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(if (isOnboarding) "Continue to Tech Stack" else if (isFromProfile) "Save Experience" else "Continue to Mock Interview", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.width(8.dp))
                if (!isFromProfile) {
                    Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
                } else {
                    Icon(Icons.Default.Save, contentDescription = null)
                }
            }
        }
    }
}
