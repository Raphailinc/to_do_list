package model;

import controller.*;
import view.*;
import user.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 *
 * @author Papech
 */
public class TodoListApp {
    private TaskListController taskListController;
    private TaskFormController taskFormController;
    private User currentUser;

    private JFrame mainFrame;
    private JList<Task> taskList;
    private List<Task> TaskList;
    private JButton addButton;
    private JButton editButton;
    private JButton sortByCreationDateButton;
    private JButton sortByDueDateButton;
    private JButton sortByPriorityButton;
    private JButton loginButton;
    private JButton registerButton;
    TaskListView taskListView = new TaskListView(TaskList);
    TaskDatabase taskDatabase = new TaskDatabase();
    TaskFormView taskFormView = new TaskFormView();
    private Task task;
    
    private Map<String, User> users;

    public TodoListApp() {
        taskListController = new TaskListController(taskListView, TaskList);
        taskFormController = new TaskFormController(taskFormView, taskDatabase);
        users = new HashMap<>();

        initializeUI();
        refreshTaskList();
    }

    private void initializeUI() {
        mainFrame = new JFrame("To-do list");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1000, 600);
        mainFrame.setLayout(new BorderLayout());

        taskList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(taskList);
        mainFrame.add(scrollPane, BorderLayout.CENTER);

        addButton = new JButton("Добавить задачу");
        editButton = new JButton("Редактирование задачи");
        sortByCreationDateButton = new JButton("Сортировать по дате создания");
        sortByDueDateButton = new JButton("Сортировать по сроку исполнения");
        sortByPriorityButton = new JButton("Сортировать по приоритету");
        loginButton = new JButton("Логин");
        registerButton = new JButton("Зарегистрироваться");

        JPanel buttonPanel1 = new JPanel();
        JPanel buttonPanel2 = new JPanel();
        JPanel buttonPanel3 = new JPanel();
        buttonPanel2.setLayout(new FlowLayout());
        buttonPanel1.add(addButton);
        buttonPanel1.add(editButton);
        buttonPanel1.add(sortByCreationDateButton);
        buttonPanel1.add(sortByDueDateButton);
        buttonPanel1.add(sortByPriorityButton);
        buttonPanel3.add(loginButton);
        buttonPanel3.add(registerButton);

        mainFrame.add(buttonPanel1, BorderLayout.SOUTH);
        mainFrame.add(buttonPanel3, BorderLayout.NORTH);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentUser != null) {
                    taskFormView.createTask();
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "Пожалуйста, войдите или зарегистрируйтесь, чтобы добавлять задания.");
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Task selectedTask = taskList.getSelectedValue();
                if (selectedTask != null) {
                    openTaskForm(selectedTask);
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "Пожалуйста, выберите задачу для редактирования.");
                }
            }
        });

        sortByCreationDateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                taskListController.sortTasksByDateCreated();
                refreshTaskList();
            }
        });

        sortByDueDateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                taskListController.sortTasksByDueDate();
                refreshTaskList();
            }
        });

        sortByPriorityButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                taskListController.sortTasksByPriority();
                refreshTaskList();
            }
        });
        
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showLoginForm();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showRegistrationForm();
            }
        });

        mainFrame.setVisible(true);
    }

    private void refreshTaskList() {
        DefaultListModel<Task> model = new DefaultListModel<>();
        for (Task task : TaskList) {
            model.addElement(task);
        }
        taskList.setModel(model);
    }

    private void openTaskForm(Task task) {
        TaskFormView taskFormView = new TaskFormView(mainFrame, task, currentUser);
        taskFormController.updateTask(taskFormView, currentUser);
            if (currentUser != null) {
                taskListController.saveTask(task);
            }
            refreshTaskList();
    }
    
    private void showLoginForm() {
        String username = JOptionPane.showInputDialog(mainFrame, "Имя пользователя:");
        String password = JOptionPane.showInputDialog(mainFrame, "Пароль:");
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            JOptionPane.showMessageDialog(mainFrame, "Вход в систему успешный.");
        } else {
            JOptionPane.showMessageDialog(mainFrame, "Неверное имя пользователя или пароль.");
        }
    }

    private void showRegistrationForm() {
        String username = JOptionPane.showInputDialog(mainFrame, "Имя пользователя:");
        String password = JOptionPane.showInputDialog(mainFrame, "Пароль:");
        String email = JOptionPane.showInputDialog(mainFrame, "Электронная почта:");
        User user = new User(username, password, email);
        users.put(username, user);
        JOptionPane.showMessageDialog(mainFrame, "Регистрация прошла успешно.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TodoListApp();
            }
        });
    }
}
