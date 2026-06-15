import { axiosClient } from '../../../services/http/axios-client';
import { AiPromptRequestDto, AiPromptResponseDto } from '../types/aiprompt.types';

export const aiPromptService = {

  async listPrompts(): Promise<AiPromptResponseDto> {
    const response = await axiosClient.get<AiPromptResponseDto>(`/api/v1/admin/ai/prompts`);
    return response.data;
  },

  async createPrompt(request: AiPromptRequestDto): Promise<AiPromptResponseDto> {
    const response = await axiosClient.post<AiPromptResponseDto>(`/api/v1/admin/ai/prompts`, request);
    return response.data;
  },

  async updatePrompt(id: string, request: AiPromptRequestDto): Promise<AiPromptResponseDto> {
    const response = await axiosClient.put<AiPromptResponseDto>(`/api/v1/admin/ai/prompts/${id}`, request);
    return response.data;
  },

  async deletePrompt(id: string): Promise<AiPromptResponseDto> {
    const response = await axiosClient.delete<AiPromptResponseDto>(`/api/v1/admin/ai/prompts/${id}`);
    return response.data;
  }
};
