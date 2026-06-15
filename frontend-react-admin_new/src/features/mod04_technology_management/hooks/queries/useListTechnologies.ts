// Placeholder for React Query Hook
import { useQuery } from '@tanstack/react-query';
import { technologyService } from '../../services/technology.service';
import { TECHNOLOGY_QUERY_KEYS } from '../../technology.query-keys';

export const useListTechnologies = () => {
  return useQuery({
    queryKey: TECHNOLOGY_QUERY_KEYS.all,
    queryFn: () => technologyService.listTechnologies()
  })
};
