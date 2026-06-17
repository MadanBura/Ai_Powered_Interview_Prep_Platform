import React from 'react';
import Sidebar from '../components/Sidebar';
import Header from '../components/Header';

export default function AdminLayout({ children }: { children: React.ReactNode }) {
  return (
    <div className="flex h-screen overflow-hidden bg-surface dark:bg-inverse-surface">
      <Sidebar />
      <div className="flex-1 flex flex-col ml-[260px]">
        <Header />
        <main className="flex-1 overflow-y-auto pt-[64px] bg-[#F8FAFC]">
          {children}
        </main>
      </div>
    </div>
  );
}
