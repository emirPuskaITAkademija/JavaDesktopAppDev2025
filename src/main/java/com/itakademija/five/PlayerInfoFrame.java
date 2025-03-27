package com.itakademija.five;

import com.itakademija.one.icon.IconLoader;
import com.itakademija.three.sport.dao.player.PlayerInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

public class PlayerInfoFrame extends JFrame {
    private final IconLoader iconLoader = new IconLoader(PlayerInfoFrame.class);

    private final PlayerInfo playerInfo;
    private final PlayerFormPanel playerFormPanel;
    private final Consumer<PlayerInfo> playerInfoConsumer;

    //ADD NEW PLAYER
    private PlayerInfoFrame(Consumer<PlayerInfo> playerInfoConsumer) {
        this(new PlayerInfo(), playerInfoConsumer);
    }

    //EDIT
    private PlayerInfoFrame(PlayerInfo playerInfo, Consumer<PlayerInfo> playerInfoConsumer) {
        super("Player Form");
        this.playerInfo = playerInfo;
        this.playerInfoConsumer = playerInfoConsumer;
        this.playerFormPanel = new PlayerFormPanel();
        this.playerFormPanel.init(playerInfo);
        createFormUI();
    }

    private void createFormUI() {
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

        add(playerFormPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void onSaveButtonClick(ActionEvent event) {
        playerInfo.setFirstName(playerFormPanel.getFirstName());
        playerInfo.setLastName(playerFormPanel.getLastName());
        playerInfo.setSport(playerFormPanel.getSport());
        playerInfo.setYears(playerFormPanel.getYears());
        playerInfo.setVegetarian(playerFormPanel.getVegetarian());
        playerInfo.setColor(playerFormPanel.getColor());
        JOptionPane.showMessageDialog(this, "Data saved successfully!", "Save", JOptionPane.INFORMATION_MESSAGE);
        dispose();
        playerInfoConsumer.accept(playerInfo);
    }

    public static void showNewPlayerForm(Consumer<PlayerInfo> addNewPlayerConsumer) {
        PlayerInfoFrame playerInfoFrame = new PlayerInfoFrame(addNewPlayerConsumer);
        playerInfoFrame.setVisible(true);
    }

    public static void showEditPlayerForm(PlayerInfo playerInfo, Consumer<PlayerInfo> editPlayerConsumer) {
        PlayerInfoFrame playerInfoFrame = new PlayerInfoFrame(playerInfo, editPlayerConsumer);
        playerInfoFrame.setVisible(true);
    }
}
