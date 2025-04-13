import java.util.*;

enum Priority {
    LOW, MEDIUM, HIGH
}

class Task {
    private String name;
    private boolean isCompleted;
    private Priority priority;
    private String category;

    public Task(String name, Priority priority, String category) {
        this.name = name;
        this.priority = priority;
        this.category = category;
        this.isCompleted = false;
    }

    public void complete() {
        this.isCompleted = true;
    }

    public String getName() {
        return name;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public Priority getPriority() {
        return priority;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return name + " [" + category + "] - Priority: " + priority + (isCompleted ? " [Completed]" : " [Pending]");
    }
}

class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(String name, Priority priority, String category) {
        tasks.add(new Task(name, priority, category));
    }

    public void printTasks() {
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    // 1. Remove a task by name
    public void removeTask(String name) {
        tasks.removeIf(task -> task.getName().equals(name));
    }

    // 2. Find all completed tasks
    public List<Task> getCompletedTasks() {
        List<Task> completedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isCompleted()) {
                completedTasks.add(task);
            }
        }
        return completedTasks;
    }

    // 3. List tasks sorted by name
    public void sortTasksByName() {
        tasks.sort(Comparator.comparing(Task::getName));
    }

    // 4. Sort tasks by priority
    public void sortTasksByPriority() {
        tasks.sort(Comparator.comparing(Task::getPriority));
    }

    // 5. Filter tasks by category
    public List<Task> filterByCategory(String category) {
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getCategory().equals(category)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }

    // 6. Find the highest-priority unfinished task
    public List<Task> getMostUrgentTasks() {
        List<Task> urgentTasks = new ArrayList<>();
        Priority highestPriority = Priority.LOW;
        for (Task task : tasks) {
            if (!task.isCompleted() && task.getPriority().ordinal() > highestPriority.ordinal()) {
                highestPriority = task.getPriority();
            }
        }
        for (Task task : tasks) {
            if (task.getPriority() == highestPriority && !task.isCompleted()) {
                urgentTasks.add(task);
            }
        }
        return urgentTasks;
    }

    // 7. Count tasks per category
    public Map<String, Integer> countTasksPerCategory() {
        Map<String, Integer> categoryCount = new HashMap<>();
        for (Task task : tasks) {
            categoryCount.put(task.getCategory(), categoryCount.getOrDefault(task.getCategory(), 0) + 1);
        }
        return categoryCount;
    }

    // 8. Mark a task as completed by name
    public void markTaskCompleted(String name) {
        for (Task task : tasks) {
            if (task.getName().equals(name)) {
                task.complete();
            }
        }
    }

    // 9. Mark all tasks in a category as completed
    public void markCategoryCompleted(String category) {
        for (Task task : tasks) {
            if (task.getCategory().equals(category)) {
                task.complete();
            }
        }
    }
}

public class SI2025Lab1Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        manager.addTask("Write report", Priority.HIGH, "Work");
        manager.addTask("Submit assignment", Priority.MEDIUM, "School");
        manager.addTask("Buy groceries", Priority.LOW, "Personal");

        // 1. Remove a task by name
        manager.removeTask("Buy groceries");

        // 2. Display completed tasks
        System.out.println("Completed tasks:");
        manager.getCompletedTasks().forEach(System.out::println);

        // 3. Sort tasks by name
        manager.sortTasksByName();
        System.out.println("\nTasks sorted by name:");
        manager.printTasks();

        // 4. Sort tasks by priority
        manager.sortTasksByPriority();
        System.out.println("\nTasks sorted by priority:");
        manager.printTasks();

        // 5. Filter tasks by category
        System.out.println("\nPersonal tasks:");
        manager.filterByCategory("Personal").forEach(System.out::println);

        // 6. Find the most urgent unfinished tasks
        System.out.println("\nMost urgent tasks:");
        manager.getMostUrgentTasks().forEach(System.out::println);

        // 7. Count tasks per category
        System.out.println("\nTask count per category:");
        manager.countTasksPerCategory().forEach((category, count) -> System.out.println(category + ": " + count));

        // 8. Mark a task as completed by name
        manager.markTaskCompleted("Write report");

        // 9. Mark all tasks in the "School" category as completed
        manager.markCategoryCompleted("School");

        // Display all tasks
        System.out.println("\nAll tasks after updates:");
        manager.printTasks();
    }
}
