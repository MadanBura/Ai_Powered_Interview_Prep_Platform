import os

base_pkg = "/Users/apple/AI-Powered Interview Preparation Platform/frontend-react-admin/src"

dirs = [
    "types",
    "services/http"
]

for d in dirs:
    os.makedirs(os.path.join(base_pkg, d), exist_ok=True)

# 1. api-result.ts
api_result_ts = """export type ApiResult<T> = 
  | { status: 'loading' }
  | { status: 'success'; data: T }
  | { status: 'error'; message: string; code?: string };
"""

# 2. axios-client.ts
axios_client_ts = """import axios from 'axios';
import { v4 as uuidv4 } from 'uuid';

export const axiosClient = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080/api/v1/',
  timeout: 10000,
});

// Correlation ID Interceptor
axiosClient.interceptors.request.use((config) => {
  config.headers['X-Correlation-Id'] = uuidv4();
  return config;
});
"""

# 3. auth-interceptor.ts
auth_interceptor_ts = """import { axiosClient } from './axios-client';

export const setupAuthInterceptor = (
  getToken: () => string | null,
  onLogout: () => void
) => {
  axiosClient.interceptors.request.use((config) => {
    const token = getToken();
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`;
    }
    return config;
  });

  axiosClient.interceptors.response.use(
    (response) => response,
    async (error) => {
      const originalRequest = error.config;
      
      if (error.response?.status === 401 && !originalRequest._retry) {
        originalRequest._retry = true;
        // In a real app, you would attempt to refresh the token here
        // const newToken = await refreshToken();
        // if (newToken) {
        //   return axiosClient(originalRequest);
        // }
        
        onLogout();
      }
      return Promise.reject(error);
    }
  );
};
"""

with open(os.path.join(base_pkg, "types/api-result.ts"), "w") as f:
    f.write(api_result_ts)

with open(os.path.join(base_pkg, "services/http/axios-client.ts"), "w") as f:
    f.write(axios_client_ts)

with open(os.path.join(base_pkg, "services/http/auth-interceptor.ts"), "w") as f:
    f.write(auth_interceptor_ts)

print("Base React API configurations setup successfully.")
