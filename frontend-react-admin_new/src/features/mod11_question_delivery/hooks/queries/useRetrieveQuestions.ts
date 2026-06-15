// Placeholder for React Query Hook
import { useQuery } from '@tanstack/react-query';
import { questionDeliveryService } from '../../services/questiondelivery.service';
import { QUESTIONDELIVERY_QUERY_KEYS } from '../../questiondelivery.query-keys';

export const useRetrieveQuestions = () => {
  return useQuery({
    queryKey: QUESTIONDELIVERY_QUERY_KEYS.all,
    queryFn: () => questionDeliveryService.retrieveQuestions()
  })
};
