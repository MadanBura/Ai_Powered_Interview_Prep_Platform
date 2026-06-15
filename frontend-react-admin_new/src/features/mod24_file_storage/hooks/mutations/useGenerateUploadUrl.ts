// Placeholder for React Query Mutation Hook
import { useMutation } from '@tanstack/react-query';
import { fileStorageService } from '../../services/filestorage.service';

export const useGenerateUploadUrl = () => {
  return useMutation({
    mutationFn: (data) => fileStorageService.generateUploadUrl(data)
  })
};
