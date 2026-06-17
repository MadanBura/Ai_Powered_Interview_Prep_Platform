import { useState, useEffect } from 'react';
import PageHeader from '../components/shared/PageHeader';
import StatCard from '../components/shared/StatCard';
import Button from '../components/ui/Button';
import Card from '../components/ui/Card';
import Badge from '../components/ui/Badge';
import DataTable from '../components/shared/DataTable';

export default function Active_Sessions_Monitoring() {
  const [sessions, setSessions] = useState<any[]>([]); // Empty for new admin
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    setTimeout(() => setIsLoading(false), 500);
  }, []);

  return (
    <>
      



{/* Header Section */}
<PageHeader 
  title="Active Sessions"
  description="Real-time monitoring of in-progress AI candidate interviews."
  actions={
    <div className="flex gap-unit-sm">
      <div className="bg-surface border border-outline-variant rounded-lg px-4 py-2 flex items-center gap-3">
        <span className="status-pulse w-2 h-2 rounded-full bg-primary"></span>
        <span className="text-label-md font-label-md text-primary">{sessions.length} LIVE SESSIONS</span>
      </div>
      <Button icon="add">CREATE SESSION</Button>
    </div>
  }
/>
{/* KPI Grid */}
<div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-unit-md mb-unit-xl">
<StatCard 
  title="Active Now"
  value={sessions.length}
  icon="sensors"
  iconColor="text-primary"
  trend={{ value: "+3 from last hour", positive: true }}
/>
<StatCard 
  title="Avg. Duration"
  value="0m 0s"
  icon="timer"
  iconColor="text-secondary"
  footer={<div className="text-body-sm text-on-surface-variant mt-1">Target: &lt; 30m</div>}
/>
<StatCard 
  title="System Load"
  value="0%"
  icon="speed"
  iconColor="text-error"
  footer={
    <div className="w-full bg-surface-container-highest h-1 rounded-full mt-3">
      <div className="bg-primary h-full rounded-full w-[0%]"></div>
    </div>
  }
/>
<StatCard 
  title="Completion Rate"
  value="0.0%"
  icon="task_alt"
  iconColor="text-tertiary"
  footer={<div className="text-body-sm text-on-surface-variant mt-1">Last 24 hours</div>}
/>
</div>
{/* Monitoring Data Grid */}
<DataTable 
  title="Live Stream Controller"
  actions={
    <div className="flex gap-2">
      <Button variant="ghost" icon="filter_list" size="sm" />
      <Button variant="ghost" icon="refresh" size="sm" />
    </div>
  }
  columns={[
    { key: 'candidate', header: 'Candidate' },
    { key: 'type', header: 'Interview Type' },
    { key: 'start', header: 'Start Time' },
    { key: 'progress', header: 'Progress', render: () => <div className="text-center">-</div> },
    { key: 'status', header: 'Live Status' },
    { key: 'actions', header: 'Actions' }
  ]}
  data={sessions}
  isLoading={isLoading}
  emptyState={
    <div className="flex flex-col items-center gap-3 py-12">
      <div className="w-16 h-16 bg-surface-container rounded-full flex items-center justify-center">
        <span className="material-symbols-outlined text-[32px] text-outline">sensors_off</span>
      </div>
      <p className="font-headline-sm text-on-surface">No Active Sessions</p>
      <p className="text-body-sm text-on-surface-variant max-w-sm text-center">There are currently no live interviews happening on the platform.</p>
    </div>
  }
/>
{/* Asymmetric Detail Cards (System Health) */}
<div className="mt-unit-xl grid grid-cols-1 lg:grid-cols-3 gap-unit-lg">
<div className="lg:col-span-2 bg-surface border border-outline-variant rounded-xl overflow-hidden relative">
<div className="absolute inset-0 opacity-[0.03] pointer-events-none">
<div className="h-full w-full"></div>
</div>
<div className="p-unit-lg relative z-10">
<h3 className="text-headline-sm font-headline-sm text-on-surface mb-unit-md flex items-center gap-2">
<span className="material-symbols-outlined text-primary">analytics</span>
                        Real-time AI Inference Load
                    </h3>
<div className="h-48 flex items-end gap-1 overflow-hidden">
{/* Simple Mock Bar Chart for Load */}
<div className="flex-1 bg-primary/20 hover:bg-primary/40 transition-colors rounded-t h-[60%]"></div>
<div className="flex-1 bg-primary/20 hover:bg-primary/40 transition-colors rounded-t h-[65%]"></div>
<div className="flex-1 bg-primary/20 hover:bg-primary/40 transition-colors rounded-t h-[40%]"></div>
<div className="flex-1 bg-primary/20 hover:bg-primary/40 transition-colors rounded-t h-[55%]"></div>
<div className="flex-1 bg-primary/20 hover:bg-primary/40 transition-colors rounded-t h-[70%]"></div>
<div className="flex-1 bg-primary/20 hover:bg-primary/40 transition-colors rounded-t h-[85%]"></div>
<div className="flex-1 bg-primary/20 hover:bg-primary/40 transition-colors rounded-t h-[75%]"></div>
<div className="flex-1 bg-primary/20 hover:bg-primary/40 transition-colors rounded-t h-[90%]"></div>
<div className="flex-1 bg-primary/20 hover:bg-primary/40 transition-colors rounded-t h-[80%]"></div>
<div className="flex-1 bg-primary/20 hover:bg-primary/40 transition-colors rounded-t h-[65%]"></div>
<div className="flex-1 bg-primary/20 hover:bg-primary/40 transition-colors rounded-t h-[45%]"></div>
<div className="flex-1 bg-primary/20 hover:bg-primary/40 transition-colors rounded-t h-[50%]"></div>
<div className="flex-1 bg-primary/30 hover:bg-primary/50 transition-colors rounded-t h-[75%] ring-2 ring-primary ring-offset-2"></div>
<div className="flex-1 bg-primary/20 hover:bg-primary/40 transition-colors rounded-t h-[60%]"></div>
<div className="flex-1 bg-primary/20 hover:bg-primary/40 transition-colors rounded-t h-[55%]"></div>
</div>
<div className="flex justify-between mt-2 text-label-md text-on-surface-variant font-code">
<span>13:00</span>
<span>13:30</span>
<span>14:00 (CURRENT)</span>
</div>
</div>
</div>
<div className="bg-primary text-on-primary rounded-xl p-unit-lg flex flex-col justify-between shadow-lg shadow-primary/10">
<div>
<span className="text-label-md font-label-md uppercase opacity-80 tracking-widest">Network Node</span>
<h3 className="text-headline-md font-headline-md mt-1 mb-unit-md">US-EAST-1A</h3>
<div className="space-y-4">
<div className="flex items-center justify-between">
<span className="text-body-sm">Latency</span>
<span className="text-body-sm font-code">14ms</span>
</div>
<div className="w-full bg-white/20 h-1.5 rounded-full overflow-hidden">
<div className="bg-white h-full"></div>
</div>
<div className="flex items-center justify-between">
<span className="text-body-sm">Throughput</span>
<span className="text-body-sm font-code">1.2 GB/s</span>
</div>
<div className="w-full bg-white/20 h-1.5 rounded-full overflow-hidden">
<div className="bg-white h-full"></div>
</div>
</div>
</div>
<button className="mt-unit-xl w-full border border-white/30 bg-white/10 hover:bg-white/20 py-3 rounded-lg text-label-md font-label-md uppercase tracking-wider transition-colors">
                    SWITCH REGION
                </button>
</div>
</div>

{/* Micro-interaction Script */}


    </>
  );
}
