import { createSlice, type PayloadAction } from '@reduxjs/toolkit';
import type { Recommendation } from './types/recommendation.types';

interface RecommendationState {
  selectedRecommendation: Recommendation | null;
  filters: Record<string, string>;
  pagination: {
    page: number;
    limit: number;
  };
}

const initialState: RecommendationState = {
  selectedRecommendation: null,
  filters: {},
  pagination: { page: 1, limit: 10 },
};

export const recommendationSlice = createSlice({
  name: 'recommendation',
  initialState,
  reducers: {
    setSelectedRecommendation: (state, action: PayloadAction<Recommendation | null>) => {
      state.selectedRecommendation = action.payload;
    },
    setFilters: (state, action: PayloadAction<Record<string, string>>) => {
      state.filters = action.payload;
    },
    setPagination: (state, action: PayloadAction<{ page: number; limit: number }>) => {
      state.pagination = action.payload;
    },
  },
});

export const { setSelectedRecommendation, setFilters, setPagination } = recommendationSlice.actions;
export default recommendationSlice.reducer;
