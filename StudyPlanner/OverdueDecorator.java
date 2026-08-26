import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class OverdueDecorator extends TaskViewDecorator {

    public OverdueDecorator(TaskView decoratedTaskView) {
    super(decoratedTaskView);
    }

    @Override
    public JPanel getPanel() {
        JPanel panel = decoratedTaskView.getPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
        return panel;
    }
}