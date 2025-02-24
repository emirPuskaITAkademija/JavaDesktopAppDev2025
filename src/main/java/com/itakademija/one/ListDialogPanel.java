package com.itakademija.one;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * LayoutManager: FlowLayout
 * <p>
 *     Mi to mijenjamo programabilno BoxLayout.Y  PAGE
 * </p>
 */
public class ListDialogPanel extends JPanel {
    static String[] names = {"Faruk", "Tarik", "Jozo", "Andrej", "Jovan", "Azur", "Emir", "Dženita",
                              "Marko", "Boris", "Gordana", "Slobodan", "Ivo", "Nikola"};
    private final JLabel actionLabel = new JLabel("Odaberi sva imena koja završavaju na 'k'");
    private final JButton selectNameButton = new JButton("Pick a new name...");


    public ListDialogPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(actionLabel);
        add(Box.createVerticalStrut(10));
        add(selectNameButton);
    }

    private void openListDialog(ActionEvent actionEvent){
//        SelectNameDialog.showDialog(this, this, "", "", names)
    }

}
