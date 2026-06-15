import { useQuery } from '@tanstack/react-query';
import { dashboardService } from '../../services/dashboard.service';
import { DASHBOARD_QUERY_KEYS } from '../../dashboard.query-keys';

export const useRetrieveAdminDashboard = () => {
  return useQuery({
    queryKey: DASHBOARD_QUERY_KEYS.all,
    queryFn: () => dashboardService.retrieveAdminDashboard()
  });
};
