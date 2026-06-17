import { useQuery } from '@tanstack/react-query';
import { optionsService } from '../../services/options.service';

export const useGetDepartments = () => {
  return useQuery({
    queryKey: ['options', 'departments'],
    queryFn: () => optionsService.getDepartments()
  });
};

export const useGetExperienceLevels = () => {
  return useQuery({
    queryKey: ['options', 'experience-levels'],
    queryFn: () => optionsService.getExperienceLevels()
  });
};
