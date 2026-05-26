import javax.swing.*;

public class cwiczeniepraktyczne extends JFrame {
    private JTextField inputWaga;
    private JLabel wagaLabel;
    private JTextField inputWzrost;
    private JLabel wzrostLabel;
    private JButton obliczButton;
    private JLabel wynikLabel;
    private JPanel panel;

    public cwiczeniepraktyczne(){
        setContentPane(panel);
        setTitle("BMI calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,400);
        setVisible(true);
        obliczButton.addActionListener(e -> {
            if(isNumeric(inputWaga.getText()) && isNumeric(inputWzrost.getText())){
                Double Waga = Double.parseDouble(inputWaga.getText());
                Double Wzrost = Double.parseDouble(inputWzrost.getText()) / 100;
                Double Wynik = Waga/(Wzrost*Wzrost);
                wynikLabel.setText("Wynik: "+Wynik);
            } else{
                wynikLabel.setText("Podałeś coś co nie jest liczbą!");
            }
        });
    }
    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
    public static void main(String[] args) {
        new cwiczeniepraktyczne();
    }
}
