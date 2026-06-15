SET FOREIGN_KEY_CHECKS = 0;

-- Drop all old tables
DROP TABLE IF EXISTS mod02_user_profile_data;
DROP TABLE IF EXISTS mod03_department_management_data;
DROP TABLE IF EXISTS mod04_technology_management_data;
DROP TABLE IF EXISTS mod05_experience_level_management_data;
DROP TABLE IF EXISTS mod06_question_repository_data;
DROP TABLE IF EXISTS mod07_bulk_upload_data;
DROP TABLE IF EXISTS mod08_categorization_engine_data;
DROP TABLE IF EXISTS mod09_interview_configuration_data;
DROP TABLE IF EXISTS mod10_interview_session_data;
DROP TABLE IF EXISTS mod11_question_delivery_data;
DROP TABLE IF EXISTS mod12_voice_recording_data;
DROP TABLE IF EXISTS mod13_speech_to_text_data;
DROP TABLE IF EXISTS mod14_ai_evaluation_data;
DROP TABLE IF EXISTS mod15_scoring_engine_data;
DROP TABLE IF EXISTS mod16_feedback_engine_data;
DROP TABLE IF EXISTS mod17_reporting_data;
DROP TABLE IF EXISTS mod18_dashboard_data;
DROP TABLE IF EXISTS mod19_recommendation_engine_data;
DROP TABLE IF EXISTS mod20_analytics_data;
DROP TABLE IF EXISTS mod21_notifications_data;
DROP TABLE IF EXISTS mod22_audit_logs_data;
DROP TABLE IF EXISTS mod23_admin_portal_data;
DROP TABLE IF EXISTS mod24_file_storage_data;
DROP TABLE IF EXISTS mod25_search_filtering_data;
DROP TABLE IF EXISTS mod26_ai_prompt_management_data;
DROP TABLE IF EXISTS mod27_configuration_management_data;

-- Drop new/existing standard tables
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS otps;
DROP TABLE IF EXISTS refresh_tokens;
DROP TABLE IF EXISTS departments;
DROP TABLE IF EXISTS technologies;
DROP TABLE IF EXISTS experience_levels;
DROP TABLE IF EXISTS questions;
DROP TABLE IF EXISTS ai_personas;
DROP TABLE IF EXISTS ai_prompts;
DROP TABLE IF EXISTS interview_configs;
DROP TABLE IF EXISTS interview_config_technologies;
DROP TABLE IF EXISTS interview_sessions;
DROP TABLE IF EXISTS session_questions;
DROP TABLE IF EXISTS voice_recordings;
DROP TABLE IF EXISTS speech_transcripts;
DROP TABLE IF EXISTS ai_evaluations;
DROP TABLE IF EXISTS bulk_uploads;
DROP TABLE IF EXISTS file_attachments;
DROP TABLE IF EXISTS notifications;
DROP TABLE IF EXISTS audit_logs;

-- CREATE NEW TABLES BASED ON ERD

