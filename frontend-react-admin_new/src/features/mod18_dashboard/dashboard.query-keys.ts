export const DASHBOARD_QUERY_KEYS = {
  all: ['dashboard'] as const,
  lists: () => [...DASHBOARD_QUERY_KEYS.all, 'list'] as const,
  list: (filters: string) => [...DASHBOARD_QUERY_KEYS.lists(), { filters }] as const,
  details: () => [...DASHBOARD_QUERY_KEYS.all, 'detail'] as const,
  detail: (id: string) => [...DASHBOARD_QUERY_KEYS.details(), id] as const,
};
