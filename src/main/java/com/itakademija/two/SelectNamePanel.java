package com.itakademija.two;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * LayoutManager: FlowLayout
 * <p>
 * Mi to mijenjamo programabilno BoxLayout.Y  PAGE
 * </p>
 */
public class SelectNamePanel extends JPanel {
    static String[] names = {"Faruk", "Tarik", "Jozo", "Andrej", "Jovan", "Azur", "Emir", "Dženita",
            "Marko", "Boris", "Gordana", "Slobodan", "Ivo", "Nikola", "Faruk", "Tarik", "Jozo", "Andrej", "Jovan", "Azur", "Emir", "Dženita",
            "Marko", "Boris", "Gordana", "Slobodan", "Ivo", "Nikola Kojo"};

    private final JLabel actionLabel = new JLabel("Odaberi omiljenog glumca");
    private final JButton selectNameButton = new JButton("Pick a new name...");

    private final JFrame frame;

    public SelectNamePanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(actionLabel);
        add(Box.createVerticalStrut(10));
        ActionListener openDialogLister = this::openNameChooserDialog;
        selectNameButton.addActionListener(openDialogLister);
        add(selectNameButton);
    }

    private void openNameChooserDialog(ActionEvent actionEvent) {
        String name = SelectNameDialog.showDialog(frame,
                this.selectNameButton,
                "Odaberi omiljenog glumca",
                "Name Chooser",
                names,
                actionLabel.getText(),
                "Jovan");
        System.out.println("Odabrani omiljeni glumac: " + name);
    }

}
