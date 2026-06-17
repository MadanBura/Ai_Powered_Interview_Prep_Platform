import { axiosClient } from '../../../services/http/axios-client';
import { OtpRequestDto, OtpVerifyDto, AuthResponseDto } from '../types/auth.types';

export const authService = {

  async requestOtp(request: OtpRequestDto): Promise<any> {
    const response = await axiosClient.post(`/api/v1/auth/otp/request`, request);
    return response.data;
  },

  async verifyOtp(request: OtpVerifyDto): Promise<AuthResponseDto> {
    const response = await axiosClient.post<AuthResponseDto>(`/api/v1/auth/otp/verify`, request);
    return response.data;
  }
};
