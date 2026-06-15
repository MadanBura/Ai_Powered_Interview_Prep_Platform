import React from 'react';

export interface BadgeProps extends React.HTMLAttributes<HTMLSpanElement> {
  children: React.ReactNode;
  variant?: 'success' | 'warning' | 'error' | 'info' | 'neutral';
  pulse?: boolean;
}

export default function Badge({ 
  children, 
  variant = 'neutral', 
  pulse = false,
  className = '',
  ...props 
}: BadgeProps) {
  
  const baseStyles = 'inline-flex items-center gap-1.5 px-2 py-0.5 rounded-full text-[11px] font-bold uppercase tracking-wider border';
  
  const variants = {
    success: 'bg-[#F0FDF4] text-[#16A34A] border-[#DCFCE7]',
    warning: 'bg-[#FFFBEB] text-[#D97706] border-[#FEF3C7]',
    error: 'bg-error-container text-on-error-container border-error/20',
    info: 'bg-primary/10 text-primary border-primary/20',
    neutral: 'bg-surface-container-high text-on-surface border-outline-variant'
  };

  return (
    <span 
      className={`${baseStyles} ${variants[variant]} ${className}`}
      {...props}
    >
      {pulse && <div className="w-1.5 h-1.5 rounded-full bg-current animate-pulse"></div>}
      {children}
    </span>
  );
}
