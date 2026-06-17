import { useState } from 'react';
import toast from 'react-hot-toast';
import { useGetPlatformMetrics } from '../features/mod20_analytics/hooks/queries/useGetPlatformMetrics';
import { useListQuestions } from '../features/mod06_question_repository/hooks/queries/useListQuestions';
import PageHeader from '../components/shared/PageHeader';
import StatCard from '../components/shared/StatCard';
import Button from '../components/ui/Button';
import Card from '../components/ui/Card';
import Badge from '../components/ui/Badge';
import DataTable from '../components/shared/DataTable';
import { useNavigate } from 'react-router-dom';

export default function Question_Dashboard() {
  const navigate = useNavigate();
  const { data: metricsData } = useGetPlatformMetrics();
  const metrics = metricsData?.data?.metrics;
  
  const { data: questionsData, isLoading: isLoadingQuestions } = useListQuestions();
  const questions = questionsData?.data?.questions || [];

  const [isExporting, setIsExporting] = useState(false);

  const handleExportPDF = () => {
    setIsExporting(true);
    const toastId = toast.loading('Generating PDF summary...');
    setTimeout(() => {
      toast.success('Question Bank Summary PDF exported successfully!', { id: toastId });
      setIsExporting(false);
    }, 2000);
  };

  return (
    <>
      



{/* Scrollable Canvas */}
<div className="flex-1 overflow-y-auto p-8 custom-scrollbar">
<div className="max-w-[1440px] mx-auto space-y-8">
{/* Header Actions */}
<PageHeader 
  title="Question Bank Health"
  description="Comprehensive overview of system intelligence assets."
  actions={
    <>
      <Button variant="outline" icon="download" onClick={handleExportPDF} isLoading={isExporting}>Export PDF</Button>
      <Button icon="add" onClick={() => navigate('/question-repository')}>Add Question</Button>
    </>
  }
/>

{/* KPI Bento Grid */}
<div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
  <StatCard 
    title="Total Questions"
    value={metrics?.questions || 0}
    icon="database"
    trend={{ value: "+12.5%", positive: true }}
  />
  <StatCard 
    title="Classification"
    value="0%"
    icon="category"
    trend={{ label: "Categorized", value: "" }}
    footer={
      <div className="w-full h-2 bg-surface-container rounded-full mt-2 overflow-hidden">
        <div className="h-full bg-primary w-[94%]"></div>
      </div>
    }
  />
  <StatCard 
    title="Top Tech"
    value=""
    icon="code"
    footer={
      <>
        <div className="flex flex-wrap gap-2">
          <Badge variant="neutral">Python</Badge>
          <Badge variant="neutral">AWS</Badge>
          <Badge variant="neutral">React</Badge>
        </div>
        <p className="text-body-sm text-on-surface-variant mt-2 font-medium">React is trending up 40%</p>
      </>
    }
  />
  <StatCard 
    title="Skill Gaps"
    value="0 Gaps"
    icon="warning"
    badge={<span className="text-error font-bold text-body-sm">Urgent</span>}
    footer={<p className="text-body-sm text-error/80">Found in Machine Learning</p>}
  />
</div>
{/* Main Layout Grid: Analytics & Activity */}
<div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
{/* Skill Coverage Matrix */}
<Card padding="none" className="lg:col-span-2 flex flex-col">
<div className="p-6 border-b border-outline-variant flex justify-between items-center">
<h3 className="text-headline-sm font-headline-sm text-on-surface">Domain Coverage Distribution</h3>
<div className="flex gap-2">
<Button variant="ghost" icon="filter_list" size="sm" className="p-1.5" />
<Button variant="ghost" icon="more_vert" size="sm" className="p-1.5" />
</div>
</div>
<div className="p-6 flex-1 flex flex-col justify-center min-h-[400px]">
{/* Visual Placeholder for a Chart/Matrix */}
<div className="relative w-full aspect-[2/1] bg-surface-container-low rounded-lg overflow-hidden border border-dashed border-outline-variant flex items-center justify-center">

<div className="relative z-10 text-center">
<p className="text-on-surface-variant font-label-md">Interactive Coverage Heatmap</p>
<p className="text-body-sm text-on-surface-variant/60">Live visualization of question difficulty vs frequency</p>
</div>
</div>
<div className="mt-6 grid grid-cols-3 gap-4">
<div className="text-center p-3 border border-outline-variant rounded-lg">
<p className="text-body-sm text-on-surface-variant">Frontend</p>
<p className="text-label-md text-primary">82.4% Full</p>
</div>
<div className="text-center p-3 border border-outline-variant rounded-lg">
<p className="text-body-sm text-on-surface-variant">Backend</p>
<p className="text-label-md text-primary">95.1% Full</p>
</div>
<div className="text-center p-3 border border-outline-variant rounded-lg">
<p className="text-body-sm text-on-surface-variant">Cloud Ops</p>
<p className="text-label-md text-error">42.8% Low</p>
</div>
</div>
</div>
</Card>
{/* Recent Activity Sidebar */}
<Card padding="none" className="flex flex-col h-full">
<div className="p-6 border-b border-outline-variant">
<h3 className="text-headline-sm font-headline-sm text-on-surface">Recent Activity</h3>
</div>
<div className="flex-1 overflow-y-auto p-6 space-y-6 custom-scrollbar">
{/* Activity Item */}
<div className="flex gap-4 group">
<div className="relative">
<div className="w-10 h-10 rounded-full bg-primary/10 flex items-center justify-center text-primary">
<span className="material-symbols-outlined text-[20px]">add_circle</span>
</div>
<div className="absolute top-10 left-1/2 -translate-x-1/2 w-[2px] h-10 bg-outline-variant group-last:hidden"></div>
</div>
<div className="flex-1">
<p className="text-body-md font-bold text-on-surface">New Python Question Added</p>
<p className="text-body-sm text-on-surface-variant">by <span className="font-medium">Sarah Chen</span> in <span className="text-primary font-medium">Data Science</span></p>
<span className="text-[10px] text-on-surface-variant/60 font-bold uppercase mt-1 inline-block">2 MINUTES AGO</span>
</div>
</div>
{/* Activity Item */}
<div className="flex gap-4 group">
<div className="relative">
<div className="w-10 h-10 rounded-full bg-secondary/10 flex items-center justify-center text-secondary">
<span className="material-symbols-outlined text-[20px]">edit</span>
</div>
<div className="absolute top-10 left-1/2 -translate-x-1/2 w-[2px] h-10 bg-outline-variant group-last:hidden"></div>
</div>
<div className="flex-1">
<p className="text-body-md font-bold text-on-surface">Updated JS Complexity</p>
<p className="text-body-sm text-on-surface-variant">Difficulty adjusted from Medium to High</p>
<span className="text-[10px] text-on-surface-variant/60 font-bold uppercase mt-1 inline-block">15 MINUTES AGO</span>
</div>
</div>
{/* Activity Item */}
<div className="flex gap-4 group">
<div className="relative">
<div className="w-10 h-10 rounded-full bg-tertiary/10 flex items-center justify-center text-tertiary">
<span className="material-symbols-outlined text-[20px]">verified</span>
</div>
<div className="absolute top-10 left-1/2 -translate-x-1/2 w-[2px] h-10 bg-outline-variant group-last:hidden"></div>
</div>
<div className="flex-1">
<p className="text-body-md font-bold text-on-surface">Batch Validation Complete</p>
<p className="text-body-sm text-on-surface-variant">42 questions verified for Q3 Cycle</p>
<span className="text-[10px] text-on-surface-variant/60 font-bold uppercase mt-1 inline-block">1 HOUR AGO</span>
</div>
</div>
{/* Activity Item */}
<div className="flex gap-4 group">
<div className="relative">
<div className="w-10 h-10 rounded-full bg-error/10 flex items-center justify-center text-error">
<span className="material-symbols-outlined text-[20px]">delete_forever</span>
</div>
<div className="absolute top-10 left-1/2 -translate-x-1/2 w-[2px] h-10 bg-outline-variant group-last:hidden"></div>
</div>
<div className="flex-1">
<p className="text-body-md font-bold text-on-surface">Archived Deprecated Content</p>
<p className="text-body-sm text-on-surface-variant">Legacy PHP questions moved to cold storage</p>
<span className="text-[10px] text-on-surface-variant/60 font-bold uppercase mt-1 inline-block">3 HOURS AGO</span>
</div>
</div>
</div>
<div className="p-4 bg-surface-container/30 text-center">
<button className="text-primary font-label-md hover:underline">View All Activity Logs</button>
</div>
</Card>
</div>
{/* High Density Content Table */}
<DataTable 
  title="Most Accessed Questions"
  subtitle="Popular technical interview queries this month."
  actions={
    <div className="flex bg-surface-container rounded p-1">
      <button className="px-3 py-1 text-label-md bg-white shadow-sm rounded">Weekly</button>
      <button className="px-3 py-1 text-label-md text-on-surface-variant">Monthly</button>
    </div>
  }
  columns={[
    { key: 'id', header: 'Question ID', render: (row: any) => <span className="text-code font-mono">QA-00{row.idx}</span> },
    { key: 'title', header: 'Title Snippet', render: (row: any) => (
      <>
        <p className="text-body-md font-bold text-on-surface">{row.title || 'Untitled Question'}</p>
        <p className="text-body-sm text-on-surface-variant truncate w-48">{row.category || 'Uncategorized'}</p>
      </>
    ) },
    { key: 'tech', header: 'Technology', render: (row: any) => <Badge variant="info">{row.technology || 'Unknown'}</Badge> },
    { key: 'success', header: 'Difficulty', render: (row: any) => (
      <Badge variant="warning">{row.difficulty || 'Mid'}</Badge>
    ) },
    { key: 'status', header: 'Status', render: (row: any) => <Badge variant={row.status === 'ACTIVE' ? 'success' : 'neutral'}>{row.status || 'Draft'}</Badge> },
    { key: 'actions', header: 'Actions', render: () => (
      <div className="flex justify-end gap-2">
        <Button variant="ghost" icon="edit" className="p-2" />
        <Button variant="ghost" icon="visibility" className="p-2" />
      </div>
    ) }
  ]}
  data={questions.map((q: any, idx: number) => ({ ...q, idx }))}
  isLoading={isLoadingQuestions}
  emptyState={
    <div className="flex flex-col items-center gap-3 py-8">
      <div className="w-16 h-16 bg-surface-container rounded-full flex items-center justify-center">
        <span className="material-symbols-outlined text-outline text-[32px]">quiz</span>
      </div>
      <p className="font-headline-sm text-on-surface">No Questions Found</p>
      <p className="text-body-sm text-on-surface-variant max-w-sm">The question bank is currently empty. Start building your repository.</p>
      <Button variant="outline" className="text-primary mt-2" onClick={() => navigate('/repository/questions')}>Add First Question</Button>
    </div>
  }
/>
</div>
</div>



    </>
  );
}
