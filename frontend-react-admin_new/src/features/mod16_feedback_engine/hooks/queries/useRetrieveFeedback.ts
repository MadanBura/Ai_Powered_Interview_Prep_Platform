// Placeholder for React Query Hook
import { useQuery } from '@tanstack/react-query';
import { feedbackEngineService } from '../../services/feedbackengine.service';
import { FEEDBACKENGINE_QUERY_KEYS } from '../../feedbackengine.query-keys';

export const useRetrieveFeedback = () => {
  return useQuery({
    queryKey: FEEDBACKENGINE_QUERY_KEYS.all,
    queryFn: () => feedbackEngineService.retrieveFeedback()
  })
};
