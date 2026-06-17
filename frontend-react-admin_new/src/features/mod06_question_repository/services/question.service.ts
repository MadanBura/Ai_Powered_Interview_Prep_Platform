import { axiosClient } from '../../../services/http/axios-client';
import { QuestionRequestDto, QuestionResponseDto } from '../types/question.types';

export const questionService = {

  async listQuestions(): Promise<QuestionResponseDto> {
    const response = await axiosClient.get<QuestionResponseDto>(`/api/v1/admin/questions`);
    return response.data;
  },

  async createQuestion(request: QuestionRequestDto): Promise<QuestionResponseDto> {
    const response = await axiosClient.post<QuestionResponseDto>(`/api/v1/admin/questions`, request);
    return response.data;
  },

  async updateQuestion(id: string, request: QuestionRequestDto): Promise<QuestionResponseDto> {
    const response = await axiosClient.put<QuestionResponseDto>(`/api/v1/admin/questions/${id}`, request);
    return response.data;
  },

  async deleteQuestion(id: string): Promise<QuestionResponseDto> {
    const response = await axiosClient.delete<QuestionResponseDto>(`/api/v1/admin/questions/${id}`);
    return response.data;
  },

  async generateQuestions(): Promise<any> {
    const response = await axiosClient.post<any>(`/api/v1/admin/questions/generate`);
    return response.data;
  }
};
