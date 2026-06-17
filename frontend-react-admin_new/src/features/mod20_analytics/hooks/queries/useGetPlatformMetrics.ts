import { useQuery } from '@tanstack/react-query';
import { analyticsService } from '../../services/analytics.service';
import { ANALYTICS_QUERY_KEYS } from '../../analytics.query-keys';

export const useGetPlatformMetrics = (date?: string) => {
  return useQuery({
    queryKey: [...ANALYTICS_QUERY_KEYS.all, { date }],
    queryFn: () => analyticsService.getPlatformMetrics(date)
  });
};
