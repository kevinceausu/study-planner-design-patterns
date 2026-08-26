import java.awt.Color;
import javax.swing.JPanel;
//decorator pattern, adds behaviour to TaskView without modifying the original class
public class CompletedDecorator extends TaskViewDecorator {

    //takes the existing taskView and passes it to the parent decorator
public CompletedDecorator(TaskView decoratedTaskView) {
super(decoratedTaskView);
    }

    //this is required to ovveride the getPanel() method in order to modify the appeareance of the panel
@Override
    public JPanel getPanel() {
    JPanel panel = decoratedTaskView.getPanel();
        panel.setBackground(Color.GREEN);
        return panel;
    }
}