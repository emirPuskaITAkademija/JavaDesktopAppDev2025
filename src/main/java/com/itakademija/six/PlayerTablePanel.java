package com.itakademija.six;

import com.itakademija.five.PlayerInfoFrame;
import com.itakademija.five.PlayerTableModel;
import com.itakademija.four.table.cell.ColorCellEditor;
import com.itakademija.four.table.cell.ColorCellRenderer;
import com.itakademija.one.icon.IconLoader;
import com.itakademija.six.action.ActionButtonFactory;
import com.itakademija.six.action.ActionCellEditor;
import com.itakademija.six.action.ActionCellRenderer;
import com.itakademija.six.action.ActionColumnModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PlayerTablePanel extends JPanel {
    private final PlayerTableModel playerTableModel;

    public PlayerTablePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(15, 15, 15, 15));


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBorder(new EmptyBorder(0, 0, 10, 0));

        JButton addPlayerButton = ActionButtonFactory.ADD.getButton(this::openForm);
        buttonPanel.add(addPlayerButton);
        add(buttonPanel);

        // Kreiranje tabele
        this.playerTableModel = new PlayerTableModel();
        JTable table = new JTable(playerTableModel);
        table.setRowHeight(40);
        table.getColumnModel().getColumn(0).setMinWidth(180);
        table.getColumnModel().getColumn(1).setMinWidth(60);
        table.getColumnModel().getColumn(2).setMinWidth(60);
        table.setFillsViewportHeight(true);
        // Render i editor za Color
        //renderovanje -> učitavanje i prikazivanje Color.class vrijednosti
        table.setDefaultRenderer(Color.class, new ColorCellRenderer());
        //za način na koji će se editovani Color.class je odgovoran ColorCellEditor
        table.setDefaultEditor(Color.class, new ColorCellEditor());

        table.setDefaultRenderer(ActionColumnModel.class, new ActionCellRenderer());
        table.setDefaultEditor(ActionColumnModel.class, new ActionCellEditor());

        // Panel za tabelu s dodatnim razmakom
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);
        tablePanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        add(tablePanel);
    }

    private void openForm(ActionEvent e) {
        PlayerInfoFrame.showNewPlayerForm(playerTableModel::addNewPlayer);
    }
}
