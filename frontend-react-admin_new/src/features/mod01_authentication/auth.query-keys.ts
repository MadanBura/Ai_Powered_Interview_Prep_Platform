export const AUTH_QUERY_KEYS = {
  all: ['auth'] as const,
  lists: () => [...AUTH_QUERY_KEYS.all, 'list'] as const,
  list: (filters: string) => [...AUTH_QUERY_KEYS.lists(), { filters }] as const,
  details: () => [...AUTH_QUERY_KEYS.all, 'detail'] as const,
  detail: (id: string) => [...AUTH_QUERY_KEYS.details(), id] as const,
};
