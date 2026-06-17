import { axiosClient } from '../../../services/http/axios-client';
import { AiEvaluationRequestDto, AiEvaluationResponseDto } from '../types/aievaluation.types';

export const aiEvaluationService = {

  async retrieveEvaluation(sessionId: string): Promise<AiEvaluationResponseDto> {
    const response = await axiosClient.get<AiEvaluationResponseDto>(`/api/v1/interviews/sessions/${sessionId}/evaluation`);
    return response.data;
  },
};
