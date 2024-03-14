package controller;

public class TaskFormController {
    private TaskFormView taskFormView;
    private TaskDatabase taskDatabase;

    public TaskFormController(TaskFormView taskFormView, TaskDatabase taskDatabase) {
        this.taskFormView = taskFormView;
        this.taskDatabase = taskDatabase;
    }

    public void createTask() {
        Task newTask = taskFormView.createTask();
        // Добавление задачи в базу данных
        taskDatabase.addTask(newTask);
        taskFormView.displaySuccessMessage("Task created successfully.");
    }

    public void updateTask(Task task) {
        Task updatedTask = taskFormView.updateTask(task);
        // Обновление задачи в базе данных
        taskDatabase.updateTask(updatedTask);
        taskFormView.displaySuccessMessage("Task updated successfully.");
    }
}
