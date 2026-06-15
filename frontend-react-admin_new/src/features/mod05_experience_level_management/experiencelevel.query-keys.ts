export const EXPERIENCELEVEL_QUERY_KEYS = {
  all: ['experienceLevel'] as const,
  lists: () => [...EXPERIENCELEVEL_QUERY_KEYS.all, 'list'] as const,
  list: (filters: string) => [...EXPERIENCELEVEL_QUERY_KEYS.lists(), { filters }] as const,
  details: () => [...EXPERIENCELEVEL_QUERY_KEYS.all, 'detail'] as const,
  detail: (id: string) => [...EXPERIENCELEVEL_QUERY_KEYS.details(), id] as const,
};
