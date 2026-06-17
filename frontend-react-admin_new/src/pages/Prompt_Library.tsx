import { useState } from 'react';
import { useListPrompts } from '../features/mod26_ai_prompt_management/hooks/queries/useListPrompts';
import { useCreateAiPrompt, useUpdateAiPrompt, useDeleteAiPrompt } from '../features/mod26_ai_prompt_management/hooks/mutations/useAiPromptMutations';
import PageHeader from '../components/shared/PageHeader';
import StatCard from '../components/shared/StatCard';
import Button from '../components/ui/Button';
import Card from '../components/ui/Card';
import Badge from '../components/ui/Badge';
import DataTable from '../components/shared/DataTable';
import Modal from '../components/ui/Modal';
import { AiPrompt, AiPromptRequestDto } from '../features/mod26_ai_prompt_management/types/aiprompt.types';

export default function Prompt_Library() {
  const { data: promptsData, isLoading } = useListPrompts();
  const prompts = promptsData?.data?.prompts || [];

  const createMutation = useCreateAiPrompt();
  const updateMutation = useUpdateAiPrompt();
  const deleteMutation = useDeleteAiPrompt();

  const [isModalOpen, setIsModalOpen] = useState(false);
  const [editingPrompt, setEditingPrompt] = useState<AiPrompt | null>(null);
  const [formData, setFormData] = useState<AiPromptRequestDto>({
    name: '',
    persona: 'Executive Interviewer',
    status: 'Active',
  });

  const handleOpenModal = (prompt?: AiPrompt) => {
    if (prompt) {
      setEditingPrompt(prompt);
      setFormData({ name: prompt.name, persona: prompt.persona, status: prompt.status });
    } else {
      setEditingPrompt(null);
      setFormData({ name: '', persona: 'Executive Interviewer', status: 'Active' });
    }
    setIsModalOpen(true);
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
    setEditingPrompt(null);
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (editingPrompt) {
      updateMutation.mutate({ id: editingPrompt.id, data: formData }, {
        onSuccess: () => handleCloseModal()
      });
    } else {
      createMutation.mutate(formData, {
        onSuccess: () => handleCloseModal()
      });
    }
  };

  const handleDelete = (id: string) => {
    if (confirm('Are you sure you want to delete this prompt?')) {
      deleteMutation.mutate(id);
    }
  };

  return (
    <>
      

{/* Main Canvas */}

{/* Top Utility Bar */}

{/* Content Area */}
<div className="p-unit-lg max-w-container-max mx-auto w-full flex-1 space-y-6">
{/* Page Header */}
<PageHeader 
  title="Prompt Library"
  description="Design, manage, and version system prompts for LLM response generation."
  breadcrumbs={[
    { label: "AI Management" },
    { label: "Prompt Library" }
  ]}
  actions={
    <Button icon="add" onClick={() => handleOpenModal()}>Create New Prompt</Button>
  }
/>
{/* Stats Bar (KPI Cards) */}
<div className="grid grid-cols-1 md:grid-cols-4 gap-4">
<StatCard 
  title="Active Prompts"
  value="0"
/>
<StatCard 
  title="Drafts"
  value="0"
/>
<StatCard 
  title="Average Latency"
  value="0.0s"
/>
<StatCard 
  title="Version Drift"
  value="0.00"
  trend={{ value: "Stable", positive: true }}
/>
</div>
{/* Filter and Table Section */}
<Card padding="md" className="flex flex-wrap items-center justify-between gap-4 mb-4">
<div className="flex items-center gap-3">
<div className="flex flex-col gap-1">
<label className="text-[10px] font-bold text-on-surface-variant uppercase ml-1">Target Persona</label>
<select className="bg-surface border border-outline rounded px-3 py-1.5 text-body-sm focus:ring-primary/20">
<option>All Personas</option>
<option>Executive Interviewer</option>
<option>Technical Recruiter</option>
<option>Junior Candidate</option>
<option>HR Generalist</option>
</select>
</div>
<div className="flex flex-col gap-1">
<label className="text-[10px] font-bold text-on-surface-variant uppercase ml-1">Status</label>
<select className="bg-surface border border-outline rounded px-3 py-1.5 text-body-sm focus:ring-primary/20">
<option>All Statuses</option>
<option>Active</option>
<option>Draft</option>
<option>Archived</option>
</select>
</div>
<button className="mt-5 flex items-center gap-1 text-primary text-label-md hover:underline px-2">
<span className="material-symbols-outlined text-[18px]">filter_list_off</span>
                            Reset
                        </button>
</div>
<div className="text-on-surface-variant text-body-sm">
                        Showing <span className="font-bold">0</span> of 0 prompts
                    </div>
</Card>
{/* Data Grid */}
<DataTable 
  columns={[
    { key: 'name', header: 'PROMPT NAME', render: (row: any) => <span className="font-bold text-on-surface">{row.name || 'Untitled'}</span> },
    { key: 'persona', header: 'TARGET PERSONA', render: (row: any) => <span>{row.persona || 'Unknown'}</span> },
    { key: 'status', header: 'STATUS', render: (row: any) => <Badge variant={row.status === 'Active' ? 'success' : 'neutral'}>{row.status || 'Draft'}</Badge> },
    { key: 'version', header: 'VERSION', render: (row: any) => <span className="font-code text-xs bg-surface-container px-2 py-1 rounded">{row.version || 'v1.0'}</span> },
    { key: 'lastModified', header: 'LAST MODIFIED', render: (row: any) => <span className="text-body-sm text-on-surface-variant">{row.lastModified || 'Just now'}</span> },
    { key: 'actions', header: 'ACTIONS', render: (row: any) => (
      <div className="flex justify-end gap-2">
        <Button variant="ghost" icon="edit" size="sm" onClick={() => handleOpenModal(row)} />
        <Button variant="ghost" icon="delete" size="sm" className="hover:text-error" onClick={() => handleDelete(row.id)} />
      </div>
    ) }
  ]}
  data={prompts}
  isLoading={isLoading}
  emptyState={
    <div className="flex flex-col items-center justify-center py-12">
      <span className="material-symbols-outlined text-4xl mb-2 text-outline">description</span>
      <p className="text-body-md font-bold text-on-surface">No Prompts Found</p>
      <Button variant="outline" icon="add" onClick={() => handleOpenModal()} className="mt-4">Create First Prompt</Button>
    </div>
  }
/>
{/* Footer Meta */}
<div className="pt-4 flex items-center justify-between border-t border-outline-variant">
<div className="flex items-center gap-4">
<div className="flex items-center gap-1 text-primary text-body-sm">
<span className="material-symbols-outlined text-sm">check_circle</span>
                        System Online
                    </div>
<div className="text-on-surface-variant text-[11px] flex items-center gap-1">
<span className="material-symbols-outlined text-xs">database</span>
                        v4.2.0.18-stable
                    </div>
</div>
<div className="text-on-surface-variant text-[11px] font-label-md">
                    © 2024 PrepCoach AI - ENTERPRISE PROMPT ENGINE
                </div>
</div>
</div>

{/* Create/Edit Prompt Modal */}
<Modal
  isOpen={isModalOpen}
  onClose={handleCloseModal}
  title={editingPrompt ? "Edit Prompt" : "Create New Prompt"}
  footer={
    <>
      <Button variant="ghost" onClick={handleCloseModal}>Cancel</Button>
      <Button onClick={handleSubmit} isLoading={createMutation.isPending || updateMutation.isPending}>
        {editingPrompt ? "Save Changes" : "Create Prompt"}
      </Button>
    </>
  }
>
  <form id="prompt-form" className="space-y-4" onSubmit={handleSubmit}>
    <div className="flex flex-col gap-1.5">
      <label className="text-label-md font-bold text-on-surface-variant">Prompt Name</label>
      <input
        required
        type="text"
        className="w-full px-4 py-2 bg-surface-container-lowest border border-outline-variant rounded focus:border-primary focus:ring-1 focus:ring-primary outline-none"
        value={formData.name}
        onChange={(e) => setFormData({ ...formData, name: e.target.value })}
      />
    </div>
    <div className="flex flex-col gap-1.5">
      <label className="text-label-md font-bold text-on-surface-variant">Target Persona</label>
      <select
        className="w-full px-4 py-2 bg-surface-container-lowest border border-outline-variant rounded focus:border-primary focus:ring-1 focus:ring-primary outline-none"
        value={formData.persona}
        onChange={(e) => setFormData({ ...formData, persona: e.target.value })}
      >
        <option value="Executive Interviewer">Executive Interviewer</option>
        <option value="Technical Recruiter">Technical Recruiter</option>
        <option value="Junior Candidate">Junior Candidate</option>
        <option value="HR Generalist">HR Generalist</option>
      </select>
    </div>
  </form>
</Modal>

    </>
  );
}
