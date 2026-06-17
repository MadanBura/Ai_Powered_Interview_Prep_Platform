import { createSlice, type PayloadAction } from '@reduxjs/toolkit';
import type { ExperienceLevel } from './types/experiencelevel.types';

interface ExperienceLevelState {
  selectedExperienceLevel: ExperienceLevel | null;
  filters: Record<string, string>;
  pagination: {
    page: number;
    limit: number;
  };
}

const initialState: ExperienceLevelState = {
  selectedExperienceLevel: null,
  filters: {},
  pagination: { page: 1, limit: 10 },
};

export const experienceLevelSlice = createSlice({
  name: 'experienceLevel',
  initialState,
  reducers: {
    setSelectedExperienceLevel: (state, action: PayloadAction<ExperienceLevel | null>) => {
      state.selectedExperienceLevel = action.payload;
    },
    setFilters: (state, action: PayloadAction<Record<string, string>>) => {
      state.filters = action.payload;
    },
    setPagination: (state, action: PayloadAction<{ page: number; limit: number }>) => {
      state.pagination = action.payload;
    },
  },
});

export const { setSelectedExperienceLevel, setFilters, setPagination } = experienceLevelSlice.actions;
export default experienceLevelSlice.reducer;
