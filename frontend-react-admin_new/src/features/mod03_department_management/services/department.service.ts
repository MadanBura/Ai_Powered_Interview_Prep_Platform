import { axiosClient } from '../../../services/http/axios-client';
import { DepartmentRequestDto, DepartmentResponseDto } from '../types/department.types';

export const departmentService = {

  async listDepartments(): Promise<DepartmentResponseDto> {
    const response = await axiosClient.get<DepartmentResponseDto>(`/api/v1/admin/departments`);
    return response.data;
  },
  async createDepartment(request: DepartmentRequestDto): Promise<DepartmentResponseDto> {
    const response = await axiosClient.post<DepartmentResponseDto>(`/api/v1/admin/departments`, request);
    return response.data;
  },
  async updateDepartment(id: string, request: DepartmentRequestDto): Promise<DepartmentResponseDto> {
    const response = await axiosClient.put<DepartmentResponseDto>(`/api/v1/admin/departments/${id}`, request);
    return response.data;
  },
  async deleteDepartment(id: string): Promise<DepartmentResponseDto> {
    const response = await axiosClient.delete<DepartmentResponseDto>(`/api/v1/admin/departments/${id}`);
    return response.data;
  }
};
