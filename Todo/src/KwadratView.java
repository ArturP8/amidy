import javax.swing.*;
import java.awt.event.ActionListener;

public class KwadratView extends JFrame {
    private JTextField liczbaField = new JTextField(10);
    private JLabel wynikLabel = new JLabel("Wynik: 0");
    private JButton obliczButton = new JButton("Oblicz");

    public KwadratView() {
        super("Kalkulator Kwadratu");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 150);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Podaj liczbę:"));
        panel.add(liczbaField);
        panel.add(obliczButton);
        panel.add(wynikLabel);
        this.add(panel);
    }

    public String getLiczbaText() {
        return liczbaField.getText();
    }

    public void setWynik(int wynik) {
        wynikLabel.setText("Wynik: " + wynik);
    }

    public void addObliczListener(ActionListener listenForObliczButton) {
        obliczButton.addActionListener(listenForObliczButton);
    }
}
