export interface DashboardRequestDto {
  payload?: string;
}

export interface DashboardResponseDto {
  success: boolean;
  data: DashboardDataDto;
}

export interface DashboardDataDto {
  id: string;
}

export interface Dashboard {
  id: string;
}
