import { Link } from 'react-router-dom';
import { useState, useRef, useEffect } from 'react';
import { useGetProfile } from '../features/mod02_user_profile/hooks/queries/useGetProfile';

export default function Header() {
  const { data, isLoading } = useGetProfile();
  const [isDropdownOpen, setIsDropdownOpen] = useState(false);
  const dropdownRef = useRef<HTMLDivElement>(null);
  
  useEffect(() => {
    const handleClickOutside = (event: MouseEvent) => {
      if (dropdownRef.current && !dropdownRef.current.contains(event.target as Node)) {
        setIsDropdownOpen(false);
      }
    };
    document.addEventListener('mousedown', handleClickOutside);
    return () => document.removeEventListener('mousedown', handleClickOutside);
  }, []);
  
  const handleLogout = () => {
    localStorage.removeItem('accessToken');
    localStorage.removeItem('refreshToken');
    window.location.href = '/login';
  };
  
  // Safe fallback if data is still loading or not available
  const name = data?.data?.name || 'Loading...';
  const role = data?.data?.role === 'SUPER_ADMIN' ? 'Super Admin' : (data?.data?.role || 'Admin');

  return (
    <header className="fixed top-0 right-0 left-[260px] h-[64px] flex justify-between items-center px-6 z-40 bg-white/80 backdrop-blur-md border-b border-outline-variant">
      <div className="flex items-center gap-4 flex-1">
        <div className="relative w-full max-w-md">
          <span className="material-symbols-outlined absolute left-3 top-1/2 -translate-y-1/2 text-on-surface-variant" data-icon="search">search</span>
          <input className="w-full bg-surface-container-low border border-outline-variant rounded-lg pl-10 pr-4 py-2 text-body-md focus:ring-2 focus:ring-primary/20 focus:border-primary outline-none transition-all" placeholder="Global search..." type="text" />
        </div>
      </div>
      <div className="flex items-center gap-2">
        <button className="hover:bg-surface-container rounded-full p-2 text-on-surface-variant relative transition-transform active:scale-95">
          <span className="material-symbols-outlined" data-icon="notifications">notifications</span>
          <span className="absolute top-2 right-2 w-2 h-2 bg-error rounded-full"></span>
        </button>
        <button className="hover:bg-surface-container rounded-full p-2 text-on-surface-variant transition-transform active:scale-95">
          <span className="material-symbols-outlined" data-icon="help_outline">help_outline</span>
        </button>
        <div className="relative" ref={dropdownRef}>
          <div 
            className="ml-4 flex items-center gap-3 cursor-pointer pl-4 border-l border-outline-variant hover:bg-surface-container-low transition-colors p-2 rounded-lg"
            onClick={() => setIsDropdownOpen(!isDropdownOpen)}
          >
            <div className="text-right hidden md:block">
              <div className="text-label-md font-label-md text-on-surface">{isLoading ? 'Loading...' : name}</div>
              <div className="text-[10px] text-on-surface-variant">{role}</div>
            </div>
            <img alt="Administrator Profile" className="w-8 h-8 rounded-full border border-outline-variant object-cover" src="https://lh3.googleusercontent.com/aida-public/AB6AXuDPC3wQ0MdkkIfrdGzWKb9v91PlRqhP58KDgPdF1bvgzjHUOO5YJo7KopGLhyfUTyblCKQH5jSgNBkWFjH7pKkC8g_Fl9hPzdGIUe1fik6AMhSDEZb-TP3aV7dbHWEEpSodIzFKtwxIlqixpqlj1-2jMNfXcZ5MshJdubcU3oc1WOGHHAyFWEc_wcxrTM_0Ztgebk7wkymuX5wxnD3Zt4TCPtQnZpv0tIxptSlRVbjpHEY4L-PC-tXqk7KQSUFBuhP8qRuNwYLJcw" />
          </div>
          
          {isDropdownOpen && (
            <div className="absolute right-0 mt-2 w-48 bg-white border border-outline-variant rounded-xl shadow-lg py-1 z-50 animate-in fade-in slide-in-from-top-2 duration-200">
              <div className="px-4 py-2 border-b border-outline-variant md:hidden">
                <div className="text-label-md font-bold text-on-surface">{name}</div>
                <div className="text-label-sm text-on-surface-variant">{role}</div>
              </div>
              <button 
                className="w-full text-left px-4 py-2 text-body-sm text-on-surface hover:bg-surface-container-low flex items-center gap-2"
                onClick={() => { setIsDropdownOpen(false); /* Navigate to settings */ }}
              >
                <span className="material-symbols-outlined text-[18px]">settings</span>
                Settings
              </button>
              <button 
                className="w-full text-left px-4 py-2 text-body-sm text-error hover:bg-error/10 flex items-center gap-2"
                onClick={handleLogout}
              >
                <span className="material-symbols-outlined text-[18px]">logout</span>
                Logout
              </button>
            </div>
          )}
        </div>
      </div>
    </header>
  );
}
