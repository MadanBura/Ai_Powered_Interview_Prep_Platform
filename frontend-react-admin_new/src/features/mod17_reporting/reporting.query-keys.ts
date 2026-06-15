export const REPORTING_QUERY_KEYS = {
  all: ['reporting'] as const,
  lists: () => [...REPORTING_QUERY_KEYS.all, 'list'] as const,
  list: (filters: string) => [...REPORTING_QUERY_KEYS.lists(), { filters }] as const,
  details: () => [...REPORTING_QUERY_KEYS.all, 'detail'] as const,
  detail: (id: string) => [...REPORTING_QUERY_KEYS.details(), id] as const,
};
