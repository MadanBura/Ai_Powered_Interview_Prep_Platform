import { createSlice, type PayloadAction } from '@reduxjs/toolkit';
import type { Question } from './types/question.types';

interface QuestionState {
  selectedQuestion: Question | null;
  filters: Record<string, string>;
  pagination: {
    page: number;
    limit: number;
  };
}

const initialState: QuestionState = {
  selectedQuestion: null,
  filters: {},
  pagination: { page: 1, limit: 10 },
};

export const questionSlice = createSlice({
  name: 'question',
  initialState,
  reducers: {
    setSelectedQuestion: (state, action: PayloadAction<Question | null>) => {
      state.selectedQuestion = action.payload;
    },
    setFilters: (state, action: PayloadAction<Record<string, string>>) => {
      state.filters = action.payload;
    },
    setPagination: (state, action: PayloadAction<{ page: number; limit: number }>) => {
      state.pagination = action.payload;
    },
  },
});

export const { setSelectedQuestion, setFilters, setPagination } = questionSlice.actions;
export default questionSlice.reducer;
