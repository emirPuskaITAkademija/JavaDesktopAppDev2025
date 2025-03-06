package com.itakademija.three.combobox;

import com.itakademija.one.icon.IconLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomComboBoxPanel extends JPanel {

    private final Icon[] petIcons;
    private final String[] petNames = {"Bird", "Cat", "Dog", "Rabbit", "Pig"};
    private final JLabel pictureLabel = new JLabel();

    public CustomComboBoxPanel() {
        super(new BorderLayout());
        this.petIcons = new Icon[petNames.length];
        IconLoader iconLoader = new IconLoader(CustomComboBoxPanel.class);
        Integer[] petsArray = new Integer[petNames.length];
        for (int i = 0; i < petNames.length; i++) {
            petsArray[i] = i;
            String path = "animals/" + petNames[i] + ".gif";
            Icon icon = iconLoader.loadIcon(path);
            petIcons[i] = icon;
        }
        JComboBox<Integer> petListComboBox = new JComboBox<>(petsArray);
        IconTextComboBoxRenderer iconTextComboBoxRenderer = new IconTextComboBoxRenderer();
        petListComboBox.setRenderer(iconTextComboBoxRenderer);
//        ActionListener actionListener = new PositionComboBoxListener(this::updatePictureLabel);
        //JComboBox<Integer> <-> ActionEvent <-> ActionListener
        ActionListener actionListener = this::onSelectionComboBoxChange;
        petListComboBox.addActionListener(actionListener);
        pictureLabel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        updatePictureLabel(0);

        add(petListComboBox, BorderLayout.PAGE_START);
        add(pictureLabel, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    private void onSelectionComboBoxChange(ActionEvent event) {
        JComboBox<Integer> petListComboBox = (JComboBox<Integer>) event.getSource();
        updatePictureLabel(petListComboBox.getSelectedIndex());
    }

    private void updatePictureLabel(int i) {
        pictureLabel.setIcon(petIcons[i]);
    }

    private class IconTextComboBoxRenderer implements ListCellRenderer<Integer> {

        @Override
        public Component getListCellRendererComponent(JList<? extends Integer> list,
                                                      Integer index,
                                                      int selectedIndex,
                                                      boolean isSelected,
                                                      boolean cellHasFocus) {
            JLabel label = new JLabel();
            label.setOpaque(true);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);

            Color backgroundColor = isSelected ? list.getSelectionBackground() : list.getBackground();
            label.setBackground(backgroundColor);
            Color foregroundColor = isSelected ? list.getSelectionForeground() : list.getForeground();
            label.setForeground(foregroundColor);
            Font font = list.getFont();
            font = font.deriveFont(Font.BOLD, 24f);
            label.setFont(font);
            Icon icon = petIcons[index];
            String petName = petNames[index];
            label.setIcon(icon);
            label.setText(petName);
            label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            return label;
        }
    }
}
