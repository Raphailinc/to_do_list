package model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDatabase {
    private Connection connection;

    public TaskDatabase() {
        // Инициализация подключения к базе данных
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:todo.db");
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }

    public void addTask(Task task) {
        String sql = "INSERT INTO tasklist(task_name, description, date_create, date_done, priority, status) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, task.getName());
            statement.setString(2, task.getDescription());
            statement.setDate(3, Date.valueOf(task.getDateCreated()));
            statement.setDate(4, Date.valueOf(task.getDateDone()));
            statement.setInt(5, task.getPriority());
            statement.setString(6, task.getStatus());

            statement.executeUpdate();
            System.out.println("Task added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding task: " + e.getMessage());
        }
    }

    public void deleteTask(int taskId) {
        String sql = "DELETE FROM tasklist WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, taskId);

            statement.executeUpdate();
            System.out.println("Task deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting task: " + e.getMessage());
        }
    }

    public void updateTask(Task task) {
        String sql = "UPDATE tasklist SET task_name = ?, description = ?, date_create = ?, date_done = ?, priority = ?, status = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, task.getName());
            statement.setString(2, task.getDescription());
            statement.setDate(3, Date.valueOf(task.getDateCreated()));
            statement.setDate(4, Date.valueOf(task.getDateDone()));
            statement.setInt(5, task.getPriority());
            statement.setString(6, task.getStatus());
            statement.setInt(7, task.getId());

            statement.executeUpdate();
            System.out.println("Task updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating task: " + e.getMessage());
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

                Task task = new Task(id, taskName, description, dateCreate, dateDone, priority, status);
                tasks.add(task);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving tasks: " + e.getMessage());
        }

        return tasks;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }
}
