package model;

import view.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

/**
 *
 * @author Papech
 */
public class To_do_list {

    public static void main(String... args) {
        TodoListApp app = new TodoListApp();
//        start_window p = new start_window();
//        p.setVisible(true);

        TaskDatabase program = new TaskDatabase();
        if (program.TaskDatabase()) {
            program.insert();
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
        Date now = new Date(System.currentTimeMillis());
        Task task1 = new Task(1, "Задача 1", "Описание 1", now, null, 1, "Не выполнено");
        taskDatabase.addTask(task1);

        // Удаление задачи с определенным идентификатором
        int taskId = 1;
        taskDatabase.deleteTask(taskId);

        // Обновление существующей задачи
        Task task2 = new Task(2, "Обновленное задачи", "Обновленное описания", now, null, 2, "Не выполнено");
        taskDatabase.updateTask(task2);

        // Получение всех задач из базы данных
        for (Task task : tasks) {
            System.out.println(task);
        }

        // Закрытие соединения с базой данных
        taskDatabase.closeConnection();
    }
}
