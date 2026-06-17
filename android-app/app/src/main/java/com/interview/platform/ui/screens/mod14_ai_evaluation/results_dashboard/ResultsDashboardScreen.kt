//package com.interview.platform.ui.screens.mod14_ai_evaluation.results_dashboard
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.StrokeCap
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//
//// Stitch Design Tokens
//private val Primary = Color(0xFF006B2C)
//private val PrimaryContainer = Color(0xFF00873A)
//private val OnPrimary = Color.White
//private val OnPrimaryContainer = Color(0xFFF7FFF2)
//private val Background = Color(0xFFF7F9FB)
//private val Surface = Color(0xFFF7F9FB)
//private val SurfaceContainerLowest = Color(0xFFFFFFFF)
//private val SurfaceContainerHigh = Color(0xFFE6E8EA)
//private val SurfaceContainerHighest = Color(0xFFE0E3E5)
//private val OnSurface = Color(0xFF191C1E)
//private val OnSurfaceVariant = Color(0xFF3E4A3D)
//private val Secondary = Color(0xFF515F74)
//private val SecondaryContainer = Color(0xFFD5E3FC)
//private val Tertiary = Color(0xFFA72D51)
//private val OutlineVariant = Color(0xFFBDCABA)
//private val PrimaryFixedDim = Color(0xFF62DF7D)
//
//data class ScoreCategory(val label: String, val score: Int, val description: String, val color: Color, val icon: androidx.compose.ui.graphics.vector.ImageVector)
//data class Insight(val text: String, val positive: Boolean)
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ResultsDashboardScreen(
//    navController: NavController,
//    onNavigateBack: () -> Unit = {},
//    onViewDetailed: () -> Unit = {},
//    viewModel: ResultsDashboardScreenViewModel = androidx.hilt.navigation.compose.hiltViewModel()
//) {
//    val uiState by viewModel.uiState.collectAsState()
//
//    val scoreCategories = listOf(
//        ScoreCategory("Technical", 88, "Excellent grasp of system architecture and API design principles.", Primary, Icons.Default.Code),
//        ScoreCategory("Communication", 76, "Good clarity, but consider being more concise during complex explanations.", Tertiary, Icons.Default.Chat),
//        ScoreCategory("Confidence", 92, "Very strong executive presence and decisive answering style.", PrimaryContainer, Icons.Default.OfflineBolt)
//    )
//
//    val strengths = listOf(
//        "Structured approach to the STAR method in behavioral questions.",
//        "Articulating user-centered design decisions with data backup."
//    )
//
//    val growthAreas = listOf(
//        "Needs more depth on edge-case scenarios in technical architecture.",
//        "Pacing: Spent slightly too long on the initial introduction."
//    )
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Column {
//                        Text("Interview Results", style = MaterialTheme.typography.titleLarge.copy(
//                            fontWeight = FontWeight.SemiBold, color = OnSurface, fontSize = 20.sp
//                        ))
//                        Text("Senior Product Designer Mock", style = MaterialTheme.typography.bodySmall.copy(
//                            color = OnSurfaceVariant, fontSize = 12.sp
//                        ))
//                    }
//                },
//                navigationIcon = {
//                    IconButton(onClick = onNavigateBack) {
//                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = OnSurface)
//                    }
//                },
//                actions = {
//                    IconButton(onClick = {}) {
//                        Icon(Icons.Default.Search, contentDescription = "Search", tint = Secondary)
//                    }
//                    IconButton(onClick = {}) {
//                        Icon(Icons.Default.Notifications, contentDescription = "Notifications", tint = Secondary)
//                    }
//                },
//                colors = TopAppBarDefaults.topAppBarColors(containerColor = SurfaceContainerLowest)
//            )
//        },
//        containerColor = Background
//    ) { padding ->
//        if (uiState is ResultsDashboardScreenUiState.Empty) {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(padding)
//                    .padding(32.dp),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center
//            ) {
//                Icon(Icons.Default.Analytics, contentDescription = null, tint = OutlineVariant, modifier = Modifier.size(64.dp))
//                Spacer(modifier = Modifier.height(16.dp))
//                Text("No results available yet", style = MaterialTheme.typography.titleLarge, color = Secondary)
//                Spacer(modifier = Modifier.height(8.dp))
//                Text("Take an interview to generate your dashboard.", style = MaterialTheme.typography.bodyMedium, color = Secondary, textAlign = TextAlign.Center)
//            }
//        } else {
//            LazyColumn(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(padding)
//                    .padding(horizontal = 16.dp),
//                verticalArrangement = Arrangement.spacedBy(24.dp),
//                contentPadding = PaddingValues(top = 24.dp, bottom = 32.dp)
//            ) {
//                // Overall Score Ring — Stitch glass-card style
//                item {
//                    Card(
//                        modifier = Modifier.fillMaxWidth(),
//                        shape = RoundedCornerShape(12.dp),
//                        colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
//                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
//                    ) {
//                        Column(
//                            modifier = Modifier.padding(24.dp).fillMaxWidth(),
//                            horizontalAlignment = Alignment.CenterHorizontally,
//                            verticalArrangement = Arrangement.spacedBy(16.dp)
//                        ) {
//                            Text("Overall Score", style = MaterialTheme.typography.labelSmall.copy(
//                                color = OnSurfaceVariant, fontSize = 12.sp, letterSpacing = 1.sp,
//                                fontWeight = FontWeight.Bold
//                            ))
//
//                            // Score ring
//                            Box(modifier = Modifier.size(192.dp), contentAlignment = Alignment.Center) {
//                                CircularProgressIndicator(
//                                    progress = { 0.84f },
//                                    modifier = Modifier.fillMaxSize(),
//                                    color = Primary,
//                                    trackColor = SurfaceContainerHighest,
//                                    strokeWidth = 12.dp,
//                                    strokeCap = StrokeCap.Round
//                                )
//                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                                    Text("84%", style = MaterialTheme.typography.displaySmall.copy(
//                                        fontWeight = FontWeight.ExtraBold, color = Primary, fontSize = 48.sp
//                                    ))
//                                    Text("Strong Fit", style = MaterialTheme.typography.labelLarge.copy(
//                                        color = Secondary, fontSize = 14.sp
//                                    ))
//                                }
//                            }
//
//                            // Trending up badge
//                            Row(
//                                modifier = Modifier
//                                    .clip(RoundedCornerShape(50.dp))
//                                    .background(Primary.copy(alpha = 0.1f))
//                                    .padding(horizontal = 16.dp, vertical = 8.dp),
//                                verticalAlignment = Alignment.CenterVertically,
//                                horizontalArrangement = Arrangement.spacedBy(8.dp)
//                            ) {
//                                Icon(Icons.Default.TrendingUp, contentDescription = null, tint = Primary, modifier = Modifier.size(20.dp))
//                                Text("+5% from last attempt", style = MaterialTheme.typography.labelLarge.copy(
//                                    color = Primary, fontWeight = FontWeight.Bold, fontSize = 14.sp
//                                ))
//                            }
//                        }
//                    }
//                }
//
//                // Interview Rating Card
//                item {
//                    Card(
//                        modifier = Modifier.fillMaxWidth(),
//                        shape = RoundedCornerShape(12.dp),
//                        colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
//                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
//                    ) {
//                        Row(
//                            modifier = Modifier.padding(24.dp).fillMaxWidth(),
//                            horizontalArrangement = Arrangement.SpaceBetween,
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
//                                Text("Interview Rating", style = MaterialTheme.typography.titleMedium.copy(
//                                    color = OnSurface, fontWeight = FontWeight.SemiBold, fontSize = 20.sp
//                                ))
//                                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
//                                    Text("4.5", style = MaterialTheme.typography.headlineMedium.copy(
//                                        color = OnSurface, fontWeight = FontWeight.Bold
//                                    ))
//                                    repeat(4) {
//                                        Icon(Icons.Default.Star, contentDescription = null, tint = Primary, modifier = Modifier.size(20.dp))
//                                    }
//                                    Icon(Icons.Default.Star, contentDescription = null, tint = Primary, modifier = Modifier.size(20.dp))
//                                    Text("/ 5.0", style = MaterialTheme.typography.bodySmall.copy(color = OnSurfaceVariant))
//                                }
//                            }
//
//                            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
//                                OutlinedButton(
//                                    onClick = {},
//                                    modifier = Modifier.height(40.dp),
//                                    shape = RoundedCornerShape(8.dp),
//                                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Secondary)
//                                ) {
//                                    Text("View Recording", fontSize = 13.sp, fontFamily = androidx.compose.ui.text.font.FontFamily.Default)
//                                }
//                                Button(
//                                    onClick = onViewDetailed,
//                                    modifier = Modifier.height(40.dp),
//                                    shape = RoundedCornerShape(8.dp),
//                                    colors = ButtonDefaults.buttonColors(containerColor = Primary)
//                                ) {
//                                    Text("Download PDF", color = OnPrimary, fontSize = 13.sp)
//                                }
//                            }
//                        }
//                    }
//                }
//
//                // Score category cards — border-left colored
//                scoreCategories.forEach { cat ->
//                    item {
//                        Card(
//                            modifier = Modifier.fillMaxWidth(),
//                            shape = RoundedCornerShape(12.dp),
//                            colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
//                            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
//                        ) {
//                            Row(modifier = Modifier.fillMaxWidth()) {
//                                // Color accent left border
//                                Box(modifier = Modifier.width(4.dp).fillMaxHeight().background(cat.color))
//                                Column(modifier = Modifier.padding(16.dp).weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
//                                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
//                                        Icon(cat.icon, contentDescription = null, tint = OnSurfaceVariant, modifier = Modifier.size(24.dp))
//                                        Text("${cat.score}%", style = MaterialTheme.typography.titleMedium.copy(
//                                            color = cat.color, fontWeight = FontWeight.Bold, fontSize = 20.sp
//                                        ))
//                                    }
//                                    Text(cat.label, style = MaterialTheme.typography.titleSmall.copy(
//                                        color = OnSurface, fontWeight = FontWeight.SemiBold, fontSize = 18.sp
//                                    ))
//                                    Text(cat.description, style = MaterialTheme.typography.bodySmall.copy(
//                                        color = OnSurfaceVariant, fontSize = 12.sp
//                                    ))
//                                    // Progress bar
//                                    Box(
//                                        modifier = Modifier.fillMaxWidth().height(6.dp).clip(CircleShape).background(SurfaceContainerHighest)
//                                    ) {
//                                        Box(
//                                            modifier = Modifier.fillMaxHeight()
//                                                .fillMaxWidth(cat.score / 100f)
//                                                .clip(CircleShape)
//                                                .background(cat.color)
//                                        )
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//
//                // Key Insights — strengths + growth areas
//                item {
//                    Card(
//                        modifier = Modifier.fillMaxWidth(),
//                        shape = RoundedCornerShape(12.dp),
//                        colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
//                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
//                    ) {
//                        Column(modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
//                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
//                                Text("Key Insights", style = MaterialTheme.typography.titleLarge.copy(
//                                    fontWeight = FontWeight.SemiBold, color = OnSurface, fontSize = 24.sp
//                                ))
//                                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
//                                    listOf("BEHAVIORAL", "SYSTEM DESIGN").forEach { tag ->
//                                        Box(
//                                            modifier = Modifier.clip(RoundedCornerShape(50.dp)).background(SurfaceContainerHigh).padding(horizontal = 12.dp, vertical = 4.dp)
//                                        ) {
//                                            Text(tag, style = MaterialTheme.typography.labelSmall.copy(
//                                                color = OnSurfaceVariant, fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.5.sp
//                                            ))
//                                        }
//                                    }
//                                }
//                            }
//
//                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
//                                // Strengths
//                                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(12.dp)) {
//                                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
//                                        Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Primary, modifier = Modifier.size(20.dp))
//                                        Text("Strengths", style = MaterialTheme.typography.titleSmall.copy(
//                                            color = Primary, fontWeight = FontWeight.SemiBold
//                                        ))
//                                    }
//                                    strengths.forEach { s ->
//                                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
//                                            Box(modifier = Modifier.size(24.dp).clip(RoundedCornerShape(4.dp)).background(Primary.copy(alpha = 0.1f)), contentAlignment = Alignment.Center) {
//                                                Icon(Icons.Default.Done, contentDescription = null, tint = Primary, modifier = Modifier.size(14.dp))
//                                            }
//                                            Text(s, style = MaterialTheme.typography.bodySmall.copy(color = OnSurface, fontSize = 12.sp))
//                                        }
//                                    }
//                                }
//                                // Growth Areas
//                                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(12.dp)) {
//                                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
//                                        Icon(Icons.Default.Warning, contentDescription = null, tint = Tertiary, modifier = Modifier.size(20.dp))
//                                        Text("Areas for Growth", style = MaterialTheme.typography.titleSmall.copy(
//                                            color = Tertiary, fontWeight = FontWeight.SemiBold
//                                        ))
//                                    }
//                                    growthAreas.forEach { g ->
//                                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
//                                            Box(modifier = Modifier.size(24.dp).clip(RoundedCornerShape(4.dp)).background(Tertiary.copy(alpha = 0.1f)), contentAlignment = Alignment.Center) {
//                                                Icon(Icons.Default.Warning, contentDescription = null, tint = Tertiary, modifier = Modifier.size(14.dp))
//                                            }
//                                            Text(g, style = MaterialTheme.typography.bodySmall.copy(color = OnSurface, fontSize = 12.sp))
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
