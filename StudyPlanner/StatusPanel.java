import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class StatusPanel extends JPanel implements PlannerObserver {

    private PlannerModel model;

    private JLabel totalHoursLabel;
    private JLabel totalTasksLabel;
    private JLabel completedTasksLabel;

    public StatusPanel(PlannerModel model) {
    this.model = model;
    this.model.addObserver(this);

            setLayout(new FlowLayout(FlowLayout.LEFT));
        setBorder(new TitledBorder("Status"));

        totalHoursLabel = new JLabel();
        totalTasksLabel = new JLabel();
        completedTasksLabel = new JLabel();

        add(totalHoursLabel);
        add(totalTasksLabel);
        add(completedTasksLabel);

        update();
}

    @Override
    public void update() {
    totalHoursLabel.setText("Total Hours: " + model.getTotalHours() + "   ");
        totalTasksLabel.setText("Total Tasks: " + model.getTotalTaskCount() + "   ");
        completedTasksLabel.setText("Completed Tasks: " + model.getCompletedTaskCount() + "   ");
    }
}