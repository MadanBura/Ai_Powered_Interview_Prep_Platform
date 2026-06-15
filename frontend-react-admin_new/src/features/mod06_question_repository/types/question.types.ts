export interface Question {
  id: string;
  title: string;
  category: string;
  technology: string;
  difficulty: string;
  status: 'Active' | 'Draft' | 'Archived';
}

export type QuestionRequestDto = Omit<Question, 'id' | 'status'>;

export interface QuestionResponseDto {
  success: boolean;
  data: {
    questions?: Question[];
    [key: string]: any;
  };
}
