package controller;

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
                if (task1.getDueDate() == null && task2.getDueDate() == null) {
                    return 0;
                } else if (task1.getDueDate() == null) {
                    return 1;
                } else if (task2.getDueDate() == null) {
                    return -1;
                } else {
                    return task1.getDueDate().compareTo(task2.getDueDate());
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
}
