package com.itakademija.five;

import com.itakademija.four.table.cell.ColorCellEditor;
import com.itakademija.four.table.cell.ColorCellRenderer;
import com.itakademija.three.sport.dao.player.PlayerInfo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

public class PlayerTablePanel2 extends JPanel {
    private final PlayerTableModel playerTableModel;
    public PlayerTablePanel2() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(15, 15, 15, 15));


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBorder(new EmptyBorder(0, 0, 10, 0));
        JButton addPlayerButton = new JButton("Add Player");
        addPlayerButton.addActionListener(this::openForm);
        buttonPanel.add(addPlayerButton);
        add(buttonPanel);

        // Kreiranje tabele
        this.playerTableModel = new PlayerTableModel();
        JTable table = new JTable(playerTableModel);
        table.setFillsViewportHeight(true);
        // Render i editor za Color
        table.setDefaultRenderer(Color.class, new ColorCellRenderer());
        table.setDefaultEditor(Color.class, new ColorCellEditor());

        table.setDefaultRenderer(ActionColumnModel.class, new ActionCellRenderer());

        // Panel za tabelu s dodatnim razmakom
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);
        tablePanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        add(tablePanel);
    }

    private void openForm(ActionEvent e) {
        Consumer<PlayerInfo> playerInfoConsumer = playerTableModel::addNewPlayer;
        PlayerInfoFrame playerInfoFrame = new PlayerInfoFrame(playerInfoConsumer);
        playerInfoFrame.setVisible(true);
    }
}
