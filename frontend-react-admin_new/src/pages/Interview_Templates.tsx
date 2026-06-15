import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useFetchOptions } from '../features/mod09_interview_configuration/hooks/queries/useFetchOptions';
import Modal from '../components/ui/Modal';
import PageHeader from '../components/shared/PageHeader';
import StatCard from '../components/shared/StatCard';
import Button from '../components/ui/Button';
import Card from '../components/ui/Card';
import Badge from '../components/ui/Badge';
import DataTable from '../components/shared/DataTable';
import { useGetTemplates } from '../features/mod09_interview_configuration/hooks/queries/useGetTemplates';

export default function Interview_Templates() {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isFilterOpen, setIsFilterOpen] = useState(false);
  const navigate = useNavigate();
  const { data: optionsData } = useFetchOptions();
  const { data: templatesData, isLoading } = useGetTemplates();
  const templates = templatesData?.data || [];

  return (
    <>
      



{/* Header Section */}
<PageHeader 
  title="Interview Templates"
  description="Configure and manage AI-driven candidate assessment workflows."
  breadcrumbs={[
    { label: "Content Management" },
    { label: "Interview Templates" }
  ]}
  actions={
    <>
      <Button variant="outline" icon="filter_list" onClick={() => setIsFilterOpen(true)}>Filters</Button>
      <Button icon="add" onClick={() => navigate('/interview-config')}>Create New Template</Button>
    </>
  }
/>
{/* Filter/Quick Stats Bar */}
<div className="grid grid-cols-1 md:grid-cols-4 gap-4 mb-unit-lg">
<StatCard 
  title="Total Templates"
  value={templates.length.toString()}
  icon="description"
/>
<StatCard 
  title="Active"
  value={templates.length.toString()}
  icon="check_circle"
  iconColor="text-green-700"
/>
<StatCard 
  title="Drafts"
  value="0"
  icon="edit_note"
  iconColor="text-amber-700"
/>
<StatCard 
  title="Usage Growth"
  value="+12%"
  icon="trending_up"
  iconColor="text-blue-700"
/>
</div>
{/* Template Grid */}
<div className="bento-grid">
{isLoading ? (
  <div className="col-span-1 md:col-span-2 lg:col-span-3 p-12 bg-white rounded-xl border border-outline-variant text-center">
    <p className="text-headline-sm mb-2 text-on-surface-variant">Loading templates...</p>
  </div>
) : templates.length === 0 ? (
  <div className="col-span-1 md:col-span-2 lg:col-span-3 p-12 bg-white rounded-xl border border-outline-variant text-center">
    <div className="w-16 h-16 bg-surface-container rounded-full flex items-center justify-center mx-auto mb-4">
      <span className="material-symbols-outlined text-[32px] text-outline">description</span>
    </div>
    <p className="text-headline-sm mb-2">No Templates Found</p>
    <p className="text-body-sm text-on-surface-variant max-w-sm mx-auto">Create your first interview template to start streamlining the assessment process.</p>
  </div>
) : (
  templates.map((t: any, i: number) => (
    <div key={i} className="bg-white rounded-xl border border-outline-variant p-6 hover:shadow-lg transition-shadow">
      <div className="flex justify-between items-start mb-4">
        <h3 className="font-bold text-headline-sm">{t.name}</h3>
        <Badge variant="success">{t.status}</Badge>
      </div>
      <p className="text-body-sm text-on-surface-variant mb-4">Focus: {t.focus}</p>
      <div className="flex justify-between text-label-sm text-on-surface-variant">
        <span>Used: {t.used} times</span>
        <span>Updated: {t.changed}</span>
      </div>
    </div>
  ))
)}
{/* Create New Card CTA */}
<button 
  onClick={() => navigate('/interview-config')}
  className="bg-surface-container-low border-2 border-dashed border-outline-variant rounded-xl p-6 flex flex-col items-center justify-center gap-4 hover:border-primary/40 hover:bg-white transition-all group min-h-[280px]"
>
<div className="w-16 h-16 rounded-full bg-primary/10 flex items-center justify-center text-primary group-hover:scale-110 transition-transform">
<span className="material-symbols-outlined text-[40px]">add_circle</span>
</div>
<div className="text-center">
<h3 className="text-body-lg font-bold">New Configuration</h3>
<p className="text-body-sm text-on-surface-variant mt-1">Start from blank or use a preset</p>
</div>
</button>
</div>
{/* Table View Alternative */}
<div className="mt-unit-xl hidden lg:block">
<DataTable 
  title="Detailed Audit List"
  actions={
    <div className="flex items-center gap-2">
      <Button variant="ghost" icon="download" size="sm" />
      <Button variant="ghost" icon="view_column" size="sm" />
    </div>
  }
  columns={[
    { key: 'name', header: 'Template Name' },
    { key: 'focus', header: 'Primary AI Focus' },
    { key: 'status', header: 'Status' },
    { key: 'used', header: 'Times Used' },
    { key: 'changed', header: 'Last Change' },
    { key: 'actions', header: 'Actions' }
  ]}
  data={templates}
  emptyState={
    <div className="flex flex-col items-center py-12 text-center">
      <div className="w-16 h-16 bg-surface-container rounded-full flex items-center justify-center mx-auto mb-4">
        <span className="material-symbols-outlined text-[32px] text-outline">description</span>
      </div>
      <p className="text-headline-sm mb-2">No Templates Found</p>
      <p className="text-body-sm text-on-surface-variant max-w-sm mx-auto">Create your first interview template to start streamlining the assessment process.</p>
    </div>
  }
/>
</div>

{/* Interactive Layer / Floating State */}
<div className={`fixed inset-0 z-[60] bg-on-surface/40 backdrop-blur-sm flex items-center justify-center transition-opacity duration-300 ${isModalOpen ? 'opacity-100 pointer-events-auto' : 'opacity-0 pointer-events-none'}`} id="create-modal">
<div className={`bg-white w-full max-w-lg rounded-2xl shadow-xl overflow-hidden transition-transform duration-300 transform ${isModalOpen ? 'scale-100' : 'scale-95'}`}>
<div className="px-8 py-6 border-b border-outline-variant flex justify-between items-center">
<h3 className="text-headline-sm font-bold">New Interview Template</h3>
<button onClick={() => setIsModalOpen(false)} className="text-on-surface-variant hover:text-on-surface transition-colors">
<span className="material-symbols-outlined">close</span>
</button>
</div>
<div className="p-8 space-y-6">
<div>
<label className="block text-label-md font-bold text-on-surface-variant uppercase mb-2">Template Name</label>
<input className="w-full px-4 py-3 rounded-lg border border-outline-variant focus:ring-2 focus:ring-primary/20 focus:border-primary outline-none transition-all" placeholder="e.g. Senior Frontend Developer" type="text"/>
</div>
<div>
<label className="block text-label-md font-bold text-on-surface-variant uppercase mb-2">Role Category</label>
<select className="w-full px-4 py-3 rounded-lg border border-outline-variant focus:ring-2 focus:ring-primary/20 focus:border-primary outline-none transition-all appearance-none bg-no-repeat bg-[right_1rem_center]">
<option>Engineering</option>
<option>Design</option>
<option>Marketing</option>
<option>Sales</option>
<option>Compliance</option>
</select>
</div>
<div>
<label className="block text-label-md font-bold text-on-surface-variant uppercase mb-2">AI Agent Persona</label>
<div className="grid grid-cols-2 gap-3">
<label className="border border-outline-variant rounded-lg p-3 flex items-center gap-3 cursor-pointer hover:border-primary transition-colors">
<input className="text-primary focus:ring-primary" name="persona" type="radio"/>
<div>
<p className="text-body-md font-bold">Technical Expert</p>
<p className="text-[10px] text-on-surface-variant">Deep technical drilling</p>
</div>
</label>
<label className="border border-outline-variant rounded-lg p-3 flex items-center gap-3 cursor-pointer hover:border-primary transition-colors">
<input className="text-primary focus:ring-primary" name="persona" type="radio"/>
<div>
<p className="text-body-md font-bold">Culture Fit</p>
<p className="text-[10px] text-on-surface-variant">Value-based soft skills</p>
</div>
</label>
</div>
</div>
</div>
<div className="px-8 py-6 bg-surface-container-low border-t border-outline-variant flex justify-end gap-3">
<button onClick={() => setIsModalOpen(false)} className="px-6 py-2.5 text-label-md font-bold hover:bg-surface-container rounded-lg transition-colors">Cancel</button>
<button onClick={() => navigate('/interview-config')} className="px-6 py-2.5 bg-primary text-white font-bold rounded-lg hover:shadow-lg transition-all active:scale-95">Initialize Template</button>
</div>
</div>
</div>

<Modal isOpen={isFilterOpen} onClose={() => setIsFilterOpen(false)} title="Template Filters" footer={<Button onClick={() => setIsFilterOpen(false)}>Apply</Button>}>
  <div className="space-y-4">
    <div className="flex flex-col gap-1.5"><label className="text-label-md font-bold">Department</label><select className="w-full px-4 py-2 border border-outline-variant rounded"><option>All</option><option>Engineering</option></select></div>
    <div className="flex flex-col gap-1.5"><label className="text-label-md font-bold">Status</label><select className="w-full px-4 py-2 border border-outline-variant rounded"><option>All</option><option>Active</option><option>Draft</option></select></div>
  </div>
</Modal>

    </>
  );
}
