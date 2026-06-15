import { createSlice, type PayloadAction } from '@reduxjs/toolkit';
import type { InterviewConfig } from './types/interviewconfig.types';

interface InterviewConfigState {
  selectedInterviewConfig: InterviewConfig | null;
  filters: Record<string, string>;
  pagination: {
    page: number;
    limit: number;
  };
}

const initialState: InterviewConfigState = {
  selectedInterviewConfig: null,
  filters: {},
  pagination: { page: 1, limit: 10 },
};

export const interviewConfigSlice = createSlice({
  name: 'interviewConfig',
  initialState,
  reducers: {
    setSelectedInterviewConfig: (state, action: PayloadAction<InterviewConfig | null>) => {
      state.selectedInterviewConfig = action.payload;
    },
    setFilters: (state, action: PayloadAction<Record<string, string>>) => {
      state.filters = action.payload;
    },
    setPagination: (state, action: PayloadAction<{ page: number; limit: number }>) => {
      state.pagination = action.payload;
    },
  },
});

export const { setSelectedInterviewConfig, setFilters, setPagination } = interviewConfigSlice.actions;
export default interviewConfigSlice.reducer;
