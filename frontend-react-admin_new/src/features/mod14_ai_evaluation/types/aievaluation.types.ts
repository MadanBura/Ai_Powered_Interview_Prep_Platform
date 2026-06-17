export interface AiEvaluationRequestDto {
  payload?: string;
}

export interface AiEvaluationResponseDto {
  success: boolean;
  data: AiEvaluationDataDto;
}

export interface AiEvaluationDataDto {
  id: string;
}

export interface AiEvaluation {
  id: string;
}
