import { axiosClient } from '../../../services/http/axios-client';
import { SystemConfigRequestDto, SystemConfigResponseDto } from '../types/systemconfig.types';

export const systemConfigService = {

  async listConfig(): Promise<SystemConfigResponseDto> {
    const response = await axiosClient.get<SystemConfigResponseDto>(`/api/v1/admin/config`);
    return response.data;
  },
};
