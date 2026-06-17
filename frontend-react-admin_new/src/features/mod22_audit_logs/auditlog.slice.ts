import { createSlice, type PayloadAction } from '@reduxjs/toolkit';
import type { AuditLog } from './types/auditlog.types';

interface AuditLogState {
  selectedAuditLog: AuditLog | null;
  filters: Record<string, string>;
  pagination: {
    page: number;
    limit: number;
  };
}

const initialState: AuditLogState = {
  selectedAuditLog: null,
  filters: {},
  pagination: { page: 1, limit: 10 },
};

export const auditLogSlice = createSlice({
  name: 'auditLog',
  initialState,
  reducers: {
    setSelectedAuditLog: (state, action: PayloadAction<AuditLog | null>) => {
      state.selectedAuditLog = action.payload;
    },
    setFilters: (state, action: PayloadAction<Record<string, string>>) => {
      state.filters = action.payload;
    },
    setPagination: (state, action: PayloadAction<{ page: number; limit: number }>) => {
      state.pagination = action.payload;
    },
  },
});

export const { setSelectedAuditLog, setFilters, setPagination } = auditLogSlice.actions;
export default auditLogSlice.reducer;
