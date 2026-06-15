import { useState } from 'react';
import toast from 'react-hot-toast';
import Modal from '../components/ui/Modal';
import PageHeader from '../components/shared/PageHeader';
import StatCard from '../components/shared/StatCard';
import Button from '../components/ui/Button';
import Card from '../components/ui/Card';
import Badge from '../components/ui/Badge';
import DataTable from '../components/shared/DataTable';
import { ScatterChart, Scatter, BarChart, Bar, ResponsiveContainer, XAxis, YAxis, ZAxis, Tooltip } from 'recharts';
import { useGetEvaluationMetrics, useGetEvaluationFlags } from '../features/mod14_ai_evaluation/hooks/queries/useGetEvaluationMetrics';

export default function AI_Evaluation_Dashboard() {
  const { data: metricsData, isLoading: isMetricsLoading } = useGetEvaluationMetrics();
  const { data: flagsData, isLoading: isFlagsLoading } = useGetEvaluationFlags();

  const heatmapData = metricsData?.data?.heatmapData || [];
  const varianceData = metricsData?.data?.varianceData || [];
  const flags = flagsData?.data || [];
  const [isFilterOpen, setIsFilterOpen] = useState(false);

  const handleExport = () => {
    const csvContent = "AI Score,Human Score,Volume\n2,2,15\n3,2,5"; // Mock data
    const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' });
    const url = URL.createObjectURL(blob);
    const link = document.createElement('a');
    link.setAttribute('href', url);
    link.setAttribute('download', 'evaluations_export.csv');
    link.style.visibility = 'hidden';
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
    toast.success('Evaluations report exported!');
  };

  return (
    <>
      
{/* Fixed Sidebar */}


{/* Top Nav Bar */}

{/* Dashboard Content */}
<div className="p-unit-lg max-w-container-max mx-auto w-full space-y-6">
{/* Page Title & Controls */}
<PageHeader 
  title="AI Evaluation Dashboard"
  description="Monitoring accuracy, latency, and feedback loops across enterprise models."
  actions={
    <>
      <Button variant="outline" icon="filter_list" onClick={() => setIsFilterOpen(true)}>FILTER</Button>
      <Button icon="download" onClick={handleExport}>EXPORT REPORT</Button>
    </>
  }
/>

{/* KPI Row */}
<div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-4">
  <StatCard 
    title="AVERAGE SCORE CORRELATION"
    value="0.00"
    icon="verified_user"
    trend={{ value: "No data yet" }}
  />
  <StatCard 
    title="AVG PROCESSING TIME"
    value="0.0s"
    icon="timer"
    trend={{ value: "No data yet" }}
  />
  <StatCard 
    title="FEEDBACK SENTIMENT"
    value="0.0/10"
    icon="sentiment_satisfied"
    trend={{ value: "No data yet" }}
  />
  <StatCard 
    title="ACTIVE FLAGS"
    value="0"
    icon="flag"
    trend={{ value: "No flags pending", positive: true }}
  />
</div>
{/* Main Charts Grid */}
<div className="grid grid-cols-1 lg:grid-cols-12 gap-6">
{/* Score Correlation Heatmap */}
<Card padding="lg" className="lg:col-span-8">
<div className="flex justify-between items-center mb-6">
<h3 className="font-headline-sm text-headline-sm text-on-surface">Score Correlation Heatmap</h3>
<div className="flex items-center gap-4 text-xs font-bold text-on-surface-variant">
<div className="flex items-center gap-1"><span className="w-3 h-3 bg-primary/20 rounded"></span> Low</div>
<div className="flex items-center gap-1"><span className="w-3 h-3 bg-primary/60 rounded"></span> Mid</div>
<div className="flex items-center gap-1"><span className="w-3 h-3 bg-primary rounded"></span> Perfect Match</div>
</div>
</div>
<div className="h-64 mt-6">
  {isMetricsLoading ? <div className="w-full h-full flex items-center justify-center text-on-surface-variant">Loading...</div> : heatmapData.length > 0 ? (
  <ResponsiveContainer width="100%" height="100%">
    <ScatterChart margin={{ top: 20, right: 20, bottom: 20, left: 20 }}>
      <XAxis type="number" dataKey="human" name="Human Score" ticks={[1,2,3,4,5]} tickLine={false} axisLine={false} label={{ value: 'Human Benchmark', position: 'bottom', offset: 0, fill: '#64748b', fontSize: 12 }} />
      <YAxis type="number" dataKey="ai" name="AI Score" ticks={[1,2,3,4,5]} tickLine={false} axisLine={false} label={{ value: 'AI Score', angle: -90, position: 'insideLeft', fill: '#64748b', fontSize: 12 }} />
      <ZAxis type="number" dataKey="count" range={[50, 800]} />
      <Tooltip cursor={{ strokeDasharray: '3 3' }} contentStyle={{ borderRadius: '8px', border: 'none', boxShadow: '0 4px 6px -1px rgb(0 0 0 / 0.1)' }} />
      <Scatter data={heatmapData} fill="#4f46e5" opacity={0.8} />
    </ScatterChart>
  </ResponsiveContainer>
  ) : <div className="w-full h-full flex items-center justify-center text-on-surface-variant italic">No data available</div>}
</div>
</Card>
{/* Score Variance Distribution */}
<Card padding="lg" className="lg:col-span-4 flex flex-col">
<h3 className="font-headline-sm text-headline-sm text-on-surface mb-6">Score Variance Distribution</h3>
<div className="h-64 mt-6">
  {isMetricsLoading ? <div className="w-full h-full flex items-center justify-center text-on-surface-variant">Loading...</div> : varianceData.length > 0 ? (
  <ResponsiveContainer width="100%" height="100%">
    <BarChart data={varianceData} margin={{ top: 20, right: 20, bottom: 20, left: 20 }}>
      <XAxis dataKey="name" tickLine={false} axisLine={false} label={{ value: 'Score Point Variance', position: 'bottom', offset: 0, fill: '#64748b', fontSize: 12 }} />
      <YAxis tickLine={false} axisLine={false} />
      <Tooltip cursor={{ fill: '#f1f5f9' }} contentStyle={{ borderRadius: '8px', border: 'none', boxShadow: '0 4px 6px -1px rgb(0 0 0 / 0.1)' }} />
      <Bar dataKey="count" fill="#0ea5e9" radius={[4, 4, 0, 0]} />
    </BarChart>
  </ResponsiveContainer>
  ) : <div className="w-full h-full flex items-center justify-center text-on-surface-variant italic">No data available</div>}
</div>
</Card>
</div>
{/* Low Confidence Flags Table */}
<DataTable 
  title="Low Confidence Flags"
  actions={<Badge variant="error">Requires Manual Review</Badge>}
  columns={[
    { key: 'id', header: 'INTERVIEW ID' },
    { key: 'type', header: 'QUESTION TYPE' },
    { key: 'confidence', header: 'AI CONFIDENCE' },
    { key: 'reason', header: 'FLAG REASON' },
    { key: 'timestamp', header: 'TIMESTAMP' },
    { key: 'action', header: 'ACTION' }
  ]}
  data={flags}
  emptyState={
    <div className="flex flex-col items-center justify-center py-12">
      <span className="material-symbols-outlined text-4xl mb-2 text-outline">done_all</span>
      <p className="text-body-md font-bold text-on-surface">All caught up</p>
      <p className="text-sm">No low confidence flags pending manual review.</p>
    </div>
  }
/>

</div>
{/* Footer / System Logs Drawer Indicator */}
<footer className="mt-auto py-4 px-unit-lg border-t border-outline-variant bg-surface flex justify-between items-center">
<div className="flex items-center gap-4 text-xs font-bold text-on-surface-variant uppercase tracking-tighter">
<span className="flex items-center gap-1"><span className="w-2 h-2 bg-primary rounded-full"></span> SYSTEM ONLINE</span>
<span className="flex items-center gap-1"><span className="w-2 h-2 bg-primary rounded-full"></span> EVALUATOR READY</span>
</div>
<p className="text-xs text-on-surface-variant">© 2024 PrepCoach AI Enterprise. All rights reserved.</p>
</footer>

{/* Floating Action Button for Emergency Manual Override */}
<button className="fixed bottom-unit-lg right-unit-lg w-14 h-14 bg-error text-white rounded-full shadow-lg hover:scale-105 transition-all flex items-center justify-center group">
<span className="material-symbols-outlined">warning</span>
<span className="absolute right-full mr-4 px-3 py-1 bg-inverse-surface text-white text-xs rounded opacity-0 group-hover:opacity-100 transition-opacity whitespace-nowrap pointer-events-none">SYSTEM OVERRIDE</span>
</button>
<Modal isOpen={isFilterOpen} onClose={() => setIsFilterOpen(false)} title="Filter Evaluations" footer={<Button onClick={() => setIsFilterOpen(false)}>Apply</Button>}>
  <div className="space-y-4">
    <div className="flex flex-col gap-1.5"><label className="text-label-md font-bold">Model</label><select className="w-full px-4 py-2 border border-outline-variant rounded"><option>All Models</option><option>GPT-4 Turbo</option><option>Claude 3</option></select></div>
    <div className="flex flex-col gap-1.5"><label className="text-label-md font-bold">Date Range</label><input type="date" className="w-full px-4 py-2 border border-outline-variant rounded" /></div>
  </div>
</Modal>

    </>
  );
}
