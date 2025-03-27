package com.itakademija.five;

import com.itakademija.six.action.ActionColumnModel;
import com.itakademija.three.sport.dao.player.PlayerInfo;
import com.itakademija.three.sport.dao.player.PlayerInfoDao;

import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.List;
import java.util.Objects;

// JTable <--> PlayerTableModel  <---> podacima iz baze PlayerInfoDao
public class PlayerTableModel extends AbstractTableModel {
    private final PlayerInfoDao playerInfoDao = new PlayerInfoDao();

    private final List<String> columnNames;
    //1 red kao jedan PlayerInfo
    //n redova List<PlayerInfo>
    private final List<PlayerInfo> playerInfoRows;

    public PlayerTableModel() {
        this.columnNames = playerInfoDao.getColumnNames();
        this.playerInfoRows = playerInfoDao.findAll();
    }

    @Override
    public int getRowCount() {
        return playerInfoRows.size();
    }

    @Override
    public int getColumnCount() {
        return playerInfoDao.getColumnCount();
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PlayerInfo playerInfo = playerInfoRows.get(rowIndex);
        String columnName = columnNames.get(columnIndex);
        return switch (columnName) {
            case "id" -> new ActionColumnModel(playerInfo);
            case "first_name" -> playerInfo.getFirstName();
            case "last_name" -> playerInfo.getLastName();
            case "sport" -> playerInfo.getSport();
            case "years" -> playerInfo.getYears();
            case "vegetarian" -> playerInfo.isVegetarian();
            case "color" -> playerInfo.getColor() == null ? Color.WHITE : playerInfo.getColor();
            default -> null;
        };
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object newValue, int rowIndex, int columnIndex) {
        //uzimamo red s kojim je povezana ćelija nad kojom radimo intervenciju
        PlayerInfo rowModel = playerInfoRows.get(rowIndex);
        //uzimamo kolonu s kojom je povezana ćelija
        String columnName = columnNames.get(columnIndex);
        switch (columnName) {
            case "id" -> rowModel.setId(((ActionColumnModel) newValue).getId());
            case "first_name" -> rowModel.setFirstName((String) newValue);
            case "last_name" -> rowModel.setLastName((String) newValue);
            case "sport" -> rowModel.setSport((String) newValue);
            case "years" -> rowModel.setYears((Integer) newValue);
            case "vegetarian" -> rowModel.setVegetarian((Boolean) newValue);
            case "color" -> rowModel.setColor((Color) newValue);
        }
        fireTableCellUpdated(rowIndex, columnIndex);
        playerInfoDao.update(rowModel);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        String columnName = columnNames.get(columnIndex);
        return switch (columnName) {
            case "id" -> ActionColumnModel.class;
            case "first_name" -> String.class;
            case "last_name" -> String.class;
            case "sport" -> String.class;
            case "years" -> Integer.class;
            case "vegetarian" -> Boolean.class;
            case "color" -> Color.class;
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return columnNames.get(column);
    }


    public void addNewPlayer(PlayerInfo playerInfo) {
        playerInfoRows.add(playerInfo);
        fireTableDataChanged();//refresh
        playerInfoDao.save(playerInfo);
    }

    public void editExistingPlayer(PlayerInfo playerInfo) {
        int indexOfPlayer = playerInfoRows.indexOf(playerInfo);
        playerInfoRows.set(indexOfPlayer, playerInfo);
        fireTableDataChanged();
        playerInfoDao.update(playerInfo);
    }

    public void deleteExistingPlayer(PlayerInfo playerInfo){
        playerInfoRows.remove(playerInfo);
        fireTableDataChanged();
        playerInfoDao.delete(playerInfo);
    }
}
