export interface SearchFilterRequestDto {
  payload?: string;
}

export interface SearchFilterResponseDto {
  success: boolean;
  data: SearchFilterDataDto;
}

export interface SearchFilterDataDto {
  id: string;
}

export interface SearchFilter {
  id: string;
}
