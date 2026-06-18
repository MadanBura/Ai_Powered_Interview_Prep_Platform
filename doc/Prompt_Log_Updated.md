# Prompt Log - Mobile & Backend (Updated)

## AI-Powered Interview Preparation and Assessment Platform — Candidate Mobile Application (Android) and Spring Boot Backend.

---

## PROMPT 1 — Initialize Prompt Logger

**Output File:** `doc/Prompt_Log_Updated.md`

---
### Prompt Content (Verbatim)

```text
I am starting the AI-Powered Interview Preparation and Assessment Platform project. 
Please initialize the prompt logger for this project by reading and following the global instructions located at `/Users/apple/.gemini/config/skills/prompt-logger.md`

Establish the Role:
- Act as the AI Prompt Auditor & SDLC Log Custodian.
- Create the `doc/Prompt_Log_Updated.md` file in the workspace root.
- Initialize it for the project: "AI-Powered Interview Preparation and Assessment Platform (Candidate Mobile & Backend)".
- Keep this skill active and log all subsequent instructions/prompts automatically.
```

---

## PROMPT 2 — Generate Project Context

**Output File:** `Project_Context.md`

---
### Prompt Content (Verbatim)

```text
System Role: You are a Principal Systems Architect and SDLC Governance Lead.

Task: Generate a `Project_Context.md` file that serves as the single source of truth for the entire engineering workspace. This file will be used by AI agents and developers to ensure consistent architecture, security, and implementation standards across all project documents and code generation tasks.

Constraint Rules:
* Output ONLY raw markdown content.
* Do not include explanations, commentary, or extra text.
* Ensure the document is reusable across all SDLC artifacts (PRD, KPIs, Scope, Architecture, Code).
* Normalize all inputs into a clean, structured system context.
* Avoid duplication and ensure strict clarity.

---

### INPUT DATA FOR THIS RUN:

* **Project Name:** AI-Powered Interview Preparation and Assessment Platform

* **Frontend (Mobile):** Android (Kotlin, Jetpack Compose, MVVM, Clean Architecture, Hilt, Coroutines, Room, DataStore, Material 3)

* **Backend:** Java 21, Spring Boot 3, Spring Security, Spring Data JPA, JWT, OpenAPI

* **Database & Cache:** PostgreSQL + Redis

* **Search Engine:** Elasticsearch

* **AI Services:** OpenAI, Gemini, Claude, Whisper (Speech-to-Text)

* **Authentication Model:** JWT, OTP, RBAC

* **User Roles:** Super Admin, Content Admin, Candidate

* **Core Domain:** AI-based mock interviews, evaluation engine, analytics dashboard, question repository system

* **Security Requirements:**

  * OWASP Top 10 Compliance
  * HTTPS/TLS encryption
  * SQL Injection prevention
  * XSS/CSRF protection
  * Secure file uploads with validation
  * Data encryption at rest and in transit
  * Audit logging for sensitive actions
  * Rate limiting (Redis-based, HTTP 429)

* **Engineering Standards:**
  * Clean Architecture (strict separation of concerns)
  * SOLID principles
  * DTO-based API communication
  * Global exception handling
  * OpenAPI-first API design
  * Structured logging (SLF4J + Logback + MDC)
  * Observability via Micrometer + Prometheus + Grafana

* **Performance Requirements:**
  * API latency < 3s
  * AI evaluation < 10s
  * Search response < 2s

* **Scalability Requirements:**
  * 100,000+ users
  * 10,000 concurrent sessions
  * Stateless backend services
  * Redis caching layer for performance

* **Agent Constraints:**
  * No silent git commits
  * Ask before executing terminal commands
  * No assumptions without clarification
  * Generate production-grade modular code only
  * Never expose secrets or API keys
  * Prefer incremental diffs over full rewrites
  * Always follow architecture-first approach

---

### OUTPUT TEMPLATE (FOLLOW STRICTLY)

# Project Context - [Project Name]

## 1. Project Overview

* Description of system
* Core purpose
* High-level architecture summary

## 2. Technology Stack

### Mobile

### Backend

### Database & Cache

### AI Services

## 3. System Architecture Principles

* Architectural style
* Design principles
* Service boundaries
* Communication patterns

## 4. Security & Compliance Standards

* Authentication & Authorization rules
* Data protection policies
* OWASP safeguards
* Encryption standards
* Audit & logging requirements
* Rate limiting rules

## 5. Engineering Standards

* Backend coding standards
* Frontend architecture rules
* API design rules
* Error handling conventions
* Logging & observability standards

## 6. Performance & Scalability Requirements
* Latency targets
* Throughput expectations
* Scaling strategy
* Caching strategy

## 7. AI Integration Standards
* LLM usage boundaries
* Prompt handling rules
* Speech-to-text pipeline
* Evaluation engine design principles

## 8. Data Architecture

* Database design principles
* Cache usage strategy
* Search indexing strategy
* Data retention rules

## 9. Authentication & Role Model

* JWT strategy
* OTP flow
* RBAC model
* Role responsibilities

## 10. Agent Operating Rules

* Execution constraints
* Code generation rules
* Safety rules
* Communication rules

## 11. Documentation Governance Rules
* Template strictness
* API contract rules
* KPI mapping rules
* Schema requirements

## 12. System Boundary Definition
* In-scope systems
* Out-of-scope systems
* External integrations

## 13. Output Principle
* No conversational output allowed
* Only structured artifacts allowed
* Always treat this file as the highest authority context
```

