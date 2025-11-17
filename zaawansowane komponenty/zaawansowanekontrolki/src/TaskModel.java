import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TaskModel extends AbstractTableModel {
    private List<Task> tasks;
    private final String[] columnNames = {"Nazwa", "Status", "Priorytet"};

    public TaskModel() {
        tasks = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return tasks.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Task task = tasks.get(rowIndex);
        switch (columnIndex) {
            case 0: return task.getName();
            case 1: return task.isCompleted();
            case 2: return task.getPriority();
            default: return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Task task = tasks.get(rowIndex);
        switch (columnIndex) {
            case 1:
                task.setCompleted((Boolean) aValue);
                break;
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public void addTask(Task task) {
        tasks.add(task);
        fireTableRowsInserted(tasks.size() - 1, tasks.size() - 1);
    }

    public void removeTask(int rowIndex) {
        tasks.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public Task getTask(int rowIndex) {
        return tasks.get(rowIndex);
    }
}
