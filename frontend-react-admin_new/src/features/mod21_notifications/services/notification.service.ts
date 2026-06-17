import { axiosClient } from '../../../services/http/axios-client';
import { NotificationRequestDto, NotificationResponseDto } from '../types/notification.types';

export const notificationService = {

  async registerDevice(request: NotificationRequestDto): Promise<NotificationResponseDto> {
    const response = await axiosClient.post<NotificationResponseDto>(`/api/v1/notifications/register-device`, request);
    return response.data;
  },
};
