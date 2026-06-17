# Prompt Log - React Admin Portal (Cross-Project)

## AI-Powered Interview Preparation and Assessment Platform — Enterprise Web Admin Portal.

---

## PROMPT 1 — Initialize Prompt Logger

**Output File:** `doc/Prompt_Log_CrossProject.md`

---
### Prompt Content (Verbatim)

```text
I am starting the AI-Powered Interview Preparation and Assessment Platform project. 
Please initialize the prompt logger for this project by reading and following the global instructions located at `/Users/apple/.gemini/config/skills/prompt-logger.md`

Establish the Role:
- Act as the AI Prompt Auditor & SDLC Log Custodian.
- Create the `doc/Prompt_Log_CrossProject.md` file in the workspace root.
- Initialize it for the project: "AI-Powered Interview Preparation and Assessment Platform (React Admin Portal)".
- Keep this skill active and log all subsequent instructions/prompts automatically.
```

---

## PROMPT 2 — Generate React Admin Portal UI

**Output File:** `frontend-react-admin_new/src/*`

---
### Prompt Content (Verbatim)

```text
## System Role

You are a Principal Frontend Architect, React Technical Lead, Enterprise UX Designer, and Material UI Expert.

@[doc/frontend_web_persona.md] @[Project_Context.md] 

## Task

Generate a complete Enterprise Admin Portal UI for the AI-Powered Interview Preparation & Assessment Platform in the `frontend-react-admin_new` folder.

Generate screens phase-by-phase.

Use Stitch MCP Design System as the source of truth to fetch UI/Figma screens for web.
```
*Note: Fetched UI design specifications and component boundaries directly from Stitch MCP screens and implemented layout modules (MUI, TypeScript, React router) under frontend-react-admin_new.*

---

## PROMPT 3 — React Backend Integration

**Output File:** `frontend-react-admin_new/src/features/*`

---
### Prompt Content (Verbatim)

```text
## System Role

You are a Principal Frontend Architect, React Technical Lead, and API Integration Specialist.

## Task

Implement the complete API Integration Layer for the React Admin Portal in the `frontend-react-admin_new` folder.
```
*Note: Structured Axios services, query hooks, and Redux Toolkit slices mapped against the Spring Boot REST endpoints.*

---

## PROMPT 4 — Resolve Spring Boot CORS Configuration Issue

**Output File:** `backend/src/main/java/com/interview/platform/config/SecurityConfig.java`

---
### Prompt Content (Verbatim)

```text
System Role: Principal Java Backend Engineer

Task: Fix frontend API integration failures resulting from missing CORS configuration in the Spring Boot backend. 

Requirements:
- Analyze `SecurityConfig.java` to identify why OPTIONS preflight requests fail.
- Implement a `CorsConfigurationSource` bean allowing methods (GET, POST, PUT, DELETE, OPTIONS) and local origins.
- Fix 401/403 unauthorized errors blocking API CRUD operations.
```
*Note: Required to enable the React admin client on localhost:5173 to communicate with the Spring Boot API.*

---

## PROMPT 5 — Fix Backend Form Validation and Missing CRUD Logic

**Output File:** `backend/src/main/java/com/interview/platform/mod03_department/DepartmentController.java`

---
### Prompt Content (Verbatim)

```text
System Role: Senior Full Stack Engineer

Task: Fix the bug where creating a department fails silently because the frontend bypasses HTML form validation and the backend accepts empty fields.

Requirements:
- Add strict validation constraints to backend DTOs (e.g., `@NotBlank`).
- Ensure frontend `onSubmit` handlers correctly trigger validation before hitting the Spring Boot endpoints.
- Ensure all department creation logic flows seamlessly from client to database.
```
*Note: Resolved silent creation failures on the React department administration screen.*

---

## PROMPT 6 — Fix User Dashboard Username showing as "Unknown"

**Output File:** `frontend-react-admin_new/src/pages/Dashboard/UserDashboard.tsx`

---
### Prompt Content (Verbatim)

