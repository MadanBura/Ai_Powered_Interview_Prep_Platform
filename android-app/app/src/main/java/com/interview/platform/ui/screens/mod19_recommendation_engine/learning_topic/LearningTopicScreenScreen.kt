package com.interview.platform.ui.screens.mod19_recommendation_engine.learning_topic

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.interview.platform.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearningTopicScreen(
    roadmapId: String,
    topicName: String,
    onNavigateBack: () -> Unit,
    viewModel: LearningTopicScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(roadmapId, topicName) {
        viewModel.loadTopicDetails(roadmapId, topicName)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Learning Topic", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PrepAiSurfaceContainerLowest)
            )
        },
        containerColor = PrepAiBackground
    ) { padding ->
        when (val state = uiState) {
            is LearningTopicScreenUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = PrepAiPrimary)
                }
            }
            is LearningTopicScreenUiState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(state.message, color = PrepAiError)
                }
            }
            is LearningTopicScreenUiState.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(24.dp)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Text(state.title, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, color = PrepAiPrimary)

                    // Content Card
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = PrepAiSurfaceContainerLowest),
                        border = BorderStroke(1.dp, PrepAiOutlineVariant)
                    ) {
                        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                            state.topicDetails?.subtopics?.let {
                                if (it.isNotEmpty()) {
                                    Text("Subtopics", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = PrepAiPrimary)
                                    it.forEach { sub -> Text("• $sub") }
                                }
                            }
                            
                            state.topicDetails?.codeSnippet?.let { snippet ->
                                if (snippet.isNotBlank()) {
                                    Text("Code Snippet", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = PrepAiPrimary)
                                    Box(modifier = Modifier.fillMaxWidth().background(Color(0xFF2B2B2B), RoundedCornerShape(8.dp)).padding(12.dp)) {
                                        Text(snippet, color = Color(0xFFA9B7C6), fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace)
                                    }
                                }
                            }

                            Row(horizontalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxWidth()) {
                                state.topicDetails?.pros?.let { pros ->
                                    if (pros.isNotEmpty()) {
                                        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                            Text("Pros", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color(0xFF4CAF50))
                                            pros.forEach { pro -> Text("• $pro", color = Color(0xFF81C784)) }
                                        }
                                    }
                                }
                                state.topicDetails?.cons?.let { cons ->
                                    if (cons.isNotEmpty()) {
                                        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                            Text("Cons", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color(0xFFE53935))
                                            cons.forEach { con -> Text("• $con", color = Color(0xFFE57373)) }
                                        }
                                    }
                                }
                            }

                            state.topicDetails?.useCases?.let {
                                if (it.isNotEmpty()) {
                                    Text("Use Cases", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = PrepAiPrimary)
                                    it.forEach { case -> Text("• $case") }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Button(
                        onClick = { viewModel.completeTopic(onSuccess = onNavigateBack) },
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrepAiPrimary),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Icon(Icons.Default.Check, contentDescription = null)
                            Text("Mark as Completed", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}
