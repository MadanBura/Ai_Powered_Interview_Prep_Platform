import { axiosClient } from '../../../services/http/axios-client';
import { AnalyticsRequestDto, AnalyticsResponseDto } from '../types/analytics.types';

export const analyticsService = {

  async getPlatformMetrics(date?: string): Promise<AnalyticsResponseDto> {
    const response = await axiosClient.get<AnalyticsResponseDto>(`/api/v1/admin/analytics/platform`, { params: { date } });
    return response.data;
  },
};
