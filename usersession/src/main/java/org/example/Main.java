package org.example;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        String[] user = {"user","user123"};
        String[] admin = {"admin","admin123"};
        JTextField loginField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        Object[] message = {
                "Login:", loginField,
                "Hasło:", passwordField
        };

        int option = JOptionPane.showConfirmDialog(
                null, message, "Logowanie", JOptionPane.OK_CANCEL_OPTION
        );

        if (option == JOptionPane.OK_OPTION) {
            String login = loginField.getText();
            String password = new String(passwordField.getPassword());

            String role = null;
            if (login.equals(admin[0]) && password.equals(admin[1])) {
                role = "Admin";
            } else if (login.equals(user[0]) && password.equals(user[1])) {
                role = "User";
            } else {
                JOptionPane.showMessageDialog(null, "Błędny login lub hasło");
                System.exit(0);
            }
            frame.setTitle("Aplikacja - " + role);
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            JMenuBar menuBar = new JMenuBar();
            JMenu menuSprzedaz = new JMenu("Sprzedaż");
            JMenu menuRaporty = new JMenu("Raporty");
            JMenu menuZarzadzanie = new JMenu("Zarządzanie Użytkownikami");

            menuBar.add(menuSprzedaz);
            menuBar.add(menuRaporty);
            menuBar.add(menuZarzadzanie);
            if (role.equals("User")) {
                menuRaporty.setEnabled(false);
                menuZarzadzanie.setVisible(false);
            }

            frame.setJMenuBar(menuBar);

            JLabel statusBar = new JLabel("Zalogowano jako: " + login + " | Rola: " + role);
            statusBar.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
            frame.add(statusBar, BorderLayout.SOUTH);

            frame.setVisible(true);
        } else {
            System.exit(0);
        }
    }
}