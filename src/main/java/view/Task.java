package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author Papech
 */
public class Task {
    private String taskName;
    private String description;
    private Date dateCreated;
    private Date dateDone;
    private int priority;
    private boolean isCompleted;
    private int id;

    // Конструктор задачи
    public Task(String taskName, String description, Date dateCreated, int priority) {
        this.taskName = taskName;
        this.description = description;
        this.dateCreated = dateCreated;
        this.priority = priority;
        this.isCompleted = false;
    }
    
    public Task(int id, String taskName, String description, Date dateCreate, Date dateDone, int priority, String status) {
        this.id = id;
        this.taskName = taskName;
        this.description = description;
        this.dateCreated = dateCreate;
        this.priority = priority;
        this.isCompleted = false;
    }

    // Метод для установки даты выполнения задачи
    public void setDateDone(Date dateDone) {
        this.dateDone = dateDone;
    }

    // Метод для установки статуса задачи (выполнено/не выполнено)
    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
    
    // Метод для получения имени задачи
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    
    // Метод для получения описания задачи
    public void setDescription(String description) {
        this.description = description;
    }
    
    // Метод для получения приоритета задачи
    public void setPriority(int priority) {
        this.priority = priority;
    }

    // Метод для получения имени задачи
    public String getTaskName() {
        return taskName;
    }

    // Метод для получения описания задачи
    public String getDescription() {
        return description;
    }

    // Метод для получения даты создания задачи
    public Date getDateCreated() {
        return dateCreated;
    }

    // Метод для получения даты выполнения задачи
    public Date getDateDone() {
        return dateDone;
    }

    // Метод для получения приоритета задачи
    public int getPriority() {
        return priority;
    }

    // Метод для проверки статуса задачи (выполнено/не выполнено)
    public boolean isCompleted() {
        return isCompleted;
    }
    
    public String getStatus() {
        return (this.isCompleted ? "Завершено" : "Не завершено");
    }

    // Метод для удаления задачи из базы данных
    public void deleteTaskFromDatabase() {
        String url = "jdbc:sqlite:C:/Users/Papech/Documents/NetBeansProjects/To_do_list/user.db";
        // Подключение к базе данных SQLite
        try (Connection connection = DriverManager.getConnection(url)) {
            // Создание SQL-запроса для удаления задачи по ее идентификатору (предполагается, что есть поле id)
            String sql = "DELETE FROM tasklist WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                // Установка параметра в SQL-запросе
                statement.setInt(1, id);
                // Выполнение SQL-запроса для удаления задачи из базы данных
                statement.executeUpdate();
                System.out.println("Задача успешно удалена.");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении задачи: " + e.getMessage());
        }
    }

    // Метод для редактирования задачи
    public void editTask(String newTaskName, String newDescription, int newPriority) {
        // Обновление полей задачи с новыми значениями
        this.taskName = newTaskName;
        this.description = newDescription;
        this.priority = newPriority;

        // Обновление задачи в базе данных
        updateTaskInDatabase();
    }

    // Метод для обновления задачи в базе данных
    private void updateTaskInDatabase() {
        // Подключение к базе данных SQLite
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/Papech/Documents/NetBeansProjects/To_do_list/user.db")) {
            // Создание SQL-запроса для обновления задачи
            String sql = "UPDATE tasklist SET task_name = ?, description = ?, priority = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                // Установка параметров в SQL-запросе
                statement.setString(1, taskName);
                statement.setString(2, description);
                statement.setInt(3, priority);
                statement.setInt(4, id);
                // Выполнение SQL-запроса для обновления задачи в базе данных
                statement.executeUpdate();
                System.out.println("Задачи успешно обновлено.");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка обновления задачи: " + e.getMessage());
        }
    }

    // Метод для отображения информации о задаче
    public void displayTask() {
        System.out.println("Название задачи: " + taskName);
        System.out.println("Описание: " + description);
        System.out.println("Дата создания: " + dateCreated);
        System.out.println("Дата выполнения: " + dateDone);
        System.out.println("Приоритет: " + priority);
        System.out.println("Статус: " + (isCompleted ? "Завершено" : "Не завершено"));
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
