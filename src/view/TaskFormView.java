package view;
import java.util.Scanner;

public class TaskFormView {
    private Scanner scanner;

    public TaskFormView() {
        scanner = new Scanner(System.in);
    }

    public Task createTask() {
        System.out.println("Create New Task:");
        System.out.print("Enter task name: ");
        String name = scanner.nextLine();
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        System.out.print("Enter task due date (yyyy-mm-dd): ");
        String dueDate = scanner.nextLine();
        System.out.print("Enter task priority: ");
        int priority = scanner.nextInt();

        return new Task(name, description, dueDate, priority);
    }

    public Task updateTask(Task task) {
        System.out.println("Update Task:");
        System.out.print("Enter task name (current: " + task.getName() + "): ");
        String name = scanner.nextLine();
        System.out.print("Enter task description (current: " + task.getDescription() + "): ");
        String description = scanner.nextLine();
        System.out.print("Enter task due date (current: " + task.getDueDate() + "): ");
        String dueDate = scanner.nextLine();
        System.out.print("Enter task priority (current: " + task.getPriority() + "): ");
        int priority = scanner.nextInt();

        task.setName(name);
        task.setDescription(description);
        task.setDueDate(dueDate);
        task.setPriority(priority);

        return task;
    }

    public void displaySuccessMessage(String message) {
        System.out.println("Success: " + message);
    }

    public void displayErrorMessage(String message) {
        System.out.println("Error: " + message);
    }
}
