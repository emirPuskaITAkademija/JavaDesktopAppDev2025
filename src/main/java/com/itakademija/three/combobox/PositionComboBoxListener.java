package com.itakademija.three.combobox;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;
//Tight Couple Logic -> Tijesna Veza između komponenti
//    private final CustomComboBoxPanel customComboBoxPanel;
//Loose Coupled Logic -> SLaba veza
public class PositionComboBoxListener implements ActionListener {

    private final Consumer<Integer> selectedIndexConsumer;

    public PositionComboBoxListener(Consumer<Integer> selectedIndexConsumer) {
        this.selectedIndexConsumer = selectedIndexConsumer;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JComboBox<Integer> selectedItem = (JComboBox<Integer>) event.getSource();
        int selectedIndex = selectedItem.getSelectedIndex();
        selectedIndexConsumer.accept(selectedIndex);
    }
}
