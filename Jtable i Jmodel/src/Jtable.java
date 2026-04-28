import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Jtable {
    private JPanel mainPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JLabel nazwaLabel;
    private JLabel iloscLabel;
    private JTextField nazwaInput;
    private JTextField iloscInput;
    private JTextField kategoriaInput;
    private JButton addButton;
    private JButton deleteButton;
    private JTable tabela;
    private JScrollPane tabelaPane;

    private ItemTableModel tableModel;
    private List<Item> itemList;
    private int currentId = 1;

    public Jtable() {
        itemList = new ArrayList<>();
        tableModel = new ItemTableModel(itemList);
        tabela.setModel(tableModel);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nazwaInput.getText();
                String quantityStr = iloscInput.getText();
                String category = kategoriaInput.getText();

                try {
                    Integer.parseInt(quantityStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Pole 'Ilość' musi być liczbą!", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Item newItem = new Item(currentId++, name, quantityStr, category);
                tableModel.addItem(newItem);

                nazwaInput.setText("");
                iloscInput.setText("");
                kategoriaInput.setText("");
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tabela.getSelectedRow();
                if (selectedRow != -1) {
                    tableModel.removeItem(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "Wybierz przedmiot z tabeli do usunięcia");
                }
            }
        });
    }
    class ItemTableModel extends AbstractTableModel {
        private final String[] columnNames = {"ID", "Nazwa", "Ilość", "Kategoria"};
        private List<Item> items;

        public ItemTableModel(List<Item> items) { this.items = items; }

        public void addItem(Item item) {
            items.add(item);
            fireTableRowsInserted(items.size() - 1, items.size() - 1);
        }

        public void removeItem(int rowIndex) {
            items.remove(rowIndex);
            fireTableRowsDeleted(rowIndex, rowIndex);
        }

        @Override public int getRowCount() { return items.size(); }
        @Override public int getColumnCount() { return columnNames.length; }
        @Override public String getColumnName(int col) { return columnNames[col]; }
        @Override public Object getValueAt(int row, int col) {
            Item item = items.get(row);
            return switch (col) {
                case 0 -> item.getId();
                case 1 -> item.getName();
                case 2 -> item.getQuantity();
                case 3 -> item.getCategory();
                default -> null;
            };
        }
    }
    class Item {
        int id;
        String name;
        String quantity;
        String category;

        public Item(int id, String name, String quantity, String category) {
            this.id = id;
            this.name = name;
            this.quantity = quantity;
            this.category = category;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public String getQuantity() { return quantity; }
        public String getCategory() { return category; }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Jtable");
        frame.setContentPane(new Jtable().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
