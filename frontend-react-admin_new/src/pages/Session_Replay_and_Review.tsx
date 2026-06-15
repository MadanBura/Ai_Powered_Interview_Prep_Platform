import Button from '../components/ui/Button';
import Card from '../components/ui/Card';

export default function Session_Replay_and_Review() {
  return (
    <>
      



{/* Content Canvas */}
<div className="flex-grow flex p-gutter gap-gutter overflow-hidden">
{/* Empty State for New Admin */}
<Card className="flex-grow flex flex-col items-center justify-center p-12 text-center m-gutter">
  <div className="w-20 h-20 bg-surface-container rounded-full flex items-center justify-center mb-6">
    <span className="material-symbols-outlined text-[40px] text-outline">smart_display</span>
  </div>
  <h2 className="text-headline-sm font-headline-sm text-on-surface mb-2">No Session Selected</h2>
  <p className="text-body-md text-on-surface-variant max-w-md mx-auto mb-6">Please select a completed or active session from the monitoring dashboard to view its replay and AI analysis.</p>
  <Button>
    Go to Active Sessions
  </Button>
</Card>
</div>



    </>
  );
}
