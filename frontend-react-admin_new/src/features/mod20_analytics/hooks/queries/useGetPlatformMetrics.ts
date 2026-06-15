import { useQuery } from '@tanstack/react-query';
import { analyticsService } from '../../services/analytics.service';
import { ANALYTICS_QUERY_KEYS } from '../../analytics.query-keys';

export const useGetPlatformMetrics = () => {
  return useQuery({
    queryKey: ANALYTICS_QUERY_KEYS.all,
    queryFn: () => analyticsService.getPlatformMetrics()
  });
};
