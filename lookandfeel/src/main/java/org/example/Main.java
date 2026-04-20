package org.example;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
        String[] names = new String[looks.length];
        int i = 0;

        for (UIManager.LookAndFeelInfo look : looks) {
            names[i++] = look.getName();
            System.out.println(look.getName());
        }
        JFrame frame = new JFrame("Aplikacja Look and Feel");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JButton btn1 = new JButton("Przycisk 1");
        JButton btn2 = new JButton("Przycisk 2");
        JButton btn3 = new JButton("Przycisk 3");


        JComboBox<String> comb1 = new JComboBox<>(names);
        comb1.addActionListener(e -> {
            int index = comb1.getSelectedIndex();
            try {
                UIManager.setLookAndFeel(looks[index].getClassName());
                SwingUtilities.updateComponentTreeUI(frame);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        SwingUtilities.updateComponentTreeUI(frame);

        frame.add(btn1);
        frame.add(btn2);
        frame.add(btn3);
        frame.add(comb1);

        frame.setVisible(true);
    }
}
