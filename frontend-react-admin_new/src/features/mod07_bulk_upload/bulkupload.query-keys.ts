export const BULKUPLOAD_QUERY_KEYS = {
  all: ['bulkUpload'] as const,
  lists: () => [...BULKUPLOAD_QUERY_KEYS.all, 'list'] as const,
  list: (filters: string) => [...BULKUPLOAD_QUERY_KEYS.lists(), { filters }] as const,
  details: () => [...BULKUPLOAD_QUERY_KEYS.all, 'detail'] as const,
  detail: (id: string) => [...BULKUPLOAD_QUERY_KEYS.details(), id] as const,
};
