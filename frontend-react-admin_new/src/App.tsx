import { Routes, Route } from 'react-router-dom';
import ToastProvider from './components/ui/ToastProvider';

import ProtectedRoute from './components/ProtectedRoute';
import AdminLayout from './layouts/AdminLayout';
import AdminLogin from './pages/Admin_Login';
import AdminOTPVerification from './pages/Admin_OTP_Verification';
import AdminForgotPassword from './pages/Admin_Forgot_Password';
import AdminDashboard from './pages/Admin_Dashboard';
import ActiveSessionsMonitoring from './pages/Active_Sessions_Monitoring';
import UserManagement from './pages/User_Management';
import DepartmentManagement from './pages/Department_Management';
import QuestionDashboard from './pages/Question_Dashboard';
import QuestionRepository from './pages/Question_Repository';
import BulkUploadCenter from './pages/Bulk_Upload_Center';
import CategorizationDashboard from './pages/Categorization_Dashboard';
import InterviewConfiguration from './pages/Interview_Configuration';
import InterviewTemplates from './pages/Interview_Templates';
import PromptDashboard from './pages/Prompt_Dashboard';
import PromptLibrary from './pages/Prompt_Library';
import PromptEditorAndTesting from './pages/Prompt_Editor_and_Testing';
import AIEvaluationDashboard from './pages/AI_Evaluation_Dashboard';
import SessionReplayAndReview from './pages/Session_Replay_and_Review';

function App() {
  return (
    <>
      <ToastProvider />
      <Routes>
      <Route path="/login" element={<AdminLogin />} />
      <Route path="/otp" element={<AdminOTPVerification />} />
      <Route path="/forgot-password" element={<AdminForgotPassword />} />
      
      {/* Protected Routes */}
      <Route element={<ProtectedRoute />}>
        <Route path="/" element={<AdminLayout><AdminDashboard /></AdminLayout>} />
        <Route path="/dashboard" element={<AdminLayout><AdminDashboard /></AdminLayout>} />
        <Route path="/sessions" element={<AdminLayout><ActiveSessionsMonitoring /></AdminLayout>} />
        <Route path="/users" element={<AdminLayout><UserManagement /></AdminLayout>} />
        <Route path="/departments" element={<AdminLayout><DepartmentManagement /></AdminLayout>} />
        <Route path="/questions" element={<AdminLayout><QuestionDashboard /></AdminLayout>} />
        <Route path="/question-repository" element={<AdminLayout><QuestionRepository /></AdminLayout>} />
        <Route path="/repository/questions" element={<AdminLayout><QuestionRepository /></AdminLayout>} />
        <Route path="/bulk-upload" element={<AdminLayout><BulkUploadCenter /></AdminLayout>} />
        <Route path="/categorization" element={<AdminLayout><CategorizationDashboard /></AdminLayout>} />
        <Route path="/interview-config" element={<AdminLayout><InterviewConfiguration /></AdminLayout>} />
        <Route path="/interview-templates" element={<AdminLayout><InterviewTemplates /></AdminLayout>} />
        <Route path="/prompts" element={<AdminLayout><PromptDashboard /></AdminLayout>} />
        <Route path="/prompt-library" element={<AdminLayout><PromptLibrary /></AdminLayout>} />
        <Route path="/prompt-editor" element={<AdminLayout><PromptEditorAndTesting /></AdminLayout>} />
        <Route path="/evaluations" element={<AdminLayout><AIEvaluationDashboard /></AdminLayout>} />
        <Route path="/replay" element={<AdminLayout><SessionReplayAndReview /></AdminLayout>} />
        <Route path="*" element={<AdminLayout><AdminDashboard /></AdminLayout>} />
      </Route>
    </Routes>
    </>
  );
}

export default App;
