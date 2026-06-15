import { createSlice, type PayloadAction } from '@reduxjs/toolkit';
import type { Auth } from './types/auth.types';

interface AuthState {
  selectedAuth: Auth | null;
  filters: Record<string, string>;
  pagination: {
    page: number;
    limit: number;
  };
}

const initialState: AuthState = {
  selectedAuth: null,
  filters: {},
  pagination: { page: 1, limit: 10 },
};

export const authSlice = createSlice({
  name: 'auth',
  initialState,
  reducers: {
    setSelectedAuth: (state, action: PayloadAction<Auth | null>) => {
      state.selectedAuth = action.payload;
    },
    setFilters: (state, action: PayloadAction<Record<string, string>>) => {
      state.filters = action.payload;
    },
    setPagination: (state, action: PayloadAction<{ page: number; limit: number }>) => {
      state.pagination = action.payload;
    },
  },
});

export const { setSelectedAuth, setFilters, setPagination } = authSlice.actions;
export default authSlice.reducer;
