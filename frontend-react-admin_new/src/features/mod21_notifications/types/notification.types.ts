export interface NotificationRequestDto {
  payload?: string;
}

export interface NotificationResponseDto {
  success: boolean;
  data: NotificationDataDto;
}

export interface NotificationDataDto {
  id: string;
}

export interface Notification {
  id: string;
}
