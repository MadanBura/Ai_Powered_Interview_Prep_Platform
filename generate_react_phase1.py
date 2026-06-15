import os

base_pkg = "/Users/apple/AI-Powered Interview Preparation Platform/frontend-react-admin/src"

folders = [
    "pages", "components", "layouts", "routes", "features", "hooks", "services", "store", "theme"
]

for folder in folders:
    os.makedirs(os.path.join(base_pkg, folder), exist_ok=True)

theme_file = """import { createTheme } from '@mui/material/styles';

export const theme = createTheme({
  palette: {
    mode: 'light',
    primary: {
      main: '#16A34A',
    },
    background: {
      default: '#F8FAFC',
      paper: '#FFFFFF',
    },
    success: {
      main: '#22C55E',
    },
    warning: {
      main: '#F59E0B',
    },
    error: {
      main: '#DC2626',
    },
  },
  typography: {
    fontFamily: '"Inter", "Roboto", "Helvetica", "Arial", sans-serif',
  },
});
"""

store_file = """import { configureStore } from '@reduxjs/toolkit';

export const store = configureStore({
  reducer: {
    // Reducers will be added here
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
"""

with open(os.path.join(base_pkg, "theme", "index.ts"), "w") as f:
    f.write(theme_file)

with open(os.path.join(base_pkg, "store", "index.ts"), "w") as f:
    f.write(store_file)

# Phase 1 Screens
phase1_screens = [
    ("auth", "Login"),
    ("auth", "OtpVerification"),
    ("auth", "ForgotPassword"),
    ("dashboard", "Dashboard"),
    ("users", "UserManagement"),
    ("users", "RoleManagement"),
    ("departments", "DepartmentManagement"),
    ("technologies", "TechnologyManagement"),
    ("experience", "ExperienceManagement"),
]

def generate_react_page(module_name, page_name):
    mod_path = os.path.join(base_pkg, "pages", module_name)
    os.makedirs(mod_path, exist_ok=True)
    
    page_content = f"""import React from 'react';
import {{ Box, Typography, Paper, CircularProgress }} from '@mui/material';

export const {page_name}Page: React.FC = () => {{
  // Placeholder for Loading/Error/Empty/Success states
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

for mod_name, page_name in phase1_screens:
    generate_react_page(mod_name, page_name)

print("Generated React Phase 1 Scaffold successfully.")
