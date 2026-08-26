import java.util.List;
import java.util.NoSuchElementException;

public abstract class PlannerComponent {

    protected String name;

    public PlannerComponent(String name) {
    this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract int getTotalEstimatedHours();

    public abstract boolean isLeaf();

    public void add(PlannerComponent component) {
    throw new UnsupportedOperationException("Cannot add child to this component");
    }

    public void remove(PlannerComponent component) {
        throw new UnsupportedOperationException("Cannot remove child from this component");
    }

    public List<PlannerComponent> getChildren() {
        throw new UnsupportedOperationException("This component has no children");
    }

    public PlannerComponent findByName(String searchName) {
        if (name.equals(searchName)) {
    return this;
    }
        throw new NoSuchElementException("Component not found: " + searchName);
    }

    @Override
    public String toString() {
        return name;
}
}