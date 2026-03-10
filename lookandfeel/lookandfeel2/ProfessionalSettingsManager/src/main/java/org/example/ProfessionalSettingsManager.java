package org.example;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
public class ProfessionalSettingsManager {

    private static JFrame frame;

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(new FlatLightLaf());

        frame = new JFrame("Professional Settings Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);
        frame.setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu view = new JMenu("Widok");
        JMenuItem light = new JMenuItem("Light Mode");
        JMenuItem dark = new JMenuItem("Dark Mode");

        light.addActionListener(e -> {
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
                SwingUtilities.updateComponentTreeUI(frame);
            } catch (Exception ex) {}
        });

        dark.addActionListener(e -> {
            try {
                UIManager.setLookAndFeel(new FlatDarkLaf());
                SwingUtilities.updateComponentTreeUI(frame);
            } catch (Exception ex) {}
        });

        view.add(light);
        view.add(dark);
        menuBar.add(view);
        frame.setJMenuBar(menuBar);

        JPanel top = new JPanel();
        JTextField textField = new JTextField(15);
        JButton button = new JButton("Button");
        JCheckBox checkBox = new JCheckBox("Check");

        top.add(textField);
        top.add(button);
        top.add(checkBox);

        String[] columns = {"ID","Name","Score"};
        Object[][] data = {
                {"1","Anna","85"},
                {"2","Jan","90"},
                {"3","Ola","78"}
        };

        JTable table = new JTable(new DefaultTableModel(data, columns));
        JScrollPane scrollPane = new JScrollPane(table);

        frame.add(top, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}