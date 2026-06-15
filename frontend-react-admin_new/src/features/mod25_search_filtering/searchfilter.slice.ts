import { createSlice, type PayloadAction } from '@reduxjs/toolkit';
import type { SearchFilter } from './types/searchfilter.types';

interface SearchFilterState {
  selectedSearchFilter: SearchFilter | null;
  filters: Record<string, string>;
  pagination: {
    page: number;
    limit: number;
  };
}

const initialState: SearchFilterState = {
  selectedSearchFilter: null,
  filters: {},
  pagination: { page: 1, limit: 10 },
};

export const searchFilterSlice = createSlice({
  name: 'searchFilter',
  initialState,
  reducers: {
    setSelectedSearchFilter: (state, action: PayloadAction<SearchFilter | null>) => {
      state.selectedSearchFilter = action.payload;
    },
    setFilters: (state, action: PayloadAction<Record<string, string>>) => {
      state.filters = action.payload;
    },
    setPagination: (state, action: PayloadAction<{ page: number; limit: number }>) => {
      state.pagination = action.payload;
    },
  },
});

export const { setSelectedSearchFilter, setFilters, setPagination } = searchFilterSlice.actions;
export default searchFilterSlice.reducer;
