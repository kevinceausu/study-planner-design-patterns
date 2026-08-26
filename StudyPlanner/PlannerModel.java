import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

//observer pattern, data model for the app
public class PlannerModel implements PlannerSubject {

    private ProjectComposite root;
    private List<PlannerObserver> observers;

    public PlannerModel() {
        this.root = new ProjectComposite("College Work");
    this.observers = new ArrayList<>();
    }

    public ProjectComposite getRoot() {
        return root;
}

    public void setRoot(ProjectComposite root) {
        this.root = root;
    notifyObservers();
    }

    public void addComponent(String parentName, PlannerComponent component) {
    try {
            PlannerComponent parent = root.findByName(parentName);

        if (!parent.isLeaf()) {
                parent.add(component);
                    notifyObservers();
            }
        } catch (NoSuchElementException e) {
        System.out.println("Parent not found: " + parentName);
        }
    }

    public void removeComponent(String componentName) {
        if (root.getName().equals(componentName)) {
        return;
        }

        boolean removed = removeRecursive(root, componentName);

        if (removed) {
        notifyObservers();
        }
    }

    private boolean removeRecursive(ProjectComposite current, String componentName) {
        List<PlannerComponent> children = current.getChildren();

        for (int i = 0; i < children.size(); i++) {
        PlannerComponent child = children.get(i);

            if (child.getName().equals(componentName)) {
                current.remove(child);
                return true;
            }

            if (!child.isLeaf()) {
                boolean removed = removeRecursive((ProjectComposite) child, componentName);
                if (removed) {
                return true;
                }
            }
        }

        return false;
    }

    public PlannerComponent findComponent(String name) {
        return root.findByName(name);
    }

    public int getTotalHours() {
    return root.getTotalEstimatedHours();
    }

    public int getTotalTaskCount() {
            return countTasks(root);
}

    private int countTasks(PlannerComponent component) {
        if (component.isLeaf()) {
            return 1;
        }

        int count = 0;
        for (PlannerComponent child : component.getChildren()) {
                count += countTasks(child);
        }
            return count;
 }

    public int getCompletedTaskCount() {
        return countCompletedTasks(root);
    }

    private int countCompletedTasks(PlannerComponent component) {
        if (component.isLeaf() && component instanceof TaskLeaf) {
            return ((TaskLeaf) component).getTask().isCompleted() ? 1 : 0;
        }

        int count = 0;
        if (!component.isLeaf()) {
            for (PlannerComponent child : component.getChildren()) {
                count += countCompletedTasks(child);
            }
        }
        return count;
    }

    public void markTaskComplete(String taskName) {
        try {
            PlannerComponent component = root.findByName(taskName);

            if (component.isLeaf() && component instanceof TaskLeaf) {
                TaskLeaf leaf = (TaskLeaf) component;
                Task oldTask = leaf.getTask();

                    Task updatedTask = new Task.Builder(oldTask.getTitle())
                        .setDescription(oldTask.getDescription())
                        .setDueDate(oldTask.getDueDate())
                        .setPriority(oldTask.getPriority())
                        .setEstimatedHours(oldTask.getEstimatedHours())
                        .setCategory(oldTask.getCategory())
                        .setCompleted(true)
                        .build();

                    replaceTask(root, taskName, new TaskLeaf(updatedTask));
            notifyObservers();
            }
        } catch (Exception e) {
        System.out.println("Task not found: " + taskName);
    }
}

    private boolean replaceTask(ProjectComposite current, String taskName, TaskLeaf newLeaf) {
        List<PlannerComponent> children = current.getChildren();

        for (int i = 0; i < children.size(); i++) {
        PlannerComponent child = children.get(i);

            if (child.isLeaf() && child.getName().equals(taskName)) {
                    children.set(i, newLeaf);
                return true;
            }

            if (!child.isLeaf()) {
            boolean replaced = replaceTask((ProjectComposite) child, taskName, newLeaf);
                if (replaced) {
                    return true;
                }
         }
        }

        return false;
    }

    @Override
    public void addObserver(PlannerObserver observer) {
            observers.add(observer);
    }

    @Override
    public void removeObserver(PlannerObserver observer) {
    observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
    for (PlannerObserver observer : observers) {
            observer.update();
        }
    }
}