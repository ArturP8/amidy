package org.example;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private JTextField itemInput;
    private JButton addbutton;
    private JButton deletebutton;
    private JList itemlist;
    private DefaultListModel listModel;
    public Main(){

        setTitle("Lista Zakupów");
        setSize(400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        listModel = new DefaultListModel<>();

        itemInput = new JTextField();
        addbutton = new JButton("Dodaj");
        deletebutton = new JButton("Usuń");
        itemlist = new JList<>(listModel);

        JScrollPane scrollPane =new JScrollPane(itemlist);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(itemInput, BorderLayout.CENTER);
        topPanel.add(addbutton, BorderLayout.EAST);

        add(topPanel,BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(deletebutton, BorderLayout.SOUTH);

        addbutton.addActionListener(e ->{
            String text = itemInput.getText().trim();
            if (!text.isEmpty()){
                listModel.addElement(text);
                itemInput.setText("");
            }else {
                JOptionPane.showMessageDialog(this, "Pole nie może być puste");
            }
        });
        deletebutton.addActionListener(e ->{
            int selectIndex = itemlist.getSelectedIndex();
            if (selectIndex != 1){
                listModel.remove((selectIndex));
            }else {
               JOptionPane.showMessageDialog(this,"wybierz element do usunięcia");
            }

        });
    }
public static void main(String[] args){
        SwingUtilities.invokeLater(()-> {
            new Main().setVisible(true);


});
}
}
