import { createSlice, type PayloadAction } from '@reduxjs/toolkit';
import type { Analytics } from './types/analytics.types';

interface AnalyticsState {
  selectedAnalytics: Analytics | null;
  filters: Record<string, string>;
  pagination: {
    page: number;
    limit: number;
  };
}

const initialState: AnalyticsState = {
  selectedAnalytics: null,
  filters: {},
  pagination: { page: 1, limit: 10 },
};

export const analyticsSlice = createSlice({
  name: 'analytics',
  initialState,
  reducers: {
    setSelectedAnalytics: (state, action: PayloadAction<Analytics | null>) => {
      state.selectedAnalytics = action.payload;
    },
    setFilters: (state, action: PayloadAction<Record<string, string>>) => {
      state.filters = action.payload;
    },
    setPagination: (state, action: PayloadAction<{ page: number; limit: number }>) => {
      state.pagination = action.payload;
    },
  },
});

export const { setSelectedAnalytics, setFilters, setPagination } = analyticsSlice.actions;
export default analyticsSlice.reducer;
