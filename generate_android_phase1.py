import os

base_pkg = "/Users/apple/AI-Powered Interview Preparation Platform/app/src/main/java/com/interview/platform/ui"

theme_files = {
    "theme/Color.kt": """package com.interview.platform.ui.theme

import androidx.compose.ui.graphics.Color

val PrimaryGreen = Color(0xFF16A34A)
val SecondarySlate = Color(0xFF475569)
val BackgroundSlate = Color(0xFFF8FAFC)
val SurfaceWhite = Color(0xFFFFFFFF)
val SuccessGreen = Color(0xFF22C55E)
val WarningAmber = Color(0xFFF59E0B)
val ErrorRed = Color(0xFFDC2626)
""",
    "theme/Theme.kt": """package com.interview.platform.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = PrimaryGreen,
    secondary = SecondarySlate,
    background = BackgroundSlate,
    surface = SurfaceWhite,
    error = ErrorRed
)

@Composable
fun InterviewPlatformTheme(
    content: @Composable () -> Unit
) {
    // Light mode only as per requirements
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}
""",
    "theme/Type.kt": """package com.interview.platform.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Note: Ensure Inter font family is added in res/font for production
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default, // Placeholder for Inter
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Default, // Placeholder for Inter
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    )
)
"""
}

screens = [
    ("mod01_authentication", "splash", "SplashScreen"),
    ("mod01_authentication", "welcome", "WelcomeScreen"),
    ("mod01_authentication", "login", "LoginScreen"),
    ("mod01_authentication", "otp_verification", "OtpVerificationScreen"),
    ("mod01_authentication", "permissions", "PermissionsScreen"),
    ("mod02_user_profile", "profile", "ProfileScreen"),
    ("mod02_user_profile", "edit_profile", "EditProfileScreen"),
]

def generate_screen_files(mod_dir, screen_dir, screen_name):
    pkg_path = f"com.interview.platform.ui.screens.{mod_dir}.{screen_dir}"
    os.makedirs(os.path.join(base_pkg, "screens", mod_dir, screen_dir), exist_ok=True)
    
    # UiState
    uistate_content = f"""package {pkg_path}

sealed class {screen_name}UiState {{
    object Loading : {screen_name}UiState()
    object Empty : {screen_name}UiState()
    data class Success(val data: String = "") : {screen_name}UiState()
    data class Error(val message: String) : {screen_name}UiState()
}}
"""
    with open(os.path.join(base_pkg, "screens", mod_dir, screen_dir, f"{screen_name}UiState.kt"), "w") as f:
        f.write(uistate_content)

    # ViewModel
    viewmodel_content = f"""package {pkg_path}

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class {screen_name}ViewModel @Inject constructor() : ViewModel() {{
    private val _uiState = MutableStateFlow<{screen_name}UiState>({screen_name}UiState.Success())
    val uiState: StateFlow<{screen_name}UiState> = _uiState.asStateFlow()
    
    fun submitAction() {{
        // Implementation placeholder
    }}
}}
"""
    with open(os.path.join(base_pkg, "screens", mod_dir, screen_dir, f"{screen_name}ViewModel.kt"), "w") as f:
        f.write(viewmodel_content)

    # Screen
    screen_content = f"""package {pkg_path}

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun {screen_name}(
    onNavigateForward: () -> Unit,
    viewModel: {screen_name}ViewModel = hiltViewModel()
) {{
    val uiState by viewModel.uiState.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {{
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {{
            Text(
                text = "{screen_name}",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(32.dp))
            
            when (uiState) {{
                is {screen_name}UiState.Loading -> CircularProgressIndicator()
                is {screen_name}UiState.Error -> Text("Error: ${{ (uiState as {screen_name}UiState.Error).message }}", color = MaterialTheme.colorScheme.error)
                is {screen_name}UiState.Empty -> Text("No data available.")
                is {screen_name}UiState.Success -> {{
                    Button(
                        onClick = onNavigateForward,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp) // Accessibility minimum
                    ) {{
                        Text("Continue")
                    }}
                }}
            }}
        }}
    }}
}}
"""
    with open(os.path.join(base_pkg, "screens", mod_dir, screen_dir, f"{screen_name}Screen.kt"), "w") as f:
        f.write(screen_content)


# Generate Theme Files
os.makedirs(os.path.join(base_pkg, "theme"), exist_ok=True)
for rel_path, content in theme_files.items():
    with open(os.path.join(base_pkg, rel_path), "w") as f:
        f.write(content)

# Generate Phase 1 Screens
for mod_dir, screen_dir, screen_name in screens:
    generate_screen_files(mod_dir, screen_dir, screen_name)

print("Generated Android Phase 1 Scaffold successfully.")
