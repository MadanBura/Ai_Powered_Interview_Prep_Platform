export interface RecommendationRequestDto {
  payload?: string;
}

export interface RecommendationResponseDto {
  success: boolean;
  data: RecommendationDataDto;
}

export interface RecommendationDataDto {
  id: string;
}

export interface Recommendation {
  id: string;
}
