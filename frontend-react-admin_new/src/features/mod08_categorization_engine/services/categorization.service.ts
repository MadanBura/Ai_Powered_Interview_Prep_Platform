import { axiosClient } from '../../../services/http/axios-client';
import { CategorizationRequestDto, CategorizationResponseDto } from '../types/categorization.types';

export const categorizationService = {

  async viewResults(taskId: string): Promise<CategorizationResponseDto> {
    const response = await axiosClient.get<CategorizationResponseDto>(`/api/v1/admin/questions/ingest/${taskId}/results`);
    return response.data;
  },
};
