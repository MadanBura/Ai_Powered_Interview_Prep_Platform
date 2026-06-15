import { createSlice, type PayloadAction } from '@reduxjs/toolkit';
import type { FeedbackEngine } from './types/feedbackengine.types';

interface FeedbackEngineState {
  selectedFeedbackEngine: FeedbackEngine | null;
  filters: Record<string, string>;
  pagination: {
    page: number;
    limit: number;
  };
}

const initialState: FeedbackEngineState = {
  selectedFeedbackEngine: null,
  filters: {},
  pagination: { page: 1, limit: 10 },
};

export const feedbackEngineSlice = createSlice({
  name: 'feedbackEngine',
  initialState,
  reducers: {
    setSelectedFeedbackEngine: (state, action: PayloadAction<FeedbackEngine | null>) => {
      state.selectedFeedbackEngine = action.payload;
    },
    setFilters: (state, action: PayloadAction<Record<string, string>>) => {
      state.filters = action.payload;
    },
    setPagination: (state, action: PayloadAction<{ page: number; limit: number }>) => {
      state.pagination = action.payload;
    },
  },
});

export const { setSelectedFeedbackEngine, setFilters, setPagination } = feedbackEngineSlice.actions;
export default feedbackEngineSlice.reducer;
