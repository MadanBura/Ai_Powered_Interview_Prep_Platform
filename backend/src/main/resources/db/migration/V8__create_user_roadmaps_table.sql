CREATE TABLE user_roadmaps (
    id CHAR(36) PRIMARY KEY,
    user_id CHAR(36) NOT NULL,
    roadmap_template_id CHAR(36) NOT NULL,
    status VARCHAR(255) NOT NULL,
    progress_percentage INT NOT NULL DEFAULT 0,
    completed_topics JSON,
    started_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);
