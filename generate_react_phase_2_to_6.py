import os

base_pkg = "/Users/apple/AI-Powered Interview Preparation Platform/frontend-react-admin/src"

# Phase 2 to 6 Screens
screens = [
    # Phase 2
    ("questions", "QuestionDashboard"),
    ("questions", "QuestionRepository"),
    ("questions", "CreateQuestion"),
    ("questions", "EditQuestion"),
    ("questions", "QuestionPreview"),
    ("content", "BulkUploadCenter"),
    ("content", "ExtractionResults"),
    ("content", "CategorizationDashboard"),
    ("content", "FileStorage"),
    ("content", "SearchDashboard"),

    # Phase 3
    ("interviews", "InterviewConfiguration"),
    ("interviews", "InterviewTemplates"),
    ("interviews", "SessionMonitoring"),
    ("interviews", "ActiveSessions"),
    ("interviews", "SessionReplay"),

    # Phase 4
    ("ai", "PromptDashboard"),
    ("ai", "PromptLibrary"),
    ("ai", "PromptEditor"),
    ("ai", "PromptVersionHistory"),
    ("ai", "PromptTestingConsole"),
    ("ai", "AiEvaluationDashboard"),
    ("ai", "EvaluationQueue"),
    ("ai", "FeedbackAnalytics"),
    ("ai", "RecommendationDashboard"),

    # Phase 5
    ("analytics", "ExecutiveDashboard"),
    ("analytics", "OperationalDashboard"),
    ("analytics", "AiPerformanceDashboard"),
    ("analytics", "CandidateAnalytics"),
    ("analytics", "TechnologyAnalytics"),
    ("analytics", "SkillGapAnalytics"),
    ("analytics", "TrendAnalytics"),
    ("analytics", "ReportCenter"),
    ("analytics", "ScheduledReports"),
    ("analytics", "ExportReports"),

    # Phase 6
    ("notifications", "NotificationCenter"),
    ("notifications", "EmailTemplates"),
    ("notifications", "PushNotifications"),
    ("notifications", "NotificationHistory"),
    ("audit", "AuditDashboard"),
    ("audit", "UserActivityLogs"),
    ("audit", "SecurityLogs"),
    ("audit", "ConfigurationChangeLogs"),
]

def generate_react_page(module_name, page_name):
    mod_path = os.path.join(base_pkg, "pages", module_name)
    os.makedirs(mod_path, exist_ok=True)
    
    page_content = f"""import React from 'react';
import {{ Box, Typography, Paper, CircularProgress }} from '@mui/material';

export const {page_name}Page: React.FC = () => {{
  const status = 'success'; 

  if (status === 'loading') return <CircularProgress />;
  if (status === 'error') return <Typography color="error">Error loading data</Typography>;

  return (
    <Box p={{3}}>
      <Paper elevation={{1}} sx={{ p: 3 }}>
        <Typography variant="h4" color="primary" gutterBottom>
          {page_name}
        </Typography>
        <Typography variant="body1">
          {page_name} implementation placeholder. This screen will contain DataGrids, Forms, or Analytics based on requirements.
        </Typography>
      </Paper>
    </Box>
  );
}};
"""
    with open(os.path.join(mod_path, f"{page_name}Page.tsx"), "w") as f:
        f.write(page_content)

for mod_name, page_name in screens:
    generate_react_page(mod_name, page_name)

print("Generated React Phases 2 to 6 Scaffold successfully.")
