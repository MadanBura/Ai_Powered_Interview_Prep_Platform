import { useState } from 'react';
import toast from 'react-hot-toast';
import { useListDepartments } from '../features/mod03_department_management/hooks/queries/useListDepartments';
import { useCreateDepartment } from '../features/mod03_department_management/hooks/mutations/useCreateDepartment';
import { useUpdateDepartment } from '../features/mod03_department_management/hooks/mutations/useUpdateDepartment';
import { useDeleteDepartment } from '../features/mod03_department_management/hooks/mutations/useDeleteDepartment';
import { useListAuditLogs } from '../features/mod22_audit_logs/hooks/queries/useListAuditLogs';
import PageHeader from '../components/shared/PageHeader';
import StatCard from '../components/shared/StatCard';
import Button from '../components/ui/Button';
import Card from '../components/ui/Card';
import Badge from '../components/ui/Badge';
import DataTable from '../components/shared/DataTable';
import Modal from '../components/ui/Modal';
import { Department, DepartmentRequestDto } from '../features/mod03_department_management/types/department.types';

export default function Department_Management() {
  const { data: departmentsResponse, isLoading } = useListDepartments();
  const departments = departmentsResponse?.data?.departments || [];
  
  const { data: auditLogsData } = useListAuditLogs();
  const auditLogs = auditLogsData?.data?.logs || [];
  
  const createDeptMutation = useCreateDepartment();
  const updateDeptMutation = useUpdateDepartment();
  const deleteDeptMutation = useDeleteDepartment();
  
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isFilterOpen, setIsFilterOpen] = useState(false);
  const [isAuditLogsOpen, setIsAuditLogsOpen] = useState(false);
  const [isAlertsOpen, setIsAlertsOpen] = useState(false);
  
  const [editingDept, setEditingDept] = useState<Department | null>(null);
  const [formData, setFormData] = useState<DepartmentRequestDto>({
    name: '',
    headOfDept: '',
  });

  const handleOpenModal = (dept?: Department) => {
    if (dept) {
      setEditingDept(dept);
      setFormData({ name: dept.name, headOfDept: dept.headOfDept });
    } else {
      setEditingDept(null);
      setFormData({ name: '', headOfDept: '' });
    }
    setIsModalOpen(true);
  };

  const [searchTerm, setSearchTerm] = useState('');
  
  const filteredDepartments = departments.filter((d: any) => 
    d.name?.toLowerCase().includes(searchTerm.toLowerCase()) || 
    d.headOfDept?.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const handleExportCSV = () => {
    if (departments.length === 0) {
      toast.error('No departments to export.');
      return;
    }
    
    const headers = ['ID', 'Name', 'Head of Dept', 'Status', 'Active Technologies'];
    const rows = departments.map((d: any) => [
      d.id,
      `"${d.name || ''}"`,
      `"${d.headOfDept || ''}"`,
      d.status || 'ACTIVE',
      d.activeTechnologies || 0
    ]);
    
    const csvContent = [headers.join(','), ...rows.map(r => r.join(','))].join('\n');
    const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' });
    const url = URL.createObjectURL(blob);
    const link = document.createElement('a');
    link.setAttribute('href', url);
    link.setAttribute('download', 'departments_export.csv');
    link.style.visibility = 'hidden';
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
    toast.success('Successfully exported departments!');
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
    setEditingDept(null);
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (editingDept) {
      updateDeptMutation.mutate({ id: editingDept.id, data: formData }, {
        onSuccess: () => handleCloseModal()
      });
    } else {
      createDeptMutation.mutate(formData, {
        onSuccess: () => handleCloseModal()
      });
    }
  };

  const handleDelete = (id: string) => {
    if (confirm('Are you sure you want to delete this department?')) {
      deleteDeptMutation.mutate(id);
    }
  };
  return (
    <>
      



{/* Content Area */}
<div className="p-unit-lg max-w-container-max w-full mx-auto flex flex-col gap-unit-lg">
{/* Page Header */}
<PageHeader 
  title="Department Management"
  description="Configure and monitor enterprise operational units and their AI technology stacks."
  actions={
    <Button icon="add" onClick={() => handleOpenModal()}>Add Department</Button>
  }
/>
{/* Bento Stats Summary (Professional High-End UI) */}
<div className="grid grid-cols-1 md:grid-cols-4 gap-unit-md">
<StatCard 
  title="TOTAL DEPARTMENTS"
  value={departments.length}
  trend={{ value: "New", positive: true }}
/>
<StatCard 
  title="ACTIVE TECHNOLOGIES"
  value="148"
  footer={<span className="text-on-surface-variant text-[10px]">Across all units</span>}
/>
<StatCard 
  title="RESOURCE UTILIZATION"
  value="84%"
  trend={{ value: "" }}
  footer={
    <div className="h-1 bg-surface-container rounded-full overflow-hidden mt-2">
      <div className="h-full bg-primary w-[84%]"></div>
    </div>
  }
/>
<StatCard 
  title="SYSTEM HEALTH"
  value={<div className="flex items-center gap-2"><div className="w-2 h-2 rounded-full bg-primary animate-pulse"></div><span className="text-headline-sm font-bold text-primary">Optimal</span></div>}
/>
</div>
{/* List/Table Section */}
<DataTable 
  title="Departments"
  actions={
    <div className="flex items-center gap-unit-md w-full md:w-auto">
      <div className="relative w-full md:w-80">
      <span className="material-symbols-outlined absolute left-3 top-1/2 -translate-y-1/2 text-on-surface-variant text-[20px]" data-icon="filter_list">filter_list</span>
      <input className="w-full pl-10 pr-4 py-2 border border-outline-variant rounded-lg text-body-md focus:ring-1 focus:ring-primary focus:border-primary" placeholder="Filter departments..." type="text" value={searchTerm} onChange={(e) => setSearchTerm(e.target.value)} />
      </div>
      <Button variant="outline" icon="tune" onClick={() => setIsFilterOpen(true)}>Advanced</Button>
    </div>
  }
  columns={[
    { key: 'name', header: 'Department Name', render: (row: any) => (
      <div className="flex items-center gap-3">
        <div className="w-10 h-10 rounded bg-primary-container flex items-center justify-center text-on-primary-container">
          <span className="material-symbols-outlined" data-icon="engineering">engineering</span>
        </div>
        <div>
          <p className="font-headline-sm text-[16px] text-on-surface">{row.name || 'Department ' + row.id}</p>
          <p className="text-body-sm text-on-surface-variant">Default Stack</p>
        </div>
      </div>
    ) },
    { key: 'head', header: 'Head of Dept', render: (row: any) => (
      <div className="flex items-center gap-2">
        <div className="w-6 h-6 rounded-full bg-secondary-container flex items-center justify-center text-[10px] font-bold">
          {row.headOfDept ? row.headOfDept.charAt(0).toUpperCase() : 'N'}
        </div>
        <span className="font-body-md text-on-surface">{row.headOfDept || 'Not Assigned'}</span>
      </div>
    ) },
    { key: 'techs', header: 'Total Active Technologies', render: (row: any) => <span className="font-headline-sm text-[16px]">{row.activeTechnologies || 0}</span> },
    { key: 'status', header: 'Status', render: (row: any) => <Badge variant={row.status === 'ACTIVE' ? 'success' : 'warning'}>{row.status || 'ACTIVE'}</Badge> },
    { key: 'actions', header: 'Actions', render: (row: any) => (
      <div className="flex justify-end gap-2">
        <Button variant="ghost" icon="edit" size="sm" onClick={() => handleOpenModal(row)} />
        <Button variant="ghost" icon="delete" size="sm" className="hover:text-error hover:bg-error/10" onClick={() => handleDelete(row.id)} />
      </div>
    ) }
  ]}
  data={filteredDepartments}
  isLoading={isLoading}
  emptyState={
    <div className="flex flex-col items-center gap-3 py-12">
      <div className="w-16 h-16 bg-surface-container rounded-full flex items-center justify-center">
        <span className="material-symbols-outlined text-outline text-[32px]">domain_disabled</span>
      </div>
      <p className="font-headline-sm text-on-surface">No Departments Found</p>
      <p className="text-body-sm text-on-surface-variant max-w-sm">There are currently no departments configured. Click "Add Department" to get started.</p>
      <Button variant="outline" onClick={() => handleOpenModal()}>Create First Department</Button>
    </div>
  }
/>
{/* Asymmetric Informational Section (Design Quality Boost) */}
<div className="flex flex-col lg:flex-row gap-unit-lg mt-unit-md">
<div className="flex-1 bg-inverse-surface text-inverse-on-surface p-unit-xl rounded-2xl relative overflow-hidden">
<div className="relative z-10 flex flex-col gap-4">
<span className="px-3 py-1 bg-primary text-on-primary text-[10px] font-black rounded w-fit tracking-widest uppercase">System Bulletin</span>
<h3 className="font-headline-md text-headline-md">Department Health Sync</h3>
<p className="font-body-md opacity-80 max-w-md">The AI Management module is currently indexing technology stacks for the Human Resources department. Expect updated utilization metrics in approximately 4 hours.</p>
<div className="mt-4 flex gap-4">
<button className="text-primary-fixed font-label-md flex items-center gap-2 hover:underline" onClick={() => setIsAuditLogsOpen(true)}>
                                View sync logs
                                <span className="material-symbols-outlined text-[16px]" data-icon="arrow_forward">arrow_forward</span>
</button>
</div>
</div>
{/* Decorative Element */}
<div className="absolute -right-12 -bottom-12 opacity-10">
<span className="material-symbols-outlined text-[200px]" data-icon="sync_alt">sync_alt</span>
</div>
</div>
<Card padding="lg" className="w-full lg:w-80 bg-surface-container flex flex-col gap-4">
<h4 className="font-label-md text-label-md text-on-surface uppercase tracking-wider">Quick Actions</h4>
<div className="flex flex-col gap-2">
<button className="flex items-center gap-3 p-3 bg-surface rounded-lg hover:border-primary border border-transparent transition-all group" onClick={handleExportCSV}>
<span className="material-symbols-outlined text-primary" data-icon="cloud_download">cloud_download</span>
<div className="text-left">
<p className="text-body-md font-bold text-on-surface">Export CSV</p>
<p className="text-[10px] text-on-surface-variant">Download list as spreadsheet</p>
</div>
</button>
<button className="flex items-center gap-3 p-3 bg-surface rounded-lg hover:border-primary border border-transparent transition-all group" onClick={() => setIsAuditLogsOpen(true)}>
<span className="material-symbols-outlined text-secondary" data-icon="history">history</span>
<div className="text-left">
<p className="text-body-md font-bold text-on-surface">Audit Logs</p>
<p className="text-[10px] text-on-surface-variant">View modification history</p>
</div>
</button>
<button className="flex items-center gap-3 p-3 bg-surface rounded-lg hover:border-primary border border-transparent transition-all group" onClick={() => setIsAlertsOpen(true)}>
<span className="material-symbols-outlined text-error" data-icon="report">report</span>
<div className="text-left">
<p className="text-body-md font-bold text-on-surface">Critical Alerts</p>
<p className="text-[10px] text-on-surface-variant">Check 2 security notices</p>
</div>
</button>
</div>
</Card>
</div>
</div>
{/* Footer Info */}
<footer className="mt-auto p-unit-lg border-t border-outline-variant">
<div className="max-w-container-max mx-auto flex justify-between items-center text-body-sm text-on-surface-variant">
<span>© 2024 PrepCoach AI Enterprise Platform.</span>
<div className="flex gap-4">
<a className="hover:text-primary transition-colors" href="#">Privacy Policy</a>
<a className="hover:text-primary transition-colors" href="#">Terms of Service</a>
</div>
</div>
</footer>


{/* Create/Edit Department Modal */}
<Modal
  isOpen={isModalOpen}
  onClose={handleCloseModal}
  title={editingDept ? "Edit Department" : "Create New Department"}
  footer={
    <>
      <Button variant="ghost" onClick={handleCloseModal}>Cancel</Button>
      <Button onClick={handleSubmit} isLoading={createDeptMutation.isPending || updateDeptMutation.isPending}>
        {editingDept ? "Save Changes" : "Create Department"}
      </Button>
    </>
  }
>
  <form id="dept-form" className="space-y-4" onSubmit={handleSubmit}>
    <div className="flex flex-col gap-1.5">
      <label className="text-label-md font-bold text-on-surface-variant">Department Name</label>
      <input
        required
        type="text"
        className="w-full px-4 py-2 bg-surface-container-lowest border border-outline-variant rounded focus:border-primary focus:ring-1 focus:ring-primary outline-none"
        value={formData.name}
        onChange={(e) => setFormData({ ...formData, name: e.target.value })}
      />
    </div>
    <div className="flex flex-col gap-1.5">
      <label className="text-label-md font-bold text-on-surface-variant">Head of Department (Name)</label>
      <input
        type="text"
        className="w-full px-4 py-2 bg-surface-container-lowest border border-outline-variant rounded focus:border-primary focus:ring-1 focus:ring-primary outline-none"
        value={formData.headOfDept}
        onChange={(e) => setFormData({ ...formData, headOfDept: e.target.value })}
      />
    </div>
  </form>
</Modal>

<Modal isOpen={isFilterOpen} onClose={() => setIsFilterOpen(false)} title="Advanced Filtering" footer={<Button onClick={() => setIsFilterOpen(false)}>Apply Filters</Button>}>
  <div className="space-y-4">
    <div className="flex flex-col gap-1.5"><label className="text-label-md font-bold">Technology Stack</label><select className="w-full px-4 py-2 bg-surface border border-outline-variant rounded"><option>All</option><option>React</option><option>Java</option></select></div>
    <div className="flex flex-col gap-1.5"><label className="text-label-md font-bold">Status</label><select className="w-full px-4 py-2 bg-surface border border-outline-variant rounded"><option>Active</option><option>Archived</option></select></div>
  </div>
</Modal>

<Modal isOpen={isAuditLogsOpen} onClose={() => setIsAuditLogsOpen(false)} title="Recent Audit Logs" footer={<Button onClick={() => setIsAuditLogsOpen(false)}>Close</Button>}>
  <div className="space-y-3 max-h-[300px] overflow-y-auto">
    {auditLogs.length > 0 ? auditLogs.map((log: any, idx: number) => (
      <div key={idx} className="p-3 border-l-2 border-primary bg-surface-container-low">
        <p className="text-body-sm font-bold">{log.action}</p>
        <p className="text-[11px] text-on-surface-variant">By {log.user} - Entity: {log.entityType}</p>
      </div>
    )) : (
      <p className="text-body-sm text-on-surface-variant">No audit logs found.</p>
    )}
  </div>
</Modal>

<Modal isOpen={isAlertsOpen} onClose={() => setIsAlertsOpen(false)} title="Critical Alerts" footer={<Button onClick={() => setIsAlertsOpen(false)}>Acknowledge All</Button>}>
  <div className="space-y-3">
    <div className="p-3 border-l-2 border-error bg-error/10"><p className="text-body-sm font-bold text-error">Resource Limit Reached</p><p className="text-[11px] text-on-surface-variant">Data Science dept exceeded quota.</p></div>
    <div className="p-3 border-l-2 border-error bg-error/10"><p className="text-body-sm font-bold text-error">API Key Expired</p><p className="text-[11px] text-on-surface-variant">Marketing dept external integration failing.</p></div>
  </div>
</Modal>

    </>
  );
}
