import { useMutation, useQueryClient } from '@tanstack/react-query';
import { departmentService } from '../../services/department.service';
import { DEPARTMENT_QUERY_KEYS } from '../../department.query-keys';

export const useDeleteDepartment = () => {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: (id: string) => departmentService.deleteDepartment(id),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: DEPARTMENT_QUERY_KEYS.all });
    },
  });
};
