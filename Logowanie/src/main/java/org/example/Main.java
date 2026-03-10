package org.example;
import javax.swing.*;
import java.awt.*;
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginWindow());
    }
}
class LoginWindow extends JFrame {

    private JTextField loginField;
    private JPasswordField passwordField;

    LoginWindow() {
        setTitle("Logowanie");
        setSize(300,150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,2));

        add(new JLabel("Login"));
        loginField = new JTextField();
        add(loginField);

        add(new JLabel("Hasło"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton loginButton = new JButton("Zaloguj");
        add(new JLabel());
        add(loginButton);
        loginButton.addActionListener(e -> login());
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void login() {
        String login = loginField.getText();
        String pass = new String(passwordField.getPassword());

        if(login.equals("admin") && pass.equals("admin123")) {
            new MainWindow("admin");
            dispose();
        } else if(login.equals("user") && pass.equals("user123")) {
            new MainWindow("user");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,"Błędne dane logowania");
        }
    }
}

class MainWindow extends JFrame {

    MainWindow(String role) {
        setTitle("System Sklepu");
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();
        JMenu sprzedaz = new JMenu("Sprzedaż");
        JMenu raporty = new JMenu("Raporty");
        JMenu users = new JMenu("Zarządzanie Użytkownikami");

        if(role.equals("user")) {
            raporty.setEnabled(false);
        }
        menuBar.add(sprzedaz);
        menuBar.add(raporty);

        if(role.equals("admin")) {
            menuBar.add(users);
        }
        setJMenuBar(menuBar);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}