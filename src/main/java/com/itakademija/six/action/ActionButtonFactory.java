package com.itakademija.six.action;

import com.itakademija.one.icon.IconLoader;

import javax.swing.*;
import java.awt.event.ActionListener;

public enum ActionButtonFactory {

    ADD("Add Player", "player/add-player.png"),
    DELETE("Delete", "player/delete16x16.png"),
    EDIT("Edit", "player/edit.png");

    private final String buttonText;
    private final String path;
    private final IconLoader iconLoader = new IconLoader(ActionButtonFactory.class);

    ActionButtonFactory(String buttonText, String path) {
        this.buttonText = buttonText;
        this.path = path;
    }

    public JButton getButton() {
        Icon icon = iconLoader.loadIcon(path);
        JButton button = new JButton(icon);
        button.setText(buttonText);
        button.setActionCommand(buttonText);
        button.setOpaque(true);
        return button;
    }

    public JButton getButton(ActionListener actionListener) {
        JButton button = getButton();
        button.addActionListener(actionListener);
        return button;
    }

    public JButton getButton(JTable table) {
        JButton button = getButton();
        button.setBackground(table.getForeground());
        return button;
    }

    public JButton getButton(JTable table, ActionListener actionListener) {
        JButton button = getButton(table);
        button.setBorderPainted(true);
        button.addActionListener(actionListener);
        return button;
    }
}
