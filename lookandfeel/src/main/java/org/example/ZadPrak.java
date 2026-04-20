package org.example;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ZadPrak {

    private JFrame frame;

    public ZadPrak() {
        initialize();
    }

    private void initialize() {

        frame = new JFrame("Professional Settings Manager");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu viewMenu = new JMenu("Widok");

        JMenuItem lightMode = new JMenuItem("Light Mode");
        JMenuItem darkMode = new JMenuItem("Dark Mode");

        lightMode.addActionListener(e -> switchTheme(new FlatLightLaf()));
        darkMode.addActionListener(e -> switchTheme(new FlatDarkLaf()));

        viewMenu.add(lightMode);
        viewMenu.add(darkMode);

        menuBar.add(viewMenu);
        frame.setJMenuBar(menuBar);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10,10));

        JPanel topPanel = new JPanel();

        JTextField textField = new JTextField(15);
        JButton button = new JButton("Zatwierdź");
        JCheckBox checkBox = new JCheckBox("Aktywuj opcję");

        topPanel.add(textField);
        topPanel.add(button);
        topPanel.add(checkBox);

        panel.add(topPanel, BorderLayout.NORTH);

        String[] columns = {"ID", "Imię", "Stanowisko"};

        Object[][] data = {
                {"1", "Anna", "Manager"},
                {"2", "Jan", "Developer"},
                {"3", "Katarzyna", "Designer"},
                {"4", "Piotr", "Tester"}
        };

        JTable table = new JTable(new DefaultTableModel(data, columns));
        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(scrollPane, BorderLayout.CENTER);

        frame.add(panel);

        frame.setVisible(true);
    }

    private void switchTheme(LookAndFeel laf) {
        try {
            UIManager.setLookAndFeel(laf);
            SwingUtilities.updateComponentTreeUI(frame);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new ZadPrak());
    }
}