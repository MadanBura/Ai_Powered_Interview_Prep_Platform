import { useState, useRef } from 'react';
import { useTriggerIngestion } from '../features/mod07_bulk_upload/hooks/mutations/useTriggerIngestion';
import PageHeader from '../components/shared/PageHeader';
import Card from '../components/ui/Card';

export default function Bulk_Upload_Center() {
  const fileInputRef = useRef<HTMLInputElement>(null);
  const uploadMutation = useTriggerIngestion();
  const [jobs, setJobs] = useState<any[]>([]);

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files && e.target.files.length > 0) {
      const file = e.target.files[0];
      setJobs([...jobs, { name: file.name, progress: 0, status: 'Processing' }]);
      uploadMutation.mutate({ file }, {
        onSuccess: () => {
          setJobs(prev => prev.map(j => j.name === file.name ? { ...j, progress: 100, status: 'Complete' } : j));
        }
      });
    }
  };

  return (
    <>
      



{/* Content Area */}
<div className="flex-1 mt-header-height p-gutter overflow-y-auto max-w-container-max mx-auto w-full">
{/* Breadcrumbs & Header */}
<PageHeader 
  title="Bulk Upload Center"
  description="Import large-scale question banks and datasets directly into the AI Interview engine. Supports high-density file parsing and automated validation."
  breadcrumbs={[
    { label: "Content Management" },
    { label: "Bulk Upload Center" }
  ]}
/>
<div className="grid grid-cols-12 gap-unit-lg">
{/* Main Upload Column */}
<div className="col-span-12 lg:col-span-8 space-y-unit-lg">
{/* Upload Zone */}
<Card className="p-unit-xl relative overflow-hidden group">
<div className="absolute inset-0 bg-primary/5 opacity-0 group-hover:opacity-100 transition-opacity pointer-events-none"></div>
<div className="upload-dashed border-2 border-dashed border-outline-variant rounded-xl h-64 flex flex-col items-center justify-center text-center cursor-pointer hover:border-primary transition-colors">
<div className="w-16 h-16 bg-primary-container/20 text-primary rounded-full flex items-center justify-center mb-4 transition-transform group-hover:scale-110">
<span className="material-symbols-outlined text-[40px]" data-icon="upload_file">upload_file</span>
</div>
<h3 className="text-headline-sm font-headline-sm text-on-surface">Drag &amp; drop files here</h3>
<p className="text-body-md text-on-surface-variant mt-2">or <span className="text-primary font-bold hover:underline" onClick={() => fileInputRef.current?.click()}>browse files</span> from your computer</p>
<input type="file" className="hidden" ref={fileInputRef} onChange={handleFileChange} />
<div className="flex gap-4 mt-6">
<div className="flex items-center gap-2 px-3 py-1.5 bg-surface-container rounded-lg border border-outline-variant">
<span className="material-symbols-outlined text-primary text-[18px]" data-icon="description">description</span>
<span className="text-label-md">CSV</span>
</div>
<div className="flex items-center gap-2 px-3 py-1.5 bg-surface-container rounded-lg border border-outline-variant">
<span className="material-symbols-outlined text-primary text-[18px]" data-icon="data_object">data_object</span>
<span className="text-label-md">JSON</span>
</div>
<div className="flex items-center gap-2 px-3 py-1.5 bg-surface-container rounded-lg border border-outline-variant">
<span className="material-symbols-outlined text-primary text-[18px]" data-icon="picture_as_pdf">picture_as_pdf</span>
<span className="text-label-md">PDF</span>
</div>
</div>
</div>
</Card>
{/* Active Uploads */}
<Card>
<div className="px-gutter py-4 border-b border-outline-variant bg-surface-container-low flex justify-between items-center">
<h3 className="text-label-md font-bold uppercase tracking-wider text-on-surface">Active Upload Jobs</h3>
<span className="bg-primary-container text-on-primary-container px-2 py-0.5 rounded text-[10px] font-bold">3 PROCESSING</span>
</div>
<div className="divide-y divide-outline-variant">
{jobs.length === 0 ? (
  <div className="p-unit-xl text-center">
    <p className="text-body-md text-on-surface-variant">No active upload jobs.</p>
  </div>
) : (
  jobs.map((job, idx) => (
    <div key={idx} className="p-unit-md hover:bg-surface-container-low transition-colors group">
      <div className="flex items-start justify-between mb-3">
        <div className="flex items-center gap-4">
          <div className="w-10 h-10 bg-secondary-container/50 rounded flex items-center justify-center">
            <span className="material-symbols-outlined text-secondary" data-icon="table_chart">table_chart</span>
          </div>
          <div>
            <p className="text-body-md font-bold text-on-surface">{job.name}</p>
            <p className="text-body-sm text-on-surface-variant">{job.status}</p>
          </div>
        </div>
      </div>
      <div className="flex items-center gap-4">
        <div className="flex-1 bg-surface-container h-2 rounded-full overflow-hidden">
          <div className={`bg-primary h-full transition-all duration-500`} style={{ width: `${job.progress}%` }}></div>
        </div>
        <span className="text-label-md text-primary font-bold">{job.progress}%</span>
      </div>
    </div>
  ))
)}
</div>
</Card>
</div>
{/* Right Sidebar Column */}
<div className="col-span-12 lg:col-span-4 space-y-unit-lg">
{/* Download Templates */}
{/* Download Templates */}
<Card padding="lg">
<div className="flex items-center gap-2 mb-4">
<span className="material-symbols-outlined text-primary" data-icon="download">download</span>
<h3 className="text-headline-sm font-headline-sm">Download Templates</h3>
</div>
<p className="text-body-md text-on-surface-variant mb-6">Ensure your data is formatted correctly before uploading to avoid parsing errors.</p>
<div className="space-y-3">
<button className="w-full flex items-center justify-between p-4 bg-surface rounded-xl border border-outline-variant hover:border-primary hover:bg-primary/5 transition-all group">
<div className="flex items-center gap-3">
<span className="material-symbols-outlined text-on-surface-variant group-hover:text-primary" data-icon="table_rows">table_rows</span>
<div className="text-left">
<p className="text-label-md font-bold">CSV Question Template</p>
<p className="text-body-sm text-on-surface-variant">Recommended for large sets</p>
</div>
</div>
<span className="material-symbols-outlined text-primary opacity-0 group-hover:opacity-100" data-icon="file_download">file_download</span>
</button>
<button className="w-full flex items-center justify-between p-4 bg-surface rounded-xl border border-outline-variant hover:border-primary hover:bg-primary/5 transition-all group">
<div className="flex items-center gap-3">
<span className="material-symbols-outlined text-on-surface-variant group-hover:text-primary" data-icon="code">code</span>
<div className="text-left">
<p className="text-label-md font-bold">JSON Data Schema</p>
<p className="text-body-sm text-on-surface-variant">For developers and complex logic</p>
</div>
</div>
<span className="material-symbols-outlined text-primary opacity-0 group-hover:opacity-100" data-icon="file_download">file_download</span>
</button>
</div>
</Card>
{/* Statistics / Info */}
<div className="bg-inverse-surface text-white rounded-xl p-gutter relative overflow-hidden">
<div className="absolute top-0 right-0 p-4 opacity-10">
<span className="material-symbols-outlined text-[80px]" data-icon="cloud_upload">cloud_upload</span>
</div>
<h4 className="text-label-md font-bold uppercase tracking-widest text-primary-fixed-dim mb-4">Capacity Status</h4>
<div className="space-y-4 relative z-10">
<div>
<div className="flex justify-between text-body-sm mb-1">
<span>Cloud Storage Used</span>
<span>8.2 GB / 20 GB</span>
</div>
<div className="bg-white/10 h-1.5 rounded-full overflow-hidden">
<div className="bg-primary-fixed-dim h-full"></div>
</div>
</div>
<div className="grid grid-cols-2 gap-4 mt-6">
<div className="bg-white/5 p-3 rounded-lg border border-white/10">
<p className="text-[20px] font-bold">14k+</p>
<p className="text-[10px] uppercase text-white/60">Total Qs Imported</p>
</div>
<div className="bg-white/5 p-3 rounded-lg border border-white/10">
<p className="text-[20px] font-bold">99.2%</p>
<p className="text-[10px] uppercase text-white/60">Parsing Accuracy</p>
</div>
</div>
</div>
</div>
{/* Guidelines Card */}
<div className="bg-surface-container rounded-xl p-gutter border border-outline-variant">
<h3 className="text-label-md font-bold text-on-surface flex items-center gap-2 mb-3">
<span className="material-symbols-outlined text-[18px]" data-icon="lightbulb">lightbulb</span>
                            Upload Guidelines
                        </h3>
<ul className="space-y-2 text-body-sm text-on-surface-variant">
<li className="flex gap-2">
<span className="text-primary font-bold">•</span>
<span>Max file size per upload: 50MB</span>
</li>
<li className="flex gap-2">
<span className="text-primary font-bold">•</span>
<span>Images must be embedded as Base64 in JSON or referenced via URL</span>
</li>
<li className="flex gap-2">
<span className="text-primary font-bold">•</span>
<span>Duplicate questions will be automatically flagged</span>
</li>
</ul>
</div>
</div>
</div>
</div>

{/* Contextual FAB */}
<button className="fixed bottom-gutter right-gutter bg-primary text-white flex items-center gap-3 px-6 py-4 rounded-full shadow-lg hover:scale-105 active:scale-95 transition-all z-50">
<span className="material-symbols-outlined" data-icon="bolt">bolt</span>
<span className="text-label-md font-bold">AI Auto-Format</span>
</button>


    </>
  );
}
