import { axiosClient } from '../../../services/http/axios-client';

export const evaluationService = {
  async getMetrics(): Promise<any> {
    const response = await axiosClient.get(`/api/v1/admin/analytics/evaluations/metrics`);
    return response.data;
  },

  async getFlags(): Promise<any> {
    const response = await axiosClient.get(`/api/v1/admin/analytics/evaluations/flags`);
    return response.data;
  },
};
