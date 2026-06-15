import os

base_pkg_dir = "/Users/apple/AI-Powered Interview Preparation Platform/frontend-react-admin_new/src"

modules = {
    # Phase 1
    "mod01_authentication": {"endpoints": [{"method": "POST", "path": "/api/v1/auth/otp/request", "name": "requestOtp"}], "domain": "Auth"},
    "mod02_user_profile": {"endpoints": [{"method": "GET", "path": "/api/v1/candidates/profile", "name": "getProfile"}], "domain": "Profile"},
    "mod03_department_management": {"endpoints": [{"method": "GET", "path": "/api/v1/admin/departments", "name": "listDepartments"}, {"method": "POST", "path": "/api/v1/admin/departments", "name": "createDepartment"}], "domain": "Department"},
    "mod04_technology_management": {"endpoints": [{"method": "GET", "path": "/api/v1/admin/technologies", "name": "listTechnologies"}], "domain": "Technology"},
    "mod05_experience_level_management": {"endpoints": [{"method": "GET", "path": "/api/v1/admin/experience-levels", "name": "listExperienceLevels"}], "domain": "ExperienceLevel"},
    "mod23_admin_portal": {"endpoints": [{"method": "GET", "path": "/api/v1/admin/users", "name": "listAdminUsers"}], "domain": "AdminUser"},

    # Phase 2
    "mod06_question_repository": {"endpoints": [{"method": "GET", "path": "/api/v1/admin/questions", "name": "listQuestions"}], "domain": "Question"},
    "mod07_bulk_upload": {"endpoints": [{"method": "POST", "path": "/api/v1/admin/questions/ingest", "name": "triggerIngestion"}], "domain": "BulkUpload"},
    "mod08_categorization_engine": {"endpoints": [{"method": "GET", "path": "/api/v1/admin/questions/ingest/{taskId}/results", "name": "viewResults"}], "domain": "Categorization"},
    "mod24_file_storage": {"endpoints": [{"method": "POST", "path": "/api/v1/files/upload-url", "name": "generateUploadUrl"}], "domain": "FileStorage"},
    "mod25_search_filtering": {"endpoints": [{"method": "GET", "path": "/api/v1/questions/search", "name": "searchQuestions"}], "domain": "SearchFilter"},

    # Phase 3
    "mod09_interview_configuration": {"endpoints": [{"method": "GET", "path": "/api/v1/interviews/configuration/options", "name": "fetchOptions"}], "domain": "InterviewConfig"},
    "mod10_interview_session": {"endpoints": [{"method": "POST", "path": "/api/v1/interviews/sessions", "name": "initializeSession"}], "domain": "InterviewSession"},
    "mod11_question_delivery": {"endpoints": [{"method": "GET", "path": "/api/v1/interviews/sessions/{sessionId}/questions", "name": "retrieveQuestions"}], "domain": "QuestionDelivery"},

    # Phase 4
    "mod14_ai_evaluation": {"endpoints": [{"method": "GET", "path": "/api/v1/interviews/sessions/{sessionId}/evaluation", "name": "retrieveEvaluation"}], "domain": "AiEvaluation"},
    "mod16_feedback_engine": {"endpoints": [{"method": "GET", "path": "/api/v1/interviews/sessions/{sessionId}/feedback", "name": "retrieveFeedback"}], "domain": "FeedbackEngine"},
    "mod19_recommendation_engine": {"endpoints": [{"method": "GET", "path": "/api/v1/candidates/recommendations", "name": "retrieveRecommendations"}], "domain": "Recommendation"},
    "mod26_ai_prompt_management": {"endpoints": [{"method": "GET", "path": "/api/v1/admin/ai/prompts", "name": "listPrompts"}], "domain": "AiPrompt"},

    # Phase 5
    "mod17_reporting": {"endpoints": [{"method": "GET", "path": "/api/v1/candidates/reports", "name": "listPastReports"}], "domain": "Reporting"},
    "mod18_dashboard": {"endpoints": [{"method": "GET", "path": "/api/v1/admin/dashboard", "name": "retrieveAdminDashboard"}], "domain": "Dashboard"},
    "mod20_analytics": {"endpoints": [{"method": "GET", "path": "/api/v1/admin/analytics/platform", "name": "getPlatformMetrics"}], "domain": "Analytics"},

    # Phase 6
    "mod21_notifications": {"endpoints": [{"method": "POST", "path": "/api/v1/notifications/register-device", "name": "registerDevice"}], "domain": "Notification"},
    "mod22_audit_logs": {"endpoints": [{"method": "GET", "path": "/api/v1/admin/audit-logs", "name": "listAuditLogs"}], "domain": "AuditLog"},
    "mod27_configuration_management": {"endpoints": [{"method": "GET", "path": "/api/v1/admin/config", "name": "listConfig"}], "domain": "SystemConfig"}
}

def to_camel_case(snake_str):
    components = snake_str.split('_')
    return components[0] + ''.join(x.title() for x in components[1:])

