import { axiosClient } from '../../../services/http/axios-client';
import { RecommendationRequestDto, RecommendationResponseDto } from '../types/recommendation.types';

export const recommendationService = {

  async retrieveRecommendations(): Promise<RecommendationResponseDto> {
    const response = await axiosClient.get<RecommendationResponseDto>(`/api/v1/candidates/recommendations`);
    return response.data;
  },
};
