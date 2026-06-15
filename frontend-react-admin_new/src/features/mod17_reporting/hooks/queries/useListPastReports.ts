// Placeholder for React Query Hook
import { useQuery } from '@tanstack/react-query';
import { reportingService } from '../../services/reporting.service';
import { REPORTING_QUERY_KEYS } from '../../reporting.query-keys';

export const useListPastReports = () => {
  return useQuery({
    queryKey: REPORTING_QUERY_KEYS.all,
    queryFn: () => reportingService.listPastReports()
  })
};
