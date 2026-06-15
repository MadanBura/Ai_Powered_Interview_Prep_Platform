export interface AiPrompt {
  id: string;
  name: string;
  persona: string;
  status: 'Active' | 'Draft' | 'Archived';
  version: string;
  lastModified: string;
}

export type AiPromptRequestDto = Omit<AiPrompt, 'id' | 'version' | 'lastModified'>;

export interface AiPromptResponseDto {
  success: boolean;
  data: {
    prompts?: AiPrompt[];
    [key: string]: any;
  };
}
