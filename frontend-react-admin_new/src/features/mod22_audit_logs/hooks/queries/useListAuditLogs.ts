// Placeholder for React Query Hook
import { useQuery } from '@tanstack/react-query';
import { auditLogService } from '../../services/auditlog.service';
import { AUDITLOG_QUERY_KEYS } from '../../auditlog.query-keys';

export const useListAuditLogs = () => {
  return useQuery({
    queryKey: AUDITLOG_QUERY_KEYS.all,
    queryFn: () => auditLogService.listAuditLogs()
  })
};
