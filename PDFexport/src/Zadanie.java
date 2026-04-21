import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.swing.*;

/*
Za co odpowiada klasa JFileChooser w kontekście tego zadania? Otwiera okno dialogowe wyboru pliku.
Dlaczego używamy metody toString() przy pobieraniu wartości z modelu tabeli? Bo przyjmuje string?
Co się stanie, jeśli zapomnimy wywołać metodę document.close()? Nigdy sie plik nie skonczy i moze po prostu noie
 */
/*public void exportToPDF(JTable table) {
    JFileChooser chooser = new JFileChooser();
    int state = chooser.showSaveDialog(null);

    if (state == JFileChooser.APPROVE_OPTION) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(chooser.getSelectedFile() + ".pdf"));
            document.open();

            // Tworzymy tabelę PDF o takiej samej liczbie kolumn co JTable
            PdfPTable pdfTable = new PdfPTable(table.getColumnCount());

            // 1. Dodawanie nagłówków
            for (int i = 0; i < table.getColumnCount(); i++) {
                pdfTable.addCell(table.getColumnName(i));
            }

            // 2. Dodawanie danych z wierszy
            for (int rows = 0; rows < table.getRowCount(); rows++) {
                for (int cols = 0; cols < table.getColumnCount(); cols++) {
                    pdfTable.addCell(table.getModel().getValueAt(rows, cols).toString());
                }
            }

            document.add(pdfTable);
            JOptionPane.showMessageDialog(null, "Eksport zakończony sukcesem!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
}*/
