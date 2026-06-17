export const FEEDBACKENGINE_QUERY_KEYS = {
  all: ['feedbackEngine'] as const,
  lists: () => [...FEEDBACKENGINE_QUERY_KEYS.all, 'list'] as const,
  list: (filters: string) => [...FEEDBACKENGINE_QUERY_KEYS.lists(), { filters }] as const,
  details: () => [...FEEDBACKENGINE_QUERY_KEYS.all, 'detail'] as const,
  detail: (id: string) => [...FEEDBACKENGINE_QUERY_KEYS.details(), id] as const,
};
