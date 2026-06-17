export const RECOMMENDATION_QUERY_KEYS = {
  all: ['recommendation'] as const,
  lists: () => [...RECOMMENDATION_QUERY_KEYS.all, 'list'] as const,
  list: (filters: string) => [...RECOMMENDATION_QUERY_KEYS.lists(), { filters }] as const,
  details: () => [...RECOMMENDATION_QUERY_KEYS.all, 'detail'] as const,
  detail: (id: string) => [...RECOMMENDATION_QUERY_KEYS.details(), id] as const,
};
