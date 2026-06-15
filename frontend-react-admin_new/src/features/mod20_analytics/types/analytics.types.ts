export interface AnalyticsRequestDto {
  payload?: string;
}

export interface AnalyticsResponseDto {
  success: boolean;
  data: AnalyticsDataDto;
}

export interface AnalyticsDataDto {
  id: string;
}

export interface Analytics {
  id: string;
}
