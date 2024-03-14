package controller;

import view.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Papech
 */
public class TaskListController {
    private TaskListView taskListView;
    private List<Task> taskList;

    public TaskListController(TaskListView taskListView, List<Task> taskList) {
        this.taskListView = taskListView;
        this.taskList = taskList;
    }

    public void displayTasks() {
        taskListView.displayTasks();
    }

    public void displayTaskDetails(Task task) {
        taskListView.displayTaskDetails(task);
    }

    public void displaySortingOptions() {
        taskListView.displaySortingOptions();
    }

    public void sortTasksByDateCreated() {
        // Реализация сортировки задач по дате создания
        Collections.sort(taskList, new Comparator<Task>() {
            @Override
            public int compare(Task task1, Task task2) {
                return task1.getDateCreated().compareTo(task2.getDateCreated());
            }
        });
        taskListView.displayTasks();
    }

    public void sortTasksByDueDate() {
        // Реализация сортировки задач по дате выполнения
        Collections.sort(taskList, new Comparator<Task>() {
            @Override
            public int compare(Task task1, Task task2) {
                if (task1.getDateDone() == null && task2.getDateDone() == null) {
                    return 0;
                } else if (task1.getDateDone() == null) {
                    return 1;
                } else if (task2.getDateDone() == null) {
                    return -1;
                } else {
                    return task1.getDateDone().compareTo(task2.getDateDone());
                }
            }
        });
        taskListView.displayTasks();
    }

    public void sortTasksByPriority() {
        // Реализация сортировки задач по приоритету
        Collections.sort(taskList, new Comparator<Task>() {
            @Override
            public int compare(Task task1, Task task2) {
                return Integer.compare(task1.getPriority(), task2.getPriority());
            }
        });
        taskListView.displayTasks();
    }

    public void saveTask(Task task) {
        throw new UnsupportedOperationException("Пока не поддерживается.");
    }
}
