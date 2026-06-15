import { useMutation, useQueryClient } from '@tanstack/react-query';
import { aiPromptService } from '../../services/aiprompt.service';
import { AiPromptRequestDto } from '../../types/aiprompt.types';
import { AIPROMPT_QUERY_KEYS } from '../../aiprompt.query-keys';

export const useCreateAiPrompt = () => {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: (data: AiPromptRequestDto) => aiPromptService.createPrompt(data),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: AIPROMPT_QUERY_KEYS.all });
    },
  });
};

export const useUpdateAiPrompt = () => {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: ({ id, data }: { id: string; data: AiPromptRequestDto }) => 
      aiPromptService.updatePrompt(id, data),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: AIPROMPT_QUERY_KEYS.all });
    },
  });
};

export const useDeleteAiPrompt = () => {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: (id: string) => aiPromptService.deletePrompt(id),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: AIPROMPT_QUERY_KEYS.all });
    },
  });
};
