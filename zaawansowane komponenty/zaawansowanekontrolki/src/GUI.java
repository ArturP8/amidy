import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private JFrame frame;
    private TaskModel taskTableModel;
    private JTable taskTable;

    public GUI() {
        frame = new JFrame("To-Do List Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(200);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

        JButton addButton = new JButton("Dodaj Zadanie");
        JButton removeButton = new JButton("Usuń Wybrane");

        controlPanel.add(addButton);
        controlPanel.add(removeButton);

        taskTableModel = new TaskModel();
        taskTable = new JTable(taskTableModel);

        taskTable.getColumnModel().getColumn(1).setCellRenderer(new Status());
        taskTable.getColumnModel().getColumn(2).setCellRenderer(new PriorityRenderer());

        JScrollPane scrollPane = new JScrollPane(taskTable);

        splitPane.setLeftComponent(controlPanel);
        splitPane.setRightComponent(scrollPane);

        frame.add(splitPane);
        frame.setVisible(true);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(frame, "Wprowadź nazwę zadania:");
                if (name != null && !name.isEmpty()) {
                    taskTableModel.addTask(new Task(name, false, "Średni"));
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = taskTable.getSelectedRow();
                if (selectedRow != -1) {
                    taskTableModel.removeTask(selectedRow);
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI();
            }
        });
    }
}
