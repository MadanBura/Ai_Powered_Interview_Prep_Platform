export const CATEGORIZATION_QUERY_KEYS = {
  all: ['categorization'] as const,
  lists: () => [...CATEGORIZATION_QUERY_KEYS.all, 'list'] as const,
  list: (filters: string) => [...CATEGORIZATION_QUERY_KEYS.lists(), { filters }] as const,
  details: () => [...CATEGORIZATION_QUERY_KEYS.all, 'detail'] as const,
  detail: (id: string) => [...CATEGORIZATION_QUERY_KEYS.details(), id] as const,
};
