import { createSlice, type PayloadAction } from '@reduxjs/toolkit';
import type { Department } from './types/department.types';

interface DepartmentState {
  selectedDepartment: Department | null;
  filters: Record<string, string>;
  pagination: {
    page: number;
    limit: number;
  };
}

const initialState: DepartmentState = {
  selectedDepartment: null,
  filters: {},
  pagination: { page: 1, limit: 10 },
};

export const departmentSlice = createSlice({
  name: 'department',
  initialState,
  reducers: {
    setSelectedDepartment: (state, action: PayloadAction<Department | null>) => {
      state.selectedDepartment = action.payload;
    },
    setFilters: (state, action: PayloadAction<Record<string, string>>) => {
      state.filters = action.payload;
    },
    setPagination: (state, action: PayloadAction<{ page: number; limit: number }>) => {
      state.pagination = action.payload;
    },
  },
});

export const { setSelectedDepartment, setFilters, setPagination } = departmentSlice.actions;
export default departmentSlice.reducer;
