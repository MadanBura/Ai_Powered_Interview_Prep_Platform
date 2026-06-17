package com.interview.platform.ui.screens.mod01_authentication.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.interview.platform.ui.theme.*

private val Background get() = PrepAiBackground
private val PrimaryContainer get() = PrepAiPrimaryContainer
private val OnPrimaryContainer get() = PrepAiOnPrimaryContainer
private val Primary get() = PrepAiPrimary
private val Secondary get() = PrepAiSecondary
private val OutlineVariant get() = PrepAiOutlineVariant
private val SurfaceContainerLowest get() = PrepAiSurfaceContainerLowest


val ActionGreen = Color(0xFF16A34A)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreenScreen(
    onNavigateToOtp: (String) -> Unit,
    viewModel: LoginScreenViewModel = hiltViewModel()
) {
    var email by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState) {
        if (uiState is LoginScreenUiState.Success) {
            onNavigateToOtp(email)
            viewModel.resetState()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        // Decorative background elements
        Box(
            modifier = Modifier
                .offset(x = (-48).dp, y = (-48).dp)
                .size(256.dp)
                .background(PrimaryContainer.copy(alpha = 0.1f), CircleShape)
        )
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .offset(x = 96.dp)
                .size(384.dp)
                .background(Color(0xFFFFD9DE).copy(alpha = 0.2f), CircleShape)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Brand
            Box(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .size(64.dp)
                    .background(PrimaryContainer, RoundedCornerShape(12.dp))
                    .shadow(elevation = 2.dp, shape = RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.School,
                    contentDescription = "Logo",
                    tint = OnPrimaryContainer,
                    modifier = Modifier.size(40.dp)
                )
            }
            Text(
                text = "PrepCoach AI",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Form Card
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(SurfaceContainerLowest.copy(alpha = 0.8f))
                    .border(1.dp, OutlineVariant.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                    .padding(24.dp)
            ) {
                Text(
                    text = "Welcome Back",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "Sign in to continue your preparation.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Secondary,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                Text(
                    text = "Email or Mobile Number",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text("name@company.com", color = OutlineVariant) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(8.dp),
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = SurfaceContainerLowest,
                        unfocusedBorderColor = OutlineVariant,
                        focusedBorderColor = ActionGreen
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                if (uiState is LoginScreenUiState.Error) {
                    Text(
                        text = (uiState as LoginScreenUiState.Error).message,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

                Button(
                    onClick = { viewModel.requestOtp(email) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = ActionGreen),
                    enabled = uiState !is LoginScreenUiState.Loading && email.isNotBlank()
                ) {
                    if (uiState is LoginScreenUiState.Loading) {
                        CircularProgressIndicator(modifier = Modifier.size(20.dp), color = Color.White)
                    } else {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text("Continue", style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Medium))
                            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(20.dp))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Divider
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    Divider(modifier = Modifier.weight(1f), color = OutlineVariant)
                    Text("Or continue with", style = MaterialTheme.typography.labelSmall, color = Secondary, modifier = Modifier.padding(horizontal = 16.dp))
                    Divider(modifier = Modifier.weight(1f), color = OutlineVariant)
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Social Buttons
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    OutlinedButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .weight(1f)
                            .height(44.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.outlinedButtonColors(containerColor = SurfaceContainerLowest),
                        border = ButtonDefaults.outlinedButtonBorder.copy(brush = androidx.compose.ui.graphics.SolidColor(OutlineVariant))
                    ) {
                        Text("Google", color = MaterialTheme.colorScheme.onSurface, style = MaterialTheme.typography.labelLarge)
                    }
                    OutlinedButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .weight(1f)
                            .height(44.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.outlinedButtonColors(containerColor = SurfaceContainerLowest),
                        border = ButtonDefaults.outlinedButtonBorder.copy(brush = androidx.compose.ui.graphics.SolidColor(OutlineVariant))
                    ) {
                        Text("LinkedIn", color = MaterialTheme.colorScheme.onSurface, style = MaterialTheme.typography.labelLarge)
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
