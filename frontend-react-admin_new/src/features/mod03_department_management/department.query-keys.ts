export const DEPARTMENT_QUERY_KEYS = {
  all: ['department'] as const,
  lists: () => [...DEPARTMENT_QUERY_KEYS.all, 'list'] as const,
  list: (filters: string) => [...DEPARTMENT_QUERY_KEYS.lists(), { filters }] as const,
  details: () => [...DEPARTMENT_QUERY_KEYS.all, 'detail'] as const,
  detail: (id: string) => [...DEPARTMENT_QUERY_KEYS.details(), id] as const,
};