---

## PROMPT 3 — Generate Product Requirement Document (PRD)

**Output File:** `doc/PRD.md`

---
### Prompt Content (Verbatim)

```text
System Role: You are a Product Manager specializing in developer-facing documentation.

Task: Generate a PRD in Markdown format from the provided BRD.

Context:
project context : @[Project_Context.md] 

Inputs:
- BRD Details: @[brd_statement] 
- Minimum OS/API Requirement: [Android API 26+]

Output Structure:

# PRD - {PROJECT_NAME}

## 1. Problem Statement
## 2. Solution Overview
### Core Features
- [Feature]: [Description]

## 3. User Flow
```
[Screen A] --> [Screen B]
```

## 4. API Design
### 4.N. [Action]
- Endpoint: METHOD /path
- Request Payload: `{}`
- Response Payload: `{}`
### Standard Error Schemas
[400 and 401 JSON schemas]

## 5. Edge Cases (minimum 3)
### 5.N. [Case]
- Case: [Description]
- Handling: [Approach]

## 6. KPIs & Acceptance Criteria
## 7. Limitations
## 8. Data Privacy & Compliance (GDPR / CCPA)
## 9. User Stories
[US-00N: As a [user], I want to [action], so that [outcome]]

## 10. Acceptance Criteria Matrix
| Feature | Acceptance Criteria |

## 11. Functional Requirements Matrix
| FR-ID | Module / Feature | Description |
```

---

## PROMPT 4 — Generate Key Performance Indicators (KPIs)

**Output File:** `doc/KPIs.md`

---
### Prompt Content (Verbatim)

```text
System Role: You are a Product Analytics Manager and SRE.

Task: Generate a KPIs document using the PRD @[doc/PRD.md] .

Context : @[Project_Context.md] 

Inputs:
- PRD features list: [paste from docs/prd.md]


Output Structure:

# KPIs - {PROJECT_NAME}

## 1. Document Control
## 2. Purpose
## 3. KPI Framework
## 4. KPI Categories
## 5. Module KPI Matrix
[Group the 27 features into 12 distinct modules and document KPIs for each module]
## 6. Business KPIs
## 7. SLA Metrics
## 8. Success Metrics
## 9. Traceability Matrix
```

---

## PROMPT 5 — Generate Project Scope

**Output File:** `doc/Project_Scope.md`

---
### Prompt Content (Verbatim)

```text
System Role: You are a Technical Program Manager and Systems Architect.

Task: Generate a Project Scope document from the PRD @[doc/PRD.md]  and KPIs @[doc/KPIs.md] .

Context : @[Project_Context.md] 

Inputs:
- Development Timeline: [e.g., 6 weeks]
- Team Roles: [e.g., PM, Architect, Android Dev, Spring Boot Dev, QA, DevOps]

Output Structure:

# Project Scope - {PROJECT_NAME}

## 1. Document Control
## 2. Project Description & Objectives

## 3. System Architecture Diagram
```mermaid
graph TD
    [Map: client → API → DB → cache → queue]
```

## 4. In-Scope Deliverables
### Phase 1: MVP
### Phase 2: [Next phase]

## 5. Release Plan
[MVP → v1.0 → v1.1 → v2.0 roadmap]

## 6. Deployment Architecture
[Frontend channel, backend hosting, DB, Redis, alerting]

## 7. RACI Matrix
| Deliverable | PM | Architect | Android Dev | Spring Boot Dev | QA | DevOps |

## 8. Milestones & Timeline
| Milestone | Description | Target Date | Owner |
```

---

## PROMPT 6 — Generate Project Boundaries

**Output File:** `doc/Project_Boundaries.md`

---
### Prompt Content (Verbatim)

