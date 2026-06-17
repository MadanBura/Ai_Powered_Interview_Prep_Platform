import { useState, useRef } from 'react';
import { useLocation, useNavigate, Navigate } from 'react-router-dom';
import { authService } from '../features/mod01_authentication/services/auth.service';

export default function Admin_OTP_Verification() {
  const [otp, setOtp] = useState(['', '', '', '', '', '']);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState('');
  const location = useLocation();
  const navigate = useNavigate();
  const inputRefs = useRef<(HTMLInputElement | null)[]>([]);

  // If user navigated here directly without email, redirect to login
  const email = location.state?.email;
  if (!email) {
    return <Navigate to="/login" replace />;
  }

  const handleChange = (index: number, value: string) => {
    if (value.length > 1) {
      value = value.slice(-1);
    }
    const newOtp = [...otp];
    newOtp[index] = value;
    setOtp(newOtp);

    // Auto-advance
    if (value && index < 5 && inputRefs.current[index + 1]) {
      inputRefs.current[index + 1]?.focus();
    }
  };

  const handleKeyDown = (index: number, e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === 'Backspace' && !otp[index] && index > 0) {
      inputRefs.current[index - 1]?.focus();
    }
  };

  const handleVerify = async (e: React.FormEvent) => {
    e.preventDefault();
    const otpString = otp.join('');
    if (otpString.length < 6) {
      setError('Please enter all 6 digits.');
      return;
    }

    setIsLoading(true);
    setError('');

    try {
      const payload = {
        target: email,
        otpCode: otpString,
      };

      const response = await authService.verifyOtp(payload);
      
      if (response && response.data) {
        const { accessToken, refreshToken, role } = response.data;
        if (role !== 'ADMIN' && role !== 'SUPER_ADMIN') {
          throw new Error('Access denied. You do not have admin privileges.');
        }
        localStorage.setItem('accessToken', accessToken);
        localStorage.setItem('refreshToken', refreshToken);
        navigate('/dashboard');
      } else {
        throw new Error("Invalid response format");
      }
    } catch (err: any) {
      setError(err.message || 'Invalid OTP code.');
    } finally {
      setIsLoading(false);
    }
  };

  const handleResend = async () => {
    setIsLoading(true);
    setError('');
    try {
      await authService.requestOtp({ target: email });
      setError('A new code has been sent.');
    } catch (err: any) {
      setError(err.message || 'Failed to resend OTP.');
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-surface">
      {/* Background Pattern */}
      <div className="fixed inset-0 z-[-1] opacity-[0.03] pointer-events-none"></div>
      
      <main className="w-full max-w-[440px] px-unit-md">
        {/* Brand Header */}
        <div className="flex flex-col items-center mb-unit-xl">
          <div className="w-12 h-12 bg-primary rounded-xl flex items-center justify-center mb-unit-md">
            <span className="material-symbols-outlined text-on-primary text-3xl" data-icon="shield_lock">shield_lock</span>
          </div>
          <h1 className="font-headline-md text-headline-md text-on-surface">PrepCoach AI</h1>
          <p className="font-label-md text-label-md text-on-surface-variant uppercase tracking-widest mt-1">Enterprise Admin Portal</p>
        </div>

        {/* Verification Card */}
        <div className="bg-white border border-outline-variant p-unit-xl shadow-sm rounded-lg">
          <div className="mb-unit-xl text-center">
            <h2 className="font-headline-sm text-headline-sm text-on-surface mb-unit-xs">Verify Your Identity</h2>
            <p className="font-body-md text-body-md text-on-surface-variant">
              Enter the 6-digit code sent to <br/> <strong>{email}</strong>
            </p>
          </div>

          {error && (
            <div className="mb-4 p-3 bg-error-container/20 text-error rounded text-body-sm font-semibold text-center border border-error/20">
              {error}
            </div>
          )}

          <form className="space-y-unit-xl" id="otp-form" onSubmit={handleVerify}>
            {/* OTP Input Grid */}
            <div className="flex justify-between gap-unit-xs">
              {[0, 1, 2, 3, 4, 5].map((index) => (
                <input 
                  key={index}
                  ref={(el) => { inputRefs.current[index] = el; }}
                  className="otp-input w-12 h-14 text-center text-headline-md font-bold bg-surface-container-lowest border border-outline-variant rounded-lg transition-all focus:border-primary focus:ring-2 focus:ring-primary/20 outline-none" 
                  maxLength={1} 
                  type="text"
                  value={otp[index]}
                  onChange={(e) => handleChange(index, e.target.value)}
                  onKeyDown={(e) => handleKeyDown(index, e)}
                  autoFocus={index === 0}
                />
              ))}
            </div>

            {/* Submit Action */}
            <button 
              className={`w-full h-12 bg-primary hover:bg-primary-container text-white font-label-md text-label-md rounded-lg transition-colors flex items-center justify-center gap-2 group ${isLoading ? 'opacity-70 cursor-not-allowed' : ''}`} 
              type="submit"
              disabled={isLoading || otp.join('').length < 6}
            >
              <span>{isLoading ? 'Verifying...' : 'Verify & Continue'}</span>
              {!isLoading && <span className="material-symbols-outlined text-[18px] group-hover:translate-x-1 transition-transform" data-icon="arrow_forward">arrow_forward</span>}
            </button>
          </form>

          {/* Footer Link & Timer */}
          <div className="mt-unit-xl text-center space-y-unit-sm">
            <p className="font-body-sm text-body-sm text-on-surface-variant">
              Didn't receive the code? 
              <button 
                className="text-primary font-bold hover:underline transition-all ml-1" 
                onClick={handleResend}
                type="button"
                disabled={isLoading}
              >
                Resend Code
              </button>
            </p>
          </div>
        </div>

        {/* Help Footer */}
        <div className="mt-unit-xl flex justify-between items-center px-unit-sm">
          <a className="flex items-center gap-1 font-body-sm text-body-sm text-on-surface-variant hover:text-primary transition-colors" href="#">
            <span className="material-symbols-outlined text-[16px]" data-icon="help">help</span>
            Support Center
          </a>
          <div className="flex gap-4">
            <span className="font-body-sm text-body-sm text-outline">v2.4.0-stable</span>
          </div>
        </div>
      </main>

      {/* Side Decoration (Illustration) */}
      <div className="hidden lg:block fixed right-0 top-0 w-1/3 h-full overflow-hidden opacity-50 pointer-events-none">
        <div className="absolute inset-0 bg-gradient-to-l from-surface to-transparent"></div>
        <img alt="Secure Infrastructure" className="w-full h-full object-cover" src="https://lh3.googleusercontent.com/aida-public/AB6AXuATwFMURL8aarIeHBqbgG06zZOBOIB71xOG_L_J8pIbdI5QBCfnoq5IK5z3DdW6xTDASoz3Wp_3mayhh2yhhKfJQrrxA4tp4DXQCp5RDdA8TgOco1TcRWZbHxEWY2C5Db3sFi3Ah_TPPGwdQZxHO3OikB8rBmZkkrciwksIQDbBrk6SLXRmTFS9NZeiLpQuYHk5-ZoQk4yn26CfNVAvrrR9mrSeyfpPVwlHiiLrITHYgcl4wQd5zcKOHHkCwP7la_4YE1kCk1vtDA"/>
      </div>
    </div>
  );
}
