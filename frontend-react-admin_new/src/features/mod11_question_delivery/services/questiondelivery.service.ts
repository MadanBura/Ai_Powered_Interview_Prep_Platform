import { axiosClient } from '../../../services/http/axios-client';
import { QuestionDeliveryRequestDto, QuestionDeliveryResponseDto } from '../types/questiondelivery.types';

export const questionDeliveryService = {

  async retrieveQuestions(sessionId: string): Promise<QuestionDeliveryResponseDto> {
    const response = await axiosClient.get<QuestionDeliveryResponseDto>(`/api/v1/interviews/sessions/${sessionId}/questions`);
    return response.data;
  },
};
