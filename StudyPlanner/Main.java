public class Main {
public static void main(String[] args) {

        //create main model that contains the model projects and tasks with the details
        PlannerModel model = new PlannerModel();

        //using builder pattern to create tasks
        Task report = new Task.Builder("Report")
                .setDescription("Write the final assignment report")
                .setPriority("High")
                .setEstimatedHours(6)
                .setDueDate("2026-04-20")
                .setCategory("OODP")
                .build();

        Task uml = new Task.Builder("UML Diagrams")
                .setPriority("Medium")
                .setEstimatedHours(3)
                .setCategory("OODP")
                .build();

        Task code = new Task.Builder("Java Code")
                .setPriority("High")
                .setEstimatedHours(10)
                .build();

        Task ipv6Lab = new Task.Builder("IPv6 Lab")
                .setPriority("Medium")
                .setEstimatedHours(4)
                .setCategory("Networks")
                .setCompleted(true)
                .build();

        Task databaseSetup = new Task.Builder("Database Setup")
                .setPriority("Low")
                .setEstimatedHours(5)
                .setCategory("Web Apps")
                .build();

                //create projects using composite
        ProjectComposite oodpProject = new ProjectComposite("OODP Assignment");
        oodpProject.add(new TaskLeaf(report));
        oodpProject.add(new TaskLeaf(uml));
        oodpProject.add(new TaskLeaf(code));

        //using composite pattern
        ProjectComposite networksProject = new ProjectComposite("Networking");
        networksProject.add(new TaskLeaf(ipv6Lab));

        ProjectComposite webAppsProject = new ProjectComposite("Rich Web Apps");
        webAppsProject.add(new TaskLeaf(databaseSetup));

        //add projects to model
        model.getRoot().add(oodpProject);
        model.getRoot().add(networksProject);
        model.getRoot().add(webAppsProject);

        //notify ui in order to get updates
        model.notifyObservers();

        new MainFrame(model);
    }
}
