export interface QuestionDeliveryRequestDto {
  payload?: string;
}

export interface QuestionDeliveryResponseDto {
  success: boolean;
  data: QuestionDeliveryDataDto;
}

export interface QuestionDeliveryDataDto {
  id: string;
}

export interface QuestionDelivery {
  id: string;
}
