public interface PlannerSubject {
    void addObserver(PlannerObserver observer);
    void removeObserver(PlannerObserver observer);
    void notifyObservers();
}