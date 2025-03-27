package com.itakademija.five;

import com.itakademija.three.sport.dao.player.PlayerInfo;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class PlayerFormPanel extends JPanel {

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField sportField;
    private JSpinner yearsSpinner;
    private JCheckBox vegetarianCheckBox;
    private Color color;

    public PlayerFormPanel() {
        createFormGUI();
    }


    public void init(PlayerInfo playerInfo) {
        if (playerInfo != null && playerInfo.getId() != null) {
            firstNameField.setText(playerInfo.getFirstName());
            lastNameField.setText(playerInfo.getLastName());
            sportField.setText(playerInfo.getSport());
            yearsSpinner.setValue(playerInfo.getYears());
            vegetarianCheckBox.setSelected(playerInfo.isVegetarian());
            color = playerInfo.getColor();
        }
    }

    private void createFormGUI() {
        setLayout(new GridLayout(6, 2, 10, 10));

        // Dodavanje paddinga oko cijelog panela
        setBorder(new EmptyBorder(15, 15, 15, 15));

        // First Name
        JLabel firstNameLabel = new JLabel("First Name:");
        this.firstNameField = new JTextField();
        add(firstNameLabel);
        add(firstNameField);

        // Last Name
        JLabel lastNameLabel = new JLabel("Last Name:");
        this.lastNameField = new JTextField();
        add(lastNameLabel);
        add(lastNameField);

        // Sport
        JLabel sportLabel = new JLabel("Sport:");
        this.sportField = new JTextField();
        add(sportLabel);
        add(sportField);

        // Years
        JLabel yearsLabel = new JLabel("Years:");
        this.yearsSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1)); // Min 0, max 100
        add(yearsLabel);
        add(yearsSpinner);

        // Vegetarian
        JLabel vegetarianLabel = new JLabel("Vegetarian:");
        this.vegetarianCheckBox = new JCheckBox();
        add(vegetarianLabel);
        add(vegetarianCheckBox);

        // Color
        JLabel colorLabel = new JLabel("Favorite Color:");
        JButton colorButton = new JButton("Choose Color");
        add(colorLabel);
        add(colorButton);

        colorButton.addActionListener(e -> onColorButtonClick(colorButton));
    }

    private void onColorButtonClick(JButton colorButton) {
        Color color = JColorChooser.showDialog(this, "Select a Color", Color.WHITE);
        if (color != null) {
            colorButton.setBackground(color);
            this.color = color;
        }
    }

    public String getFirstName() {
        return this.firstNameField.getText();
    }

    public String getLastName() {
        return this.lastNameField.getText();
    }

    public String getSport() {
        return this.sportField.getText();
    }

    public int getYears() {
        return (int) this.yearsSpinner.getValue();
    }

    public boolean getVegetarian() {
        return this.vegetarianCheckBox.isSelected();
    }

    public Color getColor() {
        return this.color;
    }
}


