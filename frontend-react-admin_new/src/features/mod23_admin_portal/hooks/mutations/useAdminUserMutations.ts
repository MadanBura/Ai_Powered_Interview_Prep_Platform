import { useMutation, useQueryClient } from '@tanstack/react-query';
import toast from 'react-hot-toast';
import { adminUserService } from '../../services/adminuser.service';
import { AdminUserRequestDto } from '../../types/adminuser.types';
import { ADMINUSER_QUERY_KEYS } from '../../adminuser.query-keys';

export const useCreateAdminUser = () => {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: (data: AdminUserRequestDto) => adminUserService.createAdminUser(data),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ADMINUSER_QUERY_KEYS.all });
      toast.success("User created successfully!");
    },
    onError: (error: any) => {
      toast.error(error.response?.data?.error || error.message || "Failed to create user");
    }
  });
};

export const useUpdateAdminUser = () => {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: ({ id, data }: { id: string; data: AdminUserRequestDto }) => 
      adminUserService.updateAdminUser(id, data),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ADMINUSER_QUERY_KEYS.all });
      toast.success("User updated successfully!");
    },
    onError: (error: any) => {
      toast.error(error.response?.data?.error || error.message || "Failed to update user");
    }
  });
};

export const useDeleteAdminUser = () => {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: (id: string) => adminUserService.deleteAdminUser(id),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ADMINUSER_QUERY_KEYS.all });
      toast.success("User deleted successfully!");
    },
    onError: (error: any) => {
      toast.error(error.response?.data?.error || error.message || "Failed to delete user");
    }
  });
};
