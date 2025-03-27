package com.itakademija.six.action;

import com.itakademija.one.icon.IconLoader;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ActionCellRenderer implements TableCellRenderer {

    private final IconLoader iconLoader = new IconLoader(ActionColumnModel.class);


    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JButton deleteButton = ActionButtonFactory.DELETE.getButton(table);
        JButton editButton = ActionButtonFactory.EDIT.getButton(table);
        JPanel panel = new JPanel();
        panel.add(deleteButton);
        panel.add(editButton);
        return panel;
    }
}