```text
System Role: You are a Lead Software Architect.

Task: Generate a Project Boundaries document to prevent scope creep.

Context : @[Project_Context.md] 

Output Structure:

# Project Boundaries - {PROJECT_NAME}

## 1. Document Control
## 2. Definition & Objectives
## 3. Explicit Exclusions (Out of Scope)
## 4. Technical & Architectural Constraints
[Numerical limits: DB size, upload caps, throttle rates]

## 5. Security & Compliance Boundaries
[PCI-DSS scope, field-level encryption, JWT constraints]

## 6. Assumption Register
## 7. Dependency Register
[Third-party APIs, packages, frameworks]

## 8. Scope Change Management Protocol
[Submission → Impact Assessment → Approval threshold → Doc update]

## 9. Legacy Assumptions & Support Policy
```

---

## PROMPT 7 — Generate Project Personas (Mobile & Backend Only)

**Output File:** `doc/frontend_mobile_persona.md`, `doc/backend_persona.md`

---
### Prompt Content (Verbatim)

```text
System Role: You are a Principal Systems Architect.

Task:
Generate TWO .md files in a single response:
1. Frontend Mobile Persona (Candidate App)
2. Backend Persona (Core System Architecture)

---

PROJECT CONTEXT
Project Name: {PROJECT_NAME}

Primary Candidate Screens (Mobile):
{MOBILE_SCREENS}

Key Entities to Model:
{KEY_ENTITIES}

Technical Constraints:
- @[Project_Context.md] 
---

OUTPUT FORMAT (STRICT)

Generate exactly 2 sections:

---

# 1. docs/frontend_mobile_persona.md

Include:

## Persona Profile
- Device assumptions
- UI toolkit
- User type

## Screen Wireframes (for each screen)
For each screen include:
- Primary goal
- Layout hierarchy
- Interaction flow (Mermaid diagram)
- Animations & transitions

## Styling (Material Design 3)
- Color system
- Typography system

## Component States
- Button / Input / Card states

## Empty States
- No data, offline, no permission

## Error States
- 401 / 403 / timeout / network failure

## Responsive Breakpoints
- Compact / Medium / Expanded

## Accessibility
- Touch targets, semantics, screen reader support

---

# 2. docs/backend_persona.md

Include:

## System Profile
- Framework
- Language
- DB
- Auth model

## Validation Layer
- DTO examples (Java records)
- Constraints

## Database Schema (PostgreSQL)
- Tables with UUID primary keys
- Foreign keys
- Indexes

## Rate Limiting
- Endpoint-based limits
- 429 response format

## Security Standards
- OWASP Top 10
- JWT rotation
- Password hashing
- CSRF protection

## Logging Standards
- SLF4J levels
- MDC correlation ID

## Monitoring
- CPU, RAM, latency, DB thresholds

## Disaster Recovery
- RPO / RTO
- backup strategy
- replication

## Async Jobs
- Job list with triggers
- concurrency control strategy

---

FORMATTING RULES
- Use clean Markdown
- Use Mermaid diagrams for flows
- Use code blocks for SQL and Java
- Be production-grade and implementation ready
- Avoid generic explanations; be specific and structured
- Ensure consistency across both personas
```

---

## PROMPT 7.1 — Generate Debug Persona (Android & Spring Boot Only)

**Output File:** `doc/Debug_Persona.md`

---
### Prompt Content (Verbatim)

```text
System Role: You are a Principal Systems Architect and Troubleshooting Expert.

Task: Generate a Debug Persona document that outlines systematic root-cause analysis, actionable debugging steps, and verification procedures for Android and Spring Boot backend technologies.

Context: 
Project Context: @[Project_Context.md]

Output Structure:
# Debug Persona

## Android Debug Persona
...
## Spring Boot Debug Persona
...
## General Rules for All Debug Personas
...
```

---

## PROMPT 8 — Generate Module Documentation Package

**Output File:** `doc/modules/module-*/` (Multiple files)

---
### Prompt Content (Verbatim)

```text
System Role: You are a Principal Systems Architect, Product Owner, Solution Architect, QA Lead, and Technical Documentation Expert.

Task: Generate a complete Module Documentation Package for the provided PRD @[doc/PRD.md].

The output must create documentation files following the required folder structure. Generate:
1. Module Overview document
2. Individual Feature documents
3. Full traceability from Module → Feature → User Story → Functional Requirement → API → Test Case → KPI

No orphan references are allowed.

Modules generated:
- MOD-01: Authentication (_module-overview, feature-01-otp-login, feature-02-jwt-token-management)
- MOD-03: Organization Management (_module-overview, feature-01-department-management)
- MOD-06: Content Management (_module-overview, feature-01-bulk-question-ingestion)
- MOD-09: Interview Session (_module-overview, feature-01-session-initialization, feature-02-voice-input-stt)
- MOD-14: AI Evaluation (_module-overview, feature-01-ai-evaluation-engine)
- MOD-18: Analytics Dashboard (_module-overview, feature-01-candidate-performance-dashboard)
- MOD-22: Compliance & Audit (_module-overview, feature-01-audit-logging)
```

