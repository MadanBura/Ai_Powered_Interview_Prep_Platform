export interface SystemConfigRequestDto {
  payload?: string;
}

export interface SystemConfigResponseDto {
  success: boolean;
  data: SystemConfigDataDto;
}

export interface SystemConfigDataDto {
  id: string;
}

export interface SystemConfig {
  id: string;
}
