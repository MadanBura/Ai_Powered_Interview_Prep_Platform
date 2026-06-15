import { createSlice, type PayloadAction } from '@reduxjs/toolkit';
import type { Reporting } from './types/reporting.types';

interface ReportingState {
  selectedReporting: Reporting | null;
  filters: Record<string, string>;
  pagination: {
    page: number;
    limit: number;
  };
}

const initialState: ReportingState = {
  selectedReporting: null,
  filters: {},
  pagination: { page: 1, limit: 10 },
};

export const reportingSlice = createSlice({
  name: 'reporting',
  initialState,
  reducers: {
    setSelectedReporting: (state, action: PayloadAction<Reporting | null>) => {
      state.selectedReporting = action.payload;
    },
    setFilters: (state, action: PayloadAction<Record<string, string>>) => {
      state.filters = action.payload;
    },
    setPagination: (state, action: PayloadAction<{ page: number; limit: number }>) => {
      state.pagination = action.payload;
    },
  },
});

export const { setSelectedReporting, setFilters, setPagination } = reportingSlice.actions;
export default reportingSlice.reducer;