---

## PROMPT 9 — Verify & Complete All 27 Modules + Generate API Schema

**Output File:** `doc/modules/module-[02..27]/` (20 new modules), `doc/api-schema.md`

---
### Prompt Content (Verbatim)

```text
System Role: You are a Principal Backend Architect and API Designer.

Task: Generate a global API Schema Overview document (`api-schema.md`) in Markdown format covering architecture, authentication strategy, common reusable schemas, and the full module-to-API mapping table.

Constraint Rules:
- Output ONLY the requested Markdown content without any conversational intro or concluding text.
- Keep schemas FIRST priority. Avoid long explanations. No unnecessary theory.
- Every API listed MUST trace back to a Module → Feature → User Story (no orphan APIs).

INPUT: @[Project_Context.md] @[doc/Project_Scope.md] @[doc/Project_Boundaries.md]
Modules: /doc/modules (all 27)

Actions performed:
1. Gap analysis identified 20 missing module directories.
2. Created missing modules MOD-02 through MOD-27 (excluding pre-existing MOD-01, MOD-03, MOD-14, MOD-18, MOD-22).
3. Removed incorrectly bundled legacy folders (module-06-content-management, module-09-interview-session).
4. All 27 modules now correctly numbered and individually documented.
5. Generated doc/api-schema.md with 57 API endpoints across 27 modules with full KPI traceability.
```

---

## PROMPT 10 — MOD-01 Implementation

**Output File:** `/backend/src/main/java/com/interview/platform/mod01_authentication/*`

---
### Prompt Content (Verbatim)

```text
System Role: You are a Principal Java Backend Engineer and Spring Boot Expert.

Task: Generate a production-hardened, compile-safe Spring Boot API implementation including JPA entities, Spring Data repositories, DTO validation records, service layers with transaction concurrency controls, REST controllers, security/rate-limiting filters, MDC logging filters, and a global RFC 7807 exception handler. Do not skip any files or use empty placeholders.

Constraint Rules:
- Output ONLY the requested file contents without any conversational intro or concluding text.
- Fully implement all error scenarios, validations, and pessimistic database locking logic to prevent race conditions.
- Enforce strict parameterization on all queries to protect against SQL injections.
- Implementation must be done MODULE BY MODULE — complete all files for Module 1 before starting Module 2.
- Every implemented endpoint must make the corresponding ❌ FAIL test cases in test-cases-log.md turn ✅ PASS.
```

---

## PROMPT 11 — Bulk Implementation of Modules 02-27

**Output File:** `/backend/src/main/java/com/interview/platform/*`

---
### Prompt Content (Verbatim)

```text
now do this same all 27 modules
```
*Note: Scaffolded entities, repositories, DTOs, services, and controllers for all 26 remaining backend modules.*

---

## PROMPT 12 — Generate Android Candidate Application UI

**Output File:** `app/src/main/java/com/interview/platform/ui/screens/*`

---
### Prompt Content (Verbatim)

```text
## System Role

You are a Lead Mobile UI/UX Engineer, Android Architect, and Senior Jetpack Compose Developer.

## Task

Generate a complete Native Android Candidate Application UI for the AI-Powered Interview Preparation & Assessment Platform.

Generate screens phase-by-phase.
@[doc/frontend_mobile_persona.md] @[Project_Context.md] 

Use Stitch MCP Design System as the source of truth for screen definitions and layout hierarchy.
```

---

## PROMPT 14 — Android Backend Integration

**Output File:** `android-app/app/src/main/java/com/interview/platform/*`

---
### Prompt Content (Verbatim)

```text
## System Role

You are a Senior Android Engineer, Mobile Architect, and Network Integration Specialist.

## Task

Implement the complete Android API Integration Layer for the Candidate Mobile Application.
```
*Note: Integrated Room, Retrofit API calls, Hilt, and flow mapping to the endpoints.*

---

## PROMPT 16 — Android Build Infrastructure Fixes

**Output File:** `android-app/build.gradle.kts`, `android-app/app/build.gradle.kts`, `android-app/gradle/libs.versions.toml`, `android-app/app/src/main/AndroidManifest.xml`

