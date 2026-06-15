import os
import re

base_dir = "/Users/apple/AI-Powered Interview Preparation Platform/android-app/app/src/main/java/com/interview/platform"

# --- 1. Global Theme Initialization ---
theme_dir = os.path.join(base_dir, "ui/theme")
os.makedirs(theme_dir, exist_ok=True)

color_kt = """package com.interview.platform.ui.theme

import androidx.compose.ui.graphics.Color

val PrimaryGreen = Color(0xFF16A34A)
val SecondarySlate = Color(0xFF475569)
val BackgroundSlate = Color(0xFFF8FAFC)
val SurfaceWhite = Color(0xFFFFFFFF)
val SuccessGreen = Color(0xFF22C55E)
val WarningAmber = Color(0xFFF59E0B)
val ErrorRed = Color(0xFFDC2626)
"""
with open(os.path.join(theme_dir, "Color.kt"), "w") as f:
    f.write(color_kt)

type_kt = """package com.interview.platform.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Default, // Simulating Inter for now as importing font files requires assets
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    )
)
"""
with open(os.path.join(theme_dir, "Type.kt"), "w") as f:
    f.write(type_kt)

theme_kt = """package com.interview.platform.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = PrimaryGreen,
    secondary = SecondarySlate,
    background = BackgroundSlate,
    surface = SurfaceWhite,
    error = ErrorRed,
    onPrimary = SurfaceWhite,
    onSecondary = SurfaceWhite,
    onBackground = SecondarySlate,
    onSurface = SecondarySlate,
    onError = SurfaceWhite
)

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme // Force Light Mode as requested
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
"""
with open(os.path.join(theme_dir, "Theme.kt"), "w") as f:
    f.write(theme_kt)


# --- 2. Auth Network Integration (ViewModels) ---
login_vm = """package com.interview.platform.ui.screens.mod01_authentication.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.platform.data.remote.api.Mod01AuthenticationApiService
import com.interview.platform.data.remote.dto.mod01_authentication.AuthDataRequestDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val authApi: Mod01AuthenticationApiService
) : ViewModel() {
    private val _uiState = MutableStateFlow<LoginScreenUiState>(LoginScreenUiState.Empty)
    val uiState: StateFlow<LoginScreenUiState> = _uiState.asStateFlow()
    
    fun requestOtp(email: String) {
        if (email.isBlank()) {
            _uiState.value = LoginScreenUiState.Error("Email cannot be empty")
            return
        }
        
        _uiState.value = LoginScreenUiState.Loading
        viewModelScope.launch {
            try {
                val request = AuthDataRequestDto(email = email, action = "REQUEST_OTP")
                val response = authApi.requestOtp(request)
                if (response.isSuccessful) {
                    _uiState.value = LoginScreenUiState.Success()
                } else {
                    _uiState.value = LoginScreenUiState.Error("Server returned ${response.code()}")
                }
            } catch (e: Exception) {
                _uiState.value = LoginScreenUiState.Error(e.message ?: "Network Error")
            }
        }
    }
    
    fun resetState() {
        _uiState.value = LoginScreenUiState.Empty
    }
}
"""
with open(os.path.join(base_dir, "ui/screens/mod01_authentication/login/LoginScreenViewModel.kt"), "w") as f:
    f.write(login_vm)

otp_vm = """package com.interview.platform.ui.screens.mod01_authentication.otp_verification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.platform.data.remote.api.Mod01AuthenticationApiService
import com.interview.platform.data.remote.dto.mod01_authentication.AuthDataRequestDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtpVerificationScreenViewModel @Inject constructor(
    private val authApi: Mod01AuthenticationApiService
) : ViewModel() {
    private val _uiState = MutableStateFlow<OtpVerificationScreenUiState>(OtpVerificationScreenUiState.Empty)
    val uiState: StateFlow<OtpVerificationScreenUiState> = _uiState.asStateFlow()
    
    fun verifyOtp(email: String, otp: String) {
        if (otp.isBlank()) {
            _uiState.value = OtpVerificationScreenUiState.Error("OTP cannot be empty")
            return
        }
        _uiState.value = OtpVerificationScreenUiState.Loading
        viewModelScope.launch {
            try {
                val request = AuthDataRequestDto(email = email, action = "VERIFY_OTP", payload = mapOf("otp" to otp))
                val response = authApi.verifyOtp(request)
                if (response.isSuccessful) {
                    _uiState.value = OtpVerificationScreenUiState.Success()
                } else {
                    _uiState.value = OtpVerificationScreenUiState.Error("Invalid OTP or expired")
                }
            } catch (e: Exception) {
                _uiState.value = OtpVerificationScreenUiState.Error(e.message ?: "Network Error")
            }
        }
    }
    
    fun resetState() {
        _uiState.value = OtpVerificationScreenUiState.Empty
    }
}
"""
with open(os.path.join(base_dir, "ui/screens/mod01_authentication/otp_verification/OtpVerificationScreenViewModel.kt"), "w") as f:
    f.write(otp_vm)

# Ensure DTO exists and has correct fields
dto_dir = os.path.join(base_dir, "data/remote/dto/mod01_authentication")
os.makedirs(dto_dir, exist_ok=True)
dto_kt = """package com.interview.platform.data.remote.dto.mod01_authentication

data class AuthDataRequestDto(
    val email: String,
    val action: String,
    val payload: Map<String, String>? = null
)

data class AuthDataResponseDto(
    val success: Boolean,
    val token: String? = null,
    val message: String? = null
)
"""
with open(os.path.join(dto_dir, "AuthDataRequestDto.kt"), "w") as f:
    f.write(dto_kt)

