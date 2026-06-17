import { axiosClient } from '../../../services/http/axios-client';
import { ExperienceLevelRequestDto, ExperienceLevelResponseDto } from '../types/experiencelevel.types';

export const experienceLevelService = {

  async listExperienceLevels(): Promise<ExperienceLevelResponseDto> {
    const response = await axiosClient.get<ExperienceLevelResponseDto>(`/api/v1/admin/experience-levels`);
    return response.data;
  },
};
