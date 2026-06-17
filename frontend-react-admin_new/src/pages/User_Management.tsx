import { useState, useRef } from 'react';
import toast from 'react-hot-toast';
import { useListAdminUsers } from '../features/mod23_admin_portal/hooks/queries/useListAdminUsers';
import { useCreateAdminUser, useUpdateAdminUser, useDeleteAdminUser } from '../features/mod23_admin_portal/hooks/mutations/useAdminUserMutations';
import PageHeader from '../components/shared/PageHeader';
import StatCard from '../components/shared/StatCard';
import Button from '../components/ui/Button';
import Card from '../components/ui/Card';
import Badge from '../components/ui/Badge';
import DataTable from '../components/shared/DataTable';
import Modal from '../components/ui/Modal';
import { AdminUser, AdminUserRequestDto } from '../features/mod23_admin_portal/types/adminuser.types';
import { useListDepartments } from '../features/mod03_department_management/hooks/queries/useListDepartments';

export default function User_Management() {
  const { data, isLoading } = useListAdminUsers();
  const { data: deptData } = useListDepartments();
  const departments = deptData?.data?.departments || [];
  
  const createMutation = useCreateAdminUser();
  const updateMutation = useUpdateAdminUser();
  const deleteMutation = useDeleteAdminUser();
  
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [editingUser, setEditingUser] = useState<AdminUser | null>(null);
  const [formData, setFormData] = useState<AdminUserRequestDto>({
    name: '',
    email: '',
    role: 'Standard User',
    dept: 'Engineering'
  });

  const [searchTerm, setSearchTerm] = useState('');
  const [roleFilter, setRoleFilter] = useState('');
  const [statusFilter, setStatusFilter] = useState('');

  const fileInputRef = useRef<HTMLInputElement>(null);

  const users = data?.data?.users || [];
  
  const filteredUsers = users.filter((u: any) => {
    const matchesSearch = u.name?.toLowerCase().includes(searchTerm.toLowerCase()) || u.email?.toLowerCase().includes(searchTerm.toLowerCase());
    const matchesRole = roleFilter ? u.role?.toLowerCase() === roleFilter.toLowerCase() : true;
    const matchesStatus = statusFilter ? u.status?.toLowerCase() === statusFilter.toLowerCase() : true;
    return matchesSearch && matchesRole && matchesStatus;
  });

  const handleFileUpload = (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    if (!file) return;
    const reader = new FileReader();
    reader.onload = async (evt) => {
      const text = evt.target?.result as string;
      const lines = text.split('\n').filter(l => l.trim() !== '');
      if (lines.length > 1) {
        toast.success(`Successfully parsed ${lines.length - 1} users from CSV.`);
      } else {
        toast.error('CSV file is empty or invalid.');
      }
    };
    reader.readAsText(file);
    if (fileInputRef.current) fileInputRef.current.value = '';
  };

  const clearFilters = () => {
    setSearchTerm('');
    setRoleFilter('');
    setStatusFilter('');
  };

  const handleOpenModal = (user?: AdminUser) => {
    if (user) {
      setEditingUser(user);
      setFormData({ name: user.name, email: user.email, role: user.role, dept: user.dept });
    } else {
      setEditingUser(null);
      setFormData({ name: '', email: '', role: 'Standard User', dept: 'Engineering' });
    }
    setIsModalOpen(true);
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
    setEditingUser(null);
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (editingUser) {
      updateMutation.mutate({ id: editingUser.id, data: formData }, {
        onSuccess: () => handleCloseModal()
      });
    } else {
      createMutation.mutate(formData, {
        onSuccess: () => handleCloseModal()
      });
    }
  };

  const handleDelete = (id: string) => {
    if (confirm('Are you sure you want to delete this user?')) {
      deleteMutation.mutate(id);
    }
  };

  return (
    <>
      



{/* Screen Content */}
<div className="p-8 max-w-container-max mx-auto w-full flex-1 flex flex-col gap-6">
{/* Page Header */}
<PageHeader 
  title="User Management"
  description="Manage enterprise users, roles, and department permissions."
  actions={
    <>
      <input type="file" accept=".csv" className="hidden" ref={fileInputRef} onChange={handleFileUpload} />
      <Button variant="outline" icon="upload_file" onClick={() => fileInputRef.current?.click()}>Bulk Import</Button>
      <Button icon="add" onClick={() => handleOpenModal()}>Create User</Button>
    </>
  }
/>
{/* Filters Bar */}
<Card padding="md" className="grid grid-cols-1 md:grid-cols-4 lg:grid-cols-5 gap-4">
<div className="md:col-span-2 relative">
<span className="material-symbols-outlined absolute left-3 top-1/2 -translate-y-1/2 text-on-surface-variant text-[20px]">filter_alt</span>
<input className="w-full border-outline-variant rounded-lg pl-10 pr-4 py-2 text-body-md focus:border-primary focus:ring-2 focus:ring-primary/20 transition-all" placeholder="Filter by name, email or ID..." type="text" value={searchTerm} onChange={(e) => setSearchTerm(e.target.value)} />
</div>
<div>
<select className="w-full border-outline-variant rounded-lg px-3 py-2 text-body-md focus:border-primary focus:ring-2 focus:ring-primary/20 transition-all appearance-none bg-no-repeat bg-[right_12px_center] cursor-pointer" value={roleFilter} onChange={(e) => setRoleFilter(e.target.value)}>
<option value="">Role: All</option>
<option value="super">Super Admin</option>
<option value="content">Content Admin</option>
<option value="interviewer">Interviewer</option>
<option value="user">Standard User</option>
</select>
</div>
<div>
<select className="w-full border-outline-variant rounded-lg px-3 py-2 text-body-md focus:border-primary focus:ring-2 focus:ring-primary/20 transition-all appearance-none bg-no-repeat bg-[right_12px_center] cursor-pointer" value={statusFilter} onChange={(e) => setStatusFilter(e.target.value)}>
<option value="">Status: All</option>
<option value="ACTIVE">Active</option>
<option value="INACTIVE">Inactive</option>
<option value="PENDING">Pending</option>
</select>
</div>
<div className="flex items-center justify-end">
<button className="text-primary font-label-md text-label-md hover:underline" onClick={clearFilters}>Clear all filters</button>
</div>
</Card>
{/* Data Grid (Dense MUI Style) */}
<DataTable 
  columns={[
    { key: 'checkbox', header: '', render: () => <input className="rounded text-primary focus:ring-primary border-outline-variant" type="checkbox"/> },
    { key: 'user', header: 'User', render: (row: any) => {
      const displayName = row.name === 'Unknown' && row.email ? row.email.split('@')[0] : row.name;
      const displayInitial = row.name === 'Unknown' && row.email ? row.email.charAt(0).toUpperCase() : row.initial;
      return (
      <div className="flex items-center gap-3">
        <div className="w-9 h-9 rounded-full bg-primary-container/20 flex items-center justify-center text-primary font-bold overflow-hidden">
          {displayInitial}
        </div>
        <div>
          <p className="font-body-md text-body-md text-on-surface font-semibold">{displayName}</p>
          <p className="text-[12px] text-on-surface-variant leading-tight">{row.email}</p>
        </div>
      </div>
      );
    } },
    { key: 'role', header: 'Role' },
    { key: 'dept', header: 'Department' },
    { key: 'lastLogin', header: 'Last Login', render: () => <span className="text-body-md text-on-surface-variant">Just now</span> },
    { key: 'status', header: 'Status', render: (row: any) => (
      <Badge variant={row.status === 'ACTIVE' ? 'success' : 'warning'}>{row.status}</Badge>
    ) },
    { key: 'actions', header: 'Actions', render: (row: any) => (
      <div className="flex justify-end gap-1 opacity-0 group-hover:opacity-100 transition-opacity">
        <Button variant="ghost" icon="edit" size="sm" className="p-1.5" onClick={() => handleOpenModal(row)} />
        <Button variant="ghost" icon="delete" size="sm" className="p-1.5 hover:text-error" onClick={() => handleDelete(row.id)} />
      </div>
    ) }
  ]}
  data={filteredUsers}
  isLoading={isLoading}
  emptyState={
    <div className="flex flex-col items-center justify-center py-12">
      <span className="material-symbols-outlined text-4xl mb-2 text-outline">group</span>
      <p className="text-body-md font-bold text-on-surface">No Users Found</p>
      <p className="text-sm">There are no users in this system yet.</p>
    </div>
  }
/>
{/* Stats Overview (Bento Style Minor Widget) */}
<div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6">
<StatCard 
  title="Total Users"
  value="1,248"
  icon="group"
  trend={{ value: "+12% this month", positive: true }}
/>
<StatCard 
  title="Active Now"
  value="142"
  icon="online_prediction"
  trend={{ value: "Current sessions" }}
/>
<StatCard 
  title="Storage Used"
  value="78.4 GB"
  icon="cloud_queue"
  footer={
    <div className="w-24 h-1.5 bg-outline-variant rounded-full mt-2 overflow-hidden">
      <div className="h-full bg-primary w-[65%]"></div>
    </div>
  }
/>
<StatCard 
  title="Pending Invites"
  value="14"
  icon="mail"
  trend={{ value: "Requires attention" }}
/>
</div>
</div>
{/* Float FAB for quick actions */}
<button className="fixed bottom-8 right-8 w-14 h-14 bg-primary text-on-primary rounded-full shadow-lg flex items-center justify-center hover:scale-105 transition-transform z-50" onClick={() => handleOpenModal()}>
<span className="material-symbols-outlined text-[28px]">add</span>
</button>

{/* Create/Edit User Modal */}
<Modal
  isOpen={isModalOpen}
  onClose={handleCloseModal}
  title={editingUser ? "Edit User" : "Create New User"}
  footer={
    <>
      <Button variant="ghost" onClick={handleCloseModal}>Cancel</Button>
      <Button onClick={handleSubmit} isLoading={createMutation.isPending || updateMutation.isPending}>
        {editingUser ? "Save Changes" : "Create User"}
      </Button>
    </>
  }
>
  <form id="user-form" className="space-y-4" onSubmit={handleSubmit}>
    <div className="flex flex-col gap-1.5">
      <label className="text-label-md font-bold text-on-surface-variant">Full Name</label>
      <input
        required
        type="text"
        className="w-full px-4 py-2 bg-surface-container-lowest border border-outline-variant rounded focus:border-primary focus:ring-1 focus:ring-primary outline-none"
        value={formData.name}
        onChange={(e) => setFormData({ ...formData, name: e.target.value })}
      />
    </div>
    <div className="flex flex-col gap-1.5">
      <label className="text-label-md font-bold text-on-surface-variant">Email Address</label>
      <input
        required
        type="email"
        className="w-full px-4 py-2 bg-surface-container-lowest border border-outline-variant rounded focus:border-primary focus:ring-1 focus:ring-primary outline-none"
        value={formData.email}
        onChange={(e) => setFormData({ ...formData, email: e.target.value })}
      />
    </div>
    <div className="grid grid-cols-2 gap-4">
      <div className="flex flex-col gap-1.5">
        <label className="text-label-md font-bold text-on-surface-variant">Role</label>
        <select
          className="w-full px-4 py-2 bg-surface-container-lowest border border-outline-variant rounded focus:border-primary focus:ring-1 focus:ring-primary outline-none"
          value={formData.role}
          onChange={(e) => setFormData({ ...formData, role: e.target.value })}
        >
          <option value="Super Admin">Super Admin</option>
          <option value="Content Admin">Content Admin</option>
          <option value="Interviewer">Interviewer</option>
          <option value="Standard User">Standard User</option>
        </select>
      </div>
      <div className="flex flex-col gap-1.5">
        <label className="text-label-md font-bold text-on-surface-variant">Department</label>
        <select
          className="w-full px-4 py-2 bg-surface-container-lowest border border-outline-variant rounded focus:border-primary focus:ring-1 focus:ring-primary outline-none"
          value={formData.dept}
          onChange={(e) => setFormData({ ...formData, dept: e.target.value })}
        >
          <option value="">Select Department</option>
          {departments.map((d: any) => (
            <option key={d.id} value={d.name}>{d.name}</option>
          ))}
        </select>
      </div>
    </div>
  </form>
</Modal>    </>
  );
}
