import { Link, useLocation } from 'react-router-dom';

export default function Sidebar() {
  const location = useLocation();

  const isActive = (path: string) => location.pathname === path;

  const NavLink = ({ to, icon, label }: { to: string, icon: string, label: string }) => {
    const active = isActive(to);
    return (
      <Link 
        className={`flex items-center gap-3 px-4 py-3 transition-colors duration-150 ${active ? 'bg-primary-container/10 dark:bg-primary-container/20 text-primary dark:text-primary-fixed-dim border-l-4 border-primary opacity-90' : 'text-on-surface-variant dark:text-on-secondary-fixed-variant hover:bg-surface-container dark:hover:bg-secondary-container/10'}`} 
        to={to}
      >
        <span className="material-symbols-outlined" data-icon={icon}>{icon}</span>
        <span className={`text-label-md font-label-md ${active ? 'font-bold' : ''}`}>{label}</span>
      </Link>
    );
  };

  return (
    <aside className="fixed left-0 top-0 h-full w-[260px] bg-white border-r border-outline-variant flex flex-col z-50">
      <div className="px-6 py-8">
        <div className="text-headline-sm font-headline-sm font-bold text-primary dark:text-primary-fixed-dim">AdminPortal</div>
        <div className="text-label-md font-label-md text-on-surface-variant opacity-70 mt-1">Enterprise AI v2.4</div>
      </div>
      <nav className="flex-1 px-2 space-y-1 overflow-y-auto custom-scrollbar">
        <NavLink to="/dashboard" icon="dashboard" label="Dashboard" />
        <NavLink to="/users" icon="admin_panel_settings" label="User Management" />
        <NavLink to="/departments" icon="corporate_fare" label="Department Management" />
        <NavLink to="/questions" icon="inventory_2" label="Question Dashboard" />
        <NavLink to="/categorization" icon="category" label="Categorization" />
        <NavLink to="/interview-config" icon="video_chat" label="Interview Configuration" />
        <NavLink to="/interview-templates" icon="list_alt" label="Interview Templates" />
        <NavLink to="/prompts" icon="psychology" label="Prompt Dashboard" />
        <NavLink to="/evaluations" icon="analytics" label="AI Evaluation" />
        <NavLink to="/replay" icon="replay" label="Session Replay" />
      </nav>
      <div className="px-2 pb-6 pt-4 border-t border-outline-variant/30">
        <Link className="flex items-center gap-3 text-on-surface-variant dark:text-on-secondary-fixed-variant px-4 py-3 hover:bg-surface-container transition-colors" to="/help">
          <span className="material-symbols-outlined" data-icon="help">help</span>
          <span className="text-label-md font-label-md">Help Center</span>
        </Link>
        <Link className="flex items-center gap-3 text-on-surface-variant dark:text-on-secondary-fixed-variant px-4 py-3 hover:bg-surface-container transition-colors" to="/login" onClick={() => localStorage.clear()}>
          <span className="material-symbols-outlined" data-icon="logout">logout</span>
          <span className="text-label-md font-label-md">Logout</span>
        </Link>
      </div>
    </aside>
  );
}
