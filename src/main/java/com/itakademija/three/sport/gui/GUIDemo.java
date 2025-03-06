package com.itakademija.three.sport.gui;

import com.itakademija.three.combobox.CustomComboBoxPanel;
import com.itakademija.three.sport.dao.player.PlayerInfo;
import com.itakademija.three.sport.dao.player.PlayerInfoDao;

import javax.swing.*;
import java.util.List;

public class GUIDemo {
    public static void main(String[] args) {
        Runnable worker = GUIDemo::createAndShowGUI;
        SwingUtilities.invokeLater(worker);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame();
        frame.setTitle("Player Info");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        PlayerInfoDao playerInfoDao = new PlayerInfoDao();
        PlayerTablePanel playerTablePanel = new PlayerTablePanel(playerInfoDao);
        frame.setContentPane(playerTablePanel);
        frame.pack();
        frame.setVisible(true);
    }
}
