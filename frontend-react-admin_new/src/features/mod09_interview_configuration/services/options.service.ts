import { axiosClient } from '../../../services/http/axios-client';

export const optionsService = {
  async getDepartments(): Promise<any> {
    const response = await axiosClient.get(`/api/v1/admin/options/departments`);
    return response.data;
  },

  async getExperienceLevels(): Promise<any> {
    const response = await axiosClient.get(`/api/v1/admin/options/experience-levels`);
    return response.data;
  },
};
