# AI-Powered Interview Preparation and Assessment Platform - Overview

### 1. Project Overview (What it is doing)
The platform is a centralized, AI-driven interview practice system featuring an **Android Mobile Client** for candidates and a **React.js Web Admin Console** for administrators. It allows candidates to engage in realistic mock interviews by responding verbally or via text to technical questions. The system evaluates these responses using advanced AI models and provides automated, detailed performance feedback.

### 2. The Problem It Solves
Traditional interview preparation platforms rely heavily on static question banks and multiple-choice tests. These do not adequately simulate real-world technical interviews where candidates must verbally articulate and explain complex concepts. This platform bridges that gap by providing a realistic environment where candidates can practice verbalizing their answers, receiving objective feedback on their communication and technical accuracy.

### 3. Output and Benefits
* **Output:** Candidates receive detailed performance reports containing evaluation scores, identified strengths and weaknesses, and generative feedback structured by predefined rubrics.
* **Benefits:** It provides a scalable, data-driven practice environment that helps candidates identify knowledge gaps, track their progress over time, and significantly boost their confidence and interview readiness.

### 4. App (Mobile) Flow with Backend Flow
The mobile application (built with Android, Kotlin, and Jetpack Compose) serves as the primary interface for **Candidates**.
1. **Authentication:** Candidate logs in using an OTP-based flow authenticated by the Spring Boot backend.
2. **Session Setup:** Candidate selects a department (e.g., Mobile Development), experience level, and the desired number of questions.
3. **Data Retrieval:** The Mobile App requests this configuration from the Backend API, which retrieves randomized questions from the PostgreSQL database or Elasticsearch search index (often via Redis cache for speed).
4. **Interview Execution:** The app presents questions one by one. The candidate responds via voice or text.
5. **Processing:** Voice inputs are sent to the Backend, which routes them to the **Whisper API** for Speech-to-Text conversion.
6. **Evaluation:** The converted text (or typed text) is sent to the Backend's AI Evaluation Engine (powered by OpenAI/Gemini/Claude). The engine compares the candidate's response against the expected answers.
7. **Feedback Delivery:** The backend calculates scores and generates structured feedback, returning it to the Mobile App to display to the candidate.

### 5. Frontend (Web Admin) Flow with Backend Flow
The Web Admin console (built with React.js, TypeScript, and Material UI) serves as the interface for **Administrators**.
1. **Authentication:** Admin logs in using JWT-based authentication via the Backend API.
2. **Content Ingestion:** Admin uploads Q&A repositories in bulk (PDF, DOCX, TXT formats) via the web interface.
3. **Data Processing:** The Frontend sends these files to the Backend API. The backend processes the documents, extracts the questions and expected answers, categorizes them, and stores them in the PostgreSQL database.
4. **Search Indexing:** The backend simultaneously indexes these questions in Elasticsearch to allow for fast, subdomain-specific querying during mock interviews.
5. **Management:** Admins can view dashboards, review AI evaluations (with the ability to override if necessary), and manage system settings, users, and roles via API calls to the backend.

### 6. Administrator Roles and Capabilities
* **Content Admins:** Responsible for building the knowledge base. They create departments and subdomains, upload question banks, and ensure the system has accurate "expected answers." This ensures the platform can scale its content across various technical fields.
* **Super Admins:** Manage overall platform configuration, oversee user access (Role-Based Access Control), and monitor system audit logs for security and compliance.
* **How it helps:** By providing specialized admin tools, the system ensures the AI always has a structured, reliable, and curriculum-aligned knowledge base to evaluate candidates against, keeping the platform's content relevant and high-quality.

### 7. User (Candidate) Experience Overview
1. **Onboarding:** Registers and authenticates via OTP.
2. **Configuration:** Customizes their interview session based on their specific target role (e.g., Backend Developer) and seniority level.
3. **Practice:** Participates in a dynamic, randomized mock interview, practicing their verbal explanation skills under simulated pressure.
4. **Review:** Analyzes their immediate, post-interview report to understand what they answered correctly and where they fell short.
5. **Iteration:** Uses the historical tracking feature on their dashboard to monitor their improvement over multiple sessions, specifically targeting their identified weak points.
