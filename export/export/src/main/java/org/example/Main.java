package org.example;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileOutputStream;
import java.time.LocalDate;

public class Main extends JFrame {
    private final JTable table;

    public Main() {
        setTitle("System Raportowania Magazynowego");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nazwa Produktu");
        model.addColumn("Ilość");
        model.addColumn("Cena");
        model.addRow(new Object[]{1, "Laptop", 10, 1500});
        model.addRow(new Object[]{2, "Mysz", 20, 100});
        model.addRow(new Object[]{3, "Klawiatura", 20, 250});

        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton btn = new JButton("Generuj Raport PDF");
        btn.addActionListener(this::exportToPDF);
        add(btn, BorderLayout.SOUTH);
    }

    static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new Main().setVisible(true));
    }
    private void exportToPDF(ActionEvent e) {

        JFileChooser chooser = new JFileChooser();
        int state = chooser.showSaveDialog(this);

        if (state == JFileChooser.APPROVE_OPTION) {
            Document document = new Document();
            try {
                PdfWriter.getInstance(document,
                        new FileOutputStream(chooser.getSelectedFile() + ".pdf"));
                document.open();

                Paragraph header = new Paragraph(
                        "Raport magazynowy Data wygenerowania: " + LocalDate.now()
                );
                header.setAlignment(Element.ALIGN_CENTER);
                document.add(header);
                document.add(new Paragraph(" "));
                PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
                pdfTable.setWidthPercentage(100);
                Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);

                for (int i = 0; i < table.getColumnCount(); i++) {
                    PdfPCell cell = new PdfPCell(
                            new Phrase(table.getColumnName(i), font));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfTable.addCell(cell);
                }
                for (int rows = 0; rows < table.getRowCount(); rows++) {
                    for (int cols = 0; cols < table.getColumnCount(); cols++) {
                        PdfPCell cell = new PdfPCell(
                                new Phrase(
                                        table.getModel()
                                                .getValueAt(rows, cols)
                                                .toString(),
                                        font));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        pdfTable.addCell(cell);
                    }
                }
                document.add(pdfTable);
                JOptionPane.showMessageDialog(this,
                        "Raport wygenerowany pomyślnie");

            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                document.close();
            }
        }
    }
}