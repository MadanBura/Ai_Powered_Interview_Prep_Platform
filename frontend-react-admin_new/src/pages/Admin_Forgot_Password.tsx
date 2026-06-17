import { useState } from 'react';
import { Link } from 'react-router-dom';

export default function Admin_Forgot_Password() {
  const [showSuccess, setShowSuccess] = useState(false);
  const [email, setEmail] = useState('');

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (email) {
      setShowSuccess(true);
    }
  };

  return (
    <>
      
{/* Atmospheric Background Element */}
<div className="fixed inset-0 z-0 pointer-events-none overflow-hidden">
<div className="absolute -top-[10%] -right-[5%] w-[500px] h-[500px] bg-primary/5 rounded-full blur-[100px]"></div>
<div className="absolute -bottom-[10%] -left-[5%] w-[400px] h-[400px] bg-secondary-container/10 rounded-full blur-[100px]"></div>
</div>
{/* Main Viewport Canvas */}
<main className="relative z-10 flex flex-col items-center justify-center min-h-screen px-unit-lg py-unit-xl">
{/* Branding Header */}
<div className="mb-unit-xl text-center">
<h1 className="font-headline-md text-headline-md font-black text-on-surface tracking-tighter">
                PrepCoach AI
            </h1>
<p className="font-label-md text-label-md text-on-surface-variant mt-1 uppercase tracking-widest">
                Enterprise Admin Portal
            </p>
</div>
{/* Recovery Card */}
<div className="w-full max-w-[440px] bg-surface-container-lowest border border-outline-variant p-10 rounded-lg shadow-sm">
{/* Header Section */}
<div className="flex flex-col items-center text-center mb-unit-xl">
<div className="w-14 h-14 bg-primary-container/10 rounded-full flex items-center justify-center mb-6">
<span className="material-symbols-outlined text-primary text-[32px]" data-icon="lock_reset">lock_reset</span>
</div>
<h2 className="font-headline-lg text-headline-lg text-on-surface mb-3">
                    Reset Your Password
                </h2>
<p className="font-body-md text-body-md text-on-surface-variant max-w-[320px]">
                    Enter your work email to receive password reset instructions.
                </p>
</div>
{/* Form Section */}
<form className="space-y-6" id="resetForm" onSubmit={handleSubmit}>
<div className="flex flex-col gap-2">
<label className="font-label-md text-label-md text-on-surface-variant ml-1" htmlFor="email">
                        WORK EMAIL ADDRESS
                    </label>
<div className="relative group">
<div className="absolute inset-y-0 left-0 pl-3.5 flex items-center pointer-events-none">
<span className="material-symbols-outlined text-outline text-body-lg" data-icon="mail">mail</span>
</div>
<input 
  className="w-full pl-11 pr-4 py-3 bg-surface border border-outline-variant rounded-lg font-body-md text-on-surface placeholder:text-outline/60 focus:ring-2 focus:ring-primary/20 focus:border-primary outline-none transition-all" 
  id="email" 
  name="email" 
  placeholder="name@company.com" 
  required 
  type="email"
  value={email}
  onChange={(e) => setEmail(e.target.value)}
/>
</div>
</div>
{/* Primary Action */}
<button className="w-full bg-primary hover:bg-primary-container text-white font-bold py-4 px-6 rounded-lg transition-all flex items-center justify-center gap-2 group active:scale-[0.98]" id="submitBtn" type="submit">
<span>Send Reset Link</span>
<span className="material-symbols-outlined text-[18px] transition-transform group-hover:translate-x-1" data-icon="arrow_forward">arrow_forward</span>
</button>
</form>
{/* Secondary Action / Back Link */}
<div className="mt-8 pt-8 border-t border-outline-variant text-center">
<Link className="inline-flex items-center gap-2 font-label-md text-label-md text-primary hover:text-primary-container font-bold transition-colors group" to="/login">
<span className="material-symbols-outlined text-[18px]" data-icon="arrow_back">arrow_back</span>
<span>Return to Login</span>
</Link>
</div>
</div>
{/* Footer / Support */}
<footer className="mt-12 text-center">
<p className="font-body-sm text-body-sm text-on-surface-variant opacity-60">
                Security by PrepCoach AI Identity Protection Services.<br/>
                Need help? <a className="underline hover:text-on-surface transition-colors" href="#">Contact System Administrator</a>
</p>
</footer>
</main>
{/* Success Modal */}
{showSuccess && (
  <div className="fixed inset-0 z-50 bg-on-background/40 backdrop-blur-sm flex items-center justify-center p-unit-lg" id="successOverlay">
  <div className="bg-surface border border-outline-variant p-8 rounded-lg max-w-[400px] w-full text-center shadow-2xl">
  <div className="w-16 h-16 bg-primary-container rounded-full flex items-center justify-center mx-auto mb-6">
  <span className="material-symbols-outlined text-primary text-[40px]" data-icon="check_circle">check_circle</span>
  </div>
  <h3 className="font-headline-sm text-headline-sm text-on-surface mb-2">Check Your Email</h3>
  <p className="font-body-md text-body-md text-on-surface-variant mb-8">
                  We've sent a secure recovery link to your inbox. It will expire in 15 minutes.
              </p>
  <button 
    className="w-full bg-primary text-white py-3 px-6 rounded-lg font-bold hover:bg-primary-container transition-colors"
    onClick={() => setShowSuccess(false)}
  >
                  Done
              </button>
  </div>
  </div>
)}


    </>
  );
}
