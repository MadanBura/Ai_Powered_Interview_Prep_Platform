import { axiosClient } from '../../../services/http/axios-client';
import { FeedbackEngineRequestDto, FeedbackEngineResponseDto } from '../types/feedbackengine.types';

export const feedbackEngineService = {

  async retrieveFeedback(sessionId: string): Promise<FeedbackEngineResponseDto> {
    const response = await axiosClient.get<FeedbackEngineResponseDto>(`/api/v1/interviews/sessions/${sessionId}/feedback`);
    return response.data;
  },
};
