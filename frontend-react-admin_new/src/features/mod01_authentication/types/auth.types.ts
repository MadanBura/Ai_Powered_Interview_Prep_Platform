export interface OtpRequestDto {
  target: string;
}

export interface OtpVerifyDto {
  target: string;
  otpCode: string;
}

export interface AuthResponseDto {
  success: boolean;
  data: AuthDataDto;
}

export interface AuthDataDto {
  accessToken: string;
  refreshToken: string;
  role?: string;
}
