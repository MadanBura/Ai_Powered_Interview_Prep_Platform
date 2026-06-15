import { createSlice, type PayloadAction } from '@reduxjs/toolkit';
import type { Profile } from './types/profile.types';

interface ProfileState {
  selectedProfile: Profile | null;
  filters: Record<string, string>;
  pagination: {
    page: number;
    limit: number;
  };
}

const initialState: ProfileState = {
  selectedProfile: null,
  filters: {},
  pagination: { page: 1, limit: 10 },
};

export const profileSlice = createSlice({
  name: 'profile',
  initialState,
  reducers: {
    setSelectedProfile: (state, action: PayloadAction<Profile | null>) => {
      state.selectedProfile = action.payload;
    },
    setFilters: (state, action: PayloadAction<Record<string, string>>) => {
      state.filters = action.payload;
    },
    setPagination: (state, action: PayloadAction<{ page: number; limit: number }>) => {
      state.pagination = action.payload;
    },
  },
});

export const { setSelectedProfile, setFilters, setPagination } = profileSlice.actions;
export default profileSlice.reducer;
