import { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { authService } from '../features/mod01_authentication/services/auth.service';

export default function Admin_Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [showPassword, setShowPassword] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault();
    setIsLoading(true);
    setError('');
    
    try {
      // The API requires 'target' (which is the email/phone) for OTP generation
      await authService.requestOtp({ target: email });
      // Navigate to OTP verification, passing the email in state
      navigate('/otp', { state: { email } });
    } catch (err: any) {
      console.error("Login OTP error:", err);
      setError(err.message || "Failed to send OTP. Please try again.");
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-surface">
      <main className="w-full max-w-[440px] flex flex-col gap-unit-lg animate-in fade-in duration-700 p-4">
        {/* Brand Header */}
        <div className="flex flex-col items-center gap-unit-sm text-center">
          <div className="w-16 h-16 bg-primary rounded-xl flex items-center justify-center mb-unit-sm">
            <span className="material-symbols-outlined text-white text-[32px]" data-icon="psychology">psychology</span>
          </div>
          <h1 className="font-headline-md text-headline-md text-on-surface tracking-tight">PrepCoach AI Admin</h1>
          <p className="font-body-md text-body-md text-on-surface-variant max-w-[280px]">
            Access the enterprise administration portal.
          </p>
        </div>

        {/* Login Card */}
        <div className="login-card bg-white p-unit-xl rounded-lg shadow-sm border border-outline-variant">
          {error && (
            <div className="mb-4 p-3 bg-error-container text-on-error-container rounded text-body-sm font-semibold">
              {error}
            </div>
          )}
          <form className="flex flex-col gap-unit-lg" id="loginForm" onSubmit={handleLogin}>
            {/* Email Field */}
            <div className="flex flex-col gap-1.5">
              <label className="font-label-md text-label-md text-on-surface-variant uppercase tracking-wider" htmlFor="email">Email Address</label>
              <div className="relative">
                <span className="material-symbols-outlined absolute left-3 top-1/2 -translate-y-1/2 text-outline text-[20px]" data-icon="mail">mail</span>
                <input 
                  className="w-full pl-10 pr-4 py-2.5 bg-surface-container-lowest border border-outline-variant rounded font-body-md text-body-md input-focus-halo transition-all outline-none focus:ring-2 focus:ring-primary/20" 
                  id="email" 
                  name="email" 
                  placeholder="admin@prepcoach.ai" 
                  required 
                  type="email"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                />
              </div>
            </div>

            {/* Password Field (Decorative for now, backend uses OTP) */}
            <div className="flex flex-col gap-1.5">
              <label className="font-label-md text-label-md text-on-surface-variant uppercase tracking-wider" htmlFor="password">Password</label>
              <div className="relative">
                <span className="material-symbols-outlined absolute left-3 top-1/2 -translate-y-1/2 text-outline text-[20px]" data-icon="lock">lock</span>
                <input 
                  className="w-full pl-10 pr-10 py-2.5 bg-surface-container-lowest border border-outline-variant rounded font-body-md text-body-md input-focus-halo transition-all outline-none focus:ring-2 focus:ring-primary/20" 
                  id="password" 
                  name="password" 
                  placeholder="••••••••" 
                  required 
                  type={showPassword ? "text" : "password"}
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                />
                <button 
                  className="absolute right-3 top-1/2 -translate-y-1/2 text-outline hover:text-primary transition-colors" 
                  type="button"
                  onClick={() => setShowPassword(!showPassword)}
                >
                  <span className="material-symbols-outlined text-[20px]" data-icon={showPassword ? "visibility_off" : "visibility"}>
                    {showPassword ? "visibility_off" : "visibility"}
                  </span>
                </button>
              </div>
            </div>

            {/* Remember & Forgot */}
            <div className="flex items-center justify-between">
              <label className="flex items-center gap-2 cursor-pointer group">
                <div className="relative flex items-center">
                  <input className="peer h-4 w-4 rounded border-outline-variant text-primary focus:ring-primary/20 cursor-pointer" type="checkbox"/>
                </div>
                <span className="font-body-sm text-body-sm text-on-surface-variant group-hover:text-on-surface transition-colors">Remember me</span>
              </label>
              <Link 
                className="font-label-md text-label-md text-primary hover:underline transition-all" 
                to="/forgot-password"
              >
                Forgot Password?
              </Link>
            </div>

            {/* Submit Button */}
            <button 
              className={`w-full bg-[#16A34A] hover:bg-primary-container text-white font-headline-sm text-headline-sm py-3 rounded shadow-sm transition-all transform active:scale-[0.98] flex items-center justify-center gap-2 ${isLoading ? 'opacity-70 cursor-not-allowed' : ''}`} 
              type="submit"
              disabled={isLoading}
            >
              {isLoading ? 'Sending OTP...' : 'Sign In'}
              {!isLoading && <span className="material-symbols-outlined" data-icon="login">login</span>}
            </button>
          </form>
        </div>

        {/* Footer / Security */}
        <div className="flex flex-col items-center gap-unit-md mt-unit-sm">
          <div className="flex items-center gap-2 px-3 py-1 bg-surface-container-high rounded-full border border-outline-variant">
            <span className="material-symbols-outlined text-primary text-[16px]" data-icon="verified_user" data-weight="fill">verified_user</span>
            <span className="font-label-md text-label-md text-on-surface-variant uppercase tracking-widest">Secure Enterprise Session</span>
          </div>
          {/* Certification Badges Mock */}
          <div className="flex items-center gap-unit-lg opacity-40 grayscale hover:grayscale-0 hover:opacity-100 transition-all duration-300">
            <div className="flex items-center gap-1">
              <span className="material-symbols-outlined text-[24px]" data-icon="security">security</span>
              <span className="font-label-md text-[10px] leading-tight uppercase font-bold">SOC2<br/>Type II</span>
            </div>
            <div className="flex items-center gap-1">
              <span className="material-symbols-outlined text-[24px]" data-icon="gpp_good">gpp_good</span>
              <span className="font-label-md text-[10px] leading-tight uppercase font-bold">ISO<br/>27001</span>
            </div>
            <div className="flex items-center gap-1">
              <span className="material-symbols-outlined text-[24px]" data-icon="shield_lock">shield_lock</span>
              <span className="font-label-md text-[10px] leading-tight uppercase font-bold">GDPR<br/>Compliant</span>
            </div>
          </div>
        </div>
      </main>

      {/* Background Subtle Glow Effect */}
      <div className="fixed top-0 left-0 w-full h-full -z-10 pointer-events-none overflow-hidden opacity-30">
        <div className="absolute -top-[20%] -left-[10%] w-[60%] h-[60%] bg-primary-fixed blur-[120px] rounded-full opacity-20"></div>
        <div className="absolute -bottom-[20%] -right-[10%] w-[50%] h-[50%] bg-secondary-fixed blur-[100px] rounded-full opacity-20"></div>
      </div>
    </div>
  );
}
