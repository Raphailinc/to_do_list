package view;

import java.util.List;

/**
 *
 * @author Papech
 */
public class TaskListView {
    private List<Task> taskList;

    public TaskListView(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void displayTasks() {
        System.out.println("Список задач:");
        for (Task task : taskList) {
            System.out.println(task);
        }
    }

    public void displayTaskDetails(Task task) {
        System.out.println("Детали задачи:");
        System.out.println(task);
    }

    public void displaySortingOptions() {
        System.out.println("Параметры сортировки:");
        System.out.println("1. Сортировать по дате создания");
        System.out.println("2. Сортировать по сроку исполнения");
        System.out.println("3. Сортировать по приоритету");
    }

    public void displayErrorMessage(String message) {
        System.out.println("Ошибка: " + message);
    }
}
