import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileOutputStream;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        String[] kolumny = {"ID", "Nazwa Produktu", "Ilość", "Cena"};
        Object[][] dane = {
                {1, "Kajzerka", 10, 0.3},
                {2, "Pluszowy pan W", 5, 25.0},
                {3, "Gruszka", 7, 4.0},
                {4, "Pomarańcza", 12, 3.5}
        };

        DefaultTableModel model = new DefaultTableModel(dane, kolumny);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton btnPDF = new JButton("Generuj Raport PDF");
        btnPDF.addActionListener((ActionEvent e) -> {
            try {
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream("raport.pdf"));
                document.open();

                PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
                for (int i = 0; i < table.getColumnCount(); i++) {
                    pdfTable.addCell(new PdfPCell(new Phrase(table.getColumnName(i))));
                }
                for (int row = 0; row < table.getRowCount(); row++) {
                    for (int col = 0; col < table.getColumnCount(); col++) {
                        pdfTable.addCell(table.getValueAt(row, col).toString());
                    }
                }

                document.add(pdfTable);
                document.close();
                JOptionPane.showMessageDialog(frame, "PDF wygenerowany!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(btnPDF, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}