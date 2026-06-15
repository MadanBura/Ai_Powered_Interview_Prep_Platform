export const ADMINUSER_QUERY_KEYS = {
  all: ['adminUser'] as const,
  lists: () => [...ADMINUSER_QUERY_KEYS.all, 'list'] as const,
  list: (filters: string) => [...ADMINUSER_QUERY_KEYS.lists(), { filters }] as const,
  details: () => [...ADMINUSER_QUERY_KEYS.all, 'detail'] as const,
  detail: (id: string) => [...ADMINUSER_QUERY_KEYS.details(), id] as const,
};
