package com.interview.platform.ui.screens.mod03_department_management.department_selection

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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.unit.sp
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
private val SurfaceContainerLow get() = PrepAiSurfaceContainerLow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DepartmentSelectionScreen(
    onNavigateForward: () -> Unit,
    onNavigateBack: () -> Unit = {},
    viewModel: DepartmentSelectionScreenViewModel = hiltViewModel()
) {
    var selectedDept by remember { mutableStateOf("Engineering") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Icon(Icons.Default.School, contentDescription = null, tint = Primary)
                        Text("PrepCoach AI", color = Primary, fontWeight = FontWeight.Bold)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colorScheme.onSurface)
                    }
                },
                actions = {
                    Text("Step 1 of 3", style = MaterialTheme.typography.labelLarge, color = Secondary, modifier = Modifier.padding(end = 16.dp))
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
            // Hero Section
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Choose Your Department", style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold))
                Text(
                    "Select the department you are interviewing for. This helps us tailor the technical questions and scenario-based assessments to your specific field.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Secondary
                )
            }

            // Grid Options
            val departments = listOf(
                Triple("Engineering", "Backend, Frontend, Full-stack, Mobile, and DevOps roles.", Icons.Default.Code),
                Triple("Marketing", "Growth, Content Strategy, Brand Management, and Analytics.", Icons.Default.TrendingUp),
                Triple("Finance", "Investment Banking, Corporate Finance, and Risk Analysis.", Icons.Default.Payments),
                Triple("Product Management", "Technical PM, Strategy, User Research, and Roadmap Execution.", Icons.Default.ViewQuilt),
                Triple("Design", "UI/UX, Visual Design, Motion, and Interaction Design.", Icons.Default.Palette)
            )

            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                departments.forEach { (name, desc, icon) ->
                    val isSelected = selectedDept == name
                    Card(
                        modifier = Modifier.fillMaxWidth().clickable { selectedDept = name },
                        colors = CardDefaults.cardColors(containerColor = if (isSelected) OnPrimaryContainer else SurfaceContainerLowest),
                        border = BorderStroke(if (isSelected) 2.dp else 1.dp, if (isSelected) Primary else OutlineVariant),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(modifier = Modifier.padding(24.dp), horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.Top) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .background(if (isSelected) PrimaryContainer else SecondaryContainer, RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(icon, contentDescription = null, tint = if (isSelected) OnPrimaryContainer else OnSecondaryContainer, modifier = Modifier.size(28.dp))
                            }
                            Column {
                                Text(name, style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium), color = MaterialTheme.colorScheme.onSurface)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(desc, style = MaterialTheme.typography.bodyMedium, color = Secondary)
                            }
                        }
                    }
                }
            }

            // Illustration Context
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = SurfaceContainerLow),
                border = BorderStroke(1.dp, OutlineVariant.copy(alpha = 0.5f)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(modifier = Modifier.padding(24.dp), horizontalArrangement = Arrangement.spacedBy(24.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(64.dp).background(SecondaryContainer, CircleShape).border(2.dp, SurfaceContainerLowest, CircleShape), contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.Info, contentDescription = null, tint = Secondary)
                    }
                    Column {
                        Text("WHY THIS MATTERS", style = MaterialTheme.typography.labelLarge, color = Primary, modifier = Modifier.padding(bottom = 4.dp))
                        Text(
                            "Our AI engines generate domain-specific case studies. Choosing \"Engineering\" focuses on coding logic and system design, while \"Marketing\" focuses on market share and customer acquisition costs.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            //
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
                    onClick = onNavigateForward,
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Primary),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Continue", style = MaterialTheme.typography.labelLarge)
                }
            }

        }
    }
}
