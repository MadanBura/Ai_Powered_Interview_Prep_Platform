import { createSlice, type PayloadAction } from '@reduxjs/toolkit';
import type { InterviewSession } from './types/interviewsession.types';

interface InterviewSessionState {
  selectedInterviewSession: InterviewSession | null;
  filters: Record<string, string>;
  pagination: {
    page: number;
    limit: number;
  };
}

const initialState: InterviewSessionState = {
  selectedInterviewSession: null,
  filters: {},
  pagination: { page: 1, limit: 10 },
};

export const interviewSessionSlice = createSlice({
  name: 'interviewSession',
  initialState,
  reducers: {
    setSelectedInterviewSession: (state, action: PayloadAction<InterviewSession | null>) => {
      state.selectedInterviewSession = action.payload;
    },
    setFilters: (state, action: PayloadAction<Record<string, string>>) => {
      state.filters = action.payload;
    },
    setPagination: (state, action: PayloadAction<{ page: number; limit: number }>) => {
      state.pagination = action.payload;
    },
  },
});

export const { setSelectedInterviewSession, setFilters, setPagination } = interviewSessionSlice.actions;
export default interviewSessionSlice.reducer;
