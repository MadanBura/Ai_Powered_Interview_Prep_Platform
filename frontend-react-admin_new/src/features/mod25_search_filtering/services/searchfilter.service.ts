import { axiosClient } from '../../../services/http/axios-client';
import { SearchFilterRequestDto, SearchFilterResponseDto } from '../types/searchfilter.types';

export const searchFilterService = {

  async searchQuestions(): Promise<SearchFilterResponseDto> {
    const response = await axiosClient.get<SearchFilterResponseDto>(`/api/v1/questions/search`);
    return response.data;
  },
};
