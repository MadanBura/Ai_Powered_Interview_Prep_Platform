export interface CategorizationRequestDto {
  payload?: string;
}

export interface CategorizationResponseDto {
  success: boolean;
  data: CategorizationDataDto;
}

export interface CategorizationDataDto {
  id: string;
}

export interface Categorization {
  id: string;
}
