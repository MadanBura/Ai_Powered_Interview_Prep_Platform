import { useMutation, useQueryClient } from '@tanstack/react-query';
import { questionService } from '../../services/question.service';
import { QUESTION_QUERY_KEYS } from '../../question.query-keys';

export const useGenerateQuestions = () => {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: () => questionService.generateQuestions(),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: QUESTION_QUERY_KEYS.all });
    },
  });
};
