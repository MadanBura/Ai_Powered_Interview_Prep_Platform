import { axiosClient } from '../../../services/http/axios-client';

export const templateService = {
  async getTemplates(): Promise<any> {
    const response = await axiosClient.get(`/api/v1/interviews/configuration/templates`);
    return response.data;
  },
};
