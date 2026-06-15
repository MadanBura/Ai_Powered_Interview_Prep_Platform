export interface InterviewConfigRequestDto {
  payload?: string;
}

export interface InterviewConfigResponseDto {
  success: boolean;
  data: InterviewConfigDataDto;
}

export interface InterviewConfigDataDto {
  id: string;
}

export interface InterviewConfig {
  id: string;
}
