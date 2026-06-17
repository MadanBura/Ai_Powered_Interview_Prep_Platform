export const NOTIFICATION_QUERY_KEYS = {
  all: ['notification'] as const,
  lists: () => [...NOTIFICATION_QUERY_KEYS.all, 'list'] as const,
  list: (filters: string) => [...NOTIFICATION_QUERY_KEYS.lists(), { filters }] as const,
  details: () => [...NOTIFICATION_QUERY_KEYS.all, 'detail'] as const,
  detail: (id: string) => [...NOTIFICATION_QUERY_KEYS.details(), id] as const,
};
