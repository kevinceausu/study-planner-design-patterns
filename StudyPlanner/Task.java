public class Task {

    private final String title;
    private final String description;
    private final String dueDate;
    private final String priority;
    private final int estimatedHours;
    private final boolean completed;
    private final String category;

    private Task(Builder builder) {
        this.title = builder.title;
        this.description = builder.description;
        this.dueDate = builder.dueDate;
        this.priority = builder.priority;
        this.estimatedHours = builder.estimatedHours;
        this.completed = builder.completed;
        this.category = builder.category;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public int getEstimatedHours() {
        return estimatedHours;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return title;
}

    public static class Builder {
        private final String title;

            private String description = "";
        private String dueDate = "";
        private String priority = "Normal";
        private int estimatedHours = 0;
        private boolean completed = false;
        private String category = "";

        public Builder(String title) {
        this.title = title;
    }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setDueDate(String dueDate) {
                this.dueDate = dueDate;
            return this;
        }

        public Builder setPriority(String priority) {
            this.priority = priority;
            return this;
        }

        public Builder setEstimatedHours(int estimatedHours) {
            this.estimatedHours = estimatedHours;
        return this;
        }

        public Builder setCompleted(boolean completed) {
            this.completed = completed;
            return this;
    }

        public Builder setCategory(String category) {
            this.category = category;
        return this;
        }

        public Task build() {
            return new Task(this);
    }
    }
}