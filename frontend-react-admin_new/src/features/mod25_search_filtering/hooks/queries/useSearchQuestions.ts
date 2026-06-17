// Placeholder for React Query Hook
import { useQuery } from '@tanstack/react-query';
import { searchFilterService } from '../../services/searchfilter.service';
import { SEARCHFILTER_QUERY_KEYS } from '../../searchfilter.query-keys';

export const useSearchQuestions = () => {
  return useQuery({
    queryKey: SEARCHFILTER_QUERY_KEYS.all,
    queryFn: () => searchFilterService.searchQuestions()
  })
};
