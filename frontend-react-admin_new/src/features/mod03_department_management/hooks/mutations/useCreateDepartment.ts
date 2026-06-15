// Placeholder for React Query Mutation Hook
import { useMutation, useQueryClient } from '@tanstack/react-query';
import { departmentService } from '../../services/department.service';
import { DEPARTMENT_QUERY_KEYS } from '../../department.query-keys';

export const useCreateDepartment = () => {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: (data: any) => departmentService.createDepartment(data),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: DEPARTMENT_QUERY_KEYS.all });
    }
  })
};
