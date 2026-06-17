import { createSlice, type PayloadAction } from '@reduxjs/toolkit';
import type { AiEvaluation } from './types/aievaluation.types';

interface AiEvaluationState {
  selectedAiEvaluation: AiEvaluation | null;
  filters: Record<string, string>;
  pagination: {
    page: number;
    limit: number;
  };
}

const initialState: AiEvaluationState = {
  selectedAiEvaluation: null,
  filters: {},
  pagination: { page: 1, limit: 10 },
};

export const aiEvaluationSlice = createSlice({
  name: 'aiEvaluation',
  initialState,
  reducers: {
    setSelectedAiEvaluation: (state, action: PayloadAction<AiEvaluation | null>) => {
      state.selectedAiEvaluation = action.payload;
    },
    setFilters: (state, action: PayloadAction<Record<string, string>>) => {
      state.filters = action.payload;
    },
    setPagination: (state, action: PayloadAction<{ page: number; limit: number }>) => {
      state.pagination = action.payload;
    },
  },
});

export const { setSelectedAiEvaluation, setFilters, setPagination } = aiEvaluationSlice.actions;
export default aiEvaluationSlice.reducer;
