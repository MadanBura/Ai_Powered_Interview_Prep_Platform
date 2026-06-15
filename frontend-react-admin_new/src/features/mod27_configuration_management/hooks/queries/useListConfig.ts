// Placeholder for React Query Hook
import { useQuery } from '@tanstack/react-query';
import { systemConfigService } from '../../services/systemconfig.service';
import { SYSTEMCONFIG_QUERY_KEYS } from '../../systemconfig.query-keys';

export const useListConfig = () => {
  return useQuery({
    queryKey: SYSTEMCONFIG_QUERY_KEYS.all,
    queryFn: () => systemConfigService.listConfig()
  })
};
