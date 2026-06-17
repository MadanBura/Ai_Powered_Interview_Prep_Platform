export interface AuditLogRequestDto {
  payload?: string;
}

export interface AuditLogResponseDto {
  success: boolean;
  data: AuditLogDataDto;
}

export interface AuditLogDataDto {
  id: string;
}

export interface AuditLog {
  id: string;
}
