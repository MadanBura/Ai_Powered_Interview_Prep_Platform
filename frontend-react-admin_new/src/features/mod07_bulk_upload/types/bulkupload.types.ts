export interface BulkUploadRequestDto {
  payload?: string;
}

export interface BulkUploadResponseDto {
  success: boolean;
  data: BulkUploadDataDto;
}

export interface BulkUploadDataDto {
  id: string;
}

export interface BulkUpload {
  id: string;
}
