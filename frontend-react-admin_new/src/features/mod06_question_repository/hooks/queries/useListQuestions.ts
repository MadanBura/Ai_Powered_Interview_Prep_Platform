// Placeholder for React Query Hook
import { useQuery } from '@tanstack/react-query';
import { questionService } from '../../services/question.service';
import { QUESTION_QUERY_KEYS } from '../../question.query-keys';

export const useListQuestions = () => {
  return useQuery({
    queryKey: QUESTION_QUERY_KEYS.all,
    queryFn: () => questionService.listQuestions()
  })
};
