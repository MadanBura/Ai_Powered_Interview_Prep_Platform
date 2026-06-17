import { useQuery } from '@tanstack/react-query';
import { dashboardService } from '../../services/dashboard.service';
import { DASHBOARD_QUERY_KEYS } from '../../dashboard.query-keys';

export const useRetrieveAdminDashboard = (date?: string) => {
  return useQuery({
    queryKey: [...DASHBOARD_QUERY_KEYS.all, { date }],
    queryFn: () => dashboardService.retrieveAdminDashboard(date)
  });
};
