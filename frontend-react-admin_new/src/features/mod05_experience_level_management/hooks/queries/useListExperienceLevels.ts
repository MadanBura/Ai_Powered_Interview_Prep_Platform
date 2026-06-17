// Placeholder for React Query Hook
import { useQuery } from '@tanstack/react-query';
import { experienceLevelService } from '../../services/experiencelevel.service';
import { EXPERIENCELEVEL_QUERY_KEYS } from '../../experiencelevel.query-keys';

export const useListExperienceLevels = () => {
  return useQuery({
    queryKey: EXPERIENCELEVEL_QUERY_KEYS.all,
    queryFn: () => experienceLevelService.listExperienceLevels()
  })
};
