CREATE TABLE user_badges (
    id CHAR(36) PRIMARY KEY,
    user_id CHAR(36) NOT NULL,
    badge_name VARCHAR(255) NOT NULL,
    description TEXT,
    earned_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    icon_url VARCHAR(512),
    FOREIGN KEY (user_id) REFERENCES users(id)
);
