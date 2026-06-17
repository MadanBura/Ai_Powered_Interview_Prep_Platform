export interface FileStorageRequestDto {
  payload?: string;
}

export interface FileStorageResponseDto {
  success: boolean;
  data: FileStorageDataDto;
}

export interface FileStorageDataDto {
  id: string;
}

export interface FileStorage {
  id: string;
}