def generate_react_layer():
    for mod_pkg, config in modules.items():
        domain_name = config["domain"]
        domain_camel = domain_name[0].lower() + domain_name[1:]
        
        feature_dir = os.path.join(base_pkg_dir, f"features/{mod_pkg}")
        
        dirs = ["types", "services", "hooks/queries", "hooks/mutations"]
        for d in dirs:
            os.makedirs(os.path.join(feature_dir, d), exist_ok=True)

        # 1. Types
        types_ts = f"""export interface {domain_name}RequestDto {{
  payload?: string;
}}

export interface {domain_name}ResponseDto {{
  success: boolean;
  data: {domain_name}DataDto;
}}

export interface {domain_name}DataDto {{
  id: string;
}}

export interface {domain_name} {{
  id: string;
}}
"""
        with open(os.path.join(feature_dir, f"types/{domain_name.lower()}.types.ts"), "w") as f:
            f.write(types_ts)

        # 2. Query Keys
        query_keys_ts = f"""export const {domain_name.upper()}_QUERY_KEYS = {{
  all: ['{domain_camel}'] as const,
  lists: () => [...{domain_name.upper()}_QUERY_KEYS.all, 'list'] as const,
  list: (filters: string) => [...{domain_name.upper()}_QUERY_KEYS.lists(), {{ filters }}] as const,
  details: () => [...{domain_name.upper()}_QUERY_KEYS.all, 'detail'] as const,
  detail: (id: string) => [...{domain_name.upper()}_QUERY_KEYS.details(), id] as const,
}};
"""
        with open(os.path.join(feature_dir, f"{domain_name.lower()}.query-keys.ts"), "w") as f:
            f.write(query_keys_ts)

        # 3. Service
        service_methods = ""
        for ep in config["endpoints"]:
            method = ep["method"].lower()
            path = ep["path"].replace("{", "${")
            name = ep["name"]
            
            # Simple parameter parsing
            params = ""
            args = ""
            if "sessionId" in path:
                params = "sessionId: string"
            elif "taskId" in path:
                params = "taskId: string"
            
            if method in ["post", "put"]:
                req = f"request: {domain_name}RequestDto"
                params = f"{params}, {req}" if params else req
                args = ", request"
            
            service_methods += f"""
  async {name}({params}): Promise<{domain_name}ResponseDto> {{
    const response = await axiosClient.{method}<{domain_name}ResponseDto>(`{path}`{args});
    return response.data;
  }},"""

        service_ts = f"""import {{ axiosClient }} from '../../../services/http/axios-client';
import {{ {domain_name}RequestDto, {domain_name}ResponseDto }} from '../types/{domain_name.lower()}.types';

export const {domain_camel}Service = {{
{service_methods}
}};
"""
        with open(os.path.join(feature_dir, f"services/{domain_name.lower()}.service.ts"), "w") as f:
            f.write(service_ts)

        # 4. Redux Slice
        slice_ts = f"""import {{ createSlice, PayloadAction }} from '@reduxjs/toolkit';
import {{ {domain_name} }} from './types/{domain_name.lower()}.types';

interface {domain_name}State {{
  selected{domain_name}: {domain_name} | null;
  filters: Record<string, string>;
  pagination: {{
    page: number;
    limit: number;
  }};
}}

const initialState: {domain_name}State = {{
  selected{domain_name}: null,
  filters: {{}},
  pagination: {{ page: 1, limit: 10 }},
}};

export const {domain_camel}Slice = createSlice({{
  name: '{domain_camel}',
  initialState,
  reducers: {{
    setSelected{domain_name}: (state, action: PayloadAction<{domain_name} | null>) => {{
      state.selected{domain_name} = action.payload;
    }},
    setFilters: (state, action: PayloadAction<Record<string, string>>) => {{
      state.filters = action.payload;
    }},
    setPagination: (state, action: PayloadAction<{{ page: number; limit: number }}>) => {{
      state.pagination = action.payload;
    }},
  }},
}});

export const {{ setSelected{domain_name}, setFilters, setPagination }} = {domain_camel}Slice.actions;
export default {domain_camel}Slice.reducer;
"""
        with open(os.path.join(feature_dir, f"{domain_name.lower()}.slice.ts"), "w") as f:
            f.write(slice_ts)

        # 5. Queries and Mutations (Hooks)
        # Using a simplified implementation mapping directly to service
        # Real-world would use @tanstack/react-query
        for ep in config["endpoints"]:
            method = ep["method"].lower()
            name = ep["name"]
            
            if method == "get":
                hook_ts = f"""// Placeholder for React Query Hook
import {{ {domain_camel}Service }} from '../../services/{domain_name.lower()}.service';
import {{ {domain_name.upper()}_QUERY_KEYS }} from '../../{domain_name.lower()}.query-keys';

export const use{name[0].upper() + name[1:]} = () => {{
  // return useQuery({{
  //   queryKey: {domain_name.upper()}_QUERY_KEYS.all,
  //   queryFn: () => {domain_camel}Service.{name}()
  // }})
}};
"""
                with open(os.path.join(feature_dir, f"hooks/queries/use{name[0].upper() + name[1:]}.ts"), "w") as f:
                    f.write(hook_ts)
            else:
                hook_ts = f"""// Placeholder for React Query Mutation Hook
import {{ {domain_camel}Service }} from '../../services/{domain_name.lower()}.service';

export const use{name[0].upper() + name[1:]} = () => {{
  // return useMutation({{
  //   mutationFn: (data) => {domain_camel}Service.{name}(data)
  // }})
}};
"""
                with open(os.path.join(feature_dir, f"hooks/mutations/use{name[0].upper() + name[1:]}.ts"), "w") as f:
                    f.write(hook_ts)

generate_react_layer()
print("Generated React API Layer for all remaining modules.")