```text
System Role: React Frontend Developer & MUI Expert

Task: Fix user dashboard screen where the username is displaying as "Unknown".

Requirements:
- Investigate `UserDashboard.tsx` state mapping. Ensure it reads the user information from the Auth context/state.
- If user data is null or empty, display a loading skeleton and fetch the user profile from the backend GET `/api/v1/users/profile` endpoint.
- Ensure the name defaults to the user's email prefix if the display name is empty, instead of showing "Unknown".
```
*Note: Corrected context consumption and API fallback logic to display correct name on load.*

---

## PROMPT 7 — Add Padding to Interview Configuration and Interview Template Screens

**Output File:** `frontend-react-admin_new/src/pages/Interview/InterviewConfigScreen.tsx`, `frontend-react-admin_new/src/pages/Interview/InterviewTemplateScreen.tsx`

---
### Prompt Content (Verbatim)

```text
System Role: Enterprise UX/UI React Designer

Task: Fix layout issues by adding proper padding and spacing to the Interview Configuration and Interview Template screens.

Requirements:
- Adjust parent grid containers in `InterviewConfigScreen.tsx` and `InterviewTemplateScreen.tsx`.
- Apply consistent padding (padding: theme.spacing(3) or 24px) around content areas.
- Ensure responsiveness: on mobile view, adjust padding to theme.spacing(2) and verify there is no horizontal layout overflow.
```
*Note: Cleaned up screen layout spacing to resolve content hugging edge-to-edge on tablet breakpoints.*

---

## PROMPT 8 — Fix User Management Username showing as "Unknown"

**Output File:** `frontend-react-admin_new/src/pages/UserManagement/UserManagement.tsx`

---
### Prompt Content (Verbatim)

```text
System Role: Senior Frontend Engineer

Task: Fix the user list table in the User Management screen where usernames are showing as "Unknown" even after login and profile setup.

Requirements:
- Open `UserManagement.tsx` and check the table columns mapping.
- Ensure the accessor field maps to `username` or `fullName` depending on the backend API schema.
- Add fallback checks: if `row.original.fullName` is not present, check `row.original.username` or `row.original.email`.
```
*Note: Modified datagrid row cell formatting to ensure the table displays names correctly.*

---

## PROMPT 9 — Fix Department Creation Modal Layout Overlap

**Output File:** `frontend-react-admin_new/src/components/Modals/DepartmentCreationModal.tsx`

---
### Prompt Content (Verbatim)

```text
System Role: MUI Component Developer

Task: Fix layout overlap in the Department Creation Dialog/Modal.

Requirements:
- Adjust margins and paddings for the DialogContent and DialogActions components.
- Ensure validation error messages under text inputs do not shift components or overlap with the action buttons.
- Limit max-width of the dialog to `sm` and make it center aligned on both desktop and mobile screens.
```
*Note: Fixed layout shifts occurring dynamically when showing form validation error states.*

---

## PROMPT 10 — Fix Router JWT Token Expiry Redirection Loop

**Output File:** `frontend-react-admin_new/src/services/axiosClient.ts`, `frontend-react-admin_new/src/routes/AppRoutes.tsx`

---
### Prompt Content (Verbatim)

```text
System Role: React Architect & Security Specialist

Task: Resolve infinite redirection loop in React Router when the JWT token expires.

Requirements:
- Update Axios interceptors in `axiosClient.ts` to detect 401 Unauthorized errors.
- On 401 error, clear JWT token from localStorage/cookies and dispatch logout action.
- Update `AppRoutes.tsx` route guards: redirect to `/login` smoothly without causing a circular redirection loop between protected routes and login path.
```
*Note: Fixed infinite reloads triggered when expired tokens remained in state.*

---

## PROMPT 11 — Fix Ingestion Status Polling Interval

**Output File:** `frontend-react-admin_new/src/features/Ingestion/IngestionStatus.tsx`

---
### Prompt Content (Verbatim)

```text
System Role: Senior React Developer

Task: Adjust status polling interval for bulk content ingestion to prevent rate-limiting.

Requirements:
- In `IngestionStatus.tsx`, change polling interval from 1 second to 5 seconds.
- Use clean `useEffect` cleanup return functions to clear the `setInterval` timer upon component unmount.
- Show a user-friendly linear progress indicator based on the backend percent completed.
```
*Note: Reduced backend load and rate-limiting triggers during high concurrency test runs.*
