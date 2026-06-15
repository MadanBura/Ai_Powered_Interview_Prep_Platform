import { createSlice, type PayloadAction } from '@reduxjs/toolkit';
import type { FileStorage } from './types/filestorage.types';

interface FileStorageState {
  selectedFileStorage: FileStorage | null;
  filters: Record<string, string>;
  pagination: {
    page: number;
    limit: number;
  };
}

const initialState: FileStorageState = {
  selectedFileStorage: null,
  filters: {},
  pagination: { page: 1, limit: 10 },
};

export const fileStorageSlice = createSlice({
  name: 'fileStorage',
  initialState,
  reducers: {
    setSelectedFileStorage: (state, action: PayloadAction<FileStorage | null>) => {
      state.selectedFileStorage = action.payload;
    },
    setFilters: (state, action: PayloadAction<Record<string, string>>) => {
      state.filters = action.payload;
    },
    setPagination: (state, action: PayloadAction<{ page: number; limit: number }>) => {
      state.pagination = action.payload;
    },
  },
});

export const { setSelectedFileStorage, setFilters, setPagination } = fileStorageSlice.actions;
export default fileStorageSlice.reducer;
