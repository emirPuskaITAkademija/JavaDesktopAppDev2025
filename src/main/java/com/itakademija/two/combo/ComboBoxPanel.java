package com.itakademija.two.combo;

import com.itakademija.one.icon.IconLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * JComboBox -> dvije forme:
 * <li>1. needitabilni combo box</li>
 * <li>2. editabilni combo box</li>
 */
public class ComboBoxPanel extends JPanel {
    private final String[] animals = {"Bird", "Cat", "Pig", "Rabbit"};
    private final JComboBox<String> animalComboBox = new JComboBox<>(animals);
    private final JLabel pictureLabel = new JLabel();

    public ComboBoxPanel() {
        //top - center
        setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(177, 132));
//        ComboBoxActionListener actionListener = new ComboBoxActionListener();
//        ActionListener actionListener = (e)->onAnimalSelectionChange(e);
//        ActionListener actionListener = this::onAnimalSelectionChange;
        animalComboBox.addActionListener(this::onAnimalSelectionChange);
        add(animalComboBox, BorderLayout.PAGE_START);
        int indexSelectedElement = animalComboBox.getSelectedIndex();
        String selectedAnimal = animals[indexSelectedElement];
        updatePictureLabel(selectedAnimal);
        pictureLabel.setHorizontalAlignment(JLabel.CENTER);
        pictureLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        pictureLabel.setFont(pictureLabel.getFont().deriveFont(Font.ITALIC));
        add(pictureLabel, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }


    private void onAnimalSelectionChange(ActionEvent event) {
        JComboBox<String> animalComboBox = (JComboBox<String>) event.getSource();
        String selectedAnimal = (String) animalComboBox.getSelectedItem();
        updatePictureLabel(selectedAnimal);
    }


    private void updatePictureLabel(String pictureName) {
        String path = "animals/" + pictureName+".gif";
        IconLoader iconLoader = new IconLoader(ComboBoxPanel.class);
        Icon imageIcon = iconLoader.loadIcon(path);
        pictureLabel.setIcon(imageIcon);
    }

    /** JComboBox   <--- ActionEvent --->        ComboBoxActionListener   **/
//    private class ComboBoxActionListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent event) {
//            JComboBox<String> animalComboBox = (JComboBox<String>) event.getSource();
//            String selectedAnimal = (String) animalComboBox.getSelectedItem();
//            updatePictureLabel(selectedAnimal);
//        }
//    }
}