CREATE TABLE users (
    id VARCHAR(36) PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    department_id VARCHAR(36),
    status VARCHAR(50) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE otps (
    id VARCHAR(36) PRIMARY KEY,
    user_id VARCHAR(36),
    otp_code VARCHAR(255) NOT NULL,
    target VARCHAR(255) NOT NULL,
    expires_at DATETIME NOT NULL,
    used BOOLEAN NOT NULL DEFAULT FALSE,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE refresh_tokens (
    id VARCHAR(36) PRIMARY KEY,
    user_id VARCHAR(36),
    jti VARCHAR(255) NOT NULL,
    expires_at DATETIME NOT NULL,
    revoked BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE departments (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    head_user_id VARCHAR(36),
    status VARCHAR(50) NOT NULL
);

CREATE TABLE technologies (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    department_id VARCHAR(36),
    status VARCHAR(50) NOT NULL
);

CREATE TABLE experience_levels (
    id VARCHAR(36) PRIMARY KEY,
    label VARCHAR(255) NOT NULL,
    rank_val INT NOT NULL
);

CREATE TABLE bulk_uploads (
    id VARCHAR(36) PRIMARY KEY,
    uploaded_by VARCHAR(36),
    file_name VARCHAR(255),
    file_type VARCHAR(255),
    status VARCHAR(50),
    questions_extracted INT DEFAULT 0,
    error_log TEXT,
    uploaded_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    processed_at DATETIME
);

CREATE TABLE questions (
    id VARCHAR(36) PRIMARY KEY,
    question_text TEXT NOT NULL,
    technology_id VARCHAR(36),
    bulk_upload_id VARCHAR(36),
    difficulty VARCHAR(50),
    type VARCHAR(50),
    category VARCHAR(255),
    confidence_score DECIMAL(5,2),
    status VARCHAR(50)
);

CREATE TABLE ai_personas (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    tone VARCHAR(255),
    description TEXT,
    created_by VARCHAR(36)
);

CREATE TABLE ai_prompts (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    version INT NOT NULL,
    content TEXT NOT NULL,
    model_target VARCHAR(255),
    persona_id VARCHAR(36),
    created_by VARCHAR(36)
);

CREATE TABLE interview_configs (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    department_id VARCHAR(36),
    experience_level_id VARCHAR(36),
    ai_persona_id VARCHAR(36),
    duration_min INT NOT NULL,
    rigor_level VARCHAR(50)
);

CREATE TABLE interview_config_technologies (
    config_id VARCHAR(36) NOT NULL,
    technology_id VARCHAR(36) NOT NULL,
    PRIMARY KEY (config_id, technology_id)
);

CREATE TABLE interview_sessions (
    id VARCHAR(36) PRIMARY KEY,
    config_id VARCHAR(36),
    candidate_user_id VARCHAR(36),
    interviewer_user_id VARCHAR(36),
    started_at DATETIME,
    ended_at DATETIME,
    status VARCHAR(50),
    overall_score DECIMAL(5,2)
);

CREATE TABLE session_questions (
    id VARCHAR(36) PRIMARY KEY,
    session_id VARCHAR(36),
    question_id VARCHAR(36),
    asked_at DATETIME,
    answer_text TEXT,
    ai_score DECIMAL(5,2),
    human_score DECIMAL(5,2)
);

CREATE TABLE voice_recordings (
    id VARCHAR(36) PRIMARY KEY,
    session_question_id VARCHAR(36),
    storage_path VARCHAR(255),
    duration_seconds INT,
    format VARCHAR(50),
    status VARCHAR(50),
    recorded_at DATETIME
);

CREATE TABLE speech_transcripts (
    id VARCHAR(36) PRIMARY KEY,
    voice_recording_id VARCHAR(36),
    transcript_text TEXT,
    engine_used VARCHAR(255),
    confidence DECIMAL(5,2),
    transcribed_at DATETIME
);

CREATE TABLE ai_evaluations (
    id VARCHAR(36) PRIMARY KEY,
    session_question_id VARCHAR(36),
    prompt_id VARCHAR(36),
    model_used VARCHAR(255),
    avg_score_correlation DECIMAL(5,2),
    processing_time_ms INT,
    sentiment_score DECIMAL(5,2),
    strengths JSON,
    weaknesses JSON,
    recommendations JSON
);

CREATE TABLE file_attachments (
    id VARCHAR(36) PRIMARY KEY,
    storage_path VARCHAR(255) NOT NULL,
    file_type VARCHAR(50),
    size_bytes BIGINT,
    owner_question_id VARCHAR(36),
    owner_upload_id VARCHAR(36),
    owner_user_id VARCHAR(36),
    owner_session_id VARCHAR(36),
    purpose VARCHAR(255),
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE notifications (
    id VARCHAR(36) PRIMARY KEY,
    user_id VARCHAR(36),
    title VARCHAR(255),
    message TEXT,
    type VARCHAR(50),
    is_read BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE audit_logs (
    id VARCHAR(36) PRIMARY KEY,
    user_id VARCHAR(36),
    action VARCHAR(255),
    entity_type VARCHAR(255),
    entity_id VARCHAR(36),
    old_value JSON,
    new_value JSON
);

-- ADD FOREIGN KEYS

ALTER TABLE otps ADD CONSTRAINT fk_otps_user FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE refresh_tokens ADD CONSTRAINT fk_rt_user FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE users ADD CONSTRAINT fk_users_dept FOREIGN KEY (department_id) REFERENCES departments(id);
ALTER TABLE departments ADD CONSTRAINT fk_dept_head FOREIGN KEY (head_user_id) REFERENCES users(id);

ALTER TABLE technologies ADD CONSTRAINT fk_tech_dept FOREIGN KEY (department_id) REFERENCES departments(id);

ALTER TABLE bulk_uploads ADD CONSTRAINT fk_bu_user FOREIGN KEY (uploaded_by) REFERENCES users(id);

ALTER TABLE questions ADD CONSTRAINT fk_q_tech FOREIGN KEY (technology_id) REFERENCES technologies(id);
ALTER TABLE questions ADD CONSTRAINT fk_q_bu FOREIGN KEY (bulk_upload_id) REFERENCES bulk_uploads(id);

ALTER TABLE ai_personas ADD CONSTRAINT fk_persona_creator FOREIGN KEY (created_by) REFERENCES users(id);
ALTER TABLE ai_prompts ADD CONSTRAINT fk_prompt_persona FOREIGN KEY (persona_id) REFERENCES ai_personas(id);
ALTER TABLE ai_prompts ADD CONSTRAINT fk_prompt_creator FOREIGN KEY (created_by) REFERENCES users(id);

ALTER TABLE interview_configs ADD CONSTRAINT fk_config_dept FOREIGN KEY (department_id) REFERENCES departments(id);
ALTER TABLE interview_configs ADD CONSTRAINT fk_config_exp FOREIGN KEY (experience_level_id) REFERENCES experience_levels(id);
ALTER TABLE interview_configs ADD CONSTRAINT fk_config_persona FOREIGN KEY (ai_persona_id) REFERENCES ai_personas(id);

ALTER TABLE interview_config_technologies ADD CONSTRAINT fk_ict_config FOREIGN KEY (config_id) REFERENCES interview_configs(id);
ALTER TABLE interview_config_technologies ADD CONSTRAINT fk_ict_tech FOREIGN KEY (technology_id) REFERENCES technologies(id);

ALTER TABLE interview_sessions ADD CONSTRAINT fk_session_config FOREIGN KEY (config_id) REFERENCES interview_configs(id);
ALTER TABLE interview_sessions ADD CONSTRAINT fk_session_candidate FOREIGN KEY (candidate_user_id) REFERENCES users(id);
ALTER TABLE interview_sessions ADD CONSTRAINT fk_session_interviewer FOREIGN KEY (interviewer_user_id) REFERENCES users(id);

ALTER TABLE session_questions ADD CONSTRAINT fk_sq_session FOREIGN KEY (session_id) REFERENCES interview_sessions(id);
ALTER TABLE session_questions ADD CONSTRAINT fk_sq_question FOREIGN KEY (question_id) REFERENCES questions(id);

ALTER TABLE voice_recordings ADD CONSTRAINT fk_vr_sq FOREIGN KEY (session_question_id) REFERENCES session_questions(id);

ALTER TABLE speech_transcripts ADD CONSTRAINT fk_st_vr FOREIGN KEY (voice_recording_id) REFERENCES voice_recordings(id);

ALTER TABLE ai_evaluations ADD CONSTRAINT fk_aie_sq FOREIGN KEY (session_question_id) REFERENCES session_questions(id);
ALTER TABLE ai_evaluations ADD CONSTRAINT fk_aie_prompt FOREIGN KEY (prompt_id) REFERENCES ai_prompts(id);

ALTER TABLE file_attachments ADD CONSTRAINT fk_fa_question FOREIGN KEY (owner_question_id) REFERENCES questions(id);
ALTER TABLE file_attachments ADD CONSTRAINT fk_fa_upload FOREIGN KEY (owner_upload_id) REFERENCES bulk_uploads(id);
ALTER TABLE file_attachments ADD CONSTRAINT fk_fa_user FOREIGN KEY (owner_user_id) REFERENCES users(id);
ALTER TABLE file_attachments ADD CONSTRAINT fk_fa_session FOREIGN KEY (owner_session_id) REFERENCES interview_sessions(id);

ALTER TABLE notifications ADD CONSTRAINT fk_notif_user FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE audit_logs ADD CONSTRAINT fk_audit_user FOREIGN KEY (user_id) REFERENCES users(id);

SET FOREIGN_KEY_CHECKS = 1;
