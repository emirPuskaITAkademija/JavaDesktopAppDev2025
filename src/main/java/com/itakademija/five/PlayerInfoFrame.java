package com.itakademija.five;

import com.itakademija.one.icon.IconLoader;
import com.itakademija.three.sport.dao.player.PlayerInfo;
import com.itakademija.three.sport.dao.player.PlayerInfoDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

public class PlayerInfoFrame extends JFrame {
    private final IconLoader iconLoader = new IconLoader(PlayerInfoFrame.class);

    private final Consumer<PlayerInfo> playerInfoConsumer;
    private final PlayerFormPanel playerFormPanel;

    public PlayerInfoFrame(Consumer<PlayerInfo> playerInfoConsumer) {
        super("Player Form");
        this.playerInfoConsumer = playerInfoConsumer;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 300);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        Icon saveIcon = iconLoader.loadIcon("player/save-icon16x16.png");
        JButton saveButton = new JButton("SAVE", saveIcon);
        saveButton.addActionListener(this::onSaveButtonClick);
        JButton cancelButton = new JButton("CANCEL");
        cancelButton.addActionListener(cancelEvent -> dispose());

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        this.playerFormPanel = new PlayerFormPanel();
        add(playerFormPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void onSaveButtonClick(ActionEvent event) {
        PlayerInfoDao playerInfoDao = new PlayerInfoDao();
        PlayerInfo playerInfo = new PlayerInfo();
        playerInfo.setFirstName(playerFormPanel.getFirstName());
        playerInfo.setLastName(playerFormPanel.getLastName());
        playerInfo.setSport(playerFormPanel.getSport());
        playerInfo.setYears(playerFormPanel.getYears());
        playerInfo.setVegetarian(playerFormPanel.getVegetarian());
        playerInfo.setColor(playerFormPanel.getColor());
        boolean success = playerInfoDao.save(playerInfo);
        if(success){
            JOptionPane.showMessageDialog(this, "Data saved successfully!", "Save", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            playerInfoConsumer.accept(playerInfo);
        }
    }
}
