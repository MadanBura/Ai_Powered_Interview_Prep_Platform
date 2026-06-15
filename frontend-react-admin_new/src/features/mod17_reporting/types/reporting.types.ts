export interface ReportingRequestDto {
  payload?: string;
}

export interface ReportingResponseDto {
  success: boolean;
  data: ReportingDataDto;
}

export interface ReportingDataDto {
  id: string;
}

export interface Reporting {
  id: string;
}
