package view;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortingUtils {
    // Метод для сортировки задач по дате создания
    public static void sortByCreationDate(List<Task> tasks) {
        Collections.sort(tasks, Comparator.comparing(Task::getDateCreated));
    }

    // Метод для сортировки задач по дате выполнения
    public static void sortByCompletionDate(List<Task> tasks) {
        Collections.sort(tasks, Comparator.comparing(Task::getDateDone));
    }

    // Метод для сортировки задач по приоритету
    public static void sortByPriority(List<Task> tasks) {
        Collections.sort(tasks, Comparator.comparing(Task::getPriority));
    }
}
