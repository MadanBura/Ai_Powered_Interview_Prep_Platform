import { createSlice, type PayloadAction } from '@reduxjs/toolkit';
import type { Technology } from './types/technology.types';

interface TechnologyState {
  selectedTechnology: Technology | null;
  filters: Record<string, string>;
  pagination: {
    page: number;
    limit: number;
  };
}

const initialState: TechnologyState = {
  selectedTechnology: null,
  filters: {},
  pagination: { page: 1, limit: 10 },
};

export const technologySlice = createSlice({
  name: 'technology',
  initialState,
  reducers: {
    setSelectedTechnology: (state, action: PayloadAction<Technology | null>) => {
      state.selectedTechnology = action.payload;
    },
    setFilters: (state, action: PayloadAction<Record<string, string>>) => {
      state.filters = action.payload;
    },
    setPagination: (state, action: PayloadAction<{ page: number; limit: number }>) => {
      state.pagination = action.payload;
    },
  },
});

export const { setSelectedTechnology, setFilters, setPagination } = technologySlice.actions;
export default technologySlice.reducer;
