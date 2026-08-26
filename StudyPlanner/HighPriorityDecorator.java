import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

//this class adds exisitng behaviour to the TaskView
public class HighPriorityDecorator extends TaskViewDecorator {

    public HighPriorityDecorator(TaskView decoratedTaskView) {
        super(decoratedTaskView);
    }

    @Override
    public JPanel getPanel() {
    JPanel panel = decoratedTaskView.getPanel();
    panel.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3));
    return panel;
    }
}