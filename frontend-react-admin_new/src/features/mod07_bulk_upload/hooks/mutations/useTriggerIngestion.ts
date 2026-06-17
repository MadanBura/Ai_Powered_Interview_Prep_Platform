import { useMutation } from '@tanstack/react-query';
import { bulkUploadService } from '../../services/bulkupload.service';

export const useTriggerIngestion = () => {
  return useMutation({
    mutationFn: (data: any) => bulkUploadService.triggerIngestion(data)
  });
};
