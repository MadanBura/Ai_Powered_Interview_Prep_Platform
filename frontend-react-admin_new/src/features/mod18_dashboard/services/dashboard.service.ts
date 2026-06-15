import { axiosClient } from '../../../services/http/axios-client';
import { DashboardRequestDto, DashboardResponseDto } from '../types/dashboard.types';

export const dashboardService = {

  async retrieveAdminDashboard(): Promise<DashboardResponseDto> {
    const response = await axiosClient.get<DashboardResponseDto>(`/api/v1/admin/dashboard`);
    return response.data;
  },

  async getCharts(): Promise<any> {
    const response = await axiosClient.get(`/api/v1/admin/dashboard/charts`);
    return response.data;
  },

  async getRecentActivity(): Promise<any> {
    const response = await axiosClient.get(`/api/v1/admin/dashboard/activity`);
    return response.data;
  },
};
