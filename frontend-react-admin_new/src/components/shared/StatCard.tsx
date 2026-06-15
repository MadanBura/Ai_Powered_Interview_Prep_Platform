import React from 'react';
import Card from '../ui/Card';

export interface StatCardProps {
  title: string;
  value: React.ReactNode;
  icon?: string;
  trend?: {
    value: string;
    label?: string;
    positive?: boolean;
  };
  badge?: React.ReactNode;
  footer?: React.ReactNode;
}

export default function StatCard({ title, value, icon, trend, badge, footer }: StatCardProps) {
  return (
    <Card hoverable className="flex flex-col gap-1">
      <div className="flex justify-between items-start mb-2">
        {icon ? (
          <div className="w-10 h-10 rounded-lg bg-surface-container-high flex items-center justify-center text-primary">
            <span className="material-symbols-outlined">{icon}</span>
          </div>
        ) : (
          <div /> /* Spacer if no icon */
        )}
        
        {badge && (
          <div>{badge}</div>
        )}
        
        {trend && !badge && (
          <div className={`flex items-center gap-1 ${trend.positive ? 'text-green-600' : 'text-on-surface-variant'}`}>
            <span className="material-symbols-outlined text-[16px]">
              {trend.positive ? 'trending_up' : 'remove'}
            </span>
            <span className="font-label-md text-label-md">{trend.value}</span>
          </div>
        )}
      </div>
      
      <p className="font-label-md text-label-md text-on-surface-variant uppercase tracking-wider">
        {title}
      </p>
      
      <h3 className="font-headline-md text-headline-md mt-1 flex items-baseline gap-2">
        {value}
        {trend?.label && (
          <span className="text-[12px] font-normal text-on-surface-variant tracking-normal">
            {trend.label}
          </span>
        )}
      </h3>
      
      {footer && (
        <div className="mt-3">
          {footer}
        </div>
      )}
    </Card>
  );
}
