export const INTERVIEWSESSION_QUERY_KEYS = {
  all: ['interviewSession'] as const,
  lists: () => [...INTERVIEWSESSION_QUERY_KEYS.all, 'list'] as const,
  list: (filters: string) => [...INTERVIEWSESSION_QUERY_KEYS.lists(), { filters }] as const,
  details: () => [...INTERVIEWSESSION_QUERY_KEYS.all, 'detail'] as const,
  detail: (id: string) => [...INTERVIEWSESSION_QUERY_KEYS.details(), id] as const,
};
