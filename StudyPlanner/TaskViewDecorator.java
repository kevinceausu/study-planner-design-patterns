import javax.swing.JPanel;

public abstract class TaskViewDecorator implements TaskView {

    protected TaskView decoratedTaskView;

    public TaskViewDecorator(TaskView decoratedTaskView) {
        this.decoratedTaskView = decoratedTaskView;
   }

    @Override
    public JPanel getPanel() {
        return decoratedTaskView.getPanel();
     }
}