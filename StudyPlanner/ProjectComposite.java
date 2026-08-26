import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ProjectComposite extends PlannerComponent {

    private List<PlannerComponent> children;

    public ProjectComposite(String name) {
        super(name);
    this.children = new ArrayList<>();
}

    @Override
    public int getTotalEstimatedHours() {
            int total = 0;

    for (PlannerComponent child : children) {
            total += child.getTotalEstimatedHours();
    }

            return total;
    }

    @Override
    public boolean isLeaf() {
    return false;
    }

    @Override
    public void add(PlannerComponent component) {
    children.add(component);
    }

    @Override
    public void remove(PlannerComponent component) {
    children.remove(component);
    }

    @Override
    public List<PlannerComponent> getChildren() {
    return children;
    }

    @Override
    public PlannerComponent findByName(String searchName) {
        if (name.equals(searchName)) {
        return this;
    }

        for (PlannerComponent child : children) {
            try {
                return child.findByName(searchName);
            } catch (NoSuchElementException e) {
                // keep searching
            }
    }

            throw new NoSuchElementException("Component not found: " + searchName);
    }
}
