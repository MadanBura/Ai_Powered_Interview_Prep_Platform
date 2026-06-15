import { useState, useEffect } from 'react';
import toast from 'react-hot-toast';
import { useViewResults } from '../features/mod08_categorization_engine/hooks/queries/useViewResults';
import PageHeader from '../components/shared/PageHeader';
import StatCard from '../components/shared/StatCard';
import Button from '../components/ui/Button';
import Card from '../components/ui/Card';
import Badge from '../components/ui/Badge';

export default function Categorization_Dashboard() {
  const { data: resultsData, isLoading } = useViewResults();
  const backendResults = resultsData?.data?.results || [];

  const [results, setResults] = useState<any[]>([]);

  useEffect(() => {
    if (backendResults.length > 0 && results.length === 0) {
      setResults(backendResults);
    }
  }, [backendResults]);

  const handleAccept = (id: string) => {
    setResults(prev => prev.filter(r => r.id !== id));
    toast.success('Suggestion Accepted');
  };

  const handleModify = (id: string) => {
    toast('Modification logic would open here.', { icon: '✏️' });
  };

  const handleDeployBatch = () => {
    if (results.length === 0) {
      toast.error('No items to deploy.');
      return;
    }
    toast.success(`Batch deployed ${results.length} items successfully!`);
    setResults([]);
  };

  return (
    <>
      

{/* TOP BAR */}


<div className="max-w-[1440px] mx-auto p-gutter space-y-6">
{/* HEADER SECTION */}
<PageHeader 
  title="Categorization Dashboard"
  description="Review and validate AI-predicted taxonomy for incoming question sets."
  actions={
    <>
      <Button variant="outline" icon="filter_list">Filter View</Button>
      <Button icon="publish" onClick={handleDeployBatch}>Deploy Batch</Button>
    </>
  }
/>

{/* KPI & SUMMARY SECTION */}
<div className="grid grid-cols-1 md:grid-cols-4 gap-6">
  {/* ACCURACY KPI */}
  <StatCard 
    title="Categorization Accuracy"
    value={
      <div className="relative w-20 h-20 -my-2 mr-2">
        <svg className="w-full h-full">
          <circle cx="40" cy="40" fill="transparent" r="34" stroke="#F1F5F9" strokeWidth="6" />
          <circle className="accuracy-ring" cx="40" cy="40" fill="transparent" r="34" stroke="#16A34A" strokeDasharray="213" strokeDashoffset="12" strokeWidth="6" />
        </svg>
        <div className="absolute inset-0 flex flex-col items-center justify-center">
          <span className="text-headline-sm font-headline-sm text-primary">94%</span>
        </div>
      </div>
    }
    trend={{ value: "+2.4% vs last week", positive: true }}
  />
  <StatCard 
    title="Pending Review"
    value={results.length}
    icon="pending_actions"
    badge={<Badge variant="info">LIVE</Badge>}
  />
  <StatCard 
    title="Auto-Tagged Today"
    value="0"
    icon="bolt"
    badge={<Badge variant="neutral">SYSTEM</Badge>}
  />
  <StatCard 
    title="Low Confidence Flags"
    value="0"
    icon="report_problem"
    badge={<Badge variant="error">URGENT</Badge>}
  />
</div>
{/* BENTO GRID SPLIT VIEW CONTENT */}
<div className="grid grid-cols-12 gap-6 items-start">
{/* LIST VIEW (PENDING ITEMS) */}
<Card padding="none" className="col-span-12 lg:col-span-8 space-y-4">
<div className="px-6 py-4 border-b border-outline-variant flex justify-between items-center bg-surface-container-low/30">
<h2 className="text-label-md font-label-md text-on-surface uppercase tracking-wider">Queue: High Priority Questions</h2>
<div className="flex gap-2">
<span className="text-body-sm text-on-surface-variant">Sorting by: <span className="font-bold">Confidence Score</span></span>
</div>
</div>
<div className="divide-y divide-outline-variant/30">
  {results.length === 0 ? (
    <div className="p-12 flex flex-col items-center justify-center text-on-surface-variant">
      <span className="material-symbols-outlined text-4xl mb-4 text-outline">done_all</span>
      <p>No questions pending review</p>
    </div>
  ) : (
    results.map((item: any, idx: number) => (
      <div key={item.id || idx} className="p-6 hover:bg-surface-container-low transition-colors group">
        <div className="flex gap-4">
          <div className="pt-1">
            <input className="rounded border-outline-variant text-primary focus:ring-primary" type="checkbox"/>
          </div>
          <div className="flex-1">
            <div className="flex justify-between items-start mb-2">
              <h3 className="text-body-lg font-bold text-on-surface">{item.questionTitle}</h3>
              <Badge variant={item.confidence > 0.9 ? 'success' : 'warning'}>
                {Math.round(item.confidence * 100)}% Confident
              </Badge>
            </div>
            <div className="flex gap-4 mb-4">
              <div className="flex items-center gap-1.5 text-body-sm">
                <span className="material-symbols-outlined text-[16px] text-on-surface-variant" data-icon="category">category</span>
                <span className="font-bold text-on-surface">Suggested: {item.suggestedCategory}</span>
              </div>
            </div>
            <div className="flex gap-2">
              <Button size="sm" icon="check" onClick={() => handleAccept(item.id)}>Accept Suggestion</Button>
              <Button size="sm" variant="outline" icon="edit" onClick={() => handleModify(item.id)}>Modify</Button>
            </div>
          </div>
        </div>
      </div>
    ))
  )}
</div>
{/* PAGINATION FOOTER */}
<div className="bg-surface-container px-6 py-3 border-t border-outline-variant flex justify-between items-center">
<div className="text-body-sm text-on-surface-variant">Showing 3 of 1,284 items</div>
<div className="flex gap-2">
<Button variant="outline" icon="chevron_left" size="sm" className="w-8 h-8 p-0" />
<button className="w-8 h-8 flex items-center justify-center rounded bg-primary text-white text-body-sm font-bold">1</button>
<button className="w-8 h-8 flex items-center justify-center rounded border border-outline-variant hover:bg-white text-on-surface-variant text-body-sm">2</button>
<button className="w-8 h-8 flex items-center justify-center rounded border border-outline-variant hover:bg-white text-on-surface-variant text-body-sm">3</button>
<Button variant="outline" icon="chevron_right" size="sm" className="w-8 h-8 p-0" />
</div>
</div>
</Card>
<div className="col-span-12 lg:col-span-4 space-y-6">
{/* TAG DISTRIBUTION CARD */}
<Card padding="lg">
<h3 className="text-label-md font-label-md text-on-surface-variant uppercase mb-4">Topic Distribution (Batch)</h3>
<div className="space-y-4">
<div>
<div className="flex justify-between text-body-sm mb-1">
<span className="font-semibold">Backend Engineering</span>
<span>42%</span>
</div>
<div className="w-full bg-surface-container rounded-full h-1.5">
<div className="bg-primary h-1.5 rounded-full"></div>
</div>
</div>
<div>
<div className="flex justify-between text-body-sm mb-1">
<span className="font-semibold">Frontend Architecture</span>
<span>28%</span>
</div>
<div className="w-full bg-surface-container rounded-full h-1.5">
<div className="bg-primary h-1.5 rounded-full opacity-60"></div>
</div>
</div>
<div>
<div className="flex justify-between text-body-sm mb-1">
<span className="font-semibold">DevOps &amp; Cloud</span>
<span>15%</span>
</div>
<div className="w-full bg-surface-container rounded-full h-1.5">
<div className="bg-primary h-1.5 rounded-full opacity-40"></div>
</div>
</div>
<div>
<div className="flex justify-between text-body-sm mb-1">
<span className="font-semibold">Mobile Development</span>
<span>10%</span>
</div>
<div className="w-full bg-surface-container rounded-full h-1.5">
<div className="bg-primary h-1.5 rounded-full opacity-20"></div>
</div>
</div>
</div>
</Card>
{/* AI SUGGESTIONS LOG CARD */}
<Card padding="none">
<div className="p-4 bg-tertiary-container text-white flex items-center gap-2">
<span className="material-symbols-outlined text-[20px]" data-icon="psychology">psychology</span>
<span className="text-label-md font-label-md">Model Insights: Llama-3-Expert</span>
</div>
<div className="p-6 space-y-4">
<div className="p-3 bg-surface-container-lowest border border-outline-variant rounded-lg">
<div className="text-[11px] font-bold text-on-surface-variant uppercase mb-1">Taxonomy Update</div>
<div className="text-body-sm">Detected 48 new questions related to <span className="font-bold">Generative AI</span>. Recommended creating a new sub-category under 'Specialized Topics'.</div>
<button className="mt-2 text-primary font-bold text-[11px] hover:underline uppercase tracking-tight">Review Proposal</button>
</div>
<div className="p-3 bg-surface-container-lowest border border-outline-variant rounded-lg">
<div className="text-[11px] font-bold text-on-surface-variant uppercase mb-1">Anomaly Alert</div>
<div className="text-body-sm">Ambiguous skill mapping found in batch #722 (SQL performance optimization). </div>
<button className="mt-2 text-primary font-bold text-[11px] hover:underline uppercase tracking-tight">Inspect Batch</button>
</div>
</div>
</Card>
{/* PERFORMANCE VISUAL */}
<div className="relative rounded-xl overflow-hidden h-[200px] bg-inverse-surface">
<div className="absolute inset-0 opacity-40">

</div>
<div className="absolute inset-0 flex flex-col items-center justify-center p-6 text-center">
<div className="text-headline-md font-headline-md text-white">Categorization Engine</div>
<div className="text-body-sm text-secondary-fixed/80">Processing 2.4k req/sec</div>
<div className="mt-4 flex gap-2">
<div className="w-2 h-2 rounded-full bg-primary-fixed-dim animate-pulse"></div>
<div className="w-2 h-2 rounded-full bg-primary-fixed-dim animate-pulse delay-75"></div>
<div className="w-2 h-2 rounded-full bg-primary-fixed-dim animate-pulse delay-150"></div>
</div>
</div>
</div>
</div>
</div>
</div>

{/* FLOATING ACTION BUTTON (RELEVANT TO CONTENT) */}
<button className="fixed bottom-8 right-8 bg-primary text-white w-14 h-14 rounded-full shadow-lg flex items-center justify-center hover:scale-105 active:scale-95 transition-all z-50">
<span className="material-symbols-outlined text-[32px]" data-icon="add">add</span>
</button>


    </>
  );
}
