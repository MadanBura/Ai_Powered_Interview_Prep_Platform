import React from 'react';

export interface ButtonProps extends React.ButtonHTMLAttributes<HTMLButtonElement> {
  variant?: 'primary' | 'secondary' | 'outline' | 'ghost';
  size?: 'sm' | 'md' | 'lg';
  icon?: string;
  children: React.ReactNode;
}

export default function Button({ 
  variant = 'primary', 
  size = 'md', 
  icon, 
  children, 
  className = '',
  ...props 
}: ButtonProps) {
  
  const baseStyles = 'font-label-md flex items-center justify-center gap-2 rounded-lg transition-all active:scale-[0.98]';
  
  const variants = {
    primary: 'bg-primary text-on-primary shadow-sm hover:opacity-90',
    secondary: 'bg-secondary text-on-secondary shadow-sm hover:bg-secondary-container hover:text-on-secondary-container',
    outline: 'bg-surface border border-outline-variant text-on-surface hover:bg-surface-container-high',
    ghost: 'bg-transparent text-on-surface hover:bg-surface-container'
  };

  const sizes = {
    sm: 'px-3 py-1.5 text-label-sm',
    md: 'px-4 py-2 text-label-md',
    lg: 'px-6 py-3 text-label-lg'
  };

  return (
    <button 
      className={`${baseStyles} ${variants[variant]} ${sizes[size]} ${className}`}
      {...props}
    >
      {icon && <span className="material-symbols-outlined text-[18px]">{icon}</span>}
      {children}
    </button>
  );
}
