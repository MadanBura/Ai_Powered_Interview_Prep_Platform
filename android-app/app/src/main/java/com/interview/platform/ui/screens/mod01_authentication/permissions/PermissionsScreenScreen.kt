package com.interview.platform.ui.screens.mod01_authentication.permissions

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
private val SurfaceContainerHigh get() = PrepAiSurfaceContainerHigh


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PermissionsScreenScreen(onNavigateForward: () -> Unit) {
    var micEnabled by remember { mutableStateOf(true) }
    var notifEnabled by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        Icon(Icons.Default.School, contentDescription = null, tint = Primary)
                        Text("PrepCoach AI", color = Primary, fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.Close, contentDescription = "Close", tint = Secondary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = SurfaceContainerLowest,
                    scrolledContainerColor = SurfaceContainerLowest
                ),
                modifier = Modifier.border(width = 1.dp, color = OutlineVariant.copy(alpha = 0.5f), shape = RoundedCornerShape(0.dp))
            )
        },
        containerColor = Background,
        ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Illustration Space
            Box(
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .size(160.dp)
                    .background(SurfaceContainerLowest, CircleShape)
                    .border(1.dp, OutlineVariant, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                // Mocking the image using placeholder colors to simulate the green tone
                Box(modifier = Modifier.fillMaxSize(0.8f).clip(CircleShape).background(Primary.copy(alpha = 0.1f)))
            }

            Text(
                text = "Let's get set up",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "To provide the most realistic interview experience, PrepCoach AI needs a few permissions.",
                style = MaterialTheme.typography.bodyMedium,
                color = Secondary,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 32.dp).width(300.dp)
            )

            // Permissions Bento Cards
            Column(verticalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxWidth()) {
                // Mic Permission
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
                    border = BorderStroke(1.dp, if (micEnabled) Primary else OutlineVariant),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .background(if (micEnabled) Primary else Primary.copy(alpha = 0.1f), RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.Mic, contentDescription = null, tint = if (micEnabled) Color.White else Primary, modifier = Modifier.size(28.dp))
                            }
                            Switch(
                                checked = micEnabled,
                                onCheckedChange = { micEnabled = it },
                                colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = PrimaryContainer)
                            )
                        }
                        Text("Microphone", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("To record your interview answers and provide real-time vocal tone analysis and speech-to-text feedback.", style = MaterialTheme.typography.bodySmall, color = Secondary)
                    }
                }

                // Notifications Permission
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
                    border = BorderStroke(1.dp, if (notifEnabled) Primary else OutlineVariant),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .background(if (notifEnabled) Primary else Color(0xFFD5E3FC).copy(alpha = 0.2f), RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.Notifications, contentDescription = null, tint = if (notifEnabled) Color.White else Secondary, modifier = Modifier.size(28.dp))
                            }
                            Switch(
                                checked = notifEnabled,
                                onCheckedChange = { notifEnabled = it },
                                colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = PrimaryContainer)
                            )
                        }
                        Text("Notifications", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("To remind you of upcoming practice sessions and alert you when your AI interview analysis is ready for review.", style = MaterialTheme.typography.bodySmall, color = Secondary)
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onNavigateForward,
                modifier = Modifier.fillMaxWidth().height(48.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Primary)
            ) {
                Text("Allow & Continue", style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Medium))
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(
                onClick = onNavigateForward,
                modifier = Modifier.fillMaxWidth().height(40.dp),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text("Maybe Later", style = MaterialTheme.typography.labelLarge, color = Secondary)
            }
        }
    }
}
