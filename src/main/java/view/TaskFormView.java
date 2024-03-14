package view;

import user.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 *
 * @author Papech
 */
public class TaskFormView {
    private JFrame mainFrame;
    private Task task;
    private static User currentUser;
    private Scanner scanner;

    public TaskFormView() {
        scanner = new Scanner(System.in);
    }
    
    public TaskFormView(JFrame mainFrame, Task task, User currentUser) {
        this.mainFrame = mainFrame;
        this.task = task;
        this.currentUser = currentUser;
    }

    public Task createTask() {
        
        System.out.println("Создать новую задачу:");
        System.out.print("Введите название задачи: ");
        String name = scanner.nextLine();
        System.out.print("Введите описание задачи: ");
        String description = scanner.nextLine();
        System.out.print("Введите дату выполнения задачи (гггг-мм-дд): ");
        String dueDateStr = scanner.nextLine();
        System.out.print("Введите приоритет задачи: ");
        int priority = scanner.nextInt();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("гггг-мм-дд");
        Date dueDate = null;
        try {
            dueDate = dateFormat.parse(dueDateStr);
        } catch (ParseException e) {
            System.out.println("Неверный формат даты. Не удалось создать задачу.");
        }

        return new Task(name, description, dueDate, priority);
    }

    public Task updateTask(Task task) {
        System.out.println("Обновление задачи:");
        System.out.print("Введите имя задачи (текущая: " + task.getTaskName() + "): ");
        String name = scanner.nextLine();
        System.out.print("Введите описание задачи (текущая: " + task.getDescription() + "): ");
        String description = scanner.nextLine();
        System.out.print("Введите дату выполнения задачи (текущая: " + task.getDateDone() + "): ");
        String dueDateStr = scanner.nextLine();
        System.out.print("Введите приоритет задачи (текущая: " + task.getPriority() + "): ");
        int priority = scanner.nextInt();

        SimpleDateFormat dateFormat = new SimpleDateFormat();
        Date dueDate = null;
        try {
            dueDate = dateFormat.parse(dueDateStr);
        } catch (ParseException e) {
            System.out.println("Неверный формат даты. Обновление задачи не удалось.");
        }
        
        task.setTaskName(name);
        task.setDescription(description);
        task.setDateDone(dueDate);
        task.setPriority(priority);

        return task;
    }

    public void displaySuccessMessage(String message) {
        System.out.println("Успех: " + message);
    }

    public Task updateTask(TaskFormView task) {
        throw new UnsupportedOperationException("Пока не поддерживается.");
    }
}
