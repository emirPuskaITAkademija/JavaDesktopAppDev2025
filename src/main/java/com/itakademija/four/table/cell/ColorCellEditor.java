package com.itakademija.four.table.cell;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ColorCellEditor extends AbstractCellEditor implements TableCellEditor {

    private Color currentColor;
    private JButton editButton;
    private JColorChooser colorChooser;
    private JDialog dialog;

    public ColorCellEditor() {
        this.editButton = new JButton();
        this.editButton.setActionCommand("edit");
        this.editButton.addActionListener(this::onEditButtonClick);
        this.editButton.setBorderPainted(false);

        this.colorChooser = new JColorChooser();
        this.dialog = JColorChooser.createDialog(
                editButton,
                "Pick a color",
                true,
                colorChooser,
                this::onEditButtonClick,
                null);
    }

    private void onEditButtonClick(ActionEvent event) {
        if("edit".equals(event.getActionCommand())) {
            editButton.setBackground(currentColor);
            colorChooser.setColor(currentColor);
            dialog.setVisible(true);

            fireEditingStopped();
        }else{
            currentColor = colorChooser.getColor();
        }
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.currentColor = (Color) value;
        return editButton;
    }

    @Override
    public Object getCellEditorValue() {
        return currentColor;
    }
}
