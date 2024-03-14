package view;
import java.util.List;

public class TaskListView {
    private List<Task> taskList;

    public TaskListView(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void displayTasks() {
        System.out.println("Task List:");
        for (Task task : taskList) {
            System.out.println(task);
        }
    }

    public void displayTaskDetails(Task task) {
        System.out.println("Task Details:");
        System.out.println(task);
    }

    public void displaySortingOptions() {
        System.out.println("Sorting Options:");
        System.out.println("1. Sort by Date Created");
        System.out.println("2. Sort by Due Date");
        System.out.println("3. Sort by Priority");
    }

    public void displayErrorMessage(String message) {
        System.out.println("Error: " + message);
    }
}
