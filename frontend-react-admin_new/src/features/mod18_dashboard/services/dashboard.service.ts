import { axiosClient } from '../../../services/http/axios-client';
import { DashboardRequestDto, DashboardResponseDto } from '../types/dashboard.types';

export const dashboardService = {

  async retrieveAdminDashboard(date?: string): Promise<DashboardResponseDto> {
    const response = await axiosClient.get<DashboardResponseDto>(`/api/v1/admin/dashboard`, { params: { date } });
    return response.data;
  },

  async getCharts(date?: string): Promise<any> {
    const response = await axiosClient.get(`/api/v1/admin/dashboard/charts`, { params: { date } });
    return response.data;
  },

  async getRecentActivity(date?: string): Promise<any> {
    const response = await axiosClient.get(`/api/v1/admin/dashboard/activity`, { params: { date } });
    return response.data;
  },
};
