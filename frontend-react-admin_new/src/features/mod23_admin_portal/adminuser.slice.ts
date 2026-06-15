import { createSlice, type PayloadAction } from '@reduxjs/toolkit';
import type { AdminUser } from './types/adminuser.types';

interface AdminUserState {
  selectedAdminUser: AdminUser | null;
  filters: Record<string, string>;
  pagination: {
    page: number;
    limit: number;
  };
}

const initialState: AdminUserState = {
  selectedAdminUser: null,
  filters: {},
  pagination: { page: 1, limit: 10 },
};

export const adminUserSlice = createSlice({
  name: 'adminUser',
  initialState,
  reducers: {
    setSelectedAdminUser: (state, action: PayloadAction<AdminUser | null>) => {
      state.selectedAdminUser = action.payload;
    },
    setFilters: (state, action: PayloadAction<Record<string, string>>) => {
      state.filters = action.payload;
    },
    setPagination: (state, action: PayloadAction<{ page: number; limit: number }>) => {
      state.pagination = action.payload;
    },
  },
});

export const { setSelectedAdminUser, setFilters, setPagination } = adminUserSlice.actions;
export default adminUserSlice.reducer;
