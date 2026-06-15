export const SYSTEMCONFIG_QUERY_KEYS = {
  all: ['systemConfig'] as const,
  lists: () => [...SYSTEMCONFIG_QUERY_KEYS.all, 'list'] as const,
  list: (filters: string) => [...SYSTEMCONFIG_QUERY_KEYS.lists(), { filters }] as const,
  details: () => [...SYSTEMCONFIG_QUERY_KEYS.all, 'detail'] as const,
  detail: (id: string) => [...SYSTEMCONFIG_QUERY_KEYS.details(), id] as const,
};
