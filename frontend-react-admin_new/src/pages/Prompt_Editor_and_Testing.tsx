import { useState } from 'react';
import Button from '../components/ui/Button';
import Card from '../components/ui/Card';

export default function Prompt_Editor_and_Testing() {
  const [testResult, setTestResult] = useState(null); // Empty state for test result

  return (
    <>
      



{/* Editor Controls Bar */}
<div className="bg-surface-container-lowest border-b border-outline-variant px-unit-lg py-3 flex justify-between items-center shrink-0">
<div className="flex items-center gap-4">
<h1 className="font-headline-sm text-headline-sm text-on-surface">System Prompt Editor</h1>
<div className="flex items-center gap-2 px-3 py-1 bg-surface-variant rounded-full border border-outline-variant">
<span className="w-2 h-2 rounded-full bg-primary"></span>
<span className="font-label-md text-label-md text-on-surface-variant">v2.4 Production</span>
</div>
<div className="flex items-center gap-1 ml-4">
<span className="font-label-md text-label-md text-on-surface-variant">Variables:</span>
<span className="px-2 py-0.5 bg-tertiary-fixed text-on-tertiary-fixed-variant rounded text-xs font-code">&#123;&#123;candidate_name&#125;&#125;</span>
<span className="px-2 py-0.5 bg-tertiary-fixed text-on-tertiary-fixed-variant rounded text-xs font-code">&#123;&#123;role&#125;&#125;</span>
<button className="text-primary hover:bg-primary/10 rounded p-1 transition-all">
<span className="material-symbols-outlined text-[18px]" data-icon="add_circle">add_circle</span>
</button>
</div>
</div>
<div className="flex items-center gap-3">
<div className="flex items-center mr-4 bg-surface-container-high rounded-lg p-1">
<button className="px-4 py-1.5 text-label-md font-bold text-on-surface bg-surface shadow-sm rounded-md transition-all">Editor</button>
<button className="px-4 py-1.5 text-label-md font-bold text-on-surface-variant hover:text-on-surface transition-all flex items-center gap-2">
<span className="material-symbols-outlined text-[16px]" data-icon="compare">compare</span>
                        Compare
                    </button>
</div>
<Button variant="outline">Save as Draft</Button>
<Button>Publish v2.5</Button>
</div>
</div>
{/* Split Pane Layout */}
<div className="flex-1 flex overflow-hidden">
{/* Left Pane: Prompt Editor (Dark Mode) */}
<section className="w-1/2 h-full flex flex-col border-r border-outline-variant bg-[#1E1E1E]">
<div className="flex items-center justify-between px-4 py-2 bg-[#252526] border-b border-[#333333]">
<div className="flex items-center gap-3">
<span className="font-label-md text-label-md text-slate-400 uppercase tracking-widest">system_prompt.md</span>
<div className="px-2 py-0.5 rounded bg-slate-700 text-[10px] text-slate-300 font-bold">MARKDOWN</div>
</div>
<div className="flex items-center gap-2 text-slate-500">
<span className="material-symbols-outlined text-[18px] cursor-pointer hover:text-slate-300" data-icon="content_copy">content_copy</span>
<span className="material-symbols-outlined text-[18px] cursor-pointer hover:text-slate-300" data-icon="history">history</span>
<span className="material-symbols-outlined text-[18px] cursor-pointer hover:text-slate-300" data-icon="fullscreen">fullscreen</span>
</div>
</div>
<div className="flex-1 relative font-code text-body-md overflow-hidden">
<div className="absolute left-0 top-0 bottom-0 w-12 bg-[#1e1e1e] border-r border-[#333333] flex flex-col items-center py-4 text-slate-600 select-none">
<span>1</span><span>2</span><span>3</span><span>4</span><span>5</span><span>6</span><span>7</span><span>8</span><span>9</span><span>10</span><span>11</span><span>12</span><span>13</span><span>14</span><span>15</span>
</div>
<textarea className="absolute inset-0 ml-12 bg-transparent text-[#D4D4D4] p-4 resize-none focus:ring-0 border-none w-full h-full leading-relaxed" placeholder="Write your system prompt here..." spellCheck="false"># Interview Evaluator Persona
You are PrepCoach AI, an elite technical recruiter for top-tier silicon valley firms. 

## Evaluation Context
Candidate Name: &#123;&#123;candidate_name&#125;&#125;
Target Role: &#123;&#123;role&#125;&#125;

## Scoring Criteria
1. Accuracy (0-40): How technically correct is the response?
2. Articulation (0-30): Clarity and structure.
3. Culture Fit (0-30): Enthusiasm and collaborative tone.

## Output Format
Return a JSON object with:
- "score": Total sum
- "feedback": Constructive paragraph
- "follow_up": Single relevant question</textarea>
</div>
<div className="h-8 bg-[#007acc] flex items-center px-4 justify-between text-white text-[11px] font-medium">
<div className="flex gap-4">
<span>Ln 15, Col 32</span>
<span>Spaces: 4</span>
<span>UTF-8</span>
</div>
<div className="flex items-center gap-1">
<span className="material-symbols-outlined text-[14px]" data-icon="check_circle">check_circle</span>
<span>Ready</span>
</div>
</div>
</section>
{/* Right Pane: Testing Console */}
<section className="w-1/2 h-full flex flex-col bg-surface-container-lowest overflow-y-auto">
<div className="p-unit-lg space-y-unit-lg">
{/* Input Section */}
<div className="space-y-4">
<div className="flex items-center justify-between">
<h2 className="font-headline-sm text-headline-sm flex items-center gap-2">
<span className="material-symbols-outlined text-primary" data-icon="play_circle">play_circle</span>
                                Testing Console
                            </h2>
<div className="flex gap-2">
<select className="bg-surface border border-outline-variant rounded-lg text-body-sm px-3 py-1 focus:ring-primary/20">
<option>Model: GPT-4o</option>
<option>Model: Claude 3.5 Sonnet</option>
<option>Model: Gemini 1.5 Pro</option>
</select>
<button className="p-1.5 hover:bg-surface-container-high rounded border border-outline-variant transition-all">
<span className="material-symbols-outlined text-[18px]" data-icon="settings_input_component">settings_input_component</span>
</button>
</div>
</div>
{/* Variable Mocking */}
<div className="grid grid-cols-2 gap-4">
<div className="space-y-1">
<label className="text-label-md text-on-surface-variant">candidate_name</label>
<input className="w-full border border-outline-variant rounded-lg p-2 text-body-md focus:border-primary focus:ring-2 focus:ring-primary/10" type="text" value="Jordan Smith"/>
</div>
<div className="space-y-1">
<label className="text-label-md text-on-surface-variant">role</label>
<input className="w-full border border-outline-variant rounded-lg p-2 text-body-md focus:border-primary focus:ring-2 focus:ring-primary/10" type="text" value="Senior Backend Engineer"/>
</div>
</div>
{/* Candidate Answer Mock */}
<div className="space-y-1">
<label className="text-label-md text-on-surface-variant">Candidate Response</label>
<textarea className="w-full border border-outline-variant rounded-lg p-3 text-body-md focus:border-primary focus:ring-2 focus:ring-primary/10" placeholder="Paste a candidate's answer here to test the prompt..." rows={4} defaultValue="For a distributed system handling high-concurrency writes, I'd typically implement an Event Sourcing pattern combined with a Kafka-based message bus. This ensures strong eventual consistency and provides a reliable audit log. I'd also use Redis for a fast read-cache to offload the primary Postgres instance..."></textarea>
</div>
<Button className="w-full" icon="bolt" size="lg">Run Test Evaluation</Button>
</div>
{/* Output Results */}
<div className="space-y-4">
<div className="flex items-center justify-between">
<span className="font-label-md text-label-md text-on-surface-variant uppercase tracking-widest">Generated AI Feedback</span>
<span className="text-body-sm text-on-surface-variant">Last run: 2 mins ago (Latency: 1.2s)</span>
</div>
{testResult ? (
  <Card className="p-6 space-y-6">
      {/* Existing KPI and Narrative content... */}
  </Card>
) : (
  <Card className="flex flex-col items-center justify-center p-12 text-center">
    <span className="material-symbols-outlined text-4xl mb-2 text-outline">science</span>
    <p className="text-body-md font-bold text-on-surface">No Test Run Yet</p>
    <p className="text-sm text-on-surface-variant">Click "Run Test Evaluation" to see generated AI feedback.</p>
  </Card>
)}
</div>
</div>
</section>
</div>

{/* Version Comparison Modal (Hidden by Default) */}
<div className="hidden fixed inset-0 bg-on-surface/50 backdrop-blur-sm z-[100] flex items-center justify-center p-12" id="compareModal">
<div className="bg-surface w-full max-w-6xl h-5/6 rounded-2xl shadow-2xl flex flex-col overflow-hidden">
<div className="p-6 border-b border-outline-variant flex justify-between items-center">
<h3 className="font-headline-md text-headline-md">Compare Prompt Versions</h3>
<button className="p-2 hover:bg-surface-container-high rounded-full transition-all">
<span className="material-symbols-outlined" data-icon="close">close</span>
</button>
</div>
<div className="flex-1 flex overflow-hidden">
<div className="w-1/2 p-6 overflow-y-auto border-r border-outline-variant">
<div className="mb-4 flex items-center gap-2">
<span className="px-2 py-1 bg-surface-container-highest text-label-md rounded">Current Draft</span>
<span className="text-body-sm text-on-surface-variant">Last edited: Today, 10:45 AM</span>
</div>
<div className="font-code text-body-sm bg-surface-container-low p-4 rounded border border-outline-variant">
<span className="bg-primary/20 text-on-primary-container px-1">Added: ## Evaluation Context</span><br/>
<span className="bg-primary/20 text-on-primary-container px-1">Added: Candidate Name: &#123;&#123;candidate_name&#125;&#125;</span><br/>
                        ... [existing content] ...
                    </div>
</div>
<div className="w-1/2 p-6 overflow-y-auto">
<div className="mb-4 flex items-center gap-2">
<span className="px-2 py-1 bg-primary/10 text-primary text-label-md rounded">Production (v2.4)</span>
<span className="text-body-sm text-on-surface-variant">Published: Oct 24, 2023</span>
</div>
<div className="font-code text-body-sm bg-surface-container-low p-4 rounded border border-outline-variant">
<span className="bg-error/20 text-on-error-container px-1 line-through">Old Title: Recruiter Bot</span><br/>
                        ... [existing content] ...
                    </div>
</div>
</div>
<div className="p-6 bg-surface-container border-t border-outline-variant flex justify-end gap-3">
<button className="px-6 py-2 border border-outline rounded-lg font-bold text-label-md">Close Comparison</button>
<button className="px-6 py-2 bg-primary text-on-primary rounded-lg font-bold text-label-md shadow-md">Apply Merge Changes</button>
</div>
</div>
</div>


    </>
  );
}
