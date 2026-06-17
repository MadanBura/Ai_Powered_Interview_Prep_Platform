import { axiosClient } from '../../../services/http/axios-client';
import { InterviewConfigRequestDto, InterviewConfigResponseDto } from '../types/interviewconfig.types';

export const interviewConfigService = {

  async fetchOptions(): Promise<InterviewConfigResponseDto> {
    const response = await axiosClient.get<InterviewConfigResponseDto>(`/api/v1/interviews/configuration/options`);
    return response.data;
  },
};
