import { useQuery } from '@tanstack/react-query';
import { adminUserService } from '../../services/adminuser.service';
import { ADMINUSER_QUERY_KEYS } from '../../adminuser.query-keys';

export const useListAdminUsers = () => {
  return useQuery({
    queryKey: ADMINUSER_QUERY_KEYS.all,
    queryFn: () => adminUserService.listAdminUsers()
  });
};
