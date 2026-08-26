import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class TaskTreePanel extends JPanel implements PlannerObserver {

    private PlannerModel model;
    private JTree tree;
    private DefaultMutableTreeNode rootNode;
    private JScrollPane scrollPane;

    public TaskTreePanel(PlannerModel model) {
        this.model = model;
        this.model.addObserver(this);

        setLayout(new BorderLayout());

        rootNode = buildTreeNode(model.getRoot());
        tree = new JTree(rootNode);
        scrollPane = new JScrollPane(tree);

        add(scrollPane, BorderLayout.CENTER);
    }

    private DefaultMutableTreeNode buildTreeNode(PlannerComponent component) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(component.getName());

        if (!component.isLeaf()) {
            for (PlannerComponent child : component.getChildren()) {
                node.add(buildTreeNode(child));
            }
        }

        return node;
    }

    public JTree getTree() {
        return tree;
    }

    public void refreshTree() {
         remove(scrollPane);

        rootNode = buildTreeNode(model.getRoot());
    tree = new JTree(rootNode);
        scrollPane = new JScrollPane(tree);

        add(scrollPane, BorderLayout.CENTER);
            revalidate();
        repaint();
    }

    @Override
    public void update() {
            refreshTree();
}
}