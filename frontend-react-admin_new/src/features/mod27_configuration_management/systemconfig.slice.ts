import { createSlice, type PayloadAction } from '@reduxjs/toolkit';
import type { SystemConfig } from './types/systemconfig.types';

interface SystemConfigState {
  selectedSystemConfig: SystemConfig | null;
  filters: Record<string, string>;
  pagination: {
    page: number;
    limit: number;
  };
}

const initialState: SystemConfigState = {
  selectedSystemConfig: null,
  filters: {},
  pagination: { page: 1, limit: 10 },
};

export const systemConfigSlice = createSlice({
  name: 'systemConfig',
  initialState,
  reducers: {
    setSelectedSystemConfig: (state, action: PayloadAction<SystemConfig | null>) => {
      state.selectedSystemConfig = action.payload;
    },
    setFilters: (state, action: PayloadAction<Record<string, string>>) => {
      state.filters = action.payload;
    },
    setPagination: (state, action: PayloadAction<{ page: number; limit: number }>) => {
      state.pagination = action.payload;
    },
  },
});

export const { setSelectedSystemConfig, setFilters, setPagination } = systemConfigSlice.actions;
export default systemConfigSlice.reducer;
