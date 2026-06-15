export interface ExperienceLevelRequestDto {
  payload?: string;
}

export interface ExperienceLevelResponseDto {
  success: boolean;
  data: ExperienceLevelDataDto;
}

export interface ExperienceLevelDataDto {
  id: string;
}

export interface ExperienceLevel {
  id: string;
}
