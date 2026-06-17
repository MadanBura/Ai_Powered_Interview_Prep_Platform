package com.interview.platform.ui.screens.mod02_user_profile.edit_profile

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
import androidx.compose.ui.draw.clip
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
private val OutlineVariant get() = PrepAiOutlineVariant
private val SurfaceContainerLowest get() = PrepAiSurfaceContainerLowest
private val SurfaceContainerLow get() = PrepAiSurfaceContainerLow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    onNavigateForward: () -> Unit,
    onNavigateBack: () -> Unit = {},
    viewModel: EditProfileScreenViewModel = hiltViewModel()
) {
    var name by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
    var selectedExp by remember { mutableStateOf("") }
    var department by remember { mutableStateOf("") }
    var deptDropdownExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Profile", color = Primary, fontWeight = FontWeight.SemiBold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Primary)
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
            // Top Card (Avatar & Summary)
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, OutlineVariant)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(modifier = Modifier.size(128.dp)) {
                        Box(
                            modifier = Modifier
                                .size(128.dp)
                                .clip(CircleShape)
                                .background(Primary.copy(alpha = 0.1f))
                                .border(4.dp, PrimaryContainer, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.size(64.dp), tint = Primary)
                        }
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .background(Primary, CircleShape)
                                .padding(8.dp)
                        ) {
                            Icon(Icons.Default.PhotoCamera, contentDescription = "Edit Photo", tint = Color.White, modifier = Modifier.size(20.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        placeholder = { Text("Your Name", color = Secondary) },
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = SurfaceContainerLow),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        placeholder = { Text("Your Job Title", color = Secondary) },
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = SurfaceContainerLow),
                        singleLine = true
                    )
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    Divider(color = OutlineVariant)
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("0", style = MaterialTheme.typography.headlineMedium, color = Primary)
                            Text("INTERVIEWS", style = MaterialTheme.typography.labelSmall, color = Secondary)
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("0%", style = MaterialTheme.typography.headlineMedium, color = Primary)
                            Text("AVG SCORE", style = MaterialTheme.typography.labelSmall, color = Secondary)
                        }
                    }
                }
            }

            // Form Fields
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, OutlineVariant)
            ) {
                Column(modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(24.dp)) {
                    // Department
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Icon(Icons.Default.CorporateFare, contentDescription = null, tint = Primary)
                            Text("Department", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium))
                        }
                        Box {
                            OutlinedTextField(
                                value = department,
                                onValueChange = {},
                                modifier = Modifier.fillMaxWidth().clickable { deptDropdownExpanded = true },
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    containerColor = SurfaceContainerLow,
                                    unfocusedBorderColor = OutlineVariant,
                                    focusedBorderColor = Primary,
                                    disabledTextColor = MaterialTheme.colorScheme.onSurface,
                                    disabledBorderColor = OutlineVariant
                                ),
                                trailingIcon = { Icon(Icons.Default.ArrowDropDown, contentDescription = null) },
                                readOnly = true,
                                enabled = false,
                                shape = RoundedCornerShape(8.dp)
                            )
                            DropdownMenu(
                                expanded = deptDropdownExpanded,
                                onDismissRequest = { deptDropdownExpanded = false }
                            ) {
                                listOf("Engineering", "Product", "Design", "Marketing").forEach { dept ->
                                    DropdownMenuItem(
                                        text = { Text(dept) },
                                        onClick = {
                                            department = dept
                                            deptDropdownExpanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }

                    // Technology Stack
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Icon(Icons.Default.Code, contentDescription = null, tint = Primary)
                            Text("Technologies", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium))
                        }
                        
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(SurfaceContainerLow, RoundedCornerShape(8.dp))
                                .border(1.dp, OutlineVariant, RoundedCornerShape(8.dp))
                                .padding(12.dp)
                        ) {
                            Text("Add tech...", color = Secondary, modifier = Modifier.align(Alignment.CenterStart).padding(start = 4.dp))
                        }
                        
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text("Suggestions:", style = MaterialTheme.typography.bodySmall, color = Secondary)
                            listOf("Next.js", "Node.js", "GraphQL").forEach {
                                Surface(color = Color(0xFFE0E3E5), shape = RoundedCornerShape(4.dp)) {
                                    Text(it, modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp), style = MaterialTheme.typography.bodySmall)
                                }
                            }
                        }
                    }

                    // Experience Level
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Icon(Icons.Default.Star, contentDescription = null, tint = Primary) // using Star as generic 'Stairs' replacement
                            Text("Experience Level", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium))
                        }
                        
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                            val options = listOf("Fresher" to Icons.Default.FilterVintage, "Junior" to Icons.Default.Hiking, "Mid-Level" to Icons.Default.Star, "Senior" to Icons.Default.WorkspacePremium)
                            options.forEach { (label, icon) ->
                                val isSelected = selectedExp == label
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .background(if (isSelected) PrimaryContainer else SurfaceContainerLowest, RoundedCornerShape(8.dp))
                                        .border(1.dp, if (isSelected) Primary else OutlineVariant, RoundedCornerShape(8.dp))
                                        .clickable { selectedExp = label }
                                        .padding(vertical = 16.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Icon(icon, contentDescription = null, tint = if (isSelected) OnPrimaryContainer else Secondary)
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(label, style = MaterialTheme.typography.labelLarge, color = if (isSelected) OnPrimaryContainer else Secondary)
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Save Button
                    val isFormValid = name.isNotBlank() && title.isNotBlank() && selectedExp.isNotBlank() && department.isNotBlank()
                    Button(
                        onClick = { viewModel.submitAction(onSuccess = onNavigateForward) },
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Primary),
                        enabled = isFormValid
                    ) {
                        Icon(Icons.Default.Save, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Save Changes", style = MaterialTheme.typography.titleMedium)
                    }

                    if (!isFormValid) {
                        Text("Please fill out all fields to save.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                    }
                }
            }
        }
    }
}
