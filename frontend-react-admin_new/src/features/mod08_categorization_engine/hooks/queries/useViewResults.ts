import { useQuery } from '@tanstack/react-query';
import { categorizationService } from '../../services/categorization.service';
import { CATEGORIZATION_QUERY_KEYS } from '../../categorization.query-keys';

export const useViewResults = () => {
  return useQuery({
    queryKey: CATEGORIZATION_QUERY_KEYS.all,
    queryFn: () => categorizationService.viewResults()
  });
};
