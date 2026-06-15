export const ANALYTICS_QUERY_KEYS = {
  all: ['analytics'] as const,
  lists: () => [...ANALYTICS_QUERY_KEYS.all, 'list'] as const,
  list: (filters: string) => [...ANALYTICS_QUERY_KEYS.lists(), { filters }] as const,
  details: () => [...ANALYTICS_QUERY_KEYS.all, 'detail'] as const,
  detail: (id: string) => [...ANALYTICS_QUERY_KEYS.details(), id] as const,
};
