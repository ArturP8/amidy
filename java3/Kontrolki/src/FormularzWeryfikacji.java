import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FormularzWeryfikacji extends JFrame {

    private JTextField poleWieku;
    private JCheckBox checkRegulamin;
    private JButton przyciskZatwierdz;

    public FormularzWeryfikacji() {
        super("Formularz Weryfikacji");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new FlowLayout());

        JLabel etykietaWiek = new JLabel("Podaj swój wiek:");
        poleWieku = new JTextField(10);
        checkRegulamin = new JCheckBox("Akceptuję regulamin");
        przyciskZatwierdz = new JButton("Zatwierdź");

        przyciskZatwierdz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tekstWiek = poleWieku.getText();
                try {
                    int wiek = Integer.parseInt(tekstWiek);
                    boolean regulaminZaznaczony = checkRegulamin.isSelected();

                    if (wiek >= 18 && regulaminZaznaczony) {
                        JOptionPane.showMessageDialog(null, "Rejestracja pomyślna!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Wymagany wiek 18+ i akceptacja regulaminu!");
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Podaj prawidłowy wiek (liczba całkowita)!");
                }
            }
        });
        add(etykietaWiek);
        add(poleWieku);
        add(checkRegulamin);
        add(przyciskZatwierdz);

        setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormularzWeryfikacji());
    }
}
