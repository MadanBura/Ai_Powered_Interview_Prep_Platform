export const FILESTORAGE_QUERY_KEYS = {
  all: ['fileStorage'] as const,
  lists: () => [...FILESTORAGE_QUERY_KEYS.all, 'list'] as const,
  list: (filters: string) => [...FILESTORAGE_QUERY_KEYS.lists(), { filters }] as const,
  details: () => [...FILESTORAGE_QUERY_KEYS.all, 'detail'] as const,
  detail: (id: string) => [...FILESTORAGE_QUERY_KEYS.details(), id] as const,
};
