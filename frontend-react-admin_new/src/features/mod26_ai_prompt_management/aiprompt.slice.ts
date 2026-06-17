import { createSlice, type PayloadAction } from '@reduxjs/toolkit';
import type { AiPrompt } from './types/aiprompt.types';

interface AiPromptState {
  selectedAiPrompt: AiPrompt | null;
  filters: Record<string, string>;
  pagination: {
    page: number;
    limit: number;
  };
}

const initialState: AiPromptState = {
  selectedAiPrompt: null,
  filters: {},
  pagination: { page: 1, limit: 10 },
};

export const aiPromptSlice = createSlice({
  name: 'aiPrompt',
  initialState,
  reducers: {
    setSelectedAiPrompt: (state, action: PayloadAction<AiPrompt | null>) => {
      state.selectedAiPrompt = action.payload;
    },
    setFilters: (state, action: PayloadAction<Record<string, string>>) => {
      state.filters = action.payload;
    },
    setPagination: (state, action: PayloadAction<{ page: number; limit: number }>) => {
      state.pagination = action.payload;
    },
  },
});

export const { setSelectedAiPrompt, setFilters, setPagination } = aiPromptSlice.actions;
export default aiPromptSlice.reducer;
