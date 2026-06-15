export const PROFILE_QUERY_KEYS = {
  all: ['profile'] as const,
  lists: () => [...PROFILE_QUERY_KEYS.all, 'list'] as const,
  list: (filters: string) => [...PROFILE_QUERY_KEYS.lists(), { filters }] as const,
  details: () => [...PROFILE_QUERY_KEYS.all, 'detail'] as const,
  detail: (id: string) => [...PROFILE_QUERY_KEYS.details(), id] as const,
};
