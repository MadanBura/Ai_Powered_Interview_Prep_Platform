import { useQuery } from '@tanstack/react-query';
import { aiPromptService } from '../../services/aiprompt.service';
import { AIPROMPT_QUERY_KEYS } from '../../aiprompt.query-keys';

export const useListPrompts = () => {
  return useQuery({
    queryKey: AIPROMPT_QUERY_KEYS.all,
    queryFn: () => aiPromptService.listPrompts()
  });
};
