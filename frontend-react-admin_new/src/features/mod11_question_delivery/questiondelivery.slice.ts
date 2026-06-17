import { createSlice, type PayloadAction } from '@reduxjs/toolkit';
import type { QuestionDelivery } from './types/questiondelivery.types';

interface QuestionDeliveryState {
  selectedQuestionDelivery: QuestionDelivery | null;
  filters: Record<string, string>;
  pagination: {
    page: number;
    limit: number;
  };
}

const initialState: QuestionDeliveryState = {
  selectedQuestionDelivery: null,
  filters: {},
  pagination: { page: 1, limit: 10 },
};

export const questionDeliverySlice = createSlice({
  name: 'questionDelivery',
  initialState,
  reducers: {
    setSelectedQuestionDelivery: (state, action: PayloadAction<QuestionDelivery | null>) => {
      state.selectedQuestionDelivery = action.payload;
    },
    setFilters: (state, action: PayloadAction<Record<string, string>>) => {
      state.filters = action.payload;
    },
    setPagination: (state, action: PayloadAction<{ page: number; limit: number }>) => {
      state.pagination = action.payload;
    },
  },
});

export const { setSelectedQuestionDelivery, setFilters, setPagination } = questionDeliverySlice.actions;
export default questionDeliverySlice.reducer;
