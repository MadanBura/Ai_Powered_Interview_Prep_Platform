import { axiosClient } from '../../../services/http/axios-client';
import { InterviewSessionRequestDto, InterviewSessionResponseDto } from '../types/interviewsession.types';

export const interviewSessionService = {

  async initializeSession(request: InterviewSessionRequestDto): Promise<InterviewSessionResponseDto> {
    const response = await axiosClient.post<InterviewSessionResponseDto>(`/api/v1/interviews/sessions`, request);
    return response.data;
  },
};
