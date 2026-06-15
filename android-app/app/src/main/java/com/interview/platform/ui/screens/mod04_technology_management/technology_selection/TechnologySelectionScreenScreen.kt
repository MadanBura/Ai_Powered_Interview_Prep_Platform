package com.interview.platform.ui.screens.mod04_technology_management.technology_selection

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.interview.platform.ui.theme.*

private val Background get() = PrepAiBackground
private val Primary get() = PrepAiPrimary
private val PrimaryContainer get() = PrepAiPrimaryContainer
private val OnPrimaryContainer get() = PrepAiOnPrimaryContainer
private val Secondary get() = PrepAiSecondary
private val SecondaryContainer get() = PrepAiSecondaryContainer
private val OnSecondaryContainer get() = PrepAiOnSecondaryContainer
private val OutlineVariant get() = PrepAiOutlineVariant
private val SurfaceContainerLowest get() = PrepAiSurfaceContainerLowest
private val SurfaceContainerHigh get() = PrepAiSurfaceContainerHigh


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TechnologySelectionScreen(
    onNavigateForward: () -> Unit,
    onNavigateBack: () -> Unit = {},
    onNavigateHome: () -> Unit = {},
    isOnboarding: Boolean = false,
    isFromProfile: Boolean = false,
    viewModel: TechnologySelectionScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    var selectedTech by remember { mutableStateOf("React") }
    
    LaunchedEffect(uiState) {
        if (uiState is TechnologySelectionScreenUiState.Success) {
            val defaultTech = (uiState as TechnologySelectionScreenUiState.Success).defaultTechnology
            if (defaultTech.isNotEmpty() && selectedTech == "React") {
                selectedTech = defaultTech
            }
        }
    }

    var selectedFilter by remember { mutableStateOf("All Tech") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (isOnboarding || isFromProfile) "Select Technology" else "Preparing for which stack?", color = Primary, fontWeight = FontWeight.SemiBold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Secondary)
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
        containerColor = Background,
        ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Search and Filter Section
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Search interview topics (e.g., Python, AWS, React)...") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = OutlineVariant) },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = SurfaceContainerLowest,
                        unfocusedBorderColor = OutlineVariant,
                        focusedBorderColor = Primary
                    ),
                    shape = RoundedCornerShape(12.dp)
                )

                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    val filters = listOf("All Tech", "Frontend", "Backend", "Mobile", "Data Science")
                    items(filters.size) { index ->
                        val filter = filters[index]
                        val isSelected = filter == selectedFilter
                        Surface(
                            shape = RoundedCornerShape(24.dp),
                            color = if (isSelected) PrimaryContainer else SurfaceContainerHigh,
                            modifier = Modifier.clickable { selectedFilter = filter }
                        ) {
                            Text(
                                text = filter,
                                color = if (isSelected) OnPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    }
                }
            }

            // Selection Grid
            val allTechnologies = listOf(
                Triple("React", "Modern frontend development with hooks and state management.", Icons.Default.Code),
                Triple("Android", "Mobile app architecture, Kotlin, and Material Design.", Icons.Default.PhoneAndroid),
                Triple("Java", "Core foundations, Spring Boot, and enterprise design patterns.", Icons.Default.Coffee),
                Triple("DevOps", "CI/CD pipelines, Docker, Kubernetes, and cloud infrastructure.", Icons.Default.Terminal),
                Triple("Python", "Data structures, scripting, and backend web frameworks.", Icons.Default.DataObject),
                Triple("Node.js", "Scalable server-side applications and event-driven architecture.", Icons.Default.Javascript)
            )

            val technologies = allTechnologies.filter { 
                it.first.contains(searchQuery, ignoreCase = true) || it.second.contains(searchQuery, ignoreCase = true)
            }

            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                technologies.forEach { (name, desc, icon) ->
                    val isSelected = selectedTech == name
                    Card(
                        modifier = Modifier.fillMaxWidth().clickable { selectedTech = name },
                        colors = CardDefaults.cardColors(containerColor = if (isSelected) OnPrimaryContainer else SurfaceContainerLowest),
                        border = BorderStroke(if (isSelected) 2.dp else 1.dp, if (isSelected) Primary else OutlineVariant),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(modifier = Modifier.padding(24.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Top) {
                            Row(horizontalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.weight(1f)) {
                                Box(
                                    modifier = Modifier
                                        .size(48.dp)
                                        .background(if (isSelected) SecondaryContainer else SurfaceContainerHigh, RoundedCornerShape(8.dp)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(icon, contentDescription = null, tint = if (isSelected) OnSecondaryContainer else Secondary, modifier = Modifier.size(32.dp))
                                }
                                Column {
                                    Text(name, style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium), color = MaterialTheme.colorScheme.onSurface)
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(desc, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                }
                            }
                            if (isSelected) {
                                Icon(Icons.Default.CheckCircle, contentDescription = "Selected", tint = Primary, modifier = Modifier.size(24.dp))
                            } else {
                                Icon(Icons.Default.RadioButtonUnchecked, contentDescription = "Unselected", tint = OutlineVariant, modifier = Modifier.size(24.dp))
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Bottom Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedButton(
                    onClick = onNavigateBack,
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    border = BorderStroke(1.dp, OutlineVariant),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Cancel", style = MaterialTheme.typography.labelLarge, color = Secondary)
                }

                Button(
                    onClick = { 
                        if (selectedTech.isNotEmpty()) {
                            viewModel.submitAction(selectedTech, isOnboarding, isFromProfile, onNavigateForward)
                        } 
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Primary),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(if (isFromProfile) "Save Tech Stack" else "Continue", style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}
