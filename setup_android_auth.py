import os

base_dir = "/Users/apple/AI-Powered Interview Preparation Platform/android-app/app/src/main/java/com/interview/platform"

# 1. AppNavHost.kt
nav_host_kt = """package com.interview.platform.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.interview.platform.ui.screens.mod01_authentication.login.LoginScreenScreen
import com.interview.platform.ui.screens.mod01_authentication.otp_verification.OtpVerificationScreenScreen

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreenScreen(
                onNavigateToOtp = { email ->
                    navController.navigate("otp/$email")
                }
            )
        }
        composable("otp/{email}") { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            OtpVerificationScreenScreen(
                email = email,
                onNavigateToHome = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }
        composable("home") {
            androidx.compose.material3.Text("Home Protected Screen")
        }
    }
}
"""
os.makedirs(os.path.join(base_dir, "ui/navigation"), exist_ok=True)
with open(os.path.join(base_dir, "ui/navigation/AppNavHost.kt"), "w") as f:
    f.write(nav_host_kt)

# 2. Update MainActivity.kt
main_activity_kt = """package com.interview.platform

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import com.interview.platform.ui.navigation.AppNavHost

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AppNavHost()
                }
            }
        }
    }
}
"""
with open(os.path.join(base_dir, "MainActivity.kt"), "w") as f:
    f.write(main_activity_kt)

# 3. LoginScreenScreen.kt
login_screen_kt = """package com.interview.platform.ui.screens.mod01_authentication.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Candidate Login", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email Address") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        
        if (uiState is LoginScreenUiState.Error) {
            Text((uiState as LoginScreenUiState.Error).message, color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = { viewModel.requestOtp(email) },
            modifier = Modifier.fillMaxWidth(),
            enabled = uiState !is LoginScreenUiState.Loading && email.isNotBlank()
        ) {
            if (uiState is LoginScreenUiState.Loading) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp))
            } else {
                Text("Send OTP")
            }
        }
    }
}
"""
with open(os.path.join(base_dir, "ui/screens/mod01_authentication/login/LoginScreenScreen.kt"), "w") as f:
    f.write(login_screen_kt)

# 4. LoginScreenViewModel.kt
login_vm_kt = """package com.interview.platform.ui.screens.mod01_authentication.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    // private val authApi: Mod01AuthenticationApiService // Simplified for stub
) : ViewModel() {
    private val _uiState = MutableStateFlow<LoginScreenUiState>(LoginScreenUiState.Empty)
    val uiState: StateFlow<LoginScreenUiState> = _uiState.asStateFlow()
    
    fun requestOtp(email: String) {
        _uiState.value = LoginScreenUiState.Loading
        viewModelScope.launch {
            try {
                // Simulate network
                kotlinx.coroutines.delay(1000)
                _uiState.value = LoginScreenUiState.Success()
            } catch (e: Exception) {
                _uiState.value = LoginScreenUiState.Error("Network Error")
            }
        }
    }
    
    fun resetState() {
        _uiState.value = LoginScreenUiState.Empty
    }
}
"""
with open(os.path.join(base_dir, "ui/screens/mod01_authentication/login/LoginScreenViewModel.kt"), "w") as f:
    f.write(login_vm_kt)

# 5. OtpVerificationScreenScreen.kt
otp_screen_kt = """package com.interview.platform.ui.screens.mod01_authentication.otp_verification

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Verify OTP", style = MaterialTheme.typography.headlineMedium)
        Text("Sent to $email", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = otp,
            onValueChange = { otp = it },
            label = { Text("OTP Code") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        
        if (uiState is OtpVerificationScreenUiState.Error) {
            Text((uiState as OtpVerificationScreenUiState.Error).message, color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = { viewModel.verifyOtp(email, otp) },
            modifier = Modifier.fillMaxWidth(),
            enabled = uiState !is OtpVerificationScreenUiState.Loading && otp.isNotBlank()
        ) {
            if (uiState is OtpVerificationScreenUiState.Loading) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp))
            } else {
                Text("Verify")
            }
        }
    }
}
"""
with open(os.path.join(base_dir, "ui/screens/mod01_authentication/otp_verification/OtpVerificationScreenScreen.kt"), "w") as f:
    f.write(otp_screen_kt)

# 6. OtpVerificationScreenViewModel.kt
otp_vm_kt = """package com.interview.platform.ui.screens.mod01_authentication.otp_verification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtpVerificationScreenViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow<OtpVerificationScreenUiState>(OtpVerificationScreenUiState.Empty)
    val uiState: StateFlow<OtpVerificationScreenUiState> = _uiState.asStateFlow()
    
    fun verifyOtp(email: String, otp: String) {
        _uiState.value = OtpVerificationScreenUiState.Loading
        viewModelScope.launch {
            try {
                // Simulate network
                kotlinx.coroutines.delay(1000)
                _uiState.value = OtpVerificationScreenUiState.Success()
            } catch (e: Exception) {
                _uiState.value = OtpVerificationScreenUiState.Error("Invalid OTP")
            }
        }
    }
    
    fun resetState() {
        _uiState.value = OtpVerificationScreenUiState.Empty
    }
}
"""
with open(os.path.join(base_dir, "ui/screens/mod01_authentication/otp_verification/OtpVerificationScreenViewModel.kt"), "w") as f:
    f.write(otp_vm_kt)

# 7. Add Empty UI states
login_state_kt = """package com.interview.platform.ui.screens.mod01_authentication.login

sealed interface LoginScreenUiState {
    object Empty : LoginScreenUiState
    object Loading : LoginScreenUiState
    class Success() : LoginScreenUiState
    class Error(val message: String) : LoginScreenUiState
}
"""
with open(os.path.join(base_dir, "ui/screens/mod01_authentication/login/LoginScreenUiState.kt"), "w") as f:
    f.write(login_state_kt)

otp_state_kt = """package com.interview.platform.ui.screens.mod01_authentication.otp_verification

sealed interface OtpVerificationScreenUiState {
    object Empty : OtpVerificationScreenUiState
    object Loading : OtpVerificationScreenUiState
    class Success() : OtpVerificationScreenUiState
    class Error(val message: String) : OtpVerificationScreenUiState
}
"""
with open(os.path.join(base_dir, "ui/screens/mod01_authentication/otp_verification/OtpVerificationScreenUiState.kt"), "w") as f:
    f.write(otp_state_kt)

print("Android Auth components configured.")
