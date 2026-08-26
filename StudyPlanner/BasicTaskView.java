import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

//concrete implementation of TaskView interface, displays task details in the gui panel
public class BasicTaskView implements TaskView {

private JPanel panel;

    public BasicTaskView(Task task) {
        panel = new JPanel(new GridLayout(6, 1, 5, 5));
        panel.setBorder(new TitledBorder("Task Details"));

        panel.add(new JLabel("Name: " + task.getTitle()));
        panel.add(new JLabel("Description: " + task.getDescription()));
        panel.add(new JLabel("Due Date: " + task.getDueDate()));
        panel.add(new JLabel("Priority: " + task.getPriority()));
        panel.add(new JLabel("Estimated Hours: " + task.getEstimatedHours()));
        panel.add(new JLabel("Completed: " + task.isCompleted()));
    }
//return the constructed JPanel so it can used in the ui
@Override
public JPanel getPanel() {
return panel;
    }
}