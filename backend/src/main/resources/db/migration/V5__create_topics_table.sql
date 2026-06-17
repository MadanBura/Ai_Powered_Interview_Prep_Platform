CREATE TABLE topics (
    id CHAR(36) PRIMARY KEY,
    tech VARCHAR(255),
    level VARCHAR(255),
    title VARCHAR(255),
    subtopics JSON,
    code_snippet TEXT,
    pros JSON,
    cons JSON,
    use_cases JSON,
    tags JSON,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
