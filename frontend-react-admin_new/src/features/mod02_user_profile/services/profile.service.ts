import { axiosClient } from '../../../services/http/axios-client';
import { ProfileRequestDto, ProfileResponseDto } from '../types/profile.types';

export const profileService = {

  async getProfile(): Promise<ProfileResponseDto> {
    const response = await axiosClient.get<ProfileResponseDto>(`/api/v1/admin/users/profile`);
    return response.data;
  },
};
