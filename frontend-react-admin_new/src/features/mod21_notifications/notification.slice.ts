import { createSlice, type PayloadAction } from '@reduxjs/toolkit';
import type { Notification } from './types/notification.types';

interface NotificationState {
  selectedNotification: Notification | null;
  filters: Record<string, string>;
  pagination: {
    page: number;
    limit: number;
  };
}

const initialState: NotificationState = {
  selectedNotification: null,
  filters: {},
  pagination: { page: 1, limit: 10 },
};

export const notificationSlice = createSlice({
  name: 'notification',
  initialState,
  reducers: {
    setSelectedNotification: (state, action: PayloadAction<Notification | null>) => {
      state.selectedNotification = action.payload;
    },
    setFilters: (state, action: PayloadAction<Record<string, string>>) => {
      state.filters = action.payload;
    },
    setPagination: (state, action: PayloadAction<{ page: number; limit: number }>) => {
      state.pagination = action.payload;
    },
  },
});

export const { setSelectedNotification, setFilters, setPagination } = notificationSlice.actions;
export default notificationSlice.reducer;
