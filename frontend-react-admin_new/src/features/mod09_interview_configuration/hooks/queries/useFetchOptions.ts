import { useQuery } from '@tanstack/react-query';
import { interviewConfigService } from '../../services/interviewconfig.service';
import { INTERVIEWCONFIG_QUERY_KEYS } from '../../interviewconfig.query-keys';

export const useFetchOptions = () => {
  return useQuery({
    queryKey: INTERVIEWCONFIG_QUERY_KEYS.all,
    queryFn: () => interviewConfigService.fetchOptions()
  });
};
