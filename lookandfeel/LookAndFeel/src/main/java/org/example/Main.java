package org.example;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Dynamiczna zmiana LaF");
        frame.setSize(400,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JButton b1 = new JButton("Przycisk 1");
        JButton b2 = new JButton("Przycisk 2");

        String[] styles = {"Metal","Nimbus","CDE/Motif"};
        JComboBox<String> combo = new JComboBox<>(styles);

        combo.addActionListener(e -> {
            String selected = (String) combo.getSelectedItem();
            try {
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if (info.getName().equals(selected)) {
                        UIManager.setLookAndFeel(info.getClassName());
                        SwingUtilities.updateComponentTreeUI(frame);
                        break;
                    }
                }
            } catch (Exception ex) {}
        });

        frame.add(combo);
        frame.add(b1);
        frame.add(b2);

        frame.setVisible(true);
    }
}