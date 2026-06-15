import os
import re

base_dir = "/Users/apple/AI-Powered Interview Preparation Platform/android-app/app/src/main/java/com/interview/platform"

# 1. Update AppNavHost.kt to wrap non-auth routes in a Scaffold with BottomAppBar
nav_host_path = os.path.join(base_dir, "ui/navigation/AppNavHost.kt")
with open(nav_host_path, "r") as f:
    nav_host_content = f.read()

# We need to inject the Scaffold and currentBackStackEntry dependencies.
# The easiest way is to rewrite the AppNavHost composable entirely using string manipulation.

if "Scaffold" not in nav_host_content:
    new_imports = """
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.navigation.compose.currentBackStackEntryAsState
"""
    nav_host_content = nav_host_content.replace("import androidx.navigation.compose.rememberNavController", "import androidx.navigation.compose.rememberNavController\n" + new_imports)
    
    app_nav_start = nav_host_content.find("fun AppNavHost(")
    app_nav_end = nav_host_content.rfind("}")
    
    # We will modify the function body
    old_body = nav_host_content[app_nav_start:app_nav_end+1]
    
    new_body = """fun AppNavHost(navController: NavHostController = rememberNavController()) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route
    val authRoutes = listOf("splash", "welcome", "login", "otp/{email}", "permissions")
    val showBottomBar = currentRoute != null && currentRoute !in authRoutes

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    tonalElevation = 8.dp
                ) {
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                        label = { Text("Home") },
                        selected = currentRoute == "home",
                        onClick = { navController.navigate("home") }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.List, contentDescription = "Interviews") },
                        label = { Text("Interviews") },
                        selected = currentRoute == "activesessions",
                        onClick = { navController.navigate("activesessions") }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                        label = { Text("Profile") },
                        selected = currentRoute == "profile",
                        onClick = { navController.navigate("profile") }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
                        label = { Text("Settings") },
                        selected = currentRoute == "interviewconfiguration",
                        onClick = { navController.navigate("interviewconfiguration") }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController, 
            startDestination = "splash",
            modifier = Modifier.padding(innerPadding).fillMaxSize()
        ) {
"""
    
    # We need to extract the NavHost body from the old content
    nav_host_body_start = old_body.find('NavHost(navController = navController, startDestination = "splash") {')
    nav_host_body = old_body[nav_host_body_start + len('NavHost(navController = navController, startDestination = "splash") {'):-1]
    
    # Replace the generic Dashboard with a high-fidelity Dashboard Hub
    dashboard_ui = """
        composable("home") {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text("Welcome back, Candidate", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurface)
                Text("Dashboard", style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.onBackground)
                Spacer(modifier = Modifier.height(24.dp))
                
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
                    shape = MaterialTheme.shapes.large
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        Text("Next Interview", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onPrimary)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Senior Android Engineer", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.onPrimary)
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { navController.navigate("activesessions") },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surface, contentColor = MaterialTheme.colorScheme.primary)
                        ) {
                            Text("Join Session")
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(32.dp))
                Text("Quick Actions", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onBackground)
                Spacer(modifier = Modifier.height(16.dp))
                
                // Inject the massive list of other modules strictly mapped via loop
"""
    
    # We will preserve the existing home_buttons mapped in the previous script by extracting it
    home_start = nav_host_body.find('composable("home") {')
    home_end = nav_host_body.find('composable("user_profile") {')
    if home_end == -1:
        home_end = nav_host_body.find('composable("profile") {') # Try alternative name
    
    # We will regex out the old home route and inject the new one
    old_home_route_match = re.search(r'composable\("home"\)\s*\{.*?\}(?=\s*composable\("[a-zA-Z0-9_]+"\))', nav_host_body, re.DOTALL)
    if old_home_route_match:
        old_home_route = old_home_route_match.group(0)
        
        # Extract the buttons
        buttons_match = re.findall(r'Button\(.*?\)\s*\{.*?\}', old_home_route, re.DOTALL)
        buttons_str = "\n                ".join(buttons_match)
        
        new_home_route = dashboard_ui + "                " + buttons_str + "\n            }\n        }"
        nav_host_body = nav_host_body.replace(old_home_route, new_home_route)
    
    full_new_function = new_body + nav_host_body + "    }\n}"
    
    nav_host_content = nav_host_content.replace(old_body, full_new_function)
    
    with open(nav_host_path, "w") as f:
        f.write(nav_host_content)

print("Android Phase 2 layout scripts completed successfully.")
