import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import toast from 'react-hot-toast';
import { useListQuestions } from '../features/mod06_question_repository/hooks/queries/useListQuestions';
import { useCreateQuestion, useUpdateQuestion, useDeleteQuestion } from '../features/mod06_question_repository/hooks/mutations/useQuestionMutations';
import { useGenerateQuestions } from '../features/mod06_question_repository/hooks/mutations/useGenerateQuestions';
import PageHeader from '../components/shared/PageHeader';
import StatCard from '../components/shared/StatCard';
import Button from '../components/ui/Button';
import Card from '../components/ui/Card';
import Badge from '../components/ui/Badge';
import DataTable from '../components/shared/DataTable';
import Modal from '../components/ui/Modal';
import { Question, QuestionRequestDto } from '../features/mod06_question_repository/types/question.types';
import { useListTechnologies } from '../features/mod04_technology_management/hooks/queries/useListTechnologies';

export default function Question_Repository() {
  const { data: questionsData, isLoading } = useListQuestions();
  const questions = questionsData?.data?.questions || [];

  const { data: techData } = useListTechnologies();
  const technologies = techData?.data?.technologies || [];

  const navigate = useNavigate();
  const [techFilter, setTechFilter] = useState('');
  const [diffFilter, setDiffFilter] = useState('');
  const [catFilter, setCatFilter] = useState('');
  const [isGenerating, setIsGenerating] = useState(false);

  const filteredQuestions = questions.filter((q: any) => {
    const mTech = techFilter ? q.technology?.toLowerCase() === techFilter.toLowerCase() : true;
    const mDiff = diffFilter ? q.difficulty?.toLowerCase() === diffFilter.toLowerCase() : true;
    const mCat = catFilter ? q.category?.toLowerCase() === catFilter.toLowerCase() : true;
    return mTech && mDiff && mCat;
  });

  const clearFilters = () => {
    setTechFilter('');
    setDiffFilter('');
    setCatFilter('');
  };

  const createMutation = useCreateQuestion();
  const updateMutation = useUpdateQuestion();
  const deleteMutation = useDeleteQuestion();
  const generateMutation = useGenerateQuestions();

  const handleGenerate = () => {
    setIsGenerating(true);
    const toastId = toast.loading('AI is generating questions...');
    generateMutation.mutate(undefined, {
      onSuccess: () => {
        toast.success('Successfully generated 10 new questions!', { id: toastId });
        setIsGenerating(false);
      },
      onError: () => {
        toast.error('Failed to generate questions', { id: toastId });
        setIsGenerating(false);
      }
    });
  };

  const [isModalOpen, setIsModalOpen] = useState(false);
  const [editingQuestion, setEditingQuestion] = useState<Question | null>(null);
  const [formData, setFormData] = useState<QuestionRequestDto>({
    title: '',
    category: 'Technical',
    technology: 'React',
    difficulty: 'Mid',
  });

  const handleOpenModal = (question?: Question) => {
    if (question) {
      setEditingQuestion(question);
      setFormData({ 
        title: question.title, 
        category: question.category, 
        technology: question.technology, 
        difficulty: question.difficulty 
      });
    } else {
      setEditingQuestion(null);
      setFormData({ title: '', category: 'Technical', technology: 'React', difficulty: 'Mid' });
    }
    setIsModalOpen(true);
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
    setEditingQuestion(null);
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (editingQuestion) {
      updateMutation.mutate({ id: editingQuestion.id, data: formData }, {
        onSuccess: () => handleCloseModal()
      });
    } else {
      createMutation.mutate(formData, {
        onSuccess: () => handleCloseModal()
      });
    }
  };

  const handleDelete = (id: string) => {
    if (confirm('Are you sure you want to delete this question?')) {
      deleteMutation.mutate(id);
    }
  };

  return (
    <>
      

{/* Main Canvas Area */}


{/* Content Content */}
<div className="pt-header-height flex-1 p-gutter max-w-container-max">
{/* Page Header Actions */}
<PageHeader 
  title="Question Repository"
  description="Manage and curate your enterprise interview bank."
  actions={
    <Button icon="add" onClick={() => handleOpenModal()}>Create Question</Button>
  }
/>
{/* Stats Bar */}
<div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-gutter mb-unit-xl">
<StatCard 
  title="Total Questions"
  value={questions.length}
  trend={{ value: "New Set", positive: true }}
/>
<StatCard 
  title="Active Tech Stacks"
  value="42"
  footer={
    <div className="flex gap-1 mt-2">
      <div className="h-1 w-full bg-primary rounded-full"></div>
      <div className="h-1 w-1/2 bg-outline-variant rounded-full"></div>
    </div>
  }
/>
<StatCard 
  title="Peer Reviewed"
  value="89%"
  footer={<p className="text-body-sm text-on-surface-variant mt-1">14 pending review</p>}
/>
<StatCard 
  title="High Difficulty"
  value="156"
  footer={<p className="text-body-sm text-on-surface-variant mt-1">Senior/Staff level</p>}
/>
</div>
{/* Filter Bar */}
<Card padding="md" className="flex flex-wrap items-center gap-3 mb-4">
<div className="flex items-center gap-2 border-r border-outline-variant pr-4 mr-1">
<span className="text-label-md text-on-surface-variant uppercase">Bulk Actions</span>
<Button variant="outline" size="sm" disabled>Archive</Button>
<Button variant="outline" size="sm" className="text-error border-error/50 hover:bg-error/10" disabled>Delete</Button>
</div>
<div className="flex flex-1 items-center gap-3 overflow-x-auto pb-1 md:pb-0">
<select className="bg-white border border-outline-variant rounded-lg px-3 py-1.5 text-body-sm focus:ring-primary focus:border-primary" value={techFilter} onChange={e => setTechFilter(e.target.value)}>
<option value="">All Technologies</option>
{technologies.map((t: any) => (
  <option key={t.id} value={t.name}>{t.name}</option>
))}
</select>
<select className="bg-white border border-outline-variant rounded-lg px-3 py-1.5 text-body-sm focus:ring-primary focus:border-primary" value={diffFilter} onChange={e => setDiffFilter(e.target.value)}>
<option value="">Difficulty</option>
<option value="Entry">Entry</option>
<option value="Mid">Mid</option>
<option value="Senior">Senior</option>
</select>
<select className="bg-white border border-outline-variant rounded-lg px-3 py-1.5 text-body-sm focus:ring-primary focus:border-primary" value={catFilter} onChange={e => setCatFilter(e.target.value)}>
<option value="">Category</option>
<option value="Technical">Technical</option>
<option value="Behavioral">Behavioral</option>
</select>
<button className="text-primary text-label-md font-bold hover:underline flex items-center gap-1 whitespace-nowrap" onClick={clearFilters}>
<span className="material-symbols-outlined text-[16px]">filter_list</span>
                        Clear All Filters
                    </button>
</div>
<div className="text-body-sm text-on-surface-variant ml-auto">
                    Showing 1-10 of 1,284
                </div>
</Card>
{/* Data Grid */}
<DataTable 
  columns={[
    { key: 'checkbox', header: '', render: () => <input className="rounded border-outline-variant text-primary focus:ring-primary" type="checkbox"/> },
    { key: 'text', header: 'Question Text', render: (row: any) => (
      <div className="max-w-xs xl:max-w-md">
        <p className="text-body-md font-semibold text-on-surface truncate">{row.title || 'Untitled'}</p>
        <p className="text-[11px] text-on-surface-variant mt-1">Created: Just now • By Admin</p>
      </div>
    ) },
    { key: 'category', header: 'Category', render: (row: any) => (
      <span className="bg-tertiary-container/10 text-on-tertiary-fixed-variant px-2 py-1 rounded text-[11px] font-bold uppercase">{row.category || 'Technical'}</span>
    ) },
    { key: 'technology', header: 'Technology', render: (row: any) => (
      <div className="flex items-center gap-2">
        <div className="w-6 h-6 rounded bg-secondary-container flex items-center justify-center text-[10px] font-bold">{row.technology ? row.technology.charAt(0).toUpperCase() : 'R'}</div>
        <span className="text-body-sm">{row.technology || 'React'}</span>
      </div>
    ) },
    { key: 'difficulty', header: 'Difficulty', render: (row: any) => <span className="text-body-sm font-medium">{row.difficulty || 'Mid'}</span> },
    { key: 'status', header: 'Status', render: (row: any) => <Badge variant="success">{row.status || 'Active'}</Badge> },
    { key: 'actions', header: 'Actions', render: (row: any) => (
      <div className="flex justify-end gap-2">
        <Button variant="ghost" icon="edit" size="sm" className="text-on-surface-variant hover:text-primary" onClick={() => handleOpenModal(row)} />
        <Button variant="ghost" icon="delete" size="sm" className="text-on-surface-variant hover:text-error" onClick={() => handleDelete(row.id)} />
      </div>
    ) }
  ]}
  data={filteredQuestions}
  isLoading={isLoading}
  emptyState={
    <div className="flex flex-col items-center gap-3 py-12">
      <div className="w-16 h-16 bg-surface-container rounded-full flex items-center justify-center">
        <span className="material-symbols-outlined text-outline text-[32px]">question_mark</span>
      </div>
      <p className="font-headline-sm text-on-surface">No Questions Available</p>
      <p className="text-body-sm text-on-surface-variant max-w-sm">Create your first interview question to start building the repository.</p>
      <Button variant="outline" icon="add" onClick={() => handleOpenModal()}>Create Question</Button>
    </div>
  }
/>
{/* Bento Card Grid for Quick Insights */}
<div className="grid grid-cols-1 md:grid-cols-3 gap-gutter mt-unit-xl">
<Card padding="lg" className="md:col-span-2 relative group overflow-hidden">
<div className="relative z-10">
<h3 className="text-headline-sm font-headline-sm mb-2">Review Queue</h3>
<p className="text-body-md text-on-surface-variant mb-6 max-w-sm">There are 14 questions currently waiting for peer validation before they can be added to the live repository.</p>
<Button variant="ghost" className="px-0 hover:bg-transparent hover:translate-x-1" icon="arrow_forward" onClick={() => navigate('/categorization')}>
                            Open review workspace
</Button>
</div>
<div className="absolute right-0 top-0 bottom-0 w-1/3 opacity-5 group-hover:opacity-10 transition-opacity">
<span className="material-symbols-outlined text-[120px] absolute -right-4 -bottom-4 rotate-12">rate_review</span>
</div>
</Card>
<div className="bg-primary text-on-primary rounded-xl p-unit-lg flex flex-col justify-between relative overflow-hidden">
<div className="relative z-10">
<span className="material-symbols-outlined text-[32px] mb-4">bolt</span>
<h3 className="text-headline-sm font-headline-sm">AI Suggestion</h3>
<p className="text-body-sm opacity-90 mt-2">Generate 10 new React Hooks questions based on recent industry trends.</p>
</div>
<button className="relative z-10 w-full mt-6 bg-white/10 hover:bg-white/20 border border-white/20 py-2 rounded-lg text-body-sm font-bold backdrop-blur-md transition-colors" onClick={handleGenerate} disabled={isGenerating || generateMutation.isPending}>
                        {isGenerating || generateMutation.isPending ? 'Generating...' : 'Generate Now'}
                    </button>
{/* Subtle pattern */}
<div className="absolute top-0 right-0 p-8 opacity-20">
<div className="w-32 h-32 border-4 border-dashed border-white rounded-full"></div>
</div>
</div>
</div>
</div>
{/* Footer */}
<footer className="p-gutter border-t border-outline-variant bg-surface-container-lowest mt-auto">
<div className="flex flex-col md:flex-row justify-between items-center gap-4">
<p className="text-body-sm text-on-surface-variant">© 2023 InterviewAI Enterprise. All rights reserved.</p>
<div className="flex gap-6">
<a className="text-body-sm text-on-surface-variant hover:text-primary" href="#">System Status: <span className="text-primary font-bold">Operational</span></a>
<a className="text-body-sm text-on-surface-variant hover:text-primary" href="#">API Documentation</a>
<a className="text-body-sm text-on-surface-variant hover:text-primary" href="#">Privacy Policy</a>
</div>
</div>
</footer>

{/* Contextual Micro-interaction Script */}

{/* Create/Edit Question Modal */}
<Modal
  isOpen={isModalOpen}
  onClose={handleCloseModal}
  title={editingQuestion ? "Edit Question" : "Create New Question"}
  footer={
    <>
      <Button variant="ghost" onClick={handleCloseModal}>Cancel</Button>
      <Button onClick={handleSubmit} isLoading={createMutation.isPending || updateMutation.isPending}>
        {editingQuestion ? "Save Changes" : "Create Question"}
      </Button>
    </>
  }
>
  <form id="question-form" className="space-y-4" onSubmit={handleSubmit}>
    <div className="flex flex-col gap-1.5">
      <label className="text-label-md font-bold text-on-surface-variant">Question Title</label>
      <input
        required
        type="text"
        className="w-full px-4 py-2 bg-surface-container-lowest border border-outline-variant rounded focus:border-primary focus:ring-1 focus:ring-primary outline-none"
        value={formData.title}
        onChange={(e) => setFormData({ ...formData, title: e.target.value })}
      />
    </div>
    <div className="flex flex-col gap-1.5">
      <label className="text-label-md font-bold text-on-surface-variant">Category</label>
      <select
        className="w-full px-4 py-2 bg-surface-container-lowest border border-outline-variant rounded focus:border-primary focus:ring-1 focus:ring-primary outline-none"
        value={formData.category}
        onChange={(e) => setFormData({ ...formData, category: e.target.value })}
      >
        <option value="Technical">Technical</option>
        <option value="Behavioral">Behavioral</option>
      </select>
    </div>
    <div className="grid grid-cols-2 gap-4">
      <div className="flex flex-col gap-1.5">
        <label className="text-label-md font-bold text-on-surface-variant">Technology</label>
        <select
          className="w-full px-4 py-2 bg-surface-container-lowest border border-outline-variant rounded focus:border-primary focus:ring-1 focus:ring-primary outline-none"
          value={formData.technology}
          onChange={(e) => setFormData({ ...formData, technology: e.target.value })}
        >
          <option value="">Select Technology</option>
          {technologies.map((t: any) => (
            <option key={t.id} value={t.name}>{t.name}</option>
          ))}
        </select>
      </div>
      <div className="flex flex-col gap-1.5">
        <label className="text-label-md font-bold text-on-surface-variant">Difficulty</label>
        <select
          className="w-full px-4 py-2 bg-surface-container-lowest border border-outline-variant rounded focus:border-primary focus:ring-1 focus:ring-primary outline-none"
          value={formData.difficulty}
          onChange={(e) => setFormData({ ...formData, difficulty: e.target.value })}
        >
          <option value="Entry">Entry</option>
          <option value="Mid">Mid</option>
          <option value="Senior">Senior</option>
        </select>
      </div>
    </div>
  </form>
</Modal>

    </>
  );
}
