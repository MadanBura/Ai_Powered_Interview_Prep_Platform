import { useQuery } from '@tanstack/react-query';
import { dashboardService } from '../../services/dashboard.service';
import { DASHBOARD_QUERY_KEYS } from '../../dashboard.query-keys';

export const useGetRecentActivity = () => {
  return useQuery({
    queryKey: [...DASHBOARD_QUERY_KEYS.all, 'activity'],
    queryFn: () => dashboardService.getRecentActivity()
  });
};
