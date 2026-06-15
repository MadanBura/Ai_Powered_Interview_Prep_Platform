import { createSlice, type PayloadAction } from '@reduxjs/toolkit';
import type { Dashboard } from './types/dashboard.types';

interface DashboardState {
  selectedDashboard: Dashboard | null;
  filters: Record<string, string>;
  pagination: {
    page: number;
    limit: number;
  };
}

const initialState: DashboardState = {
  selectedDashboard: null,
  filters: {},
  pagination: { page: 1, limit: 10 },
};

export const dashboardSlice = createSlice({
  name: 'dashboard',
  initialState,
  reducers: {
    setSelectedDashboard: (state, action: PayloadAction<Dashboard | null>) => {
      state.selectedDashboard = action.payload;
    },
    setFilters: (state, action: PayloadAction<Record<string, string>>) => {
      state.filters = action.payload;
    },
    setPagination: (state, action: PayloadAction<{ page: number; limit: number }>) => {
      state.pagination = action.payload;
    },
  },
});

export const { setSelectedDashboard, setFilters, setPagination } = dashboardSlice.actions;
export default dashboardSlice.reducer;
