package com.itakademija.five;

import com.itakademija.one.icon.IconLoader;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionCellRenderer implements TableCellRenderer {

    private final IconLoader iconLoader = new IconLoader(ActionColumnModel.class);

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        ActionColumnModel actionColumnModel = (ActionColumnModel) value;

        Icon deleteIcon = iconLoader.loadIcon("player/delete16x16.png");
        JButton deleteButton = new JButton(deleteIcon);
        deleteButton.setOpaque(true);
        deleteButton.addActionListener(this::onDeleteButtonClicked);
        deleteButton.setBackground(table.getForeground());
        return deleteButton;
    }

    private void onDeleteButtonClicked(ActionEvent actionEvent) {
        int response = JOptionPane.showConfirmDialog(
                null, // Parent component
                "Are you sure you want to delete this player?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        // Provjera korisnikovog izbora
        if (response == JOptionPane.YES_OPTION) {
            // Logika za brisanje
            System.out.println("Player deleted successfully!");
        } else {
            // Korisnik je izabrao No
            System.out.println("Delete operation canceled.");
        }
    }

}
