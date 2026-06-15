export interface Department {
  id: string;
  name: string;
  headOfDept: string;
  activeTechnologies: number;
  status: 'ACTIVE' | 'INACTIVE';
}

export type DepartmentRequestDto = Omit<Department, 'id' | 'activeTechnologies' | 'status'>;

export interface DepartmentResponseDto {
  success: boolean;
  data: {
    departments?: Department[];
    [key: string]: any;
  };
}
