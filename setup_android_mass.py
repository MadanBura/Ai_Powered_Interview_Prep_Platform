import os
import re

base_dir = "/Users/apple/AI-Powered Interview Preparation Platform/android-app/app/src/main/java/com/interview/platform"
screens_dir = os.path.join(base_dir, "ui/screens")

screens = []
# Find all screen files
for root, _, files in os.walk(screens_dir):
    for file in files:
        if file.endswith("Screen.kt"):
            # Exclude auth since we already wired it uniquely
            if "mod01_authentication" in root:
                continue
            
            filepath = os.path.join(root, file)
            # Find the package name
            with open(filepath, "r") as f:
                content = f.read()
            
            pkg_match = re.search(r"^package\s+([a-zA-Z0-9_.]+)", content, re.MULTILINE)
            comp_match = re.search(r"@Composable\s+fun\s+([a-zA-Z0-9_]+)\s*\(", content)
            
            if pkg_match and comp_match:
                pkg = pkg_match.group(1)
                comp = comp_match.group(1)
                route = comp.lower().replace("screen", "")
                if route == "":
                    route = comp.lower()
                screens.append({
                    "package": pkg,
                    "composable": comp,
                    "route": route
                })

# Generate AppNavHost.kt
nav_host_imports = "\n".join([f"import {s['package']}.{s['composable']}" for s in screens])

# Auth screens are still needed in the AppNavHost
auth_imports = """
import com.interview.platform.ui.screens.mod01_authentication.splash.SplashScreenScreen
import com.interview.platform.ui.screens.mod01_authentication.welcome.WelcomeScreenScreen
import com.interview.platform.ui.screens.mod01_authentication.login.LoginScreenScreen
import com.interview.platform.ui.screens.mod01_authentication.otp_verification.OtpVerificationScreenScreen
import com.interview.platform.ui.screens.mod01_authentication.permissions.PermissionsScreenScreen
"""

routes_composable = "\n".join([f"""        composable("{s['route']}") {{
            {s['composable']}(onNavigateForward = {{ navController.navigate("home") }})
        }}""" for s in screens])

home_buttons = "\n".join([f"""            Button(onClick = {{ navController.navigate("{s['route']}") }}, modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {{ Text("{s['composable']}") }}""" for s in screens])

nav_host_kt = f"""package com.interview.platform.ui.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
{auth_imports}
{nav_host_imports}

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {{
    NavHost(navController = navController, startDestination = "splash") {{
        composable("splash") {{
            SplashScreenScreen(
                onNavigateForward = {{
                    navController.navigate("welcome") {{
                        popUpTo("splash") {{ inclusive = true }}
                    }}
                }}
            )
        }}
        composable("welcome") {{
            WelcomeScreen(
                onNavigateForward = {{
                    navController.navigate("login") {{
                        popUpTo("welcome") {{ inclusive = true }}
                    }}
                }}
            )
        }}
        composable("login") {{
            LoginScreenScreen(
                onNavigateToOtp = {{ email ->
                    navController.navigate("otp/$email")
                }}
            )
        }}
        composable("otp/{{email}}") {{ backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            OtpVerificationScreenScreen(
                email = email,
                onNavigateToHome = {{
                    navController.navigate("permissions") {{
                        popUpTo("login") {{ inclusive = true }}
                    }}
                }}
            )
        }}
        composable("permissions") {{
            PermissionsScreenScreen(
                onNavigateForward = {{
                    navController.navigate("home") {{
                        popUpTo("permissions") {{ inclusive = true }}
                    }}
                }}
            )
        }}
        composable("home") {{
            Column(modifier = Modifier.fillMaxSize().padding(16.dp).verticalScroll(rememberScrollState())) {{
                Text("Dashboard Hub", style = androidx.compose.material3.MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(16.dp))
{home_buttons}
            }}
        }}
{routes_composable}
    }}
}}
"""

os.makedirs(os.path.join(base_dir, "ui/navigation"), exist_ok=True)
with open(os.path.join(base_dir, "ui/navigation/AppNavHost.kt"), "w") as f:
    f.write(nav_host_kt)

# Also update the ViewModels to transition out of Empty state to Success so the screens actually render
# the "Continue" button
for root, _, files in os.walk(screens_dir):
    for file in files:
        if file.endswith("ViewModel.kt") and "mod01_authentication" not in root:
            filepath = os.path.join(root, file)
            with open(filepath, "r") as f:
                content = f.read()
            
            # replace Empty with Success()
            new_content = re.sub(r"MutableStateFlow<([^>]+)>\([^.]+\.Empty\)", r"MutableStateFlow<\1>(\1.Success())", content)
            
            if new_content != content:
                with open(filepath, "w") as f:
                    f.write(new_content)

print("Android Mass Integration Completed.")