# --- 3. Auth UI Overhaul ---
splash_kt = """package com.interview.platform.ui.screens.mod01_authentication.splash

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun SplashScreenScreen(onNavigateForward: () -> Unit) {
    var startAnimation by remember { mutableStateOf(false) }
    val scale = animateFloatAsState(
        targetValue = if (startAnimation) 1.5f else 0f,
        animationSpec = tween(durationMillis = 1000)
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(2000)
        onNavigateForward()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "AI INTERVIEW",
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier.scale(scale.value)
        )
    }
}
"""
with open(os.path.join(base_dir, "ui/screens/mod01_authentication/splash/SplashScreenScreen.kt"), "w") as f:
    f.write(splash_kt)

welcome_kt = """package com.interview.platform.ui.screens.mod01_authentication.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun WelcomeScreen(onNavigateForward: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f), shape = MaterialTheme.shapes.large),
            contentAlignment = Alignment.Center
        ) {
            Text("AI", style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.primary)
        }
        Spacer(modifier = Modifier.height(48.dp))
        Text(
            text = "Elevate Your Interview Skills",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Practice with our AI-powered platform and get real-time feedback to land your dream job.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(48.dp))
        Button(
            onClick = onNavigateForward,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("Get Started", style = MaterialTheme.typography.titleLarge)
        }
    }
}
"""
with open(os.path.join(base_dir, "ui/screens/mod01_authentication/welcome/WelcomeScreenScreen.kt"), "w") as f:
    f.write(welcome_kt)

login_screen_kt = """package com.interview.platform.ui.screens.mod01_authentication.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome Back",
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Enter your email to receive a verification code.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(32.dp))
        
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email Address") },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))
        
        if (uiState is LoginScreenUiState.Error) {
            Text(
                text = (uiState as LoginScreenUiState.Error).message,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = { viewModel.requestOtp(email) },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = MaterialTheme.shapes.medium,
            enabled = uiState !is LoginScreenUiState.Loading && email.isNotBlank()
        ) {
            if (uiState is LoginScreenUiState.Loading) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp), color = MaterialTheme.colorScheme.onPrimary)
            } else {
                Text("Send OTP Code", style = MaterialTheme.typography.titleLarge)
            }
        }
    }
}
"""
with open(os.path.join(base_dir, "ui/screens/mod01_authentication/login/LoginScreenScreen.kt"), "w") as f:
    f.write(login_screen_kt)

otp_screen_kt = """package com.interview.platform.ui.screens.mod01_authentication.otp_verification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun OtpVerificationScreenScreen(
    email: String,
    onNavigateToHome: () -> Unit,
    viewModel: OtpVerificationScreenViewModel = hiltViewModel()
) {
    var otp by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState) {
        if (uiState is OtpVerificationScreenUiState.Success) {
            onNavigateToHome()
            viewModel.resetState()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Verify Email",
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "We sent a 6-digit code to $email",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(32.dp))
        
        OutlinedTextField(
            value = otp,
            onValueChange = { otp = it },
            label = { Text("6-Digit OTP") },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))
        
        if (uiState is OtpVerificationScreenUiState.Error) {
            Text(
                text = (uiState as OtpVerificationScreenUiState.Error).message,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = { viewModel.verifyOtp(email, otp) },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = MaterialTheme.shapes.medium,
            enabled = uiState !is OtpVerificationScreenUiState.Loading && otp.isNotBlank()
        ) {
            if (uiState is OtpVerificationScreenUiState.Loading) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp), color = MaterialTheme.colorScheme.onPrimary)
            } else {
                Text("Verify Code", style = MaterialTheme.typography.titleLarge)
            }
        }
    }
}
"""
with open(os.path.join(base_dir, "ui/screens/mod01_authentication/otp_verification/OtpVerificationScreenScreen.kt"), "w") as f:
    f.write(otp_screen_kt)

permissions_kt = """package com.interview.platform.ui.screens.mod01_authentication.permissions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun PermissionsScreenScreen(onNavigateForward: () -> Unit) {
    var micEnabled by remember { mutableStateOf(false) }
    var cameraEnabled by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Device Permissions",
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "To participate in AI interviews, we need access to your microphone and camera.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(32.dp))
        
        Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {
            Row(modifier = Modifier.padding(16.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text("Microphone", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
                    Text("Required for voice analysis", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
                }
                Switch(checked = micEnabled, onCheckedChange = { micEnabled = it })
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {
            Row(modifier = Modifier.padding(16.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text("Camera", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
                    Text("Required for facial tracking", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
                }
                Switch(checked = cameraEnabled, onCheckedChange = { cameraEnabled = it })
            }
        }
        Spacer(modifier = Modifier.height(48.dp))
        
        Button(
            onClick = onNavigateForward,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("Continue to App", style = MaterialTheme.typography.titleLarge)
        }
    }
}
"""
with open(os.path.join(base_dir, "ui/screens/mod01_authentication/permissions/PermissionsScreenScreen.kt"), "w") as f:
    f.write(permissions_kt)

# Ensure AppTheme wraps the root NavHost in MainActivity
main_activity_path = os.path.join(base_dir, "MainActivity.kt")
with open(main_activity_path, "r") as f:
    main_content = f.read()

if "AppTheme" not in main_content:
    main_content = main_content.replace("MaterialTheme {", "com.interview.platform.ui.theme.AppTheme {")
    with open(main_activity_path, "w") as f:
        f.write(main_content)

print("Android Phase 1 Overhaul scripts completed successfully.")
