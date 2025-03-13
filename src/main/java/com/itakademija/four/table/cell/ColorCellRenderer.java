package com.itakademija.four.table.cell;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ColorCellRenderer implements TableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Color color = (Color) value;
        JLabel colorLabel = new JLabel();
        colorLabel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, table.getSelectionForeground()));
        colorLabel.setOpaque(true);
        colorLabel.setBackground(color);
        colorLabel.setToolTipText(color.toString());
        return colorLabel;
    }
}
