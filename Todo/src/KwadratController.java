import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KwadratController {
    private KwadratModel model;
    private KwadratView view;

    public KwadratController(KwadratModel model, KwadratView view) {
        this.model = model;
        this.view = view;

        this.view.addObliczListener(new ObliczListener());
    }

    class ObliczListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String liczbaText = view.getLiczbaText();
            } finally {

            }
        }
    }
}
