export const AUDITLOG_QUERY_KEYS = {
  all: ['auditLog'] as const,
  lists: () => [...AUDITLOG_QUERY_KEYS.all, 'list'] as const,
  list: (filters: string) => [...AUDITLOG_QUERY_KEYS.lists(), { filters }] as const,
  details: () => [...AUDITLOG_QUERY_KEYS.all, 'detail'] as const,
  detail: (id: string) => [...AUDITLOG_QUERY_KEYS.details(), id] as const,
};
