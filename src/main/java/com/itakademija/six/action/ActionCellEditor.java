package com.itakademija.six.action;

import com.itakademija.five.PlayerInfoFrame;
import com.itakademija.five.PlayerTableModel;
import com.itakademija.three.sport.dao.player.PlayerInfo;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ActionCellEditor extends AbstractCellEditor implements TableCellEditor {

    private ActionColumnModel actionColumnModel;

    @Override
    public Object getCellEditorValue() {
        return actionColumnModel;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.actionColumnModel = (ActionColumnModel) value;
        JPanel panel = new JPanel();
        JButton deleteButton = ActionButtonFactory.DELETE.getButton(table);
        deleteButton.addActionListener(event -> onDeleteButtonClick(event, table));

        JButton editButton = ActionButtonFactory.EDIT.getButton(table);
        editButton.addActionListener(event -> onEditButtonClick(table));

        panel.add(deleteButton);
        panel.add(editButton);

        return panel;
    }

    private void onEditButtonClick(JTable table) {
        PlayerTableModel playerTableModel = (PlayerTableModel) table.getModel();
        PlayerInfo playerInfo = actionColumnModel.getPlayerInfo();
        PlayerInfoFrame.showEditPlayerForm(playerInfo, playerTableModel::editExistingPlayer);
    }

    private void onDeleteButtonClick(ActionEvent actionEvent, JTable table) {
        PlayerTableModel playerTableModel = (PlayerTableModel) table.getModel();
        String actionCommand = actionEvent.getActionCommand();
        System.out.println("Delete Action Triggered: " + actionCommand);

        String confirmationMessage = "Are you sure that you want to delete record with name '%s'".formatted(actionColumnModel.getFullName());
        int response = JOptionPane.showConfirmDialog(null,
                confirmationMessage,
                "Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            PlayerInfo playerInfo = actionColumnModel.getPlayerInfo();
            playerTableModel.deleteExistingPlayer(playerInfo);
        } else {
            JOptionPane.showMessageDialog(null, "Player '%s' is not properly deleted".formatted(actionColumnModel.getFullName()));
        }
    }
}
