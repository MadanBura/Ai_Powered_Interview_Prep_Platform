export const QUESTIONDELIVERY_QUERY_KEYS = {
  all: ['questionDelivery'] as const,
  lists: () => [...QUESTIONDELIVERY_QUERY_KEYS.all, 'list'] as const,
  list: (filters: string) => [...QUESTIONDELIVERY_QUERY_KEYS.lists(), { filters }] as const,
  details: () => [...QUESTIONDELIVERY_QUERY_KEYS.all, 'detail'] as const,
  detail: (id: string) => [...QUESTIONDELIVERY_QUERY_KEYS.details(), id] as const,
};
