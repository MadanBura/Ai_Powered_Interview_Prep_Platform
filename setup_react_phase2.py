import os
import re

react_dir = "/Users/apple/AI-Powered Interview Preparation Platform/frontend-react-admin"

# 1. Update MUI Theme
theme_dir = os.path.join(react_dir, "src/theme")
os.makedirs(theme_dir, exist_ok=True)
theme_ts = """import { createTheme } from '@mui/material/styles';

export const theme = createTheme({
  palette: {
    primary: { main: '#16A34A', contrastText: '#FFFFFF' },
    secondary: { main: '#475569', contrastText: '#FFFFFF' },
    background: { default: '#F8FAFC', paper: '#FFFFFF' },
    success: { main: '#22C55E' },
    warning: { main: '#F59E0B' },
    error: { main: '#DC2626' },
  },
  typography: {
    fontFamily: '"Inter", "Roboto", "Helvetica", "Arial", sans-serif',
    h1: { fontWeight: 700 },
    h2: { fontWeight: 700 },
    h3: { fontWeight: 600 },
    h4: { fontWeight: 600 },
    h5: { fontWeight: 600 },
    h6: { fontWeight: 600 },
  },
  components: {
    MuiButton: {
      styleOverrides: { root: { textTransform: 'none', borderRadius: 8, padding: '10px 24px' } },
    },
    MuiCard: {
      styleOverrides: { root: { borderRadius: 12, boxShadow: '0px 4px 20px rgba(0, 0, 0, 0.05)' } },
    },
    MuiOutlinedInput: {
      styleOverrides: { root: { borderRadius: 8 } },
    },
  },
});
"""
with open(os.path.join(theme_dir, "index.ts"), "w") as f:
    f.write(theme_ts)

