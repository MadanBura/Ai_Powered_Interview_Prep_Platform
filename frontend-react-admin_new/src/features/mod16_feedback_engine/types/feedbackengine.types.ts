export interface FeedbackEngineRequestDto {
  payload?: string;
}

export interface FeedbackEngineResponseDto {
  success: boolean;
  data: FeedbackEngineDataDto;
}

export interface FeedbackEngineDataDto {
  id: string;
}

export interface FeedbackEngine {
  id: string;
}
