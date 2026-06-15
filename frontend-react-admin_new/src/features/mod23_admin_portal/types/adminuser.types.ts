export interface AdminUser {
  id: string;
  name: string;
  email: string;
  role: string;
  dept: string;
  status: 'ACTIVE' | 'INACTIVE' | 'PENDING';
  initial: string;
}

export type AdminUserRequestDto = Omit<AdminUser, 'id' | 'initial' | 'status'>;

export interface AdminUserResponseDto {
  success: boolean;
  data: {
    users?: AdminUser[];
    [key: string]: any;
  };
}
