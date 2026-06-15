export interface ProfileRequestDto {
  name?: string;
  departmentId?: string;
  technologies?: string;
  experienceLevel?: string;
}

export interface ProfileResponseDto {
  success: boolean;
  data: ProfileDataDto;
}

export interface ProfileDataDto {
  id: string;
  name?: string;
  email?: string;
  role?: string;
  status?: string;
  department?: string;
  departmentId?: string;
  technologies?: string;
  experienceLevel?: string;
  initial?: string;
}

export interface Profile {
  id: string;
  name?: string;
  email?: string;
  role?: string;
  status?: string;
  department?: string;
  departmentId?: string;
  technologies?: string;
  experienceLevel?: string;
  initial?: string;
}
