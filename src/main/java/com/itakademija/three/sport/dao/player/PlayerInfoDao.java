package com.itakademija.three.sport.dao.player;

import com.itakademija.three.sport.connection.ConnectionPool;
import com.itakademija.three.sport.dao.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerInfoDao implements Dao<PlayerInfo> {
    @Override
    public void save(PlayerInfo playerInfo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(PlayerInfo playerInfo) {
        throw new UnsupportedOperationException("Not supported yet.");
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
        try(PreparedStatement ps = connection.prepareCall(sql)){
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                PlayerInfo playerInfo = new PlayerInfo();
                playerInfo.setId(resultSet.getInt("id"));
                playerInfo.setFirstName(resultSet.getString("first_name"));
                playerInfo.setLastName(resultSet.getString("last_name"));
                playerInfo.setSport(resultSet.getString("sport"));
                playerInfo.setYears(resultSet.getInt("years"));
                playerInfo.setVegetarian(resultSet.getBoolean("vegetarian"));

                list.add(playerInfo);
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        pool.releaseConnection(connection);
        return list;
    }
}
