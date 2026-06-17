import { axiosClient } from '../../../services/http/axios-client';
import { BulkUploadRequestDto, BulkUploadResponseDto } from '../types/bulkupload.types';

export const bulkUploadService = {

  async triggerIngestion(request: BulkUploadRequestDto): Promise<BulkUploadResponseDto> {
    const response = await axiosClient.post<BulkUploadResponseDto>(`/api/v1/admin/questions/ingest`, request);
    return response.data;
  },
};
