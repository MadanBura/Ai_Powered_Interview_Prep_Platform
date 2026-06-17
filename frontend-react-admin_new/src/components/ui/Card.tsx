import React from 'react';

export interface CardProps extends React.HTMLAttributes<HTMLDivElement> {
  children?: React.ReactNode;
  padding?: 'none' | 'sm' | 'md' | 'lg';
  hoverable?: boolean;
}

export default function Card({ 
  children, 
  padding = 'md', 
  hoverable = false,
  className = '',
  ...props 
}: CardProps) {
  
  const baseStyles = 'bg-surface border border-outline-variant rounded-xl overflow-hidden';
  const hoverStyles = hoverable ? 'hover:shadow-sm transition-shadow' : '';
  
  const paddings = {
    none: '',
    sm: 'p-3',
    md: 'p-5',
    lg: 'p-6'
  };

  return (
    <div 
      className={`${baseStyles} ${hoverStyles} ${paddings[padding]} ${className}`}
      {...props}
    >
      {children}
    </div>
  );
}
