import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class MainFrame extends JFrame {

private PlannerModel model;
private TaskTreePanel treePanel;
private TaskDetailPanel detailPanel;
private StatusPanel statusPanel;

private JButton addProjectButton;
    private JButton addTaskButton;
private JButton deleteButton;
    private JButton completeButton;

    //receive the model and build gui
public MainFrame(PlannerModel model) {
        super("Study Planner and Assignment Tracker");

        this.model = model;

        treePanel = new TaskTreePanel(model);
        detailPanel = new TaskDetailPanel();
        statusPanel = new StatusPanel(model);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treePanel, detailPanel);
        splitPane.setDividerLocation(280);

        add(buildToolbar(), BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.SOUTH);

        setupTreeSelection();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(800, 550));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel buildToolbar() {
    JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));

    addProjectButton = new JButton("Add Project");
        addTaskButton = new JButton("Add Task");
        deleteButton = new JButton("Delete");
        completeButton = new JButton("Mark Complete");

        addProjectButton.addActionListener(e -> addProject());
    addTaskButton.addActionListener(e -> addTask());
    deleteButton.addActionListener(e -> deleteSelected());
        completeButton.addActionListener(e -> markSelectedComplete());

    toolbar.add(addProjectButton);
        toolbar.add(addTaskButton);
        toolbar.add(deleteButton);
        toolbar.add(completeButton);

        return toolbar;
    }

    private void setupTreeSelection() {
        treePanel.getTree().addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
            showSelectedComponent(e);
            }
        });
    }

    private void reconnectTreeSelectionListener() {
        treePanel.getTree().addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                showSelectedComponent(e);
            }
    });
    }

    private void showSelectedComponent(TreeSelectionEvent e) {
        TreePath path = e.getPath();
        String selectedName = path.getLastPathComponent().toString();

        try {
            PlannerComponent component = model.findComponent(selectedName);
            detailPanel.showComponent(component);
        } catch (Exception ex) {
            detailPanel.showComponent(null);
     }
    }

    private String getSelectedNodeName() {
    TreePath path = treePanel.getTree().getSelectionPath();

        if (path == null) {
            return null;
    }

        return path.getLastPathComponent().toString();
    }

    private void addProject() {
    String parentName = getSelectedNodeName();

        if (parentName == null) {
            parentName = model.getRoot().getName();
        }

        String projectName = JOptionPane.showInputDialog(this, "Enter project name:");
        if (projectName == null || projectName.trim().isEmpty()) {
        return;
        }

        PlannerComponent parent = model.findComponent(parentName);
        if (parent.isLeaf()) {
            JOptionPane.showMessageDialog(this, "Cannot add a project under a task.");
            return;
    }

            model.addComponent(parentName, new ProjectComposite(projectName));
        treePanel.refreshTree();
        reconnectTreeSelectionListener();
    }

    private void addTask() {
    String parentName = getSelectedNodeName();

        if (parentName == null) {
            JOptionPane.showMessageDialog(this, "Select a project first.");
            return;
        }

        PlannerComponent parent = model.findComponent(parentName);
        if (parent.isLeaf()) {
        JOptionPane.showMessageDialog(this, "Cannot add a task under another task.");
            return;
    }

        String title = JOptionPane.showInputDialog(this, "Enter task title:");
        if (title == null || title.trim().isEmpty()) {
        return;
        }

        String priority = JOptionPane.showInputDialog(this, "Enter priority (Low / Normal / High):");
        if (priority == null || priority.trim().isEmpty()) {
        priority = "Normal";
        }

        String dueDate = JOptionPane.showInputDialog(this, "Enter due date:");
        if (dueDate == null) {
            dueDate = "";
    }

        String hoursText = JOptionPane.showInputDialog(this, "Enter estimated hours:");
        int hours = 0;

        try {
            if (hoursText != null && !hoursText.trim().isEmpty()) {
            hours = Integer.parseInt(hoursText);
        }
        } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Invalid number. Using 0 hours.");
    }

        Task task = new Task.Builder(title)
                .setPriority(priority)
                .setDueDate(dueDate)
                .setEstimatedHours(hours)
                .build();

        model.addComponent(parentName, new TaskLeaf(task));
        treePanel.refreshTree();
        reconnectTreeSelectionListener();
    }

    private void deleteSelected() {
        String selectedName = getSelectedNodeName();

        if (selectedName == null) {
            JOptionPane.showMessageDialog(this, "Select a project or task first.");
        return;
        }

        if (selectedName.equals(model.getRoot().getName())) {
        JOptionPane.showMessageDialog(this, "Cannot delete the root project.");
            return;
        }

        model.removeComponent(selectedName);
        treePanel.refreshTree();
        reconnectTreeSelectionListener();
        detailPanel.showComponent(null);
    }

    private void markSelectedComplete() {
    String selectedName = getSelectedNodeName();

        if (selectedName == null) {
            JOptionPane.showMessageDialog(this, "Select a task first.");
            return;
        }

        PlannerComponent component = model.findComponent(selectedName);

        if (!component.isLeaf()) {
            JOptionPane.showMessageDialog(this, "Only tasks can be marked complete.");
            return;
    }

        model.markTaskComplete(selectedName);
        treePanel.refreshTree();
        reconnectTreeSelectionListener();
        detailPanel.showComponent(model.findComponent(selectedName));
    }
}
