package com.itakademija.three.sport.gui;

import com.itakademija.three.sport.dao.player.PlayerInfo;
import com.itakademija.three.sport.dao.player.PlayerInfoDao;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;


//JTable <-> TableModel  <-> izvorom podataka
public class PlayerTablePanel extends JPanel {

    private final PlayerInfoDao playerInfoDao;
    private final JTable table;


    public PlayerTablePanel(PlayerInfoDao playerInfoDao) {
        this.playerInfoDao = playerInfoDao;
        PlayerInfoTableModel tableModel = new PlayerInfoTableModel();
        this.table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }


    private class PlayerInfoTableModel extends AbstractTableModel {
        private final List<PlayerInfo> playerInfos;

        public PlayerInfoTableModel() {
            this.playerInfos = playerInfoDao.findAll();
        }

        @Override
        public int getRowCount() {
            return playerInfos.size();
        }

        @Override
        public int getColumnCount() {
            return 6;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            PlayerInfo playerInfo = playerInfos.get(rowIndex);
            return switch (columnIndex) {
                case 0 -> playerInfo.getId();
                case 1 -> playerInfo.getFirstName();
                case 2 -> playerInfo.getLastName();
                case 3 -> playerInfo.getSport();
                case 4 -> playerInfo.getYears();
                case 5 -> playerInfo.isVegetarian();
                default -> "";
            };
        }
    }
}
