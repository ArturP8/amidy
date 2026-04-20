import javax.swing.*;

public class SimpleTest extends JFrame {
    private JPanel mainPanel; // To pole musi mieć 'field name' w Designerze
    private JButton actionButton;
    private JLabel myLabel;

    public SimpleTest() {
        setContentPane(mainPanel);
        setTitle("Moja Aplikacja");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
            actionButton.addActionListener(e -> myLabel.setText("Witaj w Swing!"));
    }

    public static void main(String[] args) {
        new SimpleTest();
    }
}