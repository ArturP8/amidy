import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class Status extends JCheckBox implements TableCellRenderer {
    public Status() {
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setSelected((Boolean) value);
        return this;
    }
}
