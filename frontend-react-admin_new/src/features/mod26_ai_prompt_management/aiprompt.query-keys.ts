export const AIPROMPT_QUERY_KEYS = {
  all: ['aiPrompt'] as const,
  lists: () => [...AIPROMPT_QUERY_KEYS.all, 'list'] as const,
  list: (filters: string) => [...AIPROMPT_QUERY_KEYS.lists(), { filters }] as const,
  details: () => [...AIPROMPT_QUERY_KEYS.all, 'detail'] as const,
  detail: (id: string) => [...AIPROMPT_QUERY_KEYS.details(), id] as const,
};
