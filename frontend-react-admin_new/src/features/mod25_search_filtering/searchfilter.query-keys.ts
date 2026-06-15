export const SEARCHFILTER_QUERY_KEYS = {
  all: ['searchFilter'] as const,
  lists: () => [...SEARCHFILTER_QUERY_KEYS.all, 'list'] as const,
  list: (filters: string) => [...SEARCHFILTER_QUERY_KEYS.lists(), { filters }] as const,
  details: () => [...SEARCHFILTER_QUERY_KEYS.all, 'detail'] as const,
  detail: (id: string) => [...SEARCHFILTER_QUERY_KEYS.details(), id] as const,
};
