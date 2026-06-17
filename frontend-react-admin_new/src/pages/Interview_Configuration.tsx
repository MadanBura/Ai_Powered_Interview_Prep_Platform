import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import toast from 'react-hot-toast';
import PageHeader from '../components/shared/PageHeader';
import Button from '../components/ui/Button';
import Card from '../components/ui/Card';
import Modal from '../components/ui/Modal';

import { useGetDepartments, useGetExperienceLevels } from '../features/mod09_interview_configuration/hooks/queries/useGetOptions';

export default function Interview_Configuration() {
  const navigate = useNavigate();
  const { data: deptData, isLoading: isDeptLoading } = useGetDepartments();
  const { data: expData, isLoading: isExpLoading } = useGetExperienceLevels();

  const departments = deptData?.data || [];
  const experiences = expData?.data || [];
  const [templateName, setTemplateName] = useState('Senior Backend Architect - Node.js');
  const [department, setDepartment] = useState('Engineering');
  const [experience, setExperience] = useState('L6 - Architect');
  
  // Tech Stack State
  const [techStack, setTechStack] = useState<string[]>(['Node.js', 'TypeScript', 'PostgreSQL', 'Redis']);
  const [techInput, setTechInput] = useState('');
  
  // Parameters State
  const [questionCount, setQuestionCount] = useState(12);
  const [duration, setDuration] = useState(45);
  const [aiRigor, setAiRigor] = useState('Standard');
  
  // Persona State
  const [persona, setPersona] = useState('Empathetic Mentor');

  const [isDeploying, setIsDeploying] = useState(false);
  const [isTestModalOpen, setIsTestModalOpen] = useState(false);

  const handleDeploy = () => {
    setIsDeploying(true);
    const tId = toast.loading('Deploying interview template...');
    setTimeout(() => {
      setIsDeploying(false);
      toast.success('Template successfully deployed!', { id: tId });
      navigate('/interview-templates');
    }, 1000);
  };

  const handleDiscard = () => {
    setTemplateName('');
    setDepartment('Engineering');
    setExperience('L3 - Senior');
    setTechStack([]);
    setQuestionCount(12);
    setDuration(45);
    setAiRigor('Standard');
    setPersona('Empathetic Mentor');
    toast('Draft discarded.', { icon: '🗑️' });
  };

  const addTech = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === 'Enter' && techInput.trim()) {
      e.preventDefault();
      if (!techStack.includes(techInput.trim())) {
        setTechStack([...techStack, techInput.trim()]);
      }
      setTechInput('');
    }
  };

  const removeTech = (tech: string) => {
    setTechStack(techStack.filter(t => t !== tech));
  };

  return (
    <>
      <div className="p-8 max-w-container-max mx-auto w-full flex-1 flex flex-col gap-6">



{/* Header Section */}
<PageHeader 
  title="Interview Configuration"
  description="Design an automated interview flow with specific behavioral parameters and technical scopes."
  breadcrumbs={[
    { label: "Interview Management" },
    { label: "New Configuration" }
  ]}
  actions={
    <>
      <Button variant="outline" onClick={handleDiscard}>Discard Draft</Button>
      <Button 
        onClick={handleDeploy}
        disabled={isDeploying}
      >
        {isDeploying ? 'Deploying...' : 'Deploy Template'}
      </Button>
    </>
  }
/>
<div className="grid grid-cols-12 gap-gutter">
{/* Form Section */}
<div className="col-span-8 space-y-gutter">
{/* Step 1: Identity */}
<Card padding="lg">
<div className="flex items-center gap-3 mb-unit-lg">
<div className="w-8 h-8 rounded-full bg-primary/10 flex items-center justify-center text-primary">
<span className="text-label-md">01</span>
</div>
<h4 className="text-headline-sm font-headline-sm text-on-surface">Template Identity</h4>
</div>
<div className="grid grid-cols-2 gap-unit-lg">
<div className="col-span-2">
<label className="block text-label-md text-on-surface-variant mb-2">TEMPLATE NAME</label>
<input 
  className="w-full px-4 py-3 border border-outline-variant rounded focus:border-primary focus:ring-2 focus:ring-primary/10 text-body-md" 
  type="text" 
  value={templateName}
  onChange={e => setTemplateName(e.target.value)}
/>
</div>
<div>
<label className="block text-label-md text-on-surface-variant mb-2">DEPARTMENT</label>
<select 
  className="w-full px-4 py-3 border border-outline-variant rounded focus:border-primary focus:ring-2 focus:ring-primary/10 text-body-md"
  value={department}
  onChange={e => setDepartment(e.target.value)}
>
  <option value="">Select Department...</option>
  {departments.map((d: any) => (
    <option key={d.id} value={d.name}>{d.name}</option>
  ))}
</select>
</div>
<div>
<label className="block text-label-md text-on-surface-variant mb-2">EXPERIENCE LEVEL</label>
<select 
  className="w-full px-4 py-3 border border-outline-variant rounded focus:border-primary focus:ring-2 focus:ring-primary/10 text-body-md"
  value={experience}
  onChange={e => setExperience(e.target.value)}
>
  <option value="">Select Experience...</option>
  {experiences.map((exp: any) => (
    <option key={exp.id} value={exp.name}>{exp.name}</option>
  ))}
</select>
</div>
</div>
</Card>
{/* Step 2: Technical Scope */}
<Card padding="lg">
<div className="flex items-center gap-3 mb-unit-lg">
<div className="w-8 h-8 rounded-full bg-primary/10 flex items-center justify-center text-primary">
<span className="text-label-md">02</span>
</div>
<h4 className="text-headline-sm font-headline-sm text-on-surface">Technical Stack &amp; Parameters</h4>
</div>
<div className="space-y-unit-lg">
<div>
<label className="block text-label-md text-on-surface-variant mb-2">TECHNOLOGY STACK</label>
<div className="flex flex-wrap gap-2 p-2 border border-outline-variant rounded bg-surface-container-low min-h-[48px]">
{techStack.map(tech => (
  <span key={tech} className="px-3 py-1 bg-primary-container text-on-primary-container text-body-sm rounded-full flex items-center gap-1">
    {tech} <span className="material-symbols-outlined text-[14px] cursor-pointer hover:text-error" onClick={() => removeTech(tech)}>close</span>
  </span>
))}
<input 
  className="flex-1 bg-transparent border-none focus:ring-0 text-body-sm min-w-[120px]" 
  placeholder="Add tech (press Enter)..." 
  type="text"
  value={techInput}
  onChange={(e) => setTechInput(e.target.value)}
  onKeyDown={addTech}
/>
</div>
</div>
<div className="grid grid-cols-3 gap-unit-lg">
<div>
<label className="block text-label-md text-on-surface-variant mb-2">QUESTION COUNT</label>
<input 
  className="w-full px-4 py-3 border border-outline-variant rounded focus:border-primary focus:ring-2 focus:ring-primary/10 text-body-md" 
  type="number" 
  value={questionCount}
  onChange={(e) => setQuestionCount(Number(e.target.value))}
/>
</div>
<div>
<label className="block text-label-md text-on-surface-variant mb-2">DURATION (MIN)</label>
<input 
  className="w-full px-4 py-3 border border-outline-variant rounded focus:border-primary focus:ring-2 focus:ring-primary/10 text-body-md" 
  type="number" 
  value={duration}
  onChange={(e) => setDuration(Number(e.target.value))}
/>
</div>
<div>
<label className="block text-label-md text-on-surface-variant mb-2">AI RIGOR LEVEL</label>
<div className="flex h-[48px] border border-outline-variant rounded overflow-hidden">
{['Soft', 'Standard', 'Elite'].map(level => (
  <button 
    key={level}
    type="button"
    onClick={() => setAiRigor(level)}
    className={`flex-1 text-body-sm border-r last:border-r-0 border-outline-variant ${aiRigor === level ? 'bg-primary text-white font-semibold' : 'bg-surface-container-high text-on-surface-variant hover:bg-surface-container'}`}
  >
    {level}
  </button>
))}
</div>
</div>
</div>
</div>
</Card>
{/* Step 3: AI Behavioral Persona */}
<Card padding="lg">
<div className="flex items-center gap-3 mb-unit-lg">
<div className="w-8 h-8 rounded-full bg-primary/10 flex items-center justify-center text-primary">
<span className="text-label-md">03</span>
</div>
<h4 className="text-headline-sm font-headline-sm text-on-surface">AI Behavioral Persona</h4>
</div>
<div className="grid grid-cols-3 gap-unit-md">
{[
  { id: 'Empathetic Mentor', icon: 'psychology', desc: 'Focuses on potential and growth mindset. Encouraging tone.' },
  { id: 'Strict Evaluator', icon: 'precision_manufacturing', desc: 'Deep technical drilling. High precision requirements.' },
  { id: 'System Thinker', icon: 'account_tree', desc: 'Prioritizes architectural vision and trade-off analysis.' },
].map(p => (
  <label key={p.id} className="relative cursor-pointer group">
    <input 
      checked={persona === p.id} 
      onChange={() => setPersona(p.id)}
      className="peer sr-only" 
      name="persona" 
      type="radio"
    />
    <div className="p-4 border-2 border-outline-variant rounded-lg peer-checked:border-primary peer-checked:bg-primary/5 transition-all h-full">
      <span className="material-symbols-outlined text-primary mb-2" data-icon={p.icon}>{p.icon}</span>
      <p className="text-label-md text-on-surface mb-1">{p.id}</p>
      <p className="text-body-sm text-on-surface-variant">{p.desc}</p>
    </div>
  </label>
))}
</div>
</Card>
</div>
{/* Preview Panel */}
<div className="col-span-4 sticky top-[88px] h-fit">
<div className="bg-inverse-surface text-white p-unit-lg rounded-lg shadow-xl relative overflow-hidden">
{/* Subtle background decoration */}
<div className="absolute top-0 right-0 w-32 h-32 bg-primary opacity-20 blur-3xl -mr-16 -mt-16 rounded-full"></div>
<div className="absolute bottom-0 left-0 w-24 h-24 bg-primary opacity-10 blur-2xl -ml-12 -mb-12 rounded-full"></div>
<div className="relative z-10">
<div className="flex items-center justify-between mb-unit-lg border-b border-white/10 pb-4">
<h4 className="text-headline-sm font-headline-sm">Live Preview</h4>
<span className="px-2 py-1 bg-primary text-on-primary-container text-[10px] font-bold rounded uppercase tracking-widest">Active Draft</span>
</div>
<div className="space-y-6">
<div>
<p className="text-[10px] text-white/50 font-bold uppercase tracking-widest mb-1">INTERVIEW TITLE</p>
<p className="text-body-lg font-semibold">{templateName}</p>
</div>
<div className="grid grid-cols-2 gap-4">
<div>
<p className="text-[10px] text-white/50 font-bold uppercase tracking-widest mb-1">EXPERIENCE</p>
<p className="text-body-md">{experience}</p>
</div>
<div>
<p className="text-[10px] text-white/50 font-bold uppercase tracking-widest mb-1">DURATION</p>
<p className="text-body-md">{duration} Minutes</p>
</div>
</div>
<div>
<p className="text-[10px] text-white/50 font-bold uppercase tracking-widest mb-1">CORE TECH FOCUS</p>
<div className="flex flex-wrap gap-2 mt-2">
{techStack.length === 0 ? <span className="text-white/50 italic text-body-sm">None</span> : techStack.map(t => (
  <span key={t} className="px-2 py-0.5 border border-white/20 rounded text-body-sm">{t}</span>
))}
</div>
</div>
<div className="pt-4 border-t border-white/10">
<p className="text-[10px] text-white/50 font-bold uppercase tracking-widest mb-3">AI PERSONA EMULATION</p>
<div className="flex items-start gap-3 bg-white/5 p-3 rounded">
<span className="material-symbols-outlined text-primary" data-icon="psychology">psychology</span>
<div>
<p className="text-label-md">{persona}</p>
<p className="text-body-sm text-white/60 leading-tight">AI will use a targeted persona profile to simulate this behavior.</p>
</div>
</div>
</div>
<div className="pt-4">
<div className="bg-primary/20 border border-primary/30 p-3 rounded-lg">
<div className="flex items-center gap-2 mb-2">
<span className="material-symbols-outlined text-[18px] text-primary">auto_awesome</span>
<p className="text-label-md text-primary-fixed">AI Strategy Generated</p>
</div>
<p className="text-body-sm text-white/70 italic">"Focusing on high-concurrency Node.js patterns and distributed system consistency models."</p>
</div>
</div>
</div>
<button className="w-full mt-unit-xl py-3 border border-primary text-primary hover:bg-primary hover:text-white transition-all font-label-md rounded flex items-center justify-center gap-2" onClick={() => setIsTestModalOpen(true)}>
<span className="material-symbols-outlined text-[20px]" data-icon="play_circle">play_circle</span>
                            Test AI Interaction
                        </button>
</div>
</div>
{/* Helper Tips */}
<div className="mt-gutter p-4 bg-surface-container-low border border-outline-variant rounded-lg">
<div className="flex items-start gap-3">
<span className="material-symbols-outlined text-on-surface-variant" data-icon="lightbulb">lightbulb</span>
<div>
<p className="text-label-md text-on-surface mb-1">Configuration Tip</p>
<p className="text-body-sm text-on-surface-variant">Templates for architect roles perform 30% better when 'System Thinker' persona is selected.</p>
</div>
</div>
</div>
</div>
</div>

<Modal isOpen={isTestModalOpen} onClose={() => setIsTestModalOpen(false)} title="Test AI Interaction" footer={<Button onClick={() => setIsTestModalOpen(false)}>Close Simulation</Button>}>
  <div className="flex flex-col h-64 bg-surface-container-low rounded-lg p-4 overflow-y-auto space-y-4">
    <div className="flex gap-3">
      <div className="w-8 h-8 rounded-full bg-primary/20 flex items-center justify-center text-primary"><span className="material-symbols-outlined text-[16px]">psychology</span></div>
      <div className="flex-1 bg-surface border border-outline-variant rounded-lg p-3 text-body-sm text-on-surface">
        Hello! I see you are applying for the {templateName} role in {department}. Let's start with a technical question. Can you explain your approach to distributed consistency?
      </div>
    </div>
    <div className="flex gap-3 flex-row-reverse">
      <div className="w-8 h-8 rounded-full bg-secondary/20 flex items-center justify-center text-secondary"><span className="material-symbols-outlined text-[16px]">person</span></div>
      <div className="flex-1 bg-primary text-white rounded-lg p-3 text-body-sm">
        <span className="opacity-50 italic">Candidate is typing...</span>
      </div>
    </div>
  </div>
</Modal>
      </div>
    </>
  );
}
