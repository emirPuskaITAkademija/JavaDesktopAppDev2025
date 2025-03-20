package com.itakademija.three.sport.dao.player;

import com.itakademija.five.ColorUtil;
import com.itakademija.three.sport.connection.ConnectionPool;
import com.itakademija.three.sport.dao.Dao;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerInfoDao implements Dao<PlayerInfo> {
    @Override
    public boolean save(PlayerInfo playerInfo) {
        String sqlInsert = """
                INSERT INTO player_info 
                (first_name, last_name, sport, years, vegetarian, color)
                VALUES (?, ?, ?, ?, ?, ?)
                """;
        ConnectionPool pool = ConnectionPool.instance();
        Connection connection = pool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert)) {
            preparedStatement.setString(1, playerInfo.getFirstName());
            preparedStatement.setString(2, playerInfo.getLastName());
            preparedStatement.setString(3, playerInfo.getSport());
            preparedStatement.setInt(4, playerInfo.getYears());
            preparedStatement.setBoolean(5, playerInfo.isVegetarian());
            String hexColor = ColorUtil.colorToString(playerInfo.getColor());
            preparedStatement.setString(6, hexColor);
            preparedStatement.execute();
        }catch (SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
        pool.releaseConnection(connection);
        return true;
    }

    @Override
    public void update(PlayerInfo playerInfo) {
        String sqlUpdate = """
                UPDATE player_info
                SET first_name=?,
                    last_name=?,
                    sport=?,
                    years=?,
                    vegetarian=?,
                    color=?
                WHERE id=?
                """;
        ConnectionPool pool = ConnectionPool.instance();
        Connection connection = pool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sqlUpdate)) {
            ps.setString(1, playerInfo.getFirstName());
            ps.setString(2, playerInfo.getLastName());
            ps.setString(3, playerInfo.getSport());
            ps.setInt(4, playerInfo.getYears());
            ps.setBoolean(5, playerInfo.isVegetarian());
            String hexColor = ColorUtil.colorToString(playerInfo.getColor());
            ps.setString(6, hexColor);
            ps.setInt(7, playerInfo.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        pool.releaseConnection(connection);
    }


    @Override
    public void delete(PlayerInfo playerInfo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PlayerInfo findById(int id) {
        return null;
    }


    //List<PlayerInfo> players = playerInfoDao.findAll(),
    //JTable <---------  ENIGMA ----> players

    @Override
    public List<PlayerInfo> findAll() {
        List<PlayerInfo> list = new ArrayList<>();

        ConnectionPool pool = ConnectionPool.instance();
        Connection connection = pool.getConnection();
        String sql = "SELECT * FROM player_info";
        try (PreparedStatement ps = connection.prepareCall(sql)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                PlayerInfo playerInfo = new PlayerInfo();
                playerInfo.setId(resultSet.getInt("id"));
                playerInfo.setFirstName(resultSet.getString("first_name"));
                playerInfo.setLastName(resultSet.getString("last_name"));
                playerInfo.setSport(resultSet.getString("sport"));
                playerInfo.setYears(resultSet.getInt("years"));
                playerInfo.setVegetarian(resultSet.getBoolean("vegetarian"));
                String hexColor = resultSet.getString("color");
                Color color = ColorUtil.stringToColor(hexColor);
                playerInfo.setColor(color);
                list.add(playerInfo);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        pool.releaseConnection(connection);
        return list;
    }

    //META PODACI -> npr. naziv kolona u našoj tabeli
    public List<String> getColumnNames() {
        List<String> columnNames = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.instance();
        Connection connection = pool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM player_info");
             ResultSet resultSet = ps.executeQuery()) {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = resultSetMetaData.getColumnName(i);
                columnNames.add(columnName);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        pool.releaseConnection(connection);
        return columnNames;
    }

    public int getColumnCount() {
        ConnectionPool pool = ConnectionPool.instance();
        Connection connection = pool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM player_info");
             ResultSet resultSet = ps.executeQuery()) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            return metaData.getColumnCount();
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }
        pool.releaseConnection(connection);
        return 0;
    }
}
