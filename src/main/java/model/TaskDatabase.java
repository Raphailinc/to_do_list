package model;

import view.*;
import user.*;
import controller.*;
import java.time.LocalDate;
import java.sql.*;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Papech
 */
public class TaskDatabase {
    private TaskFormController taskFormController;
    private Connection connection;
    String url = "jdbc:sqlite:C:/Users/Papech/Documents/NetBeansProjects/To_do_list/user.db";

    boolean TaskDatabase() {
        try {
            // Инициализация подключения к базе данных
            connection = DriverManager.getConnection(url);
            System.out.println("Подключено");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(To_do_list.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    void insert() {
        try {
            /*Scanner scanner = new Scanner (System.in);*/
            System.out.print("Введите имя пользователя: ");
            String first_name = "Кемал";
            System.out.print("Введите фамилию пользователя: ");
            String last_name = "Джемхуров";
            System.out.print("Введите электронную почту пользователя: ");
            String e_mail = " kgf;gskdlh";

            String query = " INSERT INTO users (first_name,last_name,e_mail)\n"
                    + " VALUES ('" + first_name + "','" + last_name + "','" + e_mail + "');";

            try (Statement state = connection.createStatement()) {
                state.executeUpdate(query);

                System.out.println("Rows added");
            }
        } catch (SQLException ex) {
            Logger.getLogger(To_do_list.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addTask(Task task) {
        String sql = "INSERT INTO tasklist(task_name, description, date_create, date_done, priority, status) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, task.getTaskName());
            statement.setString(2, task.getDescription());
            statement.setDate(3, new java.sql.Date(task.getDateCreated().getTime()));
            statement.setDate(4, new java.sql.Date(task.getDateDone().getTime()));
            statement.setInt(5, task.getPriority());
            statement.setString(6, task.getStatus());

            statement.executeUpdate();
            System.out.println("Задача успешно добавлена.");
        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении задачи: " + e.getMessage());
        }
    }

    public void deleteTask(int taskId) {
        String sql = "DELETE FROM tasklist WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, taskId);

            statement.executeUpdate();
            System.out.println("Задача успешно удалена.");
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении задачи: " + e.getMessage());
        }
    }

    public void updateTask(Task task) {
        String sql = "UPDATE tasklist SET task_name = ?, description = ?, date_create = ?, date_done = ?, priority = ?, status = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, task.getTaskName());
            statement.setString(2, task.getDescription());
            statement.setDate(3, new java.sql.Date(task.getDateCreated().getTime()));
            statement.setDate(4, new java.sql.Date(task.getDateDone().getTime()));
            statement.setInt(5, task.getPriority());
            statement.setString(6, task.getStatus());
            statement.setInt(7, task.getId());

            statement.executeUpdate();
            System.out.println("Задача успешно обновлена.");
        } catch (SQLException e) {
            System.out.println("Ошибка при обновлении задачи: " + e.getMessage());
        }
    }

    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasklist";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String taskName = resultSet.getString("task_name");
                String description = resultSet.getString("description");
                LocalDate dateCreate = resultSet.getDate("date_create").toLocalDate();
                LocalDate dateDone = resultSet.getDate("date_done").toLocalDate();
                int priority = resultSet.getInt("priority");
                String status = resultSet.getString("status");

                Task task = new Task(id, taskName, description, Date.valueOf(dateCreate), Date.valueOf(dateDone), priority, status);
                tasks.add(task);
            }
        } catch (SQLException e) {
            System.out.println("Поиск ошибок в задачах: " + e.getMessage());
        }

        return tasks;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при закрытии соединения: " + e.getMessage());
        }
    }
    
    void inserted(String x) throws SQLException {
        String query = x;
        if (connection == null) {
            System.out.println("НЕТ соединения");
        }
        TaskDatabase();
        try (Statement state = connection.createStatement()) {
            state.executeUpdate(query);

            System.out.println("Строки добавлены");
            state.close();
            connection.close();
        }
    }
    
    public void saveTask(Task task) {
        User currentUser = User.getCurrentUser();
        if (currentUser != null) {
            String username = currentUser.getUsername();
            try {
                // Установка соединения с базой данных SQLite
                Connection connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/Papech/Desktop/Project/user.db");

                // Подготовка SQL-запроса для вставки данных задачи
                String query = "INSERT INTO tasks (username, title, description, priority, created_date, due_date, status) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(query);

                // Установка значений параметров SQL-запроса на основе задачи
                statement.setString(1, username);
                statement.setString(2, task.getTaskName());
                statement.setString(3, task.getDescription());
                statement.setInt(4, task.getPriority());
                statement.setString(5, task.getDateCreated().toString());
                statement.setString(6, task.getDateDone().toString());
                statement.setBoolean(7, task.isCompleted());

                // Выполнение SQL-запроса
                statement.executeUpdate();

                // Закрытие соединения и освобождение ресурсов
                statement.close();
                connection.close();

                // Опционально: обновление списка задач после сохранения
                taskFormController.refreshTaskList();
            } catch (SQLException e) {
                e.printStackTrace();
                // Обработка ошибок при сохранении задачи в базу данных
            }
        } else {
            // Обработка случая, когда текущий пользователь не определен
        }
    }
}
