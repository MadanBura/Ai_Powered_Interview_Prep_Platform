// Placeholder for React Query Hook
import { useQuery } from '@tanstack/react-query';
import { recommendationService } from '../../services/recommendation.service';
import { RECOMMENDATION_QUERY_KEYS } from '../../recommendation.query-keys';

export const useRetrieveRecommendations = () => {
  return useQuery({
    queryKey: RECOMMENDATION_QUERY_KEYS.all,
    queryFn: () => recommendationService.retrieveRecommendations()
  })
};
