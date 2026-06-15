export const AIEVALUATION_QUERY_KEYS = {
  all: ['aiEvaluation'] as const,
  lists: () => [...AIEVALUATION_QUERY_KEYS.all, 'list'] as const,
  list: (filters: string) => [...AIEVALUATION_QUERY_KEYS.lists(), { filters }] as const,
  details: () => [...AIEVALUATION_QUERY_KEYS.all, 'detail'] as const,
  detail: (id: string) => [...AIEVALUATION_QUERY_KEYS.details(), id] as const,
};
