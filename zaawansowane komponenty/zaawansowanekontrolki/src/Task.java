public class Task {
    private String name;
    private boolean isCompleted;
    private String priority;

    public Task(String name, boolean isCompleted, String priority) {
        this.name = name;
        this.isCompleted = isCompleted;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getPriority() {
        return priority;
    }
}
