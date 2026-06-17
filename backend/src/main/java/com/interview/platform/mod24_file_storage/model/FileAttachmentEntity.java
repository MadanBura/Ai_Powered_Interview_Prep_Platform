package com.interview.platform.mod24_file_storage.model;

import com.interview.platform.mod01_authentication.model.UserEntity;
import com.interview.platform.mod06_question_repository.model.QuestionEntity;
import com.interview.platform.mod07_bulk_upload.model.BulkUploadEntity;
import com.interview.platform.mod10_interview_session.model.InterviewSessionEntity;
import jakarta.persistence.*;
import java.util.UUID;
import java.time.Instant;

@Entity
@Table(name = "file_attachments")
public class FileAttachmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "storage_path", nullable = false)
    private String storagePath;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "size_bytes")
    private Long sizeBytes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_question_id")
    private QuestionEntity ownerQuestion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_upload_id")
    private BulkUploadEntity ownerUpload;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_user_id")
    private UserEntity ownerUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_session_id")
    private InterviewSessionEntity ownerSession;

    @Column
    private String purpose;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getStoragePath() { return storagePath; }
    public void setStoragePath(String storagePath) { this.storagePath = storagePath; }
    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }
    public Long getSizeBytes() { return sizeBytes; }
    public void setSizeBytes(Long sizeBytes) { this.sizeBytes = sizeBytes; }
    public QuestionEntity getOwnerQuestion() { return ownerQuestion; }
    public void setOwnerQuestion(QuestionEntity ownerQuestion) { this.ownerQuestion = ownerQuestion; }
    public BulkUploadEntity getOwnerUpload() { return ownerUpload; }
    public void setOwnerUpload(BulkUploadEntity ownerUpload) { this.ownerUpload = ownerUpload; }
    public UserEntity getOwnerUser() { return ownerUser; }
    public void setOwnerUser(UserEntity ownerUser) { this.ownerUser = ownerUser; }
    public InterviewSessionEntity getOwnerSession() { return ownerSession; }
    public void setOwnerSession(InterviewSessionEntity ownerSession) { this.ownerSession = ownerSession; }
    public String getPurpose() { return purpose; }
    public void setPurpose(String purpose) { this.purpose = purpose; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
