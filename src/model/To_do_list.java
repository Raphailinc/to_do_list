package model;
import view.*;

public class To_do_list {
	public static void main(String[] args) {
		String url = "jdbc:sqlite:database/example.db";
		
		 try {
	         Connection connection = DriverManager.getConnection(url);
	         // Делайте операции с базой данных...
	         connection.close();
	     } catch (SQLException e) {
	         e.printStackTrace();
	     }
		List<Task> tasks = new ArrayList<>();
		// ... добавление задач в список ...

		// Сортировка задач по дате создания
		SortingUtils.sortByCreationDate(tasks);

		// Сортировка задач по дате выполнения
		SortingUtils.sortByCompletionDate(tasks);

		// Сортировка задач по приоритету
		SortingUtils.sortByPriority(tasks);
		
		TaskDatabase taskDatabase = new TaskDatabase();

		// Создание новой задачи и добавление ее в базу данных
		Task task1 = new Task("Task 1", "Description 1", LocalDate.now(), null, 1, "Not done");
		taskDatabase.addTask(task1);

		// Удаление задачи с определенным идентификатором
		int taskId = 1;
		taskDatabase.deleteTask(taskId);

		// Обновление существующей задачи
		Task task2 = new Task(2, "Updated Task", "Updated Description", LocalDate.now(), null, 2, "Not done");
		taskDatabase.updateTask(task2);

		// Получение всех задач из базы данных
		List<Task> tasks = taskDatabase.getAllTasks();
		for (Task task : tasks) {
		    System.out.println(task);
		}

		// Закрытие соединения с базой данных
		taskDatabase.closeConnection();
	}

}
