import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class TaskDetailPanel extends JPanel {

    public TaskDetailPanel() {
        setLayout(new BorderLayout());
        setBorder(new TitledBorder("Task Details"));
    }

    public void showComponent(PlannerComponent component) {
        removeAll();

        if (component == null) {
        JPanel emptyPanel = new JPanel(new GridLayout(3, 1, 5, 5));
            emptyPanel.add(new JLabel("Name: "));
            emptyPanel.add(new JLabel("Type: "));
            emptyPanel.add(new JLabel("Estimated Hours: "));
            add(emptyPanel, BorderLayout.CENTER);
            revalidate();
            repaint();
            return;
    }

        if (component.isLeaf() && component instanceof TaskLeaf) {
                Task task = ((TaskLeaf) component).getTask();

            TaskView view = new BasicTaskView(task);

            //decorator pattern applied dynamically
            if ("High".equalsIgnoreCase(task.getPriority())) {
                view = new HighPriorityDecorator(view);
            }

            if (task.isCompleted()) {
                view = new CompletedDecorator(view);
            }

            if (!task.isCompleted() && !task.getDueDate().isEmpty()) {
                view = new OverdueDecorator(view);
            }

            add(view.getPanel(), BorderLayout.CENTER);

        } else {
            JPanel projectPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        projectPanel.add(new JLabel("Name: " + component.getName()));
                projectPanel.add(new JLabel("Type: Project"));
            projectPanel.add(new JLabel("Estimated Hours: " + component.getTotalEstimatedHours()));
            add(projectPanel, BorderLayout.CENTER);
    }

    revalidate();
        repaint();
}
}