export const TECHNOLOGY_QUERY_KEYS = {
  all: ['technology'] as const,
  lists: () => [...TECHNOLOGY_QUERY_KEYS.all, 'list'] as const,
  list: (filters: string) => [...TECHNOLOGY_QUERY_KEYS.lists(), { filters }] as const,
  details: () => [...TECHNOLOGY_QUERY_KEYS.all, 'detail'] as const,
  detail: (id: string) => [...TECHNOLOGY_QUERY_KEYS.details(), id] as const,
};
