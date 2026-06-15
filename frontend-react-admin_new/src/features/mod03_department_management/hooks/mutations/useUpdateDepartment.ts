import { useMutation, useQueryClient } from '@tanstack/react-query';
import { departmentService } from '../../services/department.service';
import { DepartmentRequestDto } from '../../types/department.types';
import { DEPARTMENT_QUERY_KEYS } from '../../department.query-keys';

export const useUpdateDepartment = () => {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: ({ id, data }: { id: string; data: DepartmentRequestDto }) => 
      departmentService.updateDepartment(id, data),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: DEPARTMENT_QUERY_KEYS.all });
    },
  });
};
