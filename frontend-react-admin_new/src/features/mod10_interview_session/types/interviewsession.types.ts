export interface InterviewSessionRequestDto {
  payload?: string;
}

export interface InterviewSessionResponseDto {
  success: boolean;
  data: InterviewSessionDataDto;
}

export interface InterviewSessionDataDto {
  id: string;
}

export interface InterviewSession {
  id: string;
}
