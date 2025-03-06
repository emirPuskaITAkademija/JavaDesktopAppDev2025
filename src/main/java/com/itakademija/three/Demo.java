package com.itakademija.three;

import com.formdev.flatlaf.FlatDarkLaf;
import com.itakademija.three.combobox.CustomComboBoxPanel;

import javax.swing.*;

/**
 * 1. GUI kontejneri. JFRame, JPanel
 * 2. Layout Manageri
 * 3. GUI kontrole
 */
public class Demo {
    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        //new  () ->    ::
//        UIManager.setLookAndFeel(new FlatDarkLaf());
        Runnable worker = Demo::createAndShowGUI;
        SwingUtilities.invokeLater(worker);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame();
        frame.setTitle("Third Lecture");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(new CustomComboBoxPanel());
        frame.pack();
        frame.setVisible(true);
    }
}
