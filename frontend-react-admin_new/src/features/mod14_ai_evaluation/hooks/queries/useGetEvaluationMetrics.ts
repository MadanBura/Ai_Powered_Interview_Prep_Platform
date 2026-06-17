import { useQuery } from '@tanstack/react-query';
import { evaluationService } from '../../services/evaluation.service';

export const useGetEvaluationMetrics = () => {
  return useQuery({
    queryKey: ['evaluations', 'metrics'],
    queryFn: () => evaluationService.getMetrics()
  });
};

export const useGetEvaluationFlags = () => {
  return useQuery({
    queryKey: ['evaluations', 'flags'],
    queryFn: () => evaluationService.getFlags()
  });
};
