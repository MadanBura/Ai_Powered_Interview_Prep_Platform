import { axiosClient } from '../../../services/http/axios-client';
import { FileStorageRequestDto, FileStorageResponseDto } from '../types/filestorage.types';

export const fileStorageService = {

  async generateUploadUrl(request: FileStorageRequestDto): Promise<FileStorageResponseDto> {
    const response = await axiosClient.post<FileStorageResponseDto>(`/api/v1/files/upload-url`, request);
    return response.data;
  },
};
