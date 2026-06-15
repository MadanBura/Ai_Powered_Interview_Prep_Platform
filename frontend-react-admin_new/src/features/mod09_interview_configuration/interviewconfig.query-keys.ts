export const INTERVIEWCONFIG_QUERY_KEYS = {
  all: ['interviewConfig'] as const,
  lists: () => [...INTERVIEWCONFIG_QUERY_KEYS.all, 'list'] as const,
  list: (filters: string) => [...INTERVIEWCONFIG_QUERY_KEYS.lists(), { filters }] as const,
  details: () => [...INTERVIEWCONFIG_QUERY_KEYS.all, 'detail'] as const,
  detail: (id: string) => [...INTERVIEWCONFIG_QUERY_KEYS.details(), id] as const,
};
