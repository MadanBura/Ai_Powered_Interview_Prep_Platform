export const QUESTION_QUERY_KEYS = {
  all: ['question'] as const,
  lists: () => [...QUESTION_QUERY_KEYS.all, 'list'] as const,
  list: (filters: string) => [...QUESTION_QUERY_KEYS.lists(), { filters }] as const,
  details: () => [...QUESTION_QUERY_KEYS.all, 'detail'] as const,
  detail: (id: string) => [...QUESTION_QUERY_KEYS.details(), id] as const,
};
