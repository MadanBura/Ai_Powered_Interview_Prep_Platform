import { createSlice, type PayloadAction } from '@reduxjs/toolkit';
import type { Categorization } from './types/categorization.types';

interface CategorizationState {
  selectedCategorization: Categorization | null;
  filters: Record<string, string>;
  pagination: {
    page: number;
    limit: number;
  };
}

const initialState: CategorizationState = {
  selectedCategorization: null,
  filters: {},
  pagination: { page: 1, limit: 10 },
};

export const categorizationSlice = createSlice({
  name: 'categorization',
  initialState,
  reducers: {
    setSelectedCategorization: (state, action: PayloadAction<Categorization | null>) => {
      state.selectedCategorization = action.payload;
    },
    setFilters: (state, action: PayloadAction<Record<string, string>>) => {
      state.filters = action.payload;
    },
    setPagination: (state, action: PayloadAction<{ page: number; limit: number }>) => {
      state.pagination = action.payload;
    },
  },
});

export const { setSelectedCategorization, setFilters, setPagination } = categorizationSlice.actions;
export default categorizationSlice.reducer;
