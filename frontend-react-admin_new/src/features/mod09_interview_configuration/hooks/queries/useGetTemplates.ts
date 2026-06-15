import { useQuery } from '@tanstack/react-query';
import { templateService } from '../../services/template.service';

export const useGetTemplates = () => {
  return useQuery({
    queryKey: ['templates'],
    queryFn: () => templateService.getTemplates()
  });
};
