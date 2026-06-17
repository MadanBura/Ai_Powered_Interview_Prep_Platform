// Placeholder for React Query Mutation Hook
import { useMutation } from '@tanstack/react-query';
import { interviewSessionService } from '../../services/interviewsession.service';

export const useInitializeSession = () => {
  return useMutation({
    mutationFn: (data) => interviewSessionService.initializeSession(data)
  })
};
