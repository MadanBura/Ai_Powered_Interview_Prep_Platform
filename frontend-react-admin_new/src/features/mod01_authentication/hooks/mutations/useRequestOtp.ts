// Placeholder for React Query Mutation Hook
import { useMutation } from '@tanstack/react-query';
import { authService } from '../../services/auth.service';

export const useRequestOtp = () => {
  return useMutation({
    mutationFn: (data) => authService.requestOtp(data)
  })
};
