//import java.sql.*;
//import java.lang.ClassNotFoundException;
//public class Main {
//
//    private static final String URL = "jdbc:mysql://localhost:3306/sklep";
//    private static final String USER = "root";
//    private static final String PASSWORD = "maslo"; // Zmień na swoje hasło
//
//    public static void main(String[] args) {
//
//        // 1. Wstawianie danych
//        String sqlInsert = "INSERT INTO produkty (nazwa, cena) VALUES ('Kawa', 19.99)";
//        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
//             Statement stmt = conn.createStatement()) {
//
//            int rowsAffected = stmt.executeUpdate(sqlInsert);
//            System.out.println("Wstawiono " + rowsAffected + " produkt(y).");
//
//            // 2. Odczyt danych
//            czytajProdukty(conn);
//
//        } catch (SQLException e) {
//            System.err.println("Błąd bazy danych: " + e.getMessage());
//        }
//    }
//
//    private static void czytajProdukty(Connection conn) throws SQLException {
//        String sqlSelect = "SELECT id, nazwa, cena FROM produkty";
//        try (Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(sqlSelect)) {
//
//            System.out.println("\n--- Lista Produktów ---");
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String nazwa = rs.getString("nazwa");
//                double cena = rs.getDouble("cena");
//                System.out.printf("ID: %d | Nazwa: %s | Cena: %.2f%n", id, nazwa, cena);
//            }
//            System.out.println("-----------------------");
//        }
//    }
//}
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class Main extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    private JTextField tytulField;
    private JTextField autorField;
    private JTextField rokField;

    private static final String URL = "jdbc:mysql://localhost:3306/ksiegarnia?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Twoje hasło

    public Main() {
        setTitle("Księgozbiór - Swing + MySQL");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // --- Model JTable ---
        model = new DefaultTableModel(new String[]{"ID", "Tytuł", "Autor", "Rok"}, 0);
        table = new JTable(model);

        add(new JScrollPane(table), BorderLayout.CENTER);

        // --- Panel z polami i przyciskami ---
        JPanel panel = new JPanel(new GridLayout(2, 4, 5, 5));

        tytulField = new JTextField();
        autorField = new JTextField();
        rokField = new JTextField();

        panel.add(new JLabel("Tytuł:"));
        panel.add(tytulField);
        panel.add(new JLabel("Autor:"));
        panel.add(autorField);
        panel.add(new JLabel("Rok wydania:"));
        panel.add(rokField);

        JButton addBtn = new JButton("Dodaj");
        JButton deleteBtn = new JButton("Usuń");
        JButton updateBtn = new JButton("Aktualizuj");

        panel.add(addBtn);
        panel.add(deleteBtn);
        panel.add(updateBtn);

        add(panel, BorderLayout.SOUTH);

        // --- Obsługa zdarzeń ---

        addBtn.addActionListener(e -> dodajKsiazke());
        deleteBtn.addActionListener(e -> usunKsiazke());
        updateBtn.addActionListener(e -> aktualizujKsiazke());

        // Wczytanie danych przy starcie
        odswiezTabele();
    }

    // --- Połączenie z DB ---
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // --- Odświeżanie tabeli ---
    private void odswiezTabele() {
        model.setRowCount(0); // Wyczyść

        String sql = "SELECT * FROM ksiazki";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("tytul"),
                        rs.getString("autor"),
                        rs.getInt("rok_wydania")
                });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Błąd odświeżania: " + e.getMessage());
        }
    }

    // --- Dodawanie ---
    private void dodajKsiazke() {
        String sql = "INSERT INTO ksiazki (tytul, autor, rok_wydania) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tytulField.getText());
            ps.setString(2, autorField.getText());
            ps.setInt(3, Integer.parseInt(rokField.getText()));

            ps.executeUpdate();
            odswiezTabele();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Błąd dodawania: " + e.getMessage());
        }
    }

    // --- Usuwanie ---
    private void usunKsiazke() {
        int row = table.getSelectedRow();
        if (row == -1) return;

        int id = (int) table.getValueAt(row, 0);

        String sql = "DELETE FROM ksiazki WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            odswiezTabele();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Błąd usuwania: " + e.getMessage());
        }
    }

    // --- Aktualizowanie ---
    private void aktualizujKsiazke() {
        int row = table.getSelectedRow();
        if (row == -1) return;

        int id = (int) table.getValueAt(row, 0);

        String sql = "UPDATE ksiazki SET tytul=?, autor=?, rok_wydania=? WHERE id=?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tytulField.getText());
            ps.setString(2, autorField.getText());
            ps.setInt(3, Integer.parseInt(rokField.getText()));
            ps.setInt(4, id);

            ps.executeUpdate();
            odswiezTabele();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Błąd aktualizacji: " + e.getMessage());
        }
    }

    // --- Start ---
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
