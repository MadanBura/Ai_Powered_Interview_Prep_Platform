import { axiosClient } from '../../../services/http/axios-client';
import { ReportingRequestDto, ReportingResponseDto } from '../types/reporting.types';

export const reportingService = {

  async listPastReports(): Promise<ReportingResponseDto> {
    const response = await axiosClient.get<ReportingResponseDto>(`/api/v1/candidates/reports`);
    return response.data;
  },
};
