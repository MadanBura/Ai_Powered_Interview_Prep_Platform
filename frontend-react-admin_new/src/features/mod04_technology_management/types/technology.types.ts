export interface TechnologyRequestDto {
  payload?: string;
}

export interface TechnologyResponseDto {
  success: boolean;
  data: TechnologyDataDto;
}

export interface TechnologyDataDto {
  id: string;
}

export interface Technology {
  id: string;
}
