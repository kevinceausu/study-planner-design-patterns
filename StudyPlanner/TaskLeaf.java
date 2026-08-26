public class TaskLeaf extends PlannerComponent {

    private Task task;

    public TaskLeaf(Task task) {
        super(task.getTitle());
        this.task = task;
    }

    public Task getTask() {
    return task;
    }

    @Override
    public int getTotalEstimatedHours() {
            return task.getEstimatedHours();
    }

    @Override
    public boolean isLeaf() {
         return true;
    }
}