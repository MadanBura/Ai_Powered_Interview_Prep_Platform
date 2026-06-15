import os
import re

base_dir = "/Users/apple/AI-Powered Interview Preparation Platform/frontend-react-admin/src"
pages_dir = os.path.join(base_dir, "pages")

pages = []

for root, _, files in os.walk(pages_dir):
    for file in files:
        if file.endswith("Page.tsx"):
            if "auth" in root:
                continue
            
            filepath = os.path.join(root, file)
            # relative path from routes/index.tsx (which is in src/routes/)
            rel_path = os.path.relpath(filepath, os.path.join(base_dir, "routes"))
            # remove .tsx
            rel_path = rel_path[:-4]
            
            comp_name = file[:-4]
            
            route = comp_name.lower().replace("page", "")
            
            pages.append({
                "component": comp_name,
                "import_path": rel_path,
                "route": route
            })

# Update routes/index.tsx
routes_file = os.path.join(base_dir, "routes/index.tsx")

imports = "\n".join([f"import {{ {p['component']} }} from '{p['import_path']}';" for p in pages])
routes = "\n".join([f"  {{ path: '/{p['route']}', element: <ProtectedRoute><{p['component']} /></ProtectedRoute> }}," for p in pages])
links = "\n".join([f"      <li style={{margin: '8px 0'}}><Link to='/{p['route']}'>{p['component']}</Link></li>" for p in pages])

new_routes_tsx = f"""import React from 'react';
import {{ createBrowserRouter, RouterProvider, Navigate, Link }} from 'react-router-dom';
import {{ useAuth }} from '../contexts/AuthContext';
import {{ LoginPage }} from '../pages/auth/LoginPage';
import {{ OtpVerificationPage }} from '../pages/auth/OtpVerificationPage';

{imports}

const DashboardPage = () => {{
  const {{ logout }} = useAuth();
  return (
    <div style={{ padding: '20px', fontFamily: 'sans-serif' }}>
      <h1>Dashboard Hub</h1>
      <button onClick={{logout}} style={{ marginBottom: '20px' }}>Logout</button>
      <ul style={{ listStyleType: 'none', padding: 0 }}>
{links}
      </ul>
    </div>
  );
}};

const ProtectedRoute = ({{ children }}: {{ children: React.ReactNode }}) => {{
  const {{ isAuthenticated }} = useAuth();
  if (!isAuthenticated) return <Navigate to="/login" replace />;
  return <>{{children}}</>;
}};

const router = createBrowserRouter([
  {{ path: '/', element: <Navigate to="/dashboard" replace /> }},
  {{ path: '/login', element: <LoginPage /> }},
  {{ path: '/otp', element: <OtpVerificationPage /> }},
  {{ path: '/dashboard', element: <ProtectedRoute><DashboardPage /></ProtectedRoute> }},
{routes}
]);

export const AppRouter = () => {{
  return <RouterProvider router={{router}} />;
}};
"""

with open(routes_file, "w") as f:
    f.write(new_routes_tsx)

print("React Mass Integration Completed.")