---
### Prompt Content (Verbatim)

```text
# System Role
You are a Principal Android Architect and Build Systems Expert.

# Task

Analyze my existing Android project and generate ONLY the missing infrastructure files required to make the application build and run successfully.
```

---

## PROMPT 18 — Implement Remaining Mobile Roadmap Screens

**Output File:** `android-app/app/src/main/java/com/interview/platform/ui/screens/mod19_recommendation_engine/practice_plan/PracticePlanScreenScreen.kt` and roadmap screens.

---
### Prompt Content (Verbatim)

```text
<USER_REQUEST>
currently in mobile roadmap screens are missing please fix them also
</USER_REQUEST>
```

---

## PROMPT 19 — Fix Double Bottom Bar and Blank Screens

**Output File:** layout and navigation configuration files.

---
### Prompt Content (Verbatim)

```text
<USER_REQUEST>
analyze frontEndside android some screen shows double bottom bar
please fix these and some screen shows blank screen fix them also
</USER_REQUEST>
```

---

## PROMPT 20 — Fix Android UI and Layout Inconsistencies

**Output File:** `android-app/app/src/main/java/com/interview/platform/ui/screens/*`

---
### Prompt Content (Verbatim)

```text
System Role: Lead Mobile UI/UX Engineer

Task: Review the Android mobile screens generated from Stitch against Figma designs. Some screens are displaying a double bottom bar and blank screens. 

Requirements:
- Fix all non-functional widgets, buttons, and UI components.
- Resolve double bottom bars and blank screen layout inconsistencies.
- Ensure the current mobile UI accurately reflects the Stitch/Figma designs and preserves responsive behavior.
```

---

## PROMPT 22 — Fix Android Interview Timer and Voice Recording

**Output File:** `android-app/app/src/main/java/com/interview/platform/ui/screens/mod09_interview_session/*`

---
### Prompt Content (Verbatim)

```text
System Role: Senior Android Engineer

Task: Fix the interview session timer and voice recording features. Currently, the timer is not counting and recording is not happening.

Requirements:
- Investigate StateFlow timer emissions in the ViewModel.
- Fix MediaRecorder/AudioRecord integration for capturing candidate responses.
- Ensure the UI correctly reflects active recording states and countdowns.
```

---

## PROMPT 23 — Update Spring Boot Mock Services and OTP Bypass

**Output File:** `backend/src/main/java/com/interview/platform/mod01_authentication/OtpLoginService.java`

---
### Prompt Content (Verbatim)

```text
System Role: Principal Backend Architect

Task: Fix issues where backend services return empty collections and OTP fails for new test admins.

Requirements:
- Bypass strict OTP blocks for development by auto-provisioning non-existing admins in `OtpLoginService.java`.
- Update mock services (like `CategorizationEngineService`) to return structured mock DTO arrays rather than empty HashMaps so the UI can render correctly until the full DB is wired.
```

---

# Stabilization & Fixing Phase (New Chat)

## Entry #1

**Timestamp:** 2026-06-12 12:49:41 +05:30
**Category:** Enhancement

### User Instruction

Read and follow the global instructions located at:

`/Users/apple/.gemini/config/skills/prompt-logger.md`

Assume the role of **AI Prompt Auditor & SDLC Log Custodian**.

Project: AI-Powered Interview Preparation and Assessment Platform

Current Project State:
* Development phase completed.
* Entering a new Fixing, Stabilization, Testing, and Refinement phase.
* This is a new chat session.
* No previous prompt log exists in this workspace for this chat.

Tasks:
1. Create a new file: `Doc/Prompt_Log_Fixing_New_Chat.md`
2. Initialize the file with project metadata.
3. Immediately record the current instruction as Entry #1.
4. For every subsequent prompt automatically append a new entry.

---

## Entry #2

**Timestamp:** 2026-06-12 12:53:17 +05:30
**Category:** Bug Fix | Enhancement

### User Instruction

These screens were generated from Stitch. Please review and fix them to match the Stitch/Figma designs as closely as possible.

Requirements:
- Fetch and analyze the generated screen HTML and compare it against the corresponding Stitch/Figma designs.
- Fix all non-functional widgets, buttons, interactions, and UI components.
- Resolve layout inconsistencies, alignment issues, spacing problems, and styling mismatches.
- Ensure the current mobile UI accurately reflects the Stitch/Figma designs.
- Maintain responsive behavior while preserving design fidelity.
- Verify that all navigation flows, actions, and interactive elements function correctly.
- Update the implementation wherever necessary to achieve visual and functional parity with the provided designs.
