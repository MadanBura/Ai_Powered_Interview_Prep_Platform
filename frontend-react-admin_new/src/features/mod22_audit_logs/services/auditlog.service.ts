import { axiosClient } from '../../../services/http/axios-client';
import { AuditLogRequestDto, AuditLogResponseDto } from '../types/auditlog.types';

export const auditLogService = {

  async listAuditLogs(): Promise<AuditLogResponseDto> {
    const response = await axiosClient.get<AuditLogResponseDto>(`/api/v1/admin/audit-logs`);
    return response.data;
  },
};