# 2. Build AdminLayout.tsx
layout_dir = os.path.join(react_dir, "src/components/layout")
os.makedirs(layout_dir, exist_ok=True)
layout_tsx = """import React, { useState } from 'react';
import { Box, Drawer, AppBar, Toolbar, Typography, List, ListItem, ListItemButton, ListItemIcon, ListItemText, IconButton, Avatar, Divider, CssBaseline, Collapse } from '@mui/material';
import { Menu as MenuIcon, Dashboard, People, Analytics, Settings, Work, Help, Assessment, ExitToApp, ExpandLess, ExpandMore } from '@mui/icons-material';
import { useNavigate, useLocation } from 'react-router-dom';
import { useAuth } from '../../store/AuthContext';

const drawerWidth = 280;

export const AdminLayout = ({ children }: { children: React.ReactNode }) => {
  const [mobileOpen, setMobileOpen] = useState(false);
  const [openConfig, setOpenConfig] = useState(false);
  const { logout } = useAuth();
  const navigate = useNavigate();
  const location = useLocation();

  const menuItems = [
    { title: 'Dashboard Hub', path: '/dashboard_hub', icon: <Dashboard /> },
    { title: 'Candidates & Users', path: '/usermanagement', icon: <People /> },
    { title: 'Interview Sessions', path: '/activesessions', icon: <Work /> },
    { title: 'Questions & Prompts', path: '/questiondashboard', icon: <Help /> },
    { title: 'Evaluations & Scoring', path: '/evaluationqueue', icon: <Assessment /> },
    { title: 'Analytics & Reports', path: '/executivedashboard', icon: <Analytics /> },
  ];

  const configItems = [
    { title: 'Departments', path: '/departmentmanagement' },
    { title: 'Roles', path: '/rolemanagement' },
    { title: 'Technologies', path: '/technologymanagement' },
    { title: 'Interview Config', path: '/interviewconfiguration' },
    { title: 'System Logs', path: '/auditdashboard' },
  ];

  const drawer = (
    <Box sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}>
      <Box sx={{ p: 3, display: 'flex', alignItems: 'center', gap: 2 }}>
        <Box sx={{ width: 32, height: 32, borderRadius: 1, bgcolor: 'primary.main', display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
          <Typography variant="subtitle1" fontWeight="bold" color="white">AI</Typography>
        </Box>
        <Typography variant="h6" fontWeight="bold" color="primary">Stitch Admin</Typography>
      </Box>
      <Divider />
      <List sx={{ px: 2, flexGrow: 1 }}>
        {menuItems.map((item) => (
          <ListItem key={item.title} disablePadding sx={{ mb: 0.5 }}>
            <ListItemButton
              selected={location.pathname === item.path}
              onClick={() => navigate(item.path)}
              sx={{ borderRadius: 2 }}
            >
              <ListItemIcon sx={{ minWidth: 40, color: location.pathname === item.path ? 'primary.main' : 'text.secondary' }}>
                {item.icon}
              </ListItemIcon>
              <ListItemText primary={item.title} primaryTypographyProps={{ fontWeight: location.pathname === item.path ? 600 : 500 }} />
            </ListItemButton>
          </ListItem>
        ))}
        
        <ListItem disablePadding sx={{ mb: 0.5 }}>
          <ListItemButton onClick={() => setOpenConfig(!openConfig)} sx={{ borderRadius: 2 }}>
            <ListItemIcon sx={{ minWidth: 40 }}><Settings /></ListItemIcon>
            <ListItemText primary="Configuration" primaryTypographyProps={{ fontWeight: 500 }} />
            {openConfig ? <ExpandLess /> : <ExpandMore />}
          </ListItemButton>
        </ListItem>
        <Collapse in={openConfig} timeout="auto" unmountOnExit>
          <List component="div" disablePadding sx={{ pl: 4 }}>
            {configItems.map((item) => (
              <ListItemButton key={item.title} onClick={() => navigate(item.path)} selected={location.pathname === item.path} sx={{ borderRadius: 2, mb: 0.5 }}>
                <ListItemText primary={item.title} primaryTypographyProps={{ variant: 'body2' }} />
              </ListItemButton>
            ))}
          </List>
        </Collapse>
      </List>
      <Divider />
      <Box sx={{ p: 2 }}>
        <ListItemButton onClick={logout} sx={{ borderRadius: 2, color: 'error.main' }}>
          <ListItemIcon sx={{ minWidth: 40, color: 'error.main' }}><ExitToApp /></ListItemIcon>
          <ListItemText primary="Logout" />
        </ListItemButton>
      </Box>
    </Box>
  );

  return (
    <Box sx={{ display: 'flex', minHeight: '100vh', bgcolor: 'background.default' }}>
      <CssBaseline />
      <AppBar position="fixed" sx={{ width: { sm: `calc(100% - ${drawerWidth}px)` }, ml: { sm: `${drawerWidth}px` }, bgcolor: 'surface.main', boxShadow: 'none', borderBottom: '1px solid', borderColor: 'divider' }}>
        <Toolbar>
          <IconButton color="inherit" edge="start" onClick={() => setMobileOpen(!mobileOpen)} sx={{ mr: 2, display: { sm: 'none' }, color: 'text.primary' }}>
            <MenuIcon />
          </IconButton>
          <Box sx={{ flexGrow: 1 }} />
          <Avatar sx={{ bgcolor: 'secondary.main', width: 36, height: 36 }}>A</Avatar>
        </Toolbar>
      </AppBar>
      <Box component="nav" sx={{ width: { sm: drawerWidth }, flexShrink: { sm: 0 } }}>
        <Drawer variant="temporary" open={mobileOpen} onClose={() => setMobileOpen(false)} ModalProps={{ keepMounted: true }} sx={{ display: { xs: 'block', sm: 'none' }, '& .MuiDrawer-paper': { boxSizing: 'border-box', width: drawerWidth, borderRight: 'none', boxShadow: 3 } }}>
          {drawer}
        </Drawer>
        <Drawer variant="permanent" sx={{ display: { xs: 'none', sm: 'block' }, '& .MuiDrawer-paper': { boxSizing: 'border-box', width: drawerWidth, borderRight: '1px solid', borderColor: 'divider' } }} open>
          {drawer}
        </Drawer>
      </Box>
      <Box component="main" sx={{ flexGrow: 1, p: 3, width: { sm: `calc(100% - ${drawerWidth}px)` } }}>
        <Toolbar />
        {children}
      </Box>
    </Box>
  );
};
"""
with open(os.path.join(layout_dir, "AdminLayout.tsx"), "w") as f:
    f.write(layout_tsx)

# 3. Inject AdminLayout into routes/index.tsx
routes_file = os.path.join(react_dir, "src/routes/index.tsx")
with open(routes_file, "r") as f:
    routes_content = f.read()

# Instead of rewriting the entire routes array, we will just wrap the ProtectedRoute content in AdminLayout
if "AdminLayout" not in routes_content:
    import_layout = "import { AdminLayout } from '../components/layout/AdminLayout';\n"
    routes_content = import_layout + routes_content
    
    # Update ProtectedRoute definition
    old_protected = "return <>{children}</>;"
    new_protected = "return <AdminLayout>{children}</AdminLayout>;"
    routes_content = routes_content.replace(old_protected, new_protected)
    
    with open(routes_file, "w") as f:
        f.write(routes_content)

print("React Phase 2 layout scripts completed successfully.")
