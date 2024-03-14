package controller;

import view.*;
import model.*;
import user.*;

/**
 *
 * @author Papech
 */
public class TaskFormController {
    private TaskFormView taskFormView;
    private TaskDatabase taskDatabase;
    private Task task;
   

    public TaskFormController(TaskFormView taskFormView, TaskDatabase taskDatabase) {
        this.taskFormView = taskFormView;
        this.taskDatabase = taskDatabase;
    }

    public void createTask() {
        Task newTask = taskFormView.createTask();
        // Добавление задачи в базу данных
        taskDatabase.addTask(newTask);
        taskFormView.displaySuccessMessage("Задача создана успешно.");
    }

    public void updateTask(TaskFormView task, User currentUser) {
        Task updatedTask = taskFormView.updateTask(task); 
        // Обновление задачи в базе данных
        taskDatabase.updateTask(updatedTask);
        taskFormView.displaySuccessMessage("Задача успешно обновлена.");
        
        saveTask(updatedTask);
        refreshTaskList();
    }
    
    public void saveTask(Task task) {
        // Сохранение задачи в базе данных для текущего пользователя
        taskDatabase.saveTask(task);
    }

    public void refreshTaskList() {
        // Обновление списка задач в представлении
        taskFormView.updateTask(task);
    }
}
