import { axiosClient } from '../../../services/http/axios-client';
import { AdminUserRequestDto, AdminUserResponseDto } from '../types/adminuser.types';

export const adminUserService = {

  async listAdminUsers(): Promise<AdminUserResponseDto> {
    const response = await axiosClient.get<AdminUserResponseDto>(`/api/v1/admin/users`);
    return response.data;
  },

  async createAdminUser(request: AdminUserRequestDto): Promise<AdminUserResponseDto> {
    const response = await axiosClient.post<AdminUserResponseDto>(`/api/v1/admin/users`, request);
    return response.data;
  },

  async updateAdminUser(id: string, request: AdminUserRequestDto): Promise<AdminUserResponseDto> {
    const response = await axiosClient.put<AdminUserResponseDto>(`/api/v1/admin/users/${id}`, request);
    return response.data;
  },

  async deleteAdminUser(id: string): Promise<AdminUserResponseDto> {
    const response = await axiosClient.delete<AdminUserResponseDto>(`/api/v1/admin/users/${id}`);
    return response.data;
  }
};
