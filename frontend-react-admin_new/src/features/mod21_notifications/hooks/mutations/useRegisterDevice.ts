// Placeholder for React Query Mutation Hook
import { useMutation } from '@tanstack/react-query';
import { notificationService } from '../../services/notification.service';

export const useRegisterDevice = () => {
  return useMutation({
    mutationFn: (data) => notificationService.registerDevice(data)
  })
};
