// Placeholder for React Query Hook
import { useQuery } from '@tanstack/react-query';
import { departmentService } from '../../services/department.service';
import { DEPARTMENT_QUERY_KEYS } from '../../department.query-keys';

export const useListDepartments = () => {
  return useQuery({
    queryKey: DEPARTMENT_QUERY_KEYS.all,
    queryFn: () => departmentService.listDepartments()
  })
};
