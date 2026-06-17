import { createSlice, type PayloadAction } from '@reduxjs/toolkit';
import type { BulkUpload } from './types/bulkupload.types';

interface BulkUploadState {
  selectedBulkUpload: BulkUpload | null;
  filters: Record<string, string>;
  pagination: {
    page: number;
    limit: number;
  };
}

const initialState: BulkUploadState = {
  selectedBulkUpload: null,
  filters: {},
  pagination: { page: 1, limit: 10 },
};

export const bulkUploadSlice = createSlice({
  name: 'bulkUpload',
  initialState,
  reducers: {
    setSelectedBulkUpload: (state, action: PayloadAction<BulkUpload | null>) => {
      state.selectedBulkUpload = action.payload;
    },
    setFilters: (state, action: PayloadAction<Record<string, string>>) => {
      state.filters = action.payload;
    },
    setPagination: (state, action: PayloadAction<{ page: number; limit: number }>) => {
      state.pagination = action.payload;
    },
  },
});

export const { setSelectedBulkUpload, setFilters, setPagination } = bulkUploadSlice.actions;
export default bulkUploadSlice.reducer;
