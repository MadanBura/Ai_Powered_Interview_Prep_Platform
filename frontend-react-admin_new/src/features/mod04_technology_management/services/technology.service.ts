import { axiosClient } from '../../../services/http/axios-client';
import { TechnologyRequestDto, TechnologyResponseDto } from '../types/technology.types';

export const technologyService = {

  async listTechnologies(): Promise<TechnologyResponseDto> {
    const response = await axiosClient.get<TechnologyResponseDto>(`/api/v1/admin/technologies`);
    return response.data;
  },
};
