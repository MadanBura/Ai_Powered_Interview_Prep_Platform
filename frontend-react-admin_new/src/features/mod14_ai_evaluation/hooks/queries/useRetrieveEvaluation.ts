// Placeholder for React Query Hook
import { useQuery } from '@tanstack/react-query';
import { aiEvaluationService } from '../../services/aievaluation.service';
import { AIEVALUATION_QUERY_KEYS } from '../../aievaluation.query-keys';

export const useRetrieveEvaluation = () => {
  return useQuery({
    queryKey: AIEVALUATION_QUERY_KEYS.all,
    queryFn: () => aiEvaluationService.retrieveEvaluation()
  })
};
