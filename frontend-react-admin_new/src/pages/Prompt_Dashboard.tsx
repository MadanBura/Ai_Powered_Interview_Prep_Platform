import { useState } from 'react';
import toast from 'react-hot-toast';
import Modal from '../components/ui/Modal';
import PageHeader from '../components/shared/PageHeader';
import StatCard from '../components/shared/StatCard';
import Button from '../components/ui/Button';
import Card from '../components/ui/Card';
import Badge from '../components/ui/Badge';
import DataTable from '../components/shared/DataTable';

export default function Prompt_Dashboard() {
  const [prompts, setPrompts] = useState([]); // Empty state
  const [isNewPromptOpen, setIsNewPromptOpen] = useState(false);
  const [isHistoryOpen, setIsHistoryOpen] = useState(false);
  const [isPersonaOpen, setIsPersonaOpen] = useState(false);

  const handleCreatePrompt = () => {
    setIsNewPromptOpen(false);
    toast.success('New prompt template created successfully!');
  };

  const handleCreatePersona = () => {
    setIsPersonaOpen(false);
    toast.success('AI Persona saved to repository!');
  };

  return (
    <>
      
{/* Side Navigation Shell */}



{/* Page Content */}
<div className="p-gutter max-w-container-max mx-auto w-full">
{/* Breadcrumbs & Actions */}
<PageHeader 
  title="Prompt Engineering Hub"
  description="Real-time performance monitoring across 12 active production models."
  breadcrumbs={[
    { label: "AI Management" },
    { label: "Prompt Dashboard" }
  ]}
  actions={
    <>
      <Button variant="outline" icon="history" onClick={() => setIsHistoryOpen(true)}>History</Button>
      <Button icon="add" onClick={() => setIsNewPromptOpen(true)}>New Prompt</Button>
    </>
  }
/>

{/* KPI Cards Grid */}
<div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-gutter mb-gutter">
  <StatCard 
    title="Average AI Latency"
    value={<>0<span className="text-body-md font-normal ml-1">ms</span></>}
    icon="timer"
    trend={{ value: "-12%", positive: true }}
    footer={
      <div className="h-1 bg-surface-container rounded-full overflow-hidden">
        <div className="h-full bg-primary w-3/4"></div>
      </div>
    }
  />
  <StatCard 
    title="Prompt Version Count"
    value="0"
    icon="pest_control"
    footer={<p className="text-on-surface-variant text-[11px]">Across 0 primary prompt sets</p>}
  />
  <StatCard 
    title="Evaluation Accuracy"
    value="0.0%"
    icon="verified"
    trend={{ value: "+0.4%", positive: true }}
    footer={
      <div className="h-1 bg-surface-container rounded-full overflow-hidden">
        <div className="h-full bg-primary w-[0%]"></div>
      </div>
    }
  />
  <StatCard 
    title="Token Consumption"
    value={<>0 <span className="text-body-md font-normal text-on-surface-variant">/day</span></>}
    icon="database"
    footer={<p className="text-on-surface-variant text-[11px]">Estimated cost: $0.00 daily</p>}
  />
</div>
{/* Middle Section: Main Chart and High Performing Personas */}
<div className="grid grid-cols-1 lg:grid-cols-3 gap-gutter mb-gutter">
{/* Evaluation Accuracy Trends */}
<Card padding="lg" className="lg:col-span-2 flex flex-col">
<div className="flex items-center justify-between mb-8">
<div>
<h4 className="font-headline-sm text-headline-sm text-on-surface">Evaluation Accuracy Trends</h4>
<p className="text-on-surface-variant font-body-md text-sm">Model confidence and ground truth matching over 30 days.</p>
</div>
<select className="bg-surface-container-low border-outline-variant rounded-lg text-sm font-label-md py-1.5 focus:ring-primary/20">
<option>Last 30 Days</option>
<option>Last 7 Days</option>
<option>Last 24 Hours</option>
</select>
</div>
<div className="flex-1 min-h-[300px] flex items-end justify-between gap-4 relative px-2">
{/* Simplified representation of a chart */}
<div className="absolute inset-0 flex flex-col justify-between opacity-10 pointer-events-none">
<div className="w-full h-px bg-on-surface"></div>
<div className="w-full h-px bg-on-surface"></div>
<div className="w-full h-px bg-on-surface"></div>
<div className="w-full h-px bg-on-surface"></div>
</div>
<div className="w-full h-full flex items-end gap-2 group cursor-pointer">
<div className="flex-1 bg-surface-container h-2 rounded-t transition-all"></div>
<div className="flex-1 bg-surface-container h-2 rounded-t transition-all"></div>
<div className="flex-1 bg-surface-container h-2 rounded-t transition-all"></div>
<div className="flex-1 bg-surface-container h-2 rounded-t transition-all"></div>
<div className="flex-1 bg-surface-container h-2 rounded-t transition-all"></div>
<div className="flex-1 bg-surface-container h-2 rounded-t transition-all"></div>
<div className="flex-1 bg-surface-container h-2 rounded-t transition-all"></div>
<div className="flex-1 bg-surface-container h-2 rounded-t transition-all"></div>
<div className="flex-1 bg-surface-container h-2 rounded-t transition-all"></div>
<div className="flex-1 bg-surface-container h-2 rounded-t transition-all"></div>
<div className="flex-1 bg-surface-container h-2 rounded-t transition-all"></div>
<div className="flex-1 bg-surface-container h-2 rounded-t transition-all"></div>
</div>
</div>
<div className="flex justify-between mt-4 text-label-md text-on-surface-variant font-label-md px-2">
<span>OCT 01</span>
<span>OCT 10</span>
<span>OCT 20</span>
<span>TODAY</span>
</div>
</Card>
{/* Top Performing Personas */}
<Card padding="lg" className="flex flex-col items-center justify-center text-center">
<span className="material-symbols-outlined text-4xl mb-4 text-outline">group_off</span>
<h4 className="font-headline-sm text-headline-sm text-on-surface mb-2">No Personas Active</h4>
<p className="text-on-surface-variant text-sm mb-4">You have not created any AI personas yet.</p>
<Button variant="secondary" onClick={() => setIsPersonaOpen(true)}>Create Persona</Button>
</Card>
</div>
{/* Bottom Section: Recent Updates List */}
<DataTable 
  title="Recent Prompt Updates"
  actions={<Button variant="ghost">View Audit Log</Button>}
  columns={[
    { key: 'id', header: 'Prompt ID' },
    { key: 'version', header: 'Version' },
    { key: 'modifier', header: 'Modifier' },
    { key: 'status', header: 'Status' },
    { key: 'lastSync', header: 'Last Sync' },
    { key: 'actions', header: 'Actions' }
  ]}
  data={prompts}
  emptyState={
    <div className="flex flex-col items-center justify-center py-8">
      <span className="material-symbols-outlined text-4xl mb-2 text-outline">history</span>
      <p className="text-body-md font-bold text-on-surface">No Recent Updates</p>
      <p className="text-sm">You haven't created or updated any prompts yet.</p>
    </div>
  }
/>
</div>
{/* Footer */}
<footer className="mt-auto py-8 px-6 text-center text-on-surface-variant border-t border-outline-variant bg-surface">
<p className="text-body-sm">© 2024 PrepCoach AI Enterprise. All systems operational.</p>
</footer>

{/* Mobile Navigation (Drawer Toggle normally would go here) */}
<div className="md:hidden fixed bottom-6 right-6 z-50">
<button className="w-14 h-14 bg-primary text-on-primary rounded-full shadow-lg flex items-center justify-center">
<span className="material-symbols-outlined">menu</span>
</button>
</div>

<Modal isOpen={isNewPromptOpen} onClose={() => setIsNewPromptOpen(false)} title="Create New Prompt" footer={<Button onClick={handleCreatePrompt}>Save Prompt</Button>}>
  <div className="space-y-4">
    <div className="flex flex-col gap-1.5"><label className="text-label-md font-bold">Prompt Name</label><input type="text" className="w-full px-4 py-2 border border-outline-variant rounded" placeholder="e.g. Senior Tech Screen" /></div>
    <div className="flex flex-col gap-1.5"><label className="text-label-md font-bold">System Instruction</label><textarea className="w-full px-4 py-2 border border-outline-variant rounded h-32" placeholder="You are an expert interviewer..." /></div>
  </div>
</Modal>

<Modal isOpen={isPersonaOpen} onClose={() => setIsPersonaOpen(false)} title="Create AI Persona" footer={<Button onClick={handleCreatePersona}>Create Persona</Button>}>
  <div className="space-y-4">
    <div className="flex flex-col gap-1.5"><label className="text-label-md font-bold">Persona Name</label><input type="text" className="w-full px-4 py-2 border border-outline-variant rounded" placeholder="e.g. System Thinker" /></div>
    <div className="flex flex-col gap-1.5"><label className="text-label-md font-bold">Tone & Style</label><select className="w-full px-4 py-2 border border-outline-variant rounded"><option>Empathetic</option><option>Strict Evaluator</option><option>Neutral</option></select></div>
  </div>
</Modal>

<Modal isOpen={isHistoryOpen} onClose={() => setIsHistoryOpen(false)} title="Prompt Version History" footer={<Button onClick={() => setIsHistoryOpen(false)}>Close</Button>}>
  <div className="space-y-3">
    <div className="p-3 border-l-2 border-primary bg-surface-container-low"><p className="text-body-sm font-bold">v2.4 - Optimization</p><p className="text-[11px] text-on-surface-variant">Deployed 2 days ago</p></div>
    <div className="p-3 border-l-2 border-outline-variant bg-surface-container-low"><p className="text-body-sm font-bold text-on-surface-variant">v2.3 - Initial Release</p><p className="text-[11px] text-on-surface-variant">Deployed 1 month ago</p></div>
  </div>
</Modal>

    </>
  );
}
