import os
import re

react_dir = "/Users/apple/AI-Powered Interview Preparation Platform/frontend-react-admin/src/pages"

# We will recursively update every page component to match the MUI Enterprise Grid specs.
react_component_template = """import React from 'react';
import { Box, Typography, Card, CardContent, Grid, Button, Breadcrumbs, Link, Divider } from '@mui/material';
import { Analytics, Add as AddIcon, Refresh as RefreshIcon } from '@mui/icons-material';

export const {component_name} = () => {
  return (
    <Box sx={{ p: { xs: 2, md: 4 } }}>
      <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'flex-start', mb: 3 }}>
        <Box>
          <Breadcrumbs aria-label="breadcrumb" sx={{ mb: 1 }}>
            <Link underline="hover" color="inherit" href="/dashboard_hub">Home</Link>
            <Typography color="text.primary">{title}</Typography>
          </Breadcrumbs>
          <Typography variant="h4" fontWeight="bold" color="text.primary">
            {title}
          </Typography>
        </Box>
        <Box sx={{ display: 'flex', gap: 2 }}>
          <Button variant="outlined" startIcon={<RefreshIcon />} color="secondary">Refresh</Button>
          <Button variant="contained" startIcon={<AddIcon />}>Create New</Button>
        </Box>
      </Box>

      <Grid container spacing={3}>
        <Grid item xs={12} md={8}>
          <Card sx={{ height: '100%', minHeight: 400 }}>
            <CardContent>
              <Typography variant="h6" fontWeight="bold" gutterBottom>Data Metrics Overview</Typography>
              <Divider sx={{ mb: 2 }} />
              <Box sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center', height: 300, color: 'text.secondary' }}>
                <Analytics sx={{ fontSize: 64, mb: 2, color: 'primary.light', opacity: 0.5 }} />
                <Typography variant="body1">No active data rows found for this module.</Typography>
                <Button variant="text" sx={{ mt: 1 }}>Sync Database</Button>
              </Box>
            </CardContent>
          </Card>
        </Grid>
        
        <Grid item xs={12} md={4}>
          <Card sx={{ height: '100%' }}>
            <CardContent>
              <Typography variant="h6" fontWeight="bold" gutterBottom>System Actions</Typography>
              <Divider sx={{ mb: 2 }} />
              <Typography variant="body2" color="text.secondary" paragraph>
                Use the actions panel to configure the parameters for the {title} module. Ensure dependencies are resolved before dispatching new instances.
              </Typography>
              <Button variant="outlined" fullWidth sx={{ mb: 2 }}>Export CSV</Button>
              <Button variant="outlined" fullWidth color="error">Purge Cache</Button>
            </CardContent>
          </Card>
        </Grid>
      </Grid>
    </Box>
  );
};
"""

for root, _, files in os.walk(react_dir):
    for file in files:
        if file.endswith(".tsx") and "mod01_authentication" not in root and file != "DashboardPage.tsx":
            filepath = os.path.join(root, file)
            comp_name = file.replace(".tsx", "")
            title = re.sub(r'([A-Z])', r' \1', comp_name).strip().replace("Page", "")
            
            new_content = react_component_template.replace("{component_name}", comp_name).replace("{title}", title)
            
            with open(filepath, "w") as f:
                f.write(new_content)

print("React Phase 3 UI overrides complete.")
