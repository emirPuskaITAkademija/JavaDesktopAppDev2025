package com.itakademija.six.action;

import com.itakademija.three.sport.dao.player.PlayerInfo;

public class ActionColumnModel {
    private PlayerInfo playerInfo;

    public ActionColumnModel(PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
    }

    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }

    public Integer getId() {
        return playerInfo.getId();
    }


    public String getFullName() {
        return playerInfo.getFirstName() + " " + playerInfo.getLastName();
    }
}
