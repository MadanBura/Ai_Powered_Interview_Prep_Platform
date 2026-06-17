import React from 'react';
import Card from '../ui/Card';
import Badge from '../ui/Badge';
import Button from '../ui/Button';

export interface Column {
  key: string;
  header: string;
  render?: (row: any) => React.ReactNode;
}

export interface DataTableProps {
  title?: string;
  subtitle?: string;
  actions?: React.ReactNode;
  columns: Column[];
  data: any[];
  isLoading?: boolean;
  emptyState?: React.ReactNode;
}

export default function DataTable({ title, subtitle, actions, columns, data, isLoading, emptyState }: DataTableProps) {
  return (
    <Card padding="none" className="shadow-sm overflow-hidden w-full">
      {(title || actions) && (
        <div className="p-6 border-b border-outline-variant flex justify-between items-center">
          <div>
            {title && <h3 className="text-headline-sm font-headline-sm text-on-surface">{title}</h3>}
            {subtitle && <p className="text-body-sm text-on-surface-variant mt-1">{subtitle}</p>}
          </div>
          {actions && <div className="flex gap-4">{actions}</div>}
        </div>
      )}
      <div className="overflow-x-auto">
        <table className="w-full text-left border-collapse">
          <thead className="bg-surface-container-low border-b border-outline-variant">
            <tr>
              {columns.map((col, idx) => (
                <th key={idx} className="px-6 py-4 text-label-md text-on-surface font-bold uppercase tracking-wider">
                  {col.header}
                </th>
              ))}
            </tr>
          </thead>
          <tbody className="divide-y divide-outline-variant/50">
            {isLoading ? (
              <tr><td colSpan={columns.length} className="px-6 py-8 text-center text-on-surface-variant">Loading data...</td></tr>
            ) : data.length === 0 ? (
              <tr>
                <td colSpan={columns.length} className="px-6 py-12 text-center">
                  {emptyState || "No data found."}
                </td>
              </tr>
            ) : (
              data.map((row, rowIndex) => (
                <tr key={rowIndex} className="hover:bg-surface-container-low/50 transition-colors">
                  {columns.map((col, colIndex) => (
                    <td key={colIndex} className="px-6 py-4">
                      {col.render ? col.render(row) : row[col.key]}
                    </td>
                  ))}
                </tr>
              ))
            )}
          </tbody>
        </table>
      </div>
    </Card>
  );
}
