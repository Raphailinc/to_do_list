/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author oam
 */
public class MyModel extends DefaultTableModel {

    private final ArrayList<Task> data = new ArrayList<>();
    String[] colNames = {" № ", " Название задачи ", "Описание "," Дата созадние ","Крайний срок выполнение ","Приоритет","Статус"};
    Class[] colClasses = {int.class, String.class, String.class, String.class, String.class, String.class, String.class};

    public void addTask(Task task) {
        data.add(task);
        fireTableDataChanged();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return colClasses[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }

    @Override
    public Object getValueAt(int row, int column) {
        if (data == null || data.isEmpty()) {
            return null;
        }
       Task task = data.get(row);
        switch (column) {
            case 0:
                return task.task_id();
            case 1:
                return task.getname();
            case 2:
                task.description();
            case 3:
                task.date_create();
            case 4: 
                task.date_done();
            case 5:
                task.priority();
            default:
                return task.status();
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public int getRowCount() {
        if (data == null) {
            return 0;
        }
        return data.size();
    }

}
